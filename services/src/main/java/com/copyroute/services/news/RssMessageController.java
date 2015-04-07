//package com.penguinsrising.services.news;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.inject.Inject;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.stereotype.Component;
//
//import com.copyroute.cdm.rss.RssItem;
//import com.copyroute.cdm.rss.RssSearchRequest;
//import com.copyroute.services.global.Statics;
//import com.copyroute.services.amqp.Amqp_Service;
//import com.copyroute.util.json.PojoMapper;
//
////@Component
//public class RssMessageController extends Amqp_Service {
//
//	@Qualifier("rssItemService")
//	@Inject		private RssItemService rssItemService;	
//
//    @PostConstruct
//    public void init(){ Log("================= >>>>>> Initialized: " + this.getClass().toString());}
//
//
//	public void handleSearch(Message message){	
//		try{
//			// Map Input
//			//	RssSearchRequest input = (RssSearchRequest) PojoMapper.fromJson(
//			//		new String(message.getBody(), ENCODING), RssSearchRequest.class);		
//			RssSearchRequest input = new RssSearchRequest();
//			input.setTitle(new String(message.getBody(), ENCODING));
//			input.setPageNumber(0);
//			input.setPageSize(50);
//
//			String replyTo = message.getMessageProperties().getReplyTo();
//			Statics.Log("Body: " + input );
//			Statics.Log("ReplyTo: " + replyTo );
//
//			// Search & Publish
//			List<RssItem> rssList = handleSearch(input);
//			if(rssList != null){
//				Statics.Log("Found Items : " + rssList.size());
//				for(RssItem rssItem : rssList){
//					this.sendDefault(replyTo, PojoMapper.toJson(rssItem, true));
//					// this.sendMessage(this.exchange, "publishRssItem", PojoMapper.toJson(rssItem, true));
//				}
//			}
//		}catch(Exception ex){Log("==> Whoops!!: " + this.getClass().toString() + ":" + ex.getMessage());}			
//	}
//
//	public List<RssItem> handleSearch(RssSearchRequest input ){	
//		List<RssItem> rssList = rssItemService.find(input.getPageNumber(), input.getPageSize(), Direction.DESC, "id" );
//		return rssList;			
//	}
//
//
//	public void handlePublish(Message message){	
//		// RSSItem
//		Log("Publish Message Received" + message.toString());
//		try{
//			String body = new String(message.getBody(), ENCODING);
//			// RssItem item = (RssItem) PojoMapper.fromJson(body, RssItem.class); //new RssItem();
//			this.sendMessage(this.exchange, "rssResults", body);
//		}
//		// catch (JsonMappingException e) {Log(e.getMessage());} 
//		// catch (JsonGenerationException e) {Log(e.getMessage());} 
//		catch (IOException e) {Log(e.getMessage());}
//		catch(Exception ex){Log("==> Whoops!!: " + this.getClass().toString() + ":" + ex.getMessage());}		
//	}  
//
//
//}
