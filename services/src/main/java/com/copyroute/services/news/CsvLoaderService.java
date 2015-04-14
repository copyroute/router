package com.copyroute.services.news;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.copyroute.cdm.rss.CategoryList;
import com.copyroute.cdm.rss.ChannelList;
import com.copyroute.cdm.rss.CompanyList;
import com.copyroute.cdm.rss.DataSource;
import com.copyroute.cdm.rss.PlayList;
import com.copyroute.services.global.Statics;
import com.copyroute.services.amqp.Amqp_Service;
import com.copyroute.services.mongo.Category_Repository;
import com.copyroute.services.mongo.Channel_Repository;
import com.copyroute.services.mongo.Company_Repository;
import org.springframework.stereotype.Component;

@Component
public class CsvLoaderService extends Amqp_Service{

	@Autowired  private Category_Repository categoryRepo;											
	public Category_Repository getCategoryRepo() {return categoryRepo;}			
	
	@Autowired  private Company_Repository companyRepo;											
	public Company_Repository getCompanyRepo() {return companyRepo;}			

	@Autowired  private Channel_Repository channelRepo;											
	public Channel_Repository getChannelRepo() {return channelRepo;}	
	
	
	// Load CSV file, and split on : Catagory, Company, Description, URI
	public PlayList LoadRssFeedsFromCSV(String feedListName, String filename){
		PlayList userFeedList = new PlayList();
        userFeedList.setName(feedListName);

		List<DataSource> dsList = new ArrayList() ;		
		try {
		    InputStream inputStream = null;
		    try {
				Statics.Log(" === Loading File === ");
			    Resource resource = new ClassPathResource(filename);
			    inputStream = resource.getInputStream();
			    Scanner scanner = new Scanner(inputStream);
			    while (scanner.hasNext()) {
			    	String[] props = scanner.nextLine().split(",");
			    	if(props.length >= 4){
			    		userFeedList.getDataSources().add(
			    				createDataSource(props[0], props[1], props[2], props[3]) );
			    	}
			    }
			    scanner.close();
			    inputStream.close();
		    } 
		    finally {if (inputStream != null) {inputStream.close();}}			
		} catch (IOException e) {Log(e.getMessage());}	

		return userFeedList;

	}
	
	//  Catagory, Company, Description, URI
	public DataSource createDataSource(String category, String company, String title, String uri, String... tags){
		DataSource dataSource = new DataSource();
		dataSource.setCategory(category.trim());
		dataSource.setCompany(company.trim());
		dataSource.setChannel(title.trim());
		dataSource.setUri(uri.trim());        
		Statics.Log(" === Creating DataSource : " + dataSource.getUri());
        return dataSource;
	}


	public CategoryList createUniqueCategoryList(PlayList playlist)
	{
		Statics.Log(" === Creating CategoryList === ");
		CategoryList categoryList = new CategoryList();
		for(DataSource ds: playlist.getDataSources()){
			String category = ds.getCategory();

			// If Unique
			if(categoryList.getItems().contains(category) == false){
				categoryList.getItems().add(category);
			}
		}
		Collections.sort(categoryList.getItems());
		categoryRepo.deleteAll();
		categoryRepo.save(categoryList);
		return categoryList;
	}
	
	public CompanyList createUniqueCompanyList(PlayList playlist)
	{
		Statics.Log(" === Creating CompanyList === ");
		CompanyList companyList = new CompanyList();
		for(DataSource ds: playlist.getDataSources()){
			String company = ds.getCompany();

			// If Unique
			if(companyList.getItems().contains(company) == false){
				companyList.getItems().add(company);
			}
		}
		Collections.sort(companyList.getItems());
		companyRepo.deleteAll();
		companyRepo.save(companyList);
		return companyList;
	}
	
	public ChannelList createUniqueChannelList(PlayList playlist)
	{
		Statics.Log(" === Creating ChannelList === ");
		ChannelList channelList = new ChannelList();
		for(DataSource ds: playlist.getDataSources()){
			String channel = ds.getChannel();

			// If Unique
			if(channelList.getItems().contains(channel) == false){
				channelList.getItems().add(channel);
			}
		}
		Collections.sort(channelList.getItems());
		channelRepo.deleteAll();
		channelRepo.save(channelList);
		return channelList;
	}

	public CategoryList getCategoryList(){
		List<CategoryList> categoryLists = getCategoryRepo().findAll();
		CategoryList categoryList = new CategoryList();
		if(categoryLists.size() > 0){
			categoryList = categoryLists.get(0);
			Statics.Log("Found Categories : " + categoryList.getItems().size());
		}
		return categoryList;
	}
	
	public CompanyList getCompanyList(){
		List<CompanyList> companyLists = getCompanyRepo().findAll();
		CompanyList companyList = new CompanyList();
		if(companyLists.size() > 0){
			companyList = companyLists.get(0);
			Statics.Log("Found Companies : " + companyList.getItems().size());
		}
		return companyList;
	}
}
