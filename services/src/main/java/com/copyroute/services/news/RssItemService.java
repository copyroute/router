package com.copyroute.services.news;

import com.copyroute.cdm.rss.*;
import com.copyroute.cdm.global.Statics;
import com.copyroute.services.mongo.Category_Repository;
import com.copyroute.services.mongo.Company_Repository;
import com.copyroute.services.mongo.RssItem_Repository;
import com.copyroute.cdm.util.Time;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.copyroute.services.tika.TikaParser;

@Component
public class    RssItemService //extends Amqp_Service
{
    @Autowired  private MongoTemplate mongoTemplate;
    public MongoTemplate getMongoTemplate() {return mongoTemplate;}


    @Autowired RssItem_Repository repo;
	public RssItem_Repository getItemRepo() {return repo;}

    @Autowired  private Category_Repository categoryRepo;
	public Category_Repository getCategoryRepo() {return categoryRepo;}

	@Autowired  private Company_Repository companyRepo;
	public Company_Repository getCompanyRepo() {return companyRepo;}

	CategoryList categoryList;
	CompanyList companyList;

    public enum CollectionName {
        rssItem2015,
        rssItem2014,
        rssItem2013
    }

    @PostConstruct
	public void init(){
		Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
	}

    private List<RssItem> find(PageRequest pageRequest, Query query, Class clazz)
    {
        CollectionName collectionName = CollectionName.rssItem2015;
        List<RssItem> resultList = new ArrayList<RssItem>();

        // Search collections in reverse-time order
        while(resultList.size() < pageRequest.getPageSize()){

            // Backup Query before adding pageRequest
            Query queryCopy = query;
            queryCopy.with(pageRequest);

            // Save results
            resultList.addAll( mongoTemplate.find( queryCopy, RssItem.class, collectionName.toString() ) );

            // Modify PageRequest before next loop
            // Rewind PageCount by the number of pages in the previous collection
            if(resultList.size() < pageRequest.getPageSize()) {
                long collectionCount = mongoTemplate.count(query, collectionName.toString());
                long pageCount = collectionCount / pageRequest.getPageSize();
                for (int i = 0; i < pageCount; i++)
                    if (pageRequest.hasPrevious())
                        pageRequest.previous();

                // Increment collectionName
                collectionName = CollectionName.values()[collectionName.ordinal() + 1];
            }
        }
        return resultList;
    }

    // Find All : Sort By DateMonth
    public List<RssItem> find(int pageNumber, int resultLimit){
		PageRequest request = new PageRequest(pageNumber, resultLimit, Direction.DESC, "date");
        Query query = new Query();
        return find(request, query, RssItem.class);

        //query.with(request);
        //List<RssItem> userTest5 = mongoTemplate.find(query, RssItem.class, "rssItem2015");
        //return userTest5;
        //query.addCriteria(Criteria.where("age").gte(30));
        //query.with(new Sort(Sort.Direction.DESC, "date"));
        //query.skip(pageNumber * resultLimit);
        //query.limit(resultLimit);
        //mongoTemplate.findAll(RssItem.class, "rssItem2015");
        //return repo.findAll(request).getContent();
	}

    // Find All : Sort By Property(ies)
    public List<RssItem> find(int pageNumber, int resultLimit, Direction direction, String... property){
    	// Eg : Page<Person> result = repository.findAll(new PageRequest(1, 2, Direction.ASC, "lastname", "firstname"));
		PageRequest request = new PageRequest(pageNumber, resultLimit, direction, property);
        Query query = new Query();
        return find(request, query, RssItem.class);

//        query.with(request);
//        List<RssItem> userTest5 = mongoTemplate.find(query, RssItem.class, "rssItem2015");
//        return userTest5;
//		return repo.findAll(request).getContent();
	}


    // Find By Title
    public List<RssItem> findById(String id ){		
    	List<RssItem> rssItemList = new ArrayList<RssItem>();
		rssItemList.add(repo.findOne(id));
    	return rssItemList;
    }

