package com.penguinsrising.ui.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.messaging.core.MessageSendingOperations;

import com.penguinsrising.cdm.rss.RssItem;
import com.penguinsrising.global.Statics;
import com.penguinsrising.services.amqp.Amqp_Service;
import com.penguinsrising.services.news.RssItemService;

public class RssListener extends Amqp_Service
{

	@Qualifier("rssItemService")
	@Inject		private RssItemService rssItemService;	


	public void handleHistory(Message message) {
	   	 try {			
			Statics.Log("\n\n============= !!!!!! Rss History Handler !!!!!! ============= \n");
	 		List<RssItem> rssList = rssItemService.findCategory("News", 0, 15, Direction.DESC, "id");
			String replyToLoc = message.getMessageProperties().getReplyTo();
//			String[] replyTo = replyToLoc.split("/");
//			replyToLoc = replyTo[replyTo.length-1];
			for(RssItem rssItem : rssList)
				this.sendDefault(replyToLoc, rssItem.toString());
		 }
		 catch (Exception e) {}
	 }


}
