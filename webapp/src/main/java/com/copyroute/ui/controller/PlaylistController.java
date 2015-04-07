package com.copyroute.ui.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.copyroute.cdm.global.Statics;
import com.copyroute.webservices.mongo.PlayList_Repository;
import com.copyroute.cdm.rss.PlayList;

@Controller
public class PlaylistController {

	@Autowired  private PlayList_Repository playListRepo;											
	public PlayList_Repository getPlayListRepo() {return playListRepo;}			

	
	@PostConstruct
	public void init(){ 
		Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); 
	}
	
	@RequestMapping(value="/playlist", method = RequestMethod.GET)
	public PlayList get(HttpServletRequest request) {
		return getPlayListRepo().findAll().get(0);
	}
	
	// Create
	@RequestMapping(value = "/playlist/{id}", method = RequestMethod.POST)
	public PlayList create(HttpServletRequest request, @RequestBody PlayList playlist, @PathVariable("id") String id) {
		return getPlayListRepo().save(playlist);
	}
	
	// Update
	@RequestMapping(value = "/playlist/{id}", method = RequestMethod.PUT)
	public PlayList update(HttpServletRequest request, @RequestBody PlayList playlist, @PathVariable("id") String id) {
		return getPlayListRepo().save(playlist);		
	}
	
	// Delete
	@RequestMapping(value = "/playlist/{id}", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request, @PathVariable("id") String id) {
		getPlayListRepo().delete(id);
		return "Success";
	}


}
