package com.penguinsrising.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;

import com.penguinsrising.cdm.rss.RssItem;
import com.penguinsrising.cdm.rss.RssItemList;
import com.penguinsrising.cdm.rss.SampleContent;
import com.penguinsrising.util.Time;

public class CustomRssViewer extends AbstractRssFeedView {

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
		
		feed.setTitle("Penguins-Rising");
		feed.setDescription("UnSpun News ");
		feed.setLink("http://penguins-rising.com");
		
		super.buildFeedMetadata(model, feed, request);
	}
	
	
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		@SuppressWarnings("unchecked")
		List<RssItemList> listContent = (List<RssItemList>) model.get("RssItems");

		int size = 0;
		for(RssItemList rssItemList : listContent){
			size += rssItemList.getItems().size();
		}
		
		List<Item> items = new ArrayList<Item>( size );
		for(RssItemList rssItemList : listContent){
			for(RssItem tempContent : rssItemList.getItems() ){
			
				Content content = new Content();
				content.setValue(tempContent.getTitle());
	
				Item item = new Item();
				item.setContent(content);	
				item.setTitle(tempContent.getTitle());
				item.setLink(tempContent.getUri());
				item.setPubDate(tempContent.getDate().toGregorianCalendar().getTime());
				items.add(item);
			}
		}
		return items;
	}
	
}