
package com.copyroute.services.news;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.net.URL;


import com.copyroute.cdm.util.Time;
import com.copyroute.cdm.global.Statics;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.DiskFeedInfoCache;

import com.copyroute.services.mongo.PlayList_Repository;
import com.copyroute.cdm.rss.*;
//import com.copyroute.events.*;
import com.copyroute.cdm.util.json.*;
import com.copyroute.services.amqp.*;

import javax.annotation.PostConstruct;
import javax.xml.datatype.XMLGregorianCalendar;

@Component
public class PlayListService extends Amqp_Service
{
	@Autowired  private PlayList_Repository repo;											
	public PlayList_Repository getRepo() {return repo;}			
	
	@Autowired  private RssItemService rssItemService;								
	public RssItemService getRssItemService() {return rssItemService;}			

	@Autowired  private CsvLoaderService csvLoaderService;								
	public CsvLoaderService getCsvLoaderService() {return csvLoaderService;}			

	@Autowired  private MongoTemplate mongoTemplate;
	public MongoTemplate getMongoTemplate() {return mongoTemplate;}			
	
	public PlayList playList;
	FeedFetcher fetcher;


    @PostConstruct
    public void init(){
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
//        archieveFeeds();
    }

    public void archieveFeeds(){

        int pageNumber = 0;
        int pageSize = 50;
        long totalArticles = rssItemService.getItemRepo().count();

        // Ensure Indexing ( .. if not previously indexed !!)
        //mongoTemplate.indexOps("rssItem").ensureIndex(new Index().on("date", Order.ASCENDING));

        // Drop Collections
        mongoTemplate.dropCollection("rssItem2015");
        mongoTemplate.dropCollection("rssItem2014");
        mongoTemplate.dropCollection("rssItem2013");
        mongoTemplate.dropCollection("rssItem2012");

        // Paging data from DB Collection..
        while(pageNumber * pageSize < totalArticles )
        {
            // Find All : Sort By DateMonth
            Statics.Log("\n\n");
            Statics.Log("========================");
            Statics.Log("Percent: " + ((pageNumber * pageSize )/totalArticles * 100) + " %");
            Statics.Log("Search : " + (pageNumber * pageSize ));
            Statics.Log("Total  : " + totalArticles);
            Statics.Log("========================");

            // Retrieve the next Page of RssItems
            try{
                PageRequest request = new PageRequest(pageNumber, pageSize);
                List<RssItem> rssItems = rssItemService.getItemRepo().findAll(request).getContent();

                // For each RssItem, Save to new Collection by year
                for (RssItem rssItem : rssItems) {
                    try{
                        int rssYear = rssItem.getDate().getYear();
                        String collectionName = "rssItem" + rssYear;
                        Statics.Log("Migrate : " + rssYear + " : " + rssItem.getTitle());

                        mongoTemplate.save(rssItem, collectionName);
                    }
                    catch(Exception ex){Statics.Log("Error : Can't process rssItem" + ex.getMessage());}
                }
                pageNumber++;
            }
            catch(Exception ex){Statics.Log("Error : Can't load page from db " + ex.getMessage());}
        }
    }




    // Initialize NewsFeed PlayList
	private void initPlayList( )
	{	
		// Create Disk-Caching FeedFetcher
		FeedFetcherCache cache = new DiskFeedInfoCache("/opt/rome-fetcher/cache");
		fetcher = new HttpURLFeedFetcher(cache);
		
		// Load Playlist from CSV File
		Statics.Log(" === Loading From CSV === ");
		playList = csvLoaderService.LoadRssFeedsFromCSV(Statics.dataListName, "rssLists/feedListAll.csv");

		Statics.Log(" === Deleting Mongo Playist === ");
		repo.deleteAll();
		Statics.Log(" === Saving Mongo Playist === ");
		repo.save(playList);
		
		// Create Indices
		Statics.Log(" === Rebuilding Lists === ");
		CategoryList categoryList = csvLoaderService.createUniqueCategoryList(playList);
		CompanyList companyList = csvLoaderService.createUniqueCompanyList(playList);
		ChannelList channelList = csvLoaderService.createUniqueChannelList(playList);

		Statics.Log(" === Created Playlist: " + playList.getName());

		// Map Reduce
//		mapReduceCategories();

    }



