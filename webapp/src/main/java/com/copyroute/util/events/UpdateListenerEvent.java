//package com.penguinsrising.util.events;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.espertech.esper.client.EventBean;
//import com.espertech.esper.client.UpdateListener;
//import com.copyroute.services.news.PlayListService;
//
//public class UpdateListenerEvent implements UpdateListener 
//{
//	protected static Logger logger = LoggerFactory.getLogger(UpdateListenerEvent.class);
//
//	// Claim Event Workhorse function
//	public void update(EventBean[] newEvents,EventBean[] oldEvents){		
//		Object obj = newEvents[0].getUnderlying();
//		logger.info("Esper Event Fired : " + obj.toString());
//		
//		 if(newEvents.length > 0)
//		 	logger.info("Esper Event Fired: " + newEvents[0].getUnderlying() );
//			
//	}
//
//}
//
