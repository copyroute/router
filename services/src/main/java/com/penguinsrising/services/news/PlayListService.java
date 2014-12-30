
package com.penguinsrising.services.news;

import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.DiskFeedInfoCache;

import java.io.*;

import com.penguinsrising.services.mongo.Category_Repository;
import com.penguinsrising.services.mongo.Channel_Repository;
import com.penguinsrising.services.mongo.Company_Repository;
import com.penguinsrising.services.mongo.PlayList_Repository;
import com.penguinsrising.cdm.rss.*;
//import com.penguinsrising.events.*;
import com.penguinsrising.util.*;
import com.penguinsrising.util.json.*;
import com.penguinsrising.global.*;
import com.penguinsrising.services.amqp.*;

import static org.springframework.data.mongodb.core.mapreduce.MapReduceOptions.options;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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
	
	CategoryList categoryList; 
	public CategoryList getCategoryList() {return categoryList;}
	
	CompanyList companyList;
	public CompanyList getCompanyList() {return companyList;}
	
	ChannelList channelList; 
	public ChannelList getChannelList() {return channelList;}
	
	// Initialize NewsFeed PlayList
	private void initPlayList( )
	{	
		// Create Disk-Caching FeedFetcher
		FeedFetcherCache cache = new DiskFeedInfoCache("/opt/rome-fetcher/cache");
		fetcher = new HttpURLFeedFetcher(cache);
		
		// Load Playlist from CSV File
		Statics.Log(" === Loading From CSV === ");
		playList = csvLoaderService.LoadRssFeedsFromCSV(Statics.dataListName, "rssLists/feedListAll.csv");

		Statics.Log(" === Delete and Refresh Mongo Playist === ");
		// Delete & Re-Save Playlist in DB
		repo.deleteAll();
		repo.save(playList);
		
		// Create Indices
		Statics.Log(" === Rebuilding Indices === ");
		CategoryList categoryList = csvLoaderService.createUniqueCategoryList(playList);
		CompanyList companyList = csvLoaderService.createUniqueCompanyList(playList);
		ChannelList channelList = csvLoaderService.createUniqueChannelList(playList);

		Statics.Log(" === Created Playlist: " + playList.getName());

		// Map Reduce
		mapReduceCategories();
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
		Statics.Log("Playlist Count : " + playList.getDataSources().size());
		Statics.Log("Begin Update ================================================= ");
//		Statics.Log("Updating === Playlist : " + playList.getName() + " === DataSources: " + playList.getDataSources().size()  );
		for(int counter=0; counter< playList.getDataSources().size(); counter++)
		{	
			// RSS-Consumer : Pull RSS Messages from their respective servers
			try{	
				// Throttling
//				Thread.sleep(Statics.pollerSleepDT);
				getDataSourceMessages(playList.getDataSources().get(counter));
					
			}
			catch(Exception ex){ Log("==> Whoops!! Dead Link" + ex.getMessage() ); }				
		} 
		Statics.Log("End Update =================================================\n ");
	}

	private void getDataSourceMessages(DataSource dataSource) {

		try {
			Statics.Log("Updating : " + dataSource.getUri());
			SyndFeed feed = fetcher.retrieveFeed(new URL(dataSource.getUri()));
			@SuppressWarnings("rawtypes")
			Iterator entries = feed.getEntries().iterator(); 
			while (entries.hasNext()) {
				SyndEntry entry = (SyndEntry)entries.next(); 
	
				// Save Unique entries
				RssItem rssItem = rssItemService.saveUnique( entry , dataSource );
				
				// Publish
				if(rssItem != null){
	
					// Publish RssItem only if new
					if(rssItem.getTag() == "saved"){
						// PublishRssItem("amq.topic", rssItem.getCategory(), rssItem);
						// -- OLD : PublishRssItem("amq.topic", "publishRssItem", rssItem);
						Log("Saved Unique : \n" + rssItem.getTitle() + " \n " + rssItem.getFeed()  );				
					}
	
					// Publish Banner each time
//					PublishBanner("amq.topic", "publishBanner", rssItem);
	
					// Send Esper Update
//					this.sendEsperEvent(rssItem);
				}	
			}
		} catch (Exception e) {e.printStackTrace();}
		   
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

