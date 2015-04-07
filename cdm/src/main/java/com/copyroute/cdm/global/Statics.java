package com.copyroute.cdm.global;


import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class Statics {

	
	// PlayList Behaviors
	public static boolean reloadPlayListDB = true;
	
	// Polling Behaviors
	public static boolean shouldRssUpdate = true;
	public static boolean saveMessagesEnabled = true;
	public static int pollerSleepDT = 1000;
	
	// Formatting
	public static boolean JsonPrettyPrint = true;

	// Loggging
	public static boolean loggingOn = false;

	// Default DataList
	public static String dataListName = "ABC";	// "myFeedList";
	
	// ================================================================
	
	private static final Logger logger = Logger.getLogger(Statics.class);
	public static void Log(String message){
		if(logger.isDebugEnabled()){
			logger.debug(message);
		}
		if(loggingOn){
			System.out.println("System : " + message);
		}

	}
	
	// Set Common Page Data
	public static ModelAndView setPageData(HttpServletRequest request, ModelAndView mav, String title, String searchType, int start, int size, String term )
    {
    	// News Data
		String domain = getDomain(request);
		mav.addObject("PageTitle", title );
		mav.addObject("SearchType", searchType);
		mav.addObject("ContextPath", request.getContextPath() );
		mav.addObject("DomainName", domain);
		mav.addObject("Start", start);
		mav.addObject("Size", size);
		mav.addObject("term", term);
		mav.addObject("Theme", "cupertino");

		// Banner Data
		//RssItemList rssList = rssItemService.getRssItemCategoryList("Entertainment", 0, 10);
		//mav.addObject("rssBannerItems", rssList.getItems());

		return mav;
    }
	public static String getDomain(HttpServletRequest request){
		String domain = request.getHeader("X-Forwarded-Host");

		// Set default for non-proxied instances
		if(domain == null || domain.isEmpty())
			domain = "copyroute.com";

		return domain;
	}
//	public void getBrowserInfo(HttpServletRequest request){
	//        WURFLEngine engine = (WURFLEngine) getServletContext().getAttribute(WURFLEngine.class.getName());
//			Device device = engine.getDeviceForRequest(request);
//			Statics.Log("======================================= BrowserInfo =======================================");
//			Statics.Log("Res Width: " + device.getCapability("resolution_width"));
//			Statics.Log("Res Height: " + device.getCapability("resolution_height"));
//			Statics.Log("Phys Width: " + device.getCapability("physical_screen_width"));
//			Statics.Log("Phys Height: " + device.getCapability("physical_screen_height"));
			
//			Statics.Log("Device: " + device.getId());
//			Statics.Log("Markup: " + device.getCapability("preferred_markup"));
//			Statics.Log("Capabilities: " + device.getCapabilities());
//			Statics.Log("Virtual Caps: " + device.getVirtualCapabilities());
//			Statics.Log("DeviceRootId: " + device.getDeviceRootId());
//			Statics.Log("MatchType: " + device.getMatchType().toString());
//			Statics.Log("WURFLUserAgen: " + device.getWURFLUserAgent());
//			String deviceOS = device.getVirtualCapability("advertised_device_os");
//			String isFullDesktop = device.getVirtualCapability("is_full_desktop");
//			String isMobile = device.getVirtualCapability("is_mobile");
//			String isRobot = device.getVirtualCapability("is_robot");
//			String isSmartphone = device.getVirtualCapability("is_smartphone");
//			Statics.Log("======================================= End BrowserInfo =======================================");		
//		}



	
}
