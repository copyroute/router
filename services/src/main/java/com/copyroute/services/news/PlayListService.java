
package com.copyroute.services.news;

import java.util.*;
import java.io.IOException;
import java.net.URL;


import com.copyroute.services.global.Statics;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
		Statics.Log("Playlist Count : " + playList.getDataSources().size());
		Statics.Log("Begin Update ================================================= ");
//		Statics.Log("Updating === Playlist : " + playList.getName() + " === DataSources: " + playList.getDataSources().size()  );
		for(int counter=0; counter< playList.getDataSources().size(); counter++)
		{	
			// RSS-Consumer : Pull RSS Messages from their respective servers
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
					
					// Publish
					if(rssItem != null){
		
						// PublishRssItem("amq.topic", rssItem.getCategory(), rssItem);
						// -- OLD : PublishRssItem("amq.topic", "publishRssItem", rssItem);
//						Log("Saved Unique : \n" + rssItem.getTitle() + " \n " + rssItem.getFeed()  );				
		
						// Publish Banner each time
//							PublishBanner("amq.topic", "publishBanner", rssItem);
		
						// Send Esper Update
//							this.sendEsperEvent(rssItem);
					}	
				}
			}
			catch(Exception ex){ Log("==> Whoops!! Dead Link" + ex.getMessage() ); }				
		} 
		Statics.Log("End Update =================================================\n ");
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

