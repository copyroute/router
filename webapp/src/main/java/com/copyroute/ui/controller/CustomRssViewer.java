package com.copyroute.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;

import com.copyroute.cdm.rss.RssItem;

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
		List<RssItem> listContent = (List<RssItem>) model.get("RssItems");		
		List<Item> items = new ArrayList<Item>( listContent.size() );
		for(RssItem tempContent : listContent){
		
			Content content = new Content();
			content.setValue(tempContent.getTitle());

			Item item = new Item();
			item.setContent(content);	
			item.setTitle(tempContent.getTitle());
			item.setLink(tempContent.getUri());
			item.setPubDate(tempContent.getDate().toGregorianCalendar().getTime());
			items.add(item);
		}
		return items;
	}
	
}