
package com.penguinsrising.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

//import net.sourceforge.wurfl.core.Device;
//import net.sourceforge.wurfl.core.WURFLEngine;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.penguinsrising.services.news.RssItemService;
import com.penguinsrising.cdm.rss.RssItemList;
import com.penguinsrising.global.Statics;

@Controller
public class RssController 
{
//	@Qualifier("WurflHolder")
//	@Inject		private WURFLEngine engine;	

	@Qualifier("rssItemService")
	@Inject		private RssItemService rssItemService;	

	List<String> categoryList;
	
	@PostConstruct
	public void init(){ 
		categoryList = rssItemService.getCategoryList().getItems();
		Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); 
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView getDefault(HttpServletRequest request) {
		return getMultiSearch(request);
	}

	
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView getMultiSearch(HttpServletRequest request) 
	{
		String domain = getDomain(request);
		List<RssItemList> rssLists = new ArrayList<RssItemList>();		

		rssLists.add( rssItemService.getRssItemCategoryList("Entertainment", 0, 10) );
		rssLists.add( rssItemService.getRssItemCategoryList("Finance", 0, 10) );
		rssLists.add( rssItemService.getRssItemCategoryList("Sports", 0, 10) );
		//rssLists.add( rssItemService.getRssItemCategoryList("Technology", 0, 10) );		
		//rssLists.add( rssItemService.getRssItemCategoryList("Health", 0, 10) );		
		
		ModelAndView mav = new ModelAndView();
		mav = setPageData(request, mav, "Real Time Global News Source", "category");
		mav.addObject("RssItems", rssLists);	

		mav.setViewName("rssViewerMulti");
		return mav;
	}

	@RequestMapping(value="/banner", method = RequestMethod.GET)
	public ModelAndView getBanner(HttpServletRequest request) 
	{
		RssItemList rssList = rssItemService.getRssItemCategoryList("News", 0, 10);

		ModelAndView mav = new ModelAndView();
		mav = setPageData(request, mav, "Real Time Global News Source", "category");
		mav.addObject("rssItem", rssList.getItems().get(0));	
		mav.setViewName("banner");
		return mav;
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	protected ModelAndView handleNews(
			@RequestParam(value = "start", defaultValue = "0", required = false ) int start, 
			@RequestParam(value = "size", defaultValue = "25", required = false ) int size,
			@RequestParam(value = "term", defaultValue = "", required = false ) String term,
			HttpServletRequest request			
			) throws Exception 
	{				
		RssItemList rssItemList = new RssItemList();		
		rssItemList.setCategory(term);
		rssItemList.getItems().addAll( rssItemService.findByTitle(term, start, size) );
		Statics.Log("Found News : " + rssItemList.getItems().size());
		
		List<RssItemList> rssLists = new ArrayList<RssItemList>();
		rssLists.add(rssItemList);

		ModelAndView mav = new ModelAndView();
		mav = setPageData(request, mav, "Search for : " + term, "search");
		mav.addObject("term", term);		
		mav.addObject("RssItems", rssLists);
		mav.setViewName("rssViewerMulti");
		return mav;
	}
	
	// Search by Category
	@RequestMapping(value="/category/{category}", method = RequestMethod.GET)
	public ModelAndView getCategorySearch(			
			@PathVariable("category") String category,
			HttpServletRequest request
			) 
	{
		if(category.isEmpty())
			category = "News";
		
		List<RssItemList> rssLists = new ArrayList<RssItemList>();
		rssLists.add( rssItemService.getRssItemCategoryList(category, 0, 25) );

		ModelAndView mav = new ModelAndView();
		mav = setPageData(request, mav, "Category : " + category , "category");
		mav.addObject("RssItems", rssLists);
		mav.setViewName("rssViewerMulti");
		return mav;
	}

	// Search by Company
	@RequestMapping(value="/company/{company}", method = RequestMethod.GET)
	public ModelAndView getCompanySearch(			
			@PathVariable("company") String company,
			HttpServletRequest request
			) 
	{
		if(company.isEmpty())
			company = "AP";
		
		List<RssItemList> rssLists = new ArrayList<RssItemList>();
		rssLists.add( rssItemService.getRssItemCompanyList(company, 0, 25) );

		ModelAndView mav = new ModelAndView();
		mav = setPageData(request, mav, "Company : " + company, "company");
		mav.addObject("RssItems", rssLists);
		mav.setViewName("rssViewerMulti");
		return mav;
	}

	// Search by Channel
	@RequestMapping(value="/company/{company}/{channel}", method = RequestMethod.GET)
	protected ModelAndView handleSearchChannel(
			@PathVariable("company") String company,
			@PathVariable("channel") String channel,
			@RequestParam(value = "start", defaultValue = "0", required = false ) int start, 
			@RequestParam(value = "size", defaultValue = "25", required = false ) int size,
			@RequestParam(value = "bgColor", defaultValue = "rgba(50,50,50,0.1)", required = false ) String bgColor,
			HttpServletRequest request
			) throws Exception 
	{

		List<RssItemList> rssLists = new ArrayList<RssItemList>();
		rssLists.add( rssItemService.getRssItemCompanyAndChannelList(company, channel, start, size) );

		ModelAndView mav = new ModelAndView();
		mav = setPageData(request, mav, "Company : " + company + "Channel : " + channel, "company");
		mav.addObject("bgColor", bgColor);				
		mav.addObject("RssItems", rssLists);
		mav.setViewName("rssViewerMulti");
		return mav;
	}

	// Search by Id
	@RequestMapping(value="/id/{id}", method = RequestMethod.GET)
	public ModelAndView getIdSearch(			
			@PathVariable("id") String id, 
			HttpServletRequest request
			) 
	{
		if(id.isEmpty())
			id = "";

		List<RssItemList> rssLists = new ArrayList<RssItemList>();
		RssItemList rssItemList = rssItemService.getRssItemId(id);
		rssLists.add( rssItemList );

		String title = "";
		if(rssItemList.getItems().size() > 0){
			title = rssItemList.getItems().get(0).getTitle() ;
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("RssItems", rssLists);
		mav = setPageData(request, mav, title, "id");
		mav.setViewName("rssViewerMulti");
		return mav;
	}



	// Set Common Page Data
    private ModelAndView setPageData(HttpServletRequest request, ModelAndView mav, String title, String category)
    {		
    	// News Data
		String domain = getDomain(request);
		mav.addObject("PageTitle", title );
		mav.addObject("SearchType", category);		
		mav.addObject("CategoryList", categoryList);		
		mav.addObject("ContextPath", request.getContextPath() );   
		mav.addObject("DomainName", domain);   
		mav.addObject("Theme", "cupertino");   
			
		// Banner Data
		//RssItemList rssList = rssItemService.getRssItemCategoryList("Entertainment", 0, 10);
		//mav.addObject("rssBannerItems", rssList.getItems());	

		return mav;
    }
	public String getDomain(HttpServletRequest request){
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

