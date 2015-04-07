//package com.penguinsrising.cdm.util.events;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.espertech.esper.client.Configuration;
//import com.espertech.esper.client.EPServiceProvider;
//import com.espertech.esper.client.EPServiceProviderManager;
//import com.espertech.esper.client.EPStatement;
//import com.espertech.esper.client.EventBean;
//import com.espertech.esper.client.UpdateListener;
//import com.espertech.esper.client.EPException;
//
//import com.copyroute.services.news.PlayListService;
//
//
//public class EsperListener 
//{
//
//	protected static Logger logger = LoggerFactory.getLogger(EsperListener.class);
//	public static void Log(String message) { logger.info(message); }
//
//	// Esper Query String
//	public static String epl = "select * from RssItem as eventValue";
//
//	// Update Listeners List
//	public static List<UpdateListener> listeners = Arrays.asList(
//			(UpdateListener) new UpdateListenerEvent()
//		);
//
//	// Esper Utils
//	private static EPServiceProvider epService;
//
//	public EsperListener(){}
//	
//    public static void initEsper(String epl, Class<?> pojoClass, List<UpdateListener> listeners ){
//		Log("Esper : Init Listener for Object: " + pojoClass.getSimpleName());
//
//		// Create Configuration
//		Configuration configuration = new Configuration();
//		configuration.addEventType(pojoClass);
//		configuration.addEventTypeAutoName(pojoClass.getPackage().getName());
//		
//		// Create EPL Statement
//		epService = EPServiceProviderManager.getDefaultProvider(configuration);
//		EPStatement epStatement = epService.getEPAdministrator().createEPL(epl);		
//
//		// Add Listeners
//		for(UpdateListener listener : listeners)
//			epStatement.addListener( listener );
//	}
//
//    // This "Global Esper Accessor" should be used to call Claim Event
//    public static void sendEvent(Object obj){
//    	try{
//	    	if(epService == null){
//	    		initEsper(epl, obj.getClass(), listeners );
//	    	}
//	    	// Fire Event
//			epService.getEPRuntime().sendEvent(obj);
//    	}
//		catch(EPException ex){ Log("Esper EPException: " + ex.getMessage() ); }
//	}
//}
//
//
//
