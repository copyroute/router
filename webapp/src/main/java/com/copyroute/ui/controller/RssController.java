
package com.copyroute.ui.controller;

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
import org.springframework.beans.factory.annotation.Qualifier;

import com.copyroute.services.news.RssItemService;
import com.copyroute.cdm.rss.RssItem;
import com.copyroute.cdm.global.Statics;

@Controller
public class RssController 
{
	@Qualifier("rssItemService")
	@Inject		private RssItemService rssItemService;	

	List<String> categoryList;

	
	@PostConstruct
	public void init(){ 
		categoryList = rssItemService.getCategoryList().getItems();
		Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); 
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView getDefault(
			@RequestParam(value = "start", defaultValue = "0", required = false ) int start, 
			@RequestParam(value = "size", defaultValue = "10", required = false ) int size,
			@RequestParam(value = "term", defaultValue = "", required = false ) String term,
			HttpServletRequest request) 
	{
		return getMultiSearch(start, size, term, request);
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView getMultiSearch(
			@RequestParam(value = "start", defaultValue = "0", required = false ) int start, 
			@RequestParam(value = "size", defaultValue = "10", required = false ) int size,
			@RequestParam(value = "term", defaultValue = "", required = false ) String term,
			HttpServletRequest request) 
	{
		List<RssItem> rssLists = rssItemService.find(start, size);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("CategoryList", categoryList);	
		mav.addObject("Category", "" );
		mav = Statics.setPageData(request, mav, "Real Time Global News Source", "index", start, size, term);
		mav.addObject("RssItems", rssLists);	

		mav.setViewName("rssViewerMulti");
		return mav;
	}
	
	@RequestMapping(value="/search/search", method = RequestMethod.GET)
	protected ModelAndView handleNews(
			@RequestParam(value = "start", defaultValue = "0", required = false ) int start, 
			@RequestParam(value = "size", defaultValue = "10", required = false ) int size,
			@RequestParam(value = "term", defaultValue = "", required = false ) String term,
			HttpServletRequest request			
			) throws Exception 
	{				
		List<RssItem> rssLists = rssItemService.findByTitle(term, start, size) ;
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("CategoryList", categoryList);		
		mav = Statics.setPageData(request, mav, "Search for : " + term, "search", start, size, term);
		mav.addObject("Category", "search" );
		mav.addObject("RssItems", rssLists);
		mav.setViewName("rssViewerMulti");
		return mav;
	}
	
	// Search by Category
	@RequestMapping(value="/category/{category}", method = RequestMethod.GET)
	public ModelAndView getCategorySearch(			
			@PathVariable("category") String category,
			@RequestParam(value = "start", defaultValue = "0", required = false ) int start, 
			@RequestParam(value = "size", defaultValue = "10", required = false ) int size,
			@RequestParam(value = "term", defaultValue = "", required = false ) String term,
			HttpServletRequest request
			) 
	{
		if(category.isEmpty())
			category = "News";
		
		List<RssItem> rssLists = rssItemService.getRssItemCategoryList(category, start, size) ;

		ModelAndView mav = new ModelAndView();
		mav.addObject("CategoryList", categoryList);		
		mav.addObject("Category", category );
		mav = Statics.setPageData(request, mav, "Category : " + category , "category", start, size, term);
		mav.addObject("RssItems", rssLists);
		mav.setViewName("rssViewerMulti");
		return mav;
	}

	// Search by Company
	@RequestMapping(value="/company/{company}", method = RequestMethod.GET)
	public ModelAndView getCompanySearch(			
			@PathVariable("company") String company,
			@RequestParam(value = "start", defaultValue = "0", required = false ) int start, 
			@RequestParam(value = "size", defaultValue = "10", required = false ) int size,
			@RequestParam(value = "term", defaultValue = "", required = false ) String term,
			HttpServletRequest request
			) 
	{
		if(company.isEmpty())
			company = "AP";
		
		List<RssItem> rssLists  = rssItemService.getRssItemCompanyList(company, start, size);

		ModelAndView mav = new ModelAndView();
		mav.addObject("CategoryList", categoryList);		
		mav.addObject("Category", company );
		mav = Statics.setPageData(request, mav, "Company : " + company, "company", start, size, term);
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
			@RequestParam(value = "size", defaultValue = "10", required = false ) int size,
			@RequestParam(value = "term", defaultValue = "", required = false ) String term,
			HttpServletRequest request
			) throws Exception 
	{

		List<RssItem> rssLists = rssItemService.getRssItemCompanyAndChannelList(company, channel, start, size) ;

		ModelAndView mav = new ModelAndView();
		mav.addObject("CategoryList", categoryList);		
		mav.addObject("Category", channel );
		mav = Statics.setPageData(request, mav, "Company : " + company + "Channel : " + channel, "company", start, size, term);
		mav.addObject("RssItems", rssLists);
		mav.setViewName("rssViewerMulti");
		return mav;
	}

	// Search by Id
	@RequestMapping(value="/id/{id}", method = RequestMethod.GET)
	public ModelAndView getIdSearch(@PathVariable("id") String id, 
			@RequestParam(value = "term", defaultValue = "", required = false ) String term,
			HttpServletRequest request) 
	{
		if(id.isEmpty())
			id = "";

		List<RssItem> rssList = rssItemService.getRssItemId(id);

		String title = "";
		if(rssList.size() > 0)
		{	title = rssList.get(0).getTitle() ;	}

		ModelAndView mav = new ModelAndView();
		mav.addObject("RssItems", rssList);
		mav.addObject("CategoryList", categoryList);		
		mav.addObject("Category", "Article" );
		mav = Statics.setPageData(request, mav, title, "id", 0, 1, title);
		mav.setViewName("rssViewerMulti");
		return mav;
	}
    
}