    // Find By Category
    public List<RssItem> findCategory(String category, int pageNumber, int resultLimit, Direction direction, String... property){
		PageRequest request = new PageRequest(pageNumber, resultLimit, direction, property);
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
//        query.with(request);
        query.with(new Sort(Sort.Direction.DESC, "date"));
//        return mongoTemplate.find(query, RssItem.class, "rssItem2015");
//    	return repo.findDistinctByCategoryIgnoreCaseLikeOrderByDateDesc(category, request).getContent();
        return find(request, query, RssItem.class);
	}

    // Find By Company
    public List<RssItem> findCompany(String company, int pageNumber, int resultLimit, Direction direction, String... property){
        PageRequest request = new PageRequest(pageNumber, resultLimit, direction, property);
        Query query = new Query();
        query.addCriteria(Criteria.where("company").is(company));
        query.with(request);
        query.with(new Sort(Sort.Direction.DESC, "date"));
        return mongoTemplate.find(query, RssItem.class, "rssItem2015");
//        return repo.findDistinctByCompanyIgnoreCaseLikeOrderByDateDesc(company, request).getContent();
    }

    // Find By Company And Channel
    public List<RssItem> findCompanyAndChannel(String company, String channel, int pageNumber, int resultLimit){
		PageRequest request = new PageRequest(pageNumber, resultLimit);
        Query query = new Query();
        query.addCriteria(Criteria.where("company").is(company));
        query.addCriteria(Criteria.where("channel").is(channel));
        query.with(request);
        query.with(new Sort(Sort.Direction.DESC, "date"));
        return mongoTemplate.find(query, RssItem.class, "rssItem2015");
//    	return repo.findDistinctByCompanyAndChannelIgnoreCaseOrderByDateDesc(company, channel, request).getContent();
	}

    // Find By Title
    public List<RssItem> findByTitle(String term, int pageNumber, int resultLimit){
		PageRequest request = new PageRequest(pageNumber, resultLimit);
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(Criteria.where("title").regex(term));
        query.with(request);
        query.with(new Sort(Sort.Direction.DESC, "date"));
        return mongoTemplate.find(query, RssItem.class, "rssItem2015");
//    	return repo.findDistinctByTitleIgnoreCaseLikeOrderByDateDesc(term, request).getContent();
    }

    public List<RssItem> getRssItemId(String id){
		return findById(id);
	}
    public List<RssItem> getRssItemCategoryList(String category, int start, int size){
		return findCategory(category, start, size, Direction.DESC, "id") ;
	}
    public List<RssItem> getRssItemCompanyList(String company, int start, int size){
		return findCompany(company, start, size, Direction.DESC, "id") ;
	}
    public List<RssItem> getRssItemCompanyAndChannelList(String company, String channel, int start, int size){
		return findCompanyAndChannel(company, channel, start, size) ;
	}


    // List of Categories
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

	// List of Companies
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


    public void queryDSL(){

//		JPAQuery mQuery = new JPAQuery (em);
//		QManifestEntity qManifestEntity = QManifestEntity.manifestEntity;
//		List<ManifestEntity> mEntity = mQuery.from(qManifestEntity).list(qManifestEntity);
//
//		JPAQuery roQuery = new JPAQuery (em);
//		QRegionalOfficeEntity qRegionalOfficeEntity = QRegionalOfficeEntity.regionalOfficeEntity;
//		RegionalOfficeEntity roEntity = roQuery.from(qRegionalOfficeEntity)
//				.where(qRegionalOfficeEntity.stationnumber.equalsIgnoreCase(stationNumber))
//				.uniqueResult(qRegionalOfficeEntity);
//
//		JPAQuery intakeQuery = new JPAQuery (em);
//		QIntakeSiteEntity qIntakeSiteEntity = QIntakeSiteEntity.intakeSiteEntity;
//		IntakeSiteEntity intakeEntity = intakeQuery.from(qIntakeSiteEntity)
//				.where(qIntakeSiteEntity.intakeSiteId.eq(intakeSiteId))
//				.uniqueResult(qIntakeSiteEntity);

    }