    /// ======================================================================
	/// Main Update function : "Quartz-Timer Poller"
	/// Called by Spring "applicationContext-RSS.xml" 
	/// ====================================================================== 
	public void updatePlayList()
	{
		Statics.Log(" === Updating Playlist: ");

		// Pauses RSS Updates
		if(Statics.shouldRssUpdate == false)
			return;
		
		// Initialize Feed List if null
		if(playList == null)
			initPlayList( );

		// Process RSS Updates
        Statics.Log(" Begin Update ============================================================== ");
        Statics.Log(" RSS-Consumer : Pull RSS Messages from their respective servers, & store in DB");
		Statics.Log(" Playlist Count : " + playList.getDataSources().size());
		for(int counter=0; counter< playList.getDataSources().size(); counter++)
		{	
			try{
				DataSource dataSource = playList.getDataSources().get(counter);
				Statics.Log("Updating : " + dataSource.getUri());
				SyndFeed feed = fetcher.retrieveFeed(new URL(dataSource.getUri()));
				@SuppressWarnings("rawtypes")
				Iterator entries = feed.getEntries().iterator(); 
				while (entries.hasNext()) {

					// Throttling
					Thread.sleep(Statics.pollerSleepDT);
		
					// Save Unique entries
					SyndEntry entry = (SyndEntry)entries.next(); 
					RssItem rssItem = rssItemService.saveUnique( entry , dataSource );
					
				}
			}
			catch(Exception ex){ Log("==> Whoops!! Dead Link" + ex.getMessage() ); }				
		}
		Statics.Log("End Update =================================================\n ");
	}


    public void PublishSave( RssItem rssItem )
    {
        // Publish
        if(rssItem != null)
        {
            PublishRssItem("amq.topic", rssItem.getCategory(), rssItem);
            Log("Saved Unique : \n" + rssItem.getTitle() + " \n " + rssItem.getFeed()  );

            // Publish Banner each time
            PublishBanner("amq.topic", "publishBanner", rssItem);

            // Send Esper Update
            this.sendEsperEvent(rssItem);
        }

    }

	
	public void PublishBanner(String exchange, String routingKey, RssItem rssItem)
	{
		if(rssItem.getCategory() == null){
			Statics.Log("\n\nNull Data : =================== >>> " + rssItem.getFeed() + "\n\n");
			return;
		}
		try {
			this.sendMessage(this.exchange, "publishBanner", PojoMapper.toJson(rssItem, true));
		} 
		catch (JsonMappingException e) {e.printStackTrace();} 
		catch (JsonGenerationException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}	
	}
	
	public void PublishRssItem(String exchange, String routingKey, RssItem rssItem)
	{
		try {
			this.sendMessage(this.exchange, "publishRssItem", PojoMapper.toJson(rssItem, true));
		} 
		catch (JsonMappingException e) {e.printStackTrace();} 
		catch (JsonGenerationException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}	
	}
		
	// Publish Esper Event
	public void sendEsperEvent(RssItem rssItem){
//		EsperListener.sendEvent(rssItem);		
	}
	
	public void mapReduceCategories(){
//		Statics.Log(" === Running Map Reduce === ");
//		MapReduceResults<ValueObject> results = 
//				mongoTemplate.mapReduce(
//						"rssItem", 
//						"classpath:/resources/js/mapReduce/map.js", 
//						"classpath:/resources/js/mapReduce/reduce.js", 
//						options().outputCollection("aaa_example_out"),
//						ValueObject.class);
//		
//		for (ValueObject valueObject : results) {
//		  Statics.Log(valueObject.toString());
//		}
	}
	
	public void aggregate(){
//		Aggregation agg = new Aggregation(
//			    pipelineOP1(),
//			    pipelineOP2(),
//			    pipelineOPn()
//			);
//		AggregationResults<OutputType> results = mongoTemplate.aggregate(agg, "rssItem", OutputType.class);
//		List<OutputType> mappedResult = results.getMappedResults()
	}
	

	
}

