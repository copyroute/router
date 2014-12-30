package com.penguinsrising.services.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.penguinsrising.cdm.rss.CategoryList;
import com.penguinsrising.cdm.rss.CompanyList;
import com.penguinsrising.cdm.rss.DataSource;
import com.penguinsrising.cdm.rss.RssItem;
import com.penguinsrising.cdm.rss.RssItemList;
import com.penguinsrising.global.*;
import com.penguinsrising.services.mongo.Category_Repository;
import com.penguinsrising.services.mongo.Company_Repository;
import com.penguinsrising.services.mongo.RssItem_Repository;
import com.penguinsrising.util.Time;

@Component
public class RssItemService //extends Amqp_Service
{	
	@Autowired RssItem_Repository repo;
	public RssItem_Repository getRepo() {return repo;}
	
	@Autowired  private Category_Repository categoryRepo;											
	public Category_Repository getCategoryRepo() {return categoryRepo;}			
	
	@Autowired  private Company_Repository companyRepo;											
	public Company_Repository getCompanyRepo() {return companyRepo;}			
				
	CategoryList categoryList;
	CompanyList companyList;
	
	@PostConstruct
	public void init(){ Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString()); }

	public CategoryList getCategoryList(){
		if(categoryList == null){
			List<CategoryList> categoryLists = getCategoryRepo().findAll();
			if(categoryLists.size() > 0){
				categoryList = categoryLists.get(0);
			}
			Statics.Log("Found Categories : " + categoryList.getItems().size());
		}
		return categoryList;
	}
	public CompanyList getCompanyList(){
		if(companyList == null){
			List<CompanyList> companyLists = getCompanyRepo().findAll();
			if(companyLists.size() > 0){
				companyList = companyLists.get(0);
			}
			Statics.Log("Found Companies : " + companyList.getItems().size());
		}
		return companyList;
	}

    // Find All : Sort By Property(ies)
    public List<RssItem> find(int pageNumber, int resultLimit, Direction direction, String... property){
    	// Eg : Page<Person> result = repository.findAll(new PageRequest(1, 2, Direction.ASC, "lastname", "firstname"));
		PageRequest request = new PageRequest(pageNumber, resultLimit, direction, property); 
		return repo.findAll(request).getContent();
	}

	// Find By Title
    public List<RssItem> findById(String id ){		
    	List<RssItem> rssItemList = new ArrayList<RssItem>();
		rssItemList.add(repo.findOne(id));
    	return rssItemList;
    }

    // Find By Title
    public List<RssItem> findByTitle(String term, int pageNumber, int resultLimit){
		PageRequest request = new PageRequest(pageNumber, resultLimit); 
    	return repo.findDistinctByTitleIgnoreCaseLikeOrderByDateDesc(term, request).getContent();
    }
	// Find By Feed
    public List<RssItem> findByFeed(String feed, int pageNumber, int resultLimit){
		PageRequest request = new PageRequest(pageNumber, resultLimit); 
    	return repo.findByFeedOrderByDateDesc(feed, request).getContent();
    }
	// Find By Description
    public List<RssItem> findByDescription(String term, int pageNumber, int resultLimit){
		PageRequest request = new PageRequest(pageNumber, resultLimit); 
    	return repo.findDistinctByDescriptionIgnoreCaseLikeOrderByDateDesc(term, request).getContent();
    }

	// Find By Company
    public List<RssItem> findCompany(String company, int pageNumber, int resultLimit, Direction direction, String... property){
		PageRequest request = new PageRequest(pageNumber, resultLimit, direction, property); 
    	return repo.findDistinctByCompanyIgnoreCaseLikeOrderByDateDesc(company, request).getContent();
	}

    // Find By Category
    public List<RssItem> findCategory(String category, int pageNumber, int resultLimit, Direction direction, String... property){
		PageRequest request = new PageRequest(pageNumber, resultLimit, direction, property); 
    	return repo.findDistinctByCategoryIgnoreCaseLikeOrderByDateDesc(category, request).getContent();
	}

    // Find By Company And Channel
    public List<RssItem> findCompanyAndChannel(String company, String channel, int pageNumber, int resultLimit){
		PageRequest request = new PageRequest(pageNumber, resultLimit); 
    	return repo.findDistinctByCompanyAndChannelIgnoreCaseOrderByDateDesc(company, channel, request).getContent();
	}

    public RssItemList getRssItemId(String id){
		RssItemList rssItemList = new RssItemList();		
		rssItemList.setCategory(id);
		rssItemList.getItems().addAll( findById(id) );
		Statics.Log("Found News : " + rssItemList.getItems().size());
		return rssItemList;
	}
    public RssItemList getRssItemCategoryList(String category, int start, int size){
		RssItemList rssItemList = new RssItemList();		
		rssItemList.setCategory(category);
		rssItemList.getItems().addAll( 
			findCategory(category, start, size, Direction.DESC, "id") 
		);
		//Statics.Log("Found News : " + rssItemList.getItems().size());
		return rssItemList;
	}
    public RssItemList getRssItemCompanyList(String company, int start, int size){
		RssItemList rssItemList = new RssItemList();		
		rssItemList.setCategory(company);
		rssItemList.getItems().addAll( findCompany(company, start, size, Direction.DESC, "id") );
		Statics.Log("Found News : " + rssItemList.getItems().size());
		return rssItemList;
	}
    public RssItemList getRssItemCompanyAndChannelList(String company, String channel, int start, int size){
		RssItemList rssItemList = new RssItemList();		
		rssItemList.setCategory(company);
		rssItemList.getItems().addAll( findCompanyAndChannel(company, channel, start, size) );
		Statics.Log("Found News : " + rssItemList.getItems().size());
		return rssItemList;
	}


	// Save Unique Messages
	public RssItem saveUnique(SyndEntryImpl syndFeed, DataSource dataSource)
	{
		// Check DB for duplicates
		Statics.Log("\nSearching: " + dataSource.getUri() + "\n" );
//		Statics.Log("\nSearching: " + dataSource.getUri() + "\n" + syndFeed.toString());
		List<RssItem> savedItems = findByTitle(syndFeed.getTitle(), 0, 1 );

		if( savedItems.size() == 0)
		{
			// Convert to RSS Item
			RssItem rssItem = convertToCDM(syndFeed, dataSource );
			
			// Store Feed In DB
			if( Statics.saveMessagesEnabled ){
				Statics.Log(" ++++++++++++++++++++++++++++++++ ");
				Statics.Log("Saving : " + rssItem.getTitle() );
				rssItem.setTag("saved");

				// Get returned copy from Mongo, which includes the Id field
				rssItem = repo.save(rssItem);
				Statics.Log("Saved " + rssItem.getId() );
				Statics.Log(" ++++++++++++++++++++++++++++++++ ");
			}
			else{
				// Debug : push feeds w/o saving new
				rssItem.setTag("saved");
			}
			return rssItem;
		}
		else if( savedItems.size() > 0)
		{
			Statics.Log("Duplicate: " + savedItems.get(0).getTitle() ); 
			return savedItems.get(0);	
		}

		return null;
	}

	// Convert
	private RssItem convertToCDM(SyndEntryImpl syndFeed, DataSource dataSource){

		// Tag Content using NLP
		String tag = tag( syndFeed );

		RssItem rssItem = new RssItem();
		rssItem.setCategory(dataSource.getCategory());
		rssItem.setCompany(dataSource.getCompany());
		rssItem.setChannel(dataSource.getChannel());
		rssItem.setTag(tag);

		if(syndFeed.getTitle() != null)
			rssItem.setTitle(syndFeed.getTitle());
	
		if(syndFeed.getDescription() != null)
			rssItem.setDescription(syndFeed.getDescription().getValue());
		else if(syndFeed.getContents().get(0) != null)
			rssItem.setDescription(((SyndContentImpl)syndFeed.getContents().get(0)).getValue());

		if(syndFeed.getLink() != null)
			rssItem.setUri(syndFeed.getLink());
		if(syndFeed.getUri() != null)
			rssItem.setFeed(dataSource.getUri());
		if(syndFeed.getPublishedDate() != null)
			rssItem.setDate(Time.convertXMLGregorianCalendar( syndFeed.getPublishedDate() ));

		return rssItem;
	}

	// Heuristics : "tag content"
	private String tag(SyndEntryImpl syndFeed){

//		String title = syndFeed.getTitle().toLowerCase();
//		String description = syndFeed.getDescription().getValue().toLowerCase();		

//		naturalLanguageProcessing(title);
//		naturalLanguageProcessing(description);
		
//		if(description.contains("img"))			{return "img";}			
//		else if(description.contains("video"))	{return "video";}			
//		else {return "news";}			
		return "";
	}
	
	private void naturalLanguageProcessing(String text) {
		try {
			// TikaParser.parseText(description );	

			// Tokenize, Parts-Of-Speech Tagging, and Structure Chunking
//			String[] tokens = OpenNLP.Tokenize(text);
//			OpenNLP.POSTag(text);
//			OpenNLP.chunk(text);
//	
			// Break Paragraph into Sentence Array
//			String[] sentences = OpenNLP.SentenceDetect(text);

			// Finders
//			OpenNLP.findName(sentences);
//			OpenNLP.findOrganization(sentences);
//			OpenNLP.findLocation(sentences);
//			OpenNLP.findMoney(sentences);
//			OpenNLP.findDate(sentences);
//			OpenNLP.findTime(sentences);
		} 
		catch (Exception e) {Statics.Log(e.getMessage());}
	}
}