    // Save Unique Messages
	public RssItem saveUnique(SyndEntry syndFeed, DataSource dataSource)
	{
		// Check DB for duplicates
//		Statics.Log("\nSearching: " + dataSource.getUri() + "\n" );
//		List<RssItem> savedItems = findByTitle(syndFeed.getTitle(), 0, 1 );

//		if( savedItems.size() == 0)
//		{
			// Convert to RSS Item
			
			// Store Feed In DB
			if( Statics.saveMessagesEnabled ){

				try{
					Statics.Log(" ++++++++++++++++++++++++++++++++ ");
					RssItem rssItem = convertToCDM(syndFeed, dataSource );
					Statics.Log("Saving : " + rssItem.getTitle() );
					rssItem.setTag("saved");

					// Get returned copy from Mongo, which includes the Id field
					rssItem = repo.save(rssItem);
					Statics.Log("Saved " + rssItem.getFeed() );
					Statics.Log(" ++++++++++++++++++++++++++++++++ ");
					return rssItem;
				}
				catch(Exception ex){Statics.Log("Duplicate Message");}
            }
//		}
//		else if( savedItems.size() > 0)
//		{
//			Statics.Log("Duplicate: " + savedItems.get(0).getTitle() ); 
//			return savedItems.get(0);	
//		}
		return null;
	}

	// Convert
	private RssItem convertToCDM(SyndEntry syndFeed, DataSource dataSource){

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
	private String tag(SyndEntry syndFeed){

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




//    // Find By Title
//    public List<RssItem> findByTitle(String term, int pageNumber, int resultLimit){
//		PageRequest request = new PageRequest(pageNumber, resultLimit);
//    	return repo.findDistinctByTitleIgnoreCaseLikeOrderByDateDesc(term, request).getContent();
//    }
//	// Find By Feed
//    public List<RssItem> findByFeed(String feed, int pageNumber, int resultLimit){
//		PageRequest request = new PageRequest(pageNumber, resultLimit);
//    	return repo.findByFeedOrderByDateDesc(feed, request).getContent();
//    }

//    public List<RssItem> findAllOrderByDate(int pageNumber, int resultLimit){
//        PageRequest request = new PageRequest(pageNumber, resultLimit);
//        return repo.findAllOrderByDate(request).getContent();
//    }
//	// Find By Description
//    public List<RssItem> findByDescription(String term, int pageNumber, int resultLimit){
//		PageRequest request = new PageRequest(pageNumber, resultLimit);
//    	return repo.findDistinctByDescriptionIgnoreCaseLikeOrderByDateDesc(term, request).getContent();
//    }


//    public RssItemList getRssItemId(String id){
//		RssItemList rssItemList = new RssItemList();
//		rssItemList.setCategory(id);
//		rssItemList.getItems().addAll( findById(id) );
//		Statics.Log("Found News : " + rssItemList.getItems().size());
//		return rssItemList;
//	}
//    public RssItemList getRssItemCategoryList(String category, int start, int size){
//		RssItemList rssItemList = new RssItemList();
//		rssItemList.setCategory(category);
//		rssItemList.getItems().addAll(
//			findCategory(category, start, size, Direction.DESC, "id")
//		);
//		//Statics.Log("Found News : " + rssItemList.getItems().size());
//		return rssItemList;
//	}
//    public RssItemList getRssItemCompanyList(String company, int start, int size){
//		RssItemList rssItemList = new RssItemList();
//		rssItemList.setCategory(company);
//		rssItemList.getItems().addAll( findCompany(company, start, size, Direction.DESC, "id") );
//		Statics.Log("Found News : " + rssItemList.getItems().size());
//		return rssItemList;
//	}
//    public RssItemList getRssItemCompanyAndChannelList(String company, String channel, int start, int size){
//		RssItemList rssItemList = new RssItemList();
//		rssItemList.setCategory(company);
//		rssItemList.getItems().addAll( findCompanyAndChannel(company, channel, start, size) );
//		Statics.Log("Found News : " + rssItemList.getItems().size());
//		return rssItemList;
//	}
