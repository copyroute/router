package com.copyroute.ui.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.copyroute.cdm.rss.PlayList;
import com.copyroute.cdm.global.Statics;
import com.copyroute.services.news.RssItemService;

@Controller
public class AdminController {

	@Qualifier("rssItemService")
	@Inject		private RssItemService rssItemService;	

	@PostConstruct
	public void init(){ 
		Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); 
	}

	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public ModelAndView getDefault(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav = Statics.setPageData(request, mav, "Real Time Global News Source", "category", 0, 0, "");
		mav.setViewName("adminViewer");
		return mav;
	}

	// Create
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.POST)
	public ModelAndView createCustomer(HttpServletRequest request, @RequestBody PlayList playlist, @PathVariable("id") String id) {
		System.out.println("POST:" + playlist.toString());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewerMulti");
		mav = Statics.setPageData(request, mav, "Real Time Global News Source", "category", 0, 0, "");
//		customerService.getCustomerDAO().insert(playlist);
	    return mav;
	}
	
	// Update
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.PUT)
	public ModelAndView updateCustomer(HttpServletRequest request, @RequestBody PlayList playlist, @PathVariable("id") String id) {
		System.out.println("PUT:" + id + " : " + playlist.toString());
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("rssViewerMulti");
		mav = Statics.setPageData(request, mav, "Real Time Global News Source", "category", 0, 0, "");
//	    customerService.getCustomerDAO().update(playlist);
	    return mav;
	}
	
	// Delete
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteCustomer(HttpServletRequest request, @PathVariable("id") String id) {
		System.out.println("DELETE:" + id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewerMulti");
		mav = Statics.setPageData(request, mav, "Real Time Global News Source", "category", 0, 0, "");
//		customerService.getCustomerDAO().delete(Integer.parseInt(id));
	    return mav;
	}

	
}
