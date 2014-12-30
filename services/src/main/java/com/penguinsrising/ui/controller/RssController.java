
package com.penguinsrising.ui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.sitemaps.schemas.sitemap._0.TChangeFreq;
import org.sitemaps.schemas.sitemap._0.TSitemap;
import org.sitemaps.schemas.sitemap._0.TUrl;
import org.sitemaps.schemas.sitemap._0.Urlset;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;


import com.penguinsrising.services.amqp.Amqp_Service;
import com.penguinsrising.services.news.PlayListService;
import com.penguinsrising.cdm.rss.CategoryList;
import com.penguinsrising.cdm.rss.CompanyList;
import com.penguinsrising.cdm.rss.PlayList;
import com.penguinsrising.cdm.rss.RssItem;
import com.penguinsrising.cdm.rss.RssItemList;
import com.penguinsrising.cdm.rss.RssSearchRequest;
import com.penguinsrising.cdm.rss.SampleContent;
import com.penguinsrising.global.Statics;
import com.penguinsrising.util.Time;
import com.penguinsrising.util.json.PojoMapper;

@Controller
public class RssController 
{

	@Qualifier("playListService")
	@Inject		private PlayListService playListService;	

	String deviceOS = "";
	String isFullDesktop = "";
	String isMobile = "";
	String isRobot = "";
	String isSmartphone = "";
	
	@PostConstruct
	public void init(){ Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); }

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView getDefault(HttpServletRequest request) {
		return getMultiSearch(request);
	}

	public RssItemList getCategoryList(String category, int start, int end){
		return playListService.getRssItemService().getRssItemCategoryList(category, start, end); 
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView getMultiSearch(HttpServletRequest request) 
	{
		String domain = Statics.getDomain(request);
		List<RssItemList> rssLists = new ArrayList<RssItemList>();		

		if(domain.contains("penguins-rising")){
			rssLists.add( playListService.getRssItemService().getRssItemCategoryList("Science", 0, 10) );		
			rssLists.add( getCategoryList("Technology", 0, 10) );		
			rssLists.add( getCategoryList("Podcasts", 0, 10) );		
			if(isFullDesktop == "true"){
				rssLists.add( getCategoryList("Grants", 0, 10) );		
				rssLists.add( getCategoryList("Weather", 0, 10) );					
				rssLists.add( getCategoryList("Video", 0, 10) );		
			}
		}
		else if(domain.contains("copyroute")){
			rssLists.add( getCategoryList("News", 0, 10) );
			rssLists.add( getCategoryList("Finance", 0, 10) );
			rssLists.add( getCategoryList("Health", 0, 10) );		
			if(isFullDesktop == "true"){
				rssLists.add( getCategoryList("Political", 0, 10) );		
				rssLists.add( getCategoryList("Business", 0, 10) );		
				rssLists.add( getCategoryList("Opinion", 0, 10) );		
				rssLists.add( getCategoryList("Jobs", 0, 10) );		
			}
		}
		else if(domain.contains("theysed")){
			rssLists.add( getCategoryList("YouTube", 0, 10) );
			rssLists.add( getCategoryList("Entertainment", 0, 10) );
			rssLists.add( getCategoryList("Sports", 0, 10) );
			if(isFullDesktop == "true"){
				rssLists.add( getCategoryList("Live Channels", 0, 10) );		
				rssLists.add( getCategoryList("Kids", 0, 10) );		
				rssLists.add( getCategoryList("Photos", 0, 10) );		
				rssLists.add( getCategoryList("Education", 0, 10) );		
			}			
		}
		else{
			rssLists.add( getCategoryList("News", 0, 10) );
			rssLists.add( getCategoryList("Entertainment", 0, 10) );
			rssLists.add( getCategoryList("Sports", 0, 10) );			
			rssLists.add( getCategoryList("Weather", 0, 10) );					
		}
		
		ModelAndView mav = new ModelAndView();
		mav = setPageData(request, mav, "Real Time Global News Source", "category");
		mav.addObject("RssItems", rssLists);	

		mav.setViewName("rssViewerMulti");
		return mav;
	}

	@RequestMapping(value="/banner", method = RequestMethod.GET)
	public ModelAndView getBanner(HttpServletRequest request) 
	{
		RssItemList rssList = playListService.getRssItemService().getRssItemCategoryList("News", 0, 10);

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
			@RequestParam(value = "bgColor", defaultValue = "rgba(50,50,50,0.1)", required = false ) String bgColor,
			HttpServletRequest request			
			) throws Exception 
	{				
		RssItemList rssItemList = new RssItemList();		
		rssItemList.setCategory(term);
		rssItemList.getItems().addAll( playListService.getRssItemService().findByTitle(term, start, size) );
		Statics.Log("Found News : " + rssItemList.getItems().size());
		
		List<RssItemList> rssLists = new ArrayList<RssItemList>();
		rssLists.add(rssItemList);

		ModelAndView mav = new ModelAndView();
		mav = setPageData(request, mav, "Search for : " + term, "search");
		mav.addObject("bgColor", bgColor);		
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
		rssLists.add( playListService.getRssItemService().getRssItemCategoryList(category, 0, 25) );

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
		rssLists.add( playListService.getRssItemService().getRssItemCompanyList(company, 0, 25) );

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
		rssLists.add( playListService.getRssItemService().getRssItemCompanyAndChannelList(company, channel, start, size) );

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
		RssItemList rssItemList = playListService.getRssItemService().getRssItemId(id);
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
		mav.addObject("PageTitle", title );
		mav.addObject("SearchType", category);		
		mav.addObject("CategoryList", playListService.getCsvLoaderService().getCategoryList().getItems());		
		mav.addObject("ContextPath", request.getContextPath() );   
		mav.addObject("DomainName", Statics.getDomain(request));   
		mav.addObject("Theme", Statics.getTheme(request));   
			
		// Banner Data
		RssItemList rssList = playListService.getRssItemService().getRssItemCategoryList("Entertainment", 0, 10);
		mav.addObject("rssBannerItems", rssList.getItems());	

		return mav;
    }

}

