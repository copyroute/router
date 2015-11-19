
package com.copyroute.services.news;

import java.util.*;
import java.net.URL;


import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.util.Time;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
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

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.ws.rs.Path;

@WebService(name = "PlayListService", targetNamespace = "http://copyroute.com/services/")
@Path("/PlayListService")
@Component
public class PlayListService
{
    // Repositories
    @Autowired  protected MongoTemplate mongoTemplate;
	@Autowired  private PlayList_Repository repo;
	@Autowired  private RssItemService rssItemService;
	@Autowired  private CsvLoaderService csvLoaderService;
    @Autowired  private  CategoryService categoryService;
    @Autowired  private  ChannelService channelService;
    @Autowired  private  CompanyService companyService;

    public PlayList playList;
	FeedFetcher fetcher;

    private List<String> collectionNames;
    public String getCollectionName(){
        return collectionNames.get(collectionNames.size()-1);
    }

    public int getCollectionCount(){
        return collectionNames.size();
    }
    public int getLastCollectionIndex(){
        return collectionNames.size() - 1;
    }


    @PostConstruct
    public void init(){
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
        collectionNames = getCollectionsNames();
    }

    public List<String> getCollectionsNames()
    {
        List<String> rssItemCollectionNames = new ArrayList<String>();
        for(String collectionName : mongoTemplate.getCollectionNames()) {
            if(collectionName.contains("rssItem") && collectionName.length() == 11 ) {
                Statics.Log("Adding Collection To List : " + collectionName);
                rssItemCollectionNames.add(collectionName);
            }
        }
        Collections.sort(rssItemCollectionNames);
        return rssItemCollectionNames;
    }


    // Initialize NewsFeed PlayList
	public void initPlayList( )
	{	
		// Create Disk-Caching FeedFetcher
		FeedFetcherCache cache = new DiskFeedInfoCache("/opt/rome-fetcher/cache");
		fetcher = new HttpURLFeedFetcher(cache);
		
		// Load Playlist from CSV File
		Statics.Log(" === Loading From CSV === ");
		playList = csvLoaderService.LoadPlaylist(Statics.dataListName, "rssLists/feedListAll.csv");

		Statics.Log(" === Deleting Mongo Playist === ");
		repo.deleteAll();
		Statics.Log(" === Saving Mongo Playist === ");
		repo.save(playList);
		
		// Create Indices
		Statics.Log(" === Rebuilding Lists === ");
		CategoryList categoryList = categoryService.createUniqueCategoryList(playList);
		CompanyList companyList = companyService.createUniqueCompanyList(playList);
		ChannelList channelList = channelService.createUniqueChannelList(playList);
		Statics.Log(" === Created Playlist: " + playList.getName());
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

        for(DataSource dataSource : playList.getDataSources())
		{
			try{
				Statics.Log("Updating : " + dataSource.getUri());
				SyndFeed feed = fetcher.retrieveFeed(new URL(dataSource.getUri()));

				@SuppressWarnings("rawtypes")
				Iterator entries = feed.getEntries().iterator(); 
				while (entries.hasNext()) {

					// Throttling
					Thread.sleep(Statics.pollerSleepDT);
		
					// Get the next entry in the Rss Feed
					SyndEntryImpl entry = (SyndEntryImpl)entries.next();

                    // Save Unique entries to DB
					RssItem rssItem = saveUnique( entry , dataSource );
					
				}
			}
			catch(Exception ex){ Statics.Log("==> Whoops!! Dead Link" + ex.getMessage() ); }
		}
		Statics.Log("End Update =================================================\n ");
	}

    // Save Unique Messages  ==================================================
    private RssItem saveUnique(SyndEntryImpl syndFeed, DataSource dataSource)
    {
        // Store Feed In DB
        if( Statics.saveMessagesEnabled ){

            try{
                RssItem rssItem = convertToCDM(syndFeed, dataSource );
                rssItem.setTag("saved");

                Statics.Log(" ++++++++++++++++++++++++++++++++ ");
                Statics.Log("Saving : " + rssItem.getTitle() );
                mongoTemplate.save(rssItem, getCollectionName() );
                Statics.Log("Saved " + getCollectionName() );
                Statics.Log(" ++++++++++++++++++++++++++++++++ ");
                return rssItem;
            }
            catch(Exception ex){Statics.Log("Duplicate Message");}
        }
        return null;
    }

    // Convert  ==================================================
    private RssItem convertToCDM(SyndEntryImpl syndFeed, DataSource dataSource){

        // Tag Content using NLP
        String tag = tag( syndFeed );

        RssItem rssItem = new RssItem();
        rssItem.setCategory(dataSource.getCategory());
        rssItem.setCompany(dataSource.getCompany());
        rssItem.setChannel(dataSource.getChannel());
        rssItem.setTag(tag);

        if(syndFeed.getTitle() != null)
            rssItem.setTitle(syndFeed.getTitle());

        if(syndFeed.getDescription() != null)
            rssItem.setDescription(syndFeed.getDescription().getValue());
        else if(syndFeed.getContents().get(0) != null)
            rssItem.setDescription(((SyndContentImpl)syndFeed.getContents().get(0)).getValue());

        if(syndFeed.getLink() != null)
            rssItem.setUri(syndFeed.getLink());
        if(syndFeed.getUri() != null)
            rssItem.setFeed(dataSource.getUri());
        if(syndFeed.getPublishedDate() != null)
            rssItem.setDate(Time.convertXMLGregorianCalendar(syndFeed.getPublishedDate()));

        return rssItem;
    }

    // Tag ==================================================
    private String tag(SyndEntryImpl syndFeed){

//		String title = syndFeed.getTitle().toLowerCase();
//		String description = syndFeed.getDescription().getValue().toLowerCase();

//		naturalLanguageProcessing(title);
//		naturalLanguageProcessing(description);

//		if(description.contains("img"))			{return "img";}
//		else if(description.contains("video"))	{return "video";}
//		else {return "news";}
        return "";
    }

// Amqp ===============================
//    public void PublishSave( RssItem rssItem )
//    {
//        // Publish
//        if(rssItem != null)
//        {
//            PublishRssItem("amq.topic", rssItem.getCategory(), rssItem);
//            Log("Saved Unique : \n" + rssItem.getTitle() + " \n " + rssItem.getFeed()  );
//
//            // Publish Banner each time
//            PublishBanner("amq.topic", "publishBanner", rssItem);
//
//            // Send Esper Update
//            this.sendEsperEvent(rssItem);
//        }
//
//    }
//
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
		
//	// Publish Esper Event
//	public void sendEsperEvent(RssItem rssItem){
////		EsperListener.sendEvent(rssItem);
//	}


	
}

