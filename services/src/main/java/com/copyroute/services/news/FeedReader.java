//package com.penguinsrising.services.news;
//
//import java.net.URL;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.stereotype.Component;
//
//import com.sun.syndication.feed.synd.SyndFeed;
//import com.sun.syndication.fetcher.FeedFetcher;
//import com.sun.syndication.fetcher.FetcherEvent;
//import com.sun.syndication.fetcher.FetcherListener;
//import com.sun.syndication.fetcher.impl.FeedFetcherCache;
//import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
//import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
//
//import com.copyroute.global.*;
//

//import javax.jws.WebService;
//
//@WebService
//@Component
//public class FeedReader {
//
//	@PostConstruct
//	public void init(){ 
//		// Set Amqp Routing
////		routing = "publishRssItem";
//		Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); 
//	}
//
//	public void updatePlayList()
//	{
//		read("YouTube: Spring Integration Java"			, "http://youtube.com/rss/tag/spring+integration+java+.rss"		);
//		read("Hulu Today"								, "http://www.hulu.com/feed/popular/videos/today?cc_only=1"		);
//		read("FeedBurner : Slick-Deals"					, "http://feeds.feedburner.com/SlickdealsnetForums-7"			);
//		read("Gawker DeadSpin Full"						, "http://feeds.gawker.com/deadspin/full"						);
//		read("Wall Street Journal"						, "http://feeds.wsjonline.com/wsj/video/news/feed"				);
//		read("House Oversight & Government Reform"		, "http://oversight.house.gov/feed/rss/"						);
//		read("DeadSpin : Top "							, "http://deadspin.com/top/index.xml"							);
//		read("BBC News"									, "http://feeds.bbci.co.uk/news/rss.xml"						);
//	}
//	
//	public static void read(String description, String url) {
//        try {
//                URL feedUrl = new URL(url);
//                FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
//                FeedFetcher fetcher = new HttpURLFeedFetcher(feedInfoCache);
//
//                FetcherEventListenerImpl listener = new FetcherEventListenerImpl();
//                fetcher.addFetcherEventListener(listener);
//                System.err.println("Retrieving feed " + feedUrl);
//                
//                // Retrieve the feed.
//                // We will get a Feed Polled Event and then a
//                // Feed Retrieved event (assuming the feed is valid)
//                SyndFeed feed = fetcher.retrieveFeed(feedUrl);
//                System.err.println(feedUrl + " retrieved");
//                System.err.println(feedUrl + " has a title: " + feed.getTitle() + "and contains " + feed.getEntries().size() + " entries.");
//
//                // We will now retrieve the feed again. If the feed is unmodified
//                // and the server supports conditional gets, we will get a "Feed
//                // Unchanged" event after the Feed Polled event
//                System.err.println("Polling " + feedUrl + " again to test conditional get support.");
//                SyndFeed feed2 = fetcher.retrieveFeed(feedUrl);
//                System.err.println("If a \"Feed Unchanged\" event fired then the server supports conditional gets.");
//        }
//        catch (Exception ex) {
//                System.out.println("ERROR: "+ex.getMessage());
//                ex.printStackTrace();
//        }
//    }
//
//    static class FetcherEventListenerImpl implements FetcherListener {
//            public void fetcherEvent(FetcherEvent event) {
//                    String eventType = event.getEventType();
//                    if (FetcherEvent.EVENT_TYPE_FEED_POLLED.equals(eventType)) {
//                            System.err.println("\tEVENT: Feed Polled. URL = " + event.getUrlString());
//                    } 
//                    else if (FetcherEvent.EVENT_TYPE_FEED_RETRIEVED.equals(eventType)){
//                            System.err.println("\tEVENT: Feed Retrieved. URL = " + event.getUrlString());
//                    } 
//                    else if (FetcherEvent.EVENT_TYPE_FEED_UNCHANGED.equals(eventType)){
//                            System.err.println("\tEVENT: Feed Unchanged. URL = " + event.getUrlString());
//                    }
//            }
//    }
//}
//
