//
//package com.copyroute.services.news;
//
//import java.util.*;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//
////import org.springframework.integration.Message;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.springframework.messaging.Message;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
//import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
////import org.springframework.integration.store.PropertiesPersistingMetadataStore;
//import org.springframework.integration.metadata.PropertiesPersistingMetadataStore;
//
//import com.sun.syndication.feed.synd.SyndEntry;
//import com.sun.syndication.feed.synd.SyndEntryImpl;
//
//import com.copyroute.services.mongo.Category_Repository;
//import com.copyroute.services.mongo.Channel_Repository;
//import com.copyroute.services.mongo.Company_Repository;
//import com.copyroute.services.mongo.PlayList_Repository;
//import com.copyroute.cdm.rss.*;
////import com.copyroute.events.*;
//import com.copyroute.util.*;
//import com.copyroute.util.json.*;
//
//import com.copyroute.global.*;
//import com.copyroute.services.amqp.*;
//import static org.springframework.data.mongodb.core.mapreduce.MapReduceOptions.options;
//import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
//
//// Feed Resources
//// AP : http://hosted2.ap.org/APDEFAULT/APNewsFeeds
//// Fox : http://www.foxnews.com/story/2007/11/09/foxnewscom-rss-feeds/
//// NBC : http://www.nbcnews.com/connect
//// Charlotte Observer : http://www.charlotteobserver.com/rss/
//		
//// http://www.marketwatch.com/rss/
//// http://www.forbes.com/fdc/rss.html
//// http://www1.nyse.com/about/newsevents/1149674941598.html
//// http://sports.espn.go.com/espn/rss/
//
//public class PlayListServiceOld extends Amqp_Service
//{
//	@Autowired  private PlayList_Repository repo;											
//	public PlayList_Repository getRepo() {return repo;}			
//	
//	@Autowired  private RssItemService rssItemService;								
//	public RssItemService getRssItemService() {return rssItemService;}			
//
//	@Autowired  private CsvLoaderService csvLoaderService;								
//	public CsvLoaderService getCsvLoaderService() {return csvLoaderService;}			
//
//	@Autowired  private MongoTemplate mongoTemplate;
//	public MongoTemplate getMongoTemplate() {return mongoTemplate;}			
//	
//	
//	// MetaData Store
//	private PropertiesPersistingMetadataStore metadataStore;	
//	// Synd Feed Format 
//	public ArrayList<FeedEntryMessageSource> femsList;
//	// Internal Feed Lists
//	public PlayList playList;					
//
//	// Initialize NewsFeed PlayList
//	private void initPlayList( )
//	{	
//		// Get PlayLists from DB or Mock DataSources if null
//		List<PlayList> dbPlayLists = repo.findAll();
//		if(dbPlayLists == null || dbPlayLists.size() == 0 || Statics.reloadPlayListDB == true){
//
//			Statics.Log(" === Delete and Refresh Playist === ");
//			PlayList dataList = csvLoaderService.LoadRssFeedsFromCSV(Statics.dataListName, "rssLists/feedListAll.csv");
//			dbPlayLists.clear();
//			dbPlayLists.add( dataList );
//			repo.deleteAll();
//			repo.save(dataList);
//			
//			CategoryList categoryList = csvLoaderService.createUniqueCategoryList(dataList);
//			CompanyList companyList = csvLoaderService.createUniqueCompanyList(dataList);
//			ChannelList channelList = csvLoaderService.createUniqueChannelList(dataList);
//
//		} 
//
//		// Process Lists
//		try{				
//	        metadataStore = new PropertiesPersistingMetadataStore();
//	        metadataStore.afterPropertiesSet();
//			femsList = new ArrayList<FeedEntryMessageSource>();
//			
//			for(PlayList playList : dbPlayLists  ){ 
//				for(DataSource dataSource : playList.getDataSources() )	{ 
//					createFeedEntryMessageSource("myFeedReader", dataSource);
//					Log("Added DataSource:" + dataSource.getUri() );
//				}
//			}
//		}catch(Exception ex){Log("==> Whoops!! : init " + ex.getMessage());}
//		
//		// Map Reduce
//		mapReduceCategories();
//	}
//
//	public void createFeedEntryMessageSource(String beanName, DataSource dataSource) {
//		try{
//			// Create a Feed Consumer    
//			URL url = new URL( dataSource.getUri() );
//			FeedEntryMessageSource feedEntrySource = new FeedEntryMessageSource( url, "" );
//			feedEntrySource.setBeanName(beanName);
//		    feedEntrySource.setMetadataStore(metadataStore); 
//		    feedEntrySource.afterPropertiesSet();
//
//		    // Add to feedList
//		    femsList.add(feedEntrySource);				
//			
//			// Add to "default" list	
//			if(playList == null) {					
//					playList = new PlayList();
//					playList.setName(Statics.dataListName);
//			}	
//			playList.getDataSources().add(dataSource);
//
//		} catch(Exception ex){Log("==> Whoops!! : initFeed " + ex.getMessage());}
//	}
//	
//
//	
//	/// ====================================================================== 
//	/// Main Update function : "Quartz-Timer Poller"
//	/// Called by Spring "applicationContext-RSS.xml" 
//	/// ====================================================================== 
//	public void updatePlayList()
//	{
//		// Pauses RSS Updates
//		if(Statics.shouldRssUpdate == false)
//			return;
//		
//		// Initialize Feed List if null
//		if(femsList == null)
//			initPlayList( );
//
//		// Process RSS Updates
//		Statics.Log("\n ================================================= ");
//		Statics.Log("Updating === Playlist : " + femsList.size() + " === UserPlayist: " + playList.getDataSources().size()  );
//		for(int dataSourceCount=0; dataSourceCount< femsList.size(); dataSourceCount++)
//		{	
//			// RSS-Consumer : Pull RSS Messages from their respective servers
//			try{	
//				// Throttling
//				Thread.sleep(Statics.pollerSleepDT);
//
//				// Get Message
//				Message<?> message = femsList.get( dataSourceCount ).receive();
//				if(message != null)
//				{	// Extract RSS Payload
//					SyndEntryImpl syndEntry = (SyndEntryImpl) message.getPayload();
//					if( syndEntry != null )
//					{	
//						// Get Ref-URI and Save
//						DataSource dataSource = playList.getDataSources().get( dataSourceCount );
//
//						// Save Unique entries
//						RssItem rssItem = rssItemService.saveUnique( syndEntry , dataSource );
//						
//						// Publish
//						if(rssItem != null){
//
//							// Publish RssItem only if new
//							if(rssItem.getTag() == "saved"){
//								PublishRssItem("amq.topic", rssItem.getCategory(), rssItem);
////								PublishRssItem("amq.topic", "publishRssItem", rssItem);
//								Log("Saved Unique : " + rssItem.getFeed() + " : " + rssItem.getTitle()  );				
//							}
//
//							// Publish Banner each time
//							PublishBanner("amq.topic", "publishBanner", rssItem);
//
//							// Send Esper Update
//							this.sendEsperEvent(rssItem);
//						}
//					}
//				}
//					
//			}
//			catch(Exception ex){ Log("==> Whoops!! Dead Link" + ex.getMessage() ); }				
//		} 
//		Statics.Log(" =================================================\n ");
//	}
//	
//	public void PublishBanner(String exchange, String routingKey, RssItem rssItem)
//	{
//		if(rssItem.getCategory() == null){
//			Statics.Log("\n\nNull Data : =================== >>> " + rssItem.getFeed() + "\n\n");
//			return;
//		}
//		try {
//			this.sendMessage(this.exchange, "publishBanner", PojoMapper.toJson(rssItem, true));
//		} 
//		catch (JsonMappingException e) {e.printStackTrace();} 
//		catch (JsonGenerationException e) {e.printStackTrace();} 
//		catch (IOException e) {e.printStackTrace();}	
//	}
//	
//	public void PublishRssItem(String exchange, String routingKey, RssItem rssItem)
//	{
//		try {
//			this.sendMessage(this.exchange, "publishRssItem", PojoMapper.toJson(rssItem, true));
//		} 
//		catch (JsonMappingException e) {e.printStackTrace();} 
//		catch (JsonGenerationException e) {e.printStackTrace();} 
//		catch (IOException e) {e.printStackTrace();}	
//	}
//		
//	// Publish Esper Event
//	public void sendEsperEvent(RssItem rssItem){
////		EsperListener.sendEvent(rssItem);		
//	}
//	
//	public void mapReduceCategories(){
////		Statics.Log(" === Running Map Reduce === ");
////		MapReduceResults<ValueObject> results = 
////				mongoTemplate.mapReduce(
////						"rssItem", 
////						"classpath:/resources/js/mapReduce/map.js", 
////						"classpath:/resources/js/mapReduce/reduce.js", 
////						options().outputCollection("aaa_example_out"),
////						ValueObject.class);
////		
////		for (ValueObject valueObject : results) {
////		  Statics.Log(valueObject.toString());
////		}
//	}
//	
//	public void aggregate(){
////		Aggregation agg = new Aggregation(
////			    pipelineOP1(),
////			    pipelineOP2(),
////			    pipelineOPn()
////			);
////		AggregationResults<OutputType> results = mongoTemplate.aggregate(agg, "rssItem", OutputType.class);
////		List<OutputType> mappedResult = results.getMappedResults()
//	}
//	
//
//	
//}
//
