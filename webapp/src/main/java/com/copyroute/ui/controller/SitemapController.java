package com.copyroute.ui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.sitemaps.schemas.sitemap._0.TChangeFreq;
import org.sitemaps.schemas.sitemap._0.TUrl;
import org.sitemaps.schemas.sitemap._0.Urlset;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.copyroute.cdm.rss.CategoryList;
import com.copyroute.cdm.rss.CompanyList;
import com.copyroute.cdm.global.Statics;
import com.copyroute.webservices.news.RssItemService;


@Controller
public class SitemapController {

	@Qualifier("rssItemService")
	@Inject		private RssItemService rssItemService;	

	
	@PostConstruct
	public void init(){ Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); }
	
    @RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
    @ResponseBody
    public String getRobots(HttpServletRequest request) {
    	
    	String fileText = "";
    	String filename = "web/robots.txt";
		try {
		    InputStream inputStream = null;
		    Resource resource = null;
		    try {
			    resource = new ClassPathResource(filename);
			    inputStream = resource.getInputStream();
			    Scanner scanner0 = new Scanner(inputStream);
			    while (scanner0.hasNext()) {
			    	fileText += scanner0.nextLine() + "\n";
			    }
		    } 
		    finally {
		    	if (inputStream != null) {inputStream.close();}
		    }			
		} catch (IOException e) {Statics.Log(e.getMessage());}	
		return fileText;
    }
	
    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public Urlset getSiteMap(HttpServletRequest request) {
    	
    	String domain = getDomain(request);
    	
    	Urlset urlset = new Urlset();
		CategoryList categoryList = rssItemService.getCategoryList();
		for(String category : categoryList.getItems() ){
	    	urlset.getUrl().add(createUrl(domain, "/category/"+category));
		}
		CompanyList companyList = rssItemService.getCompanyList();
		for(String company : companyList.getItems() ){
	    	urlset.getUrl().add(createUrl(domain, "/company/"+company));
		}		
		return urlset;
    }
    public static TUrl createUrl(String domain, String path){
    	TUrl uri = new TUrl();
    	uri.setLoc("http://"+domain+path);
        Calendar now = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        uri.setLastmod(getCurrentDateTime(now));
    	uri.setChangefreq(TChangeFreq.DAILY);
    	uri.setPriority(new BigDecimal("0.8"));
    	return uri;
    }
    
	public String getDomain(HttpServletRequest request){
		String domain = request.getHeader("X-Forwarded-Host");

		// Set default for non-proxied instances
		if(domain == null || domain.isEmpty())
			domain = "penguins-rising.com";

		Statics.Log("Determined Request URL : " + domain);			
		return domain;
	}
	
    private static String getCurrentDateTime(final Calendar calendar) {
        FastDateFormat dateFormatter = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT;
        return dateFormatter.format(calendar);
    }
//  private static TSitemap createSitemapIndexItem(final String sitemapFile) {
//  TSitemap tSitemap = new TSitemap();
//  Calendar now = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
//  tSitemap.setLoc(sitemapFile);
//  tSitemap.setLastmod(getCurrentDateTime(now));
//  return tSitemap;
//}

	


}
