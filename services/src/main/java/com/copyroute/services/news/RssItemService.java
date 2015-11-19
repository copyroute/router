package com.copyroute.services.news;

import com.copyroute.cdm.rss.*;
import com.copyroute.cdm.global.Statics;
import com.copyroute.services.mongo.Category_Repository;
import com.copyroute.services.mongo.Company_Repository;
import com.copyroute.services.mongo.RssItem_Repository;
import com.copyroute.cdm.util.Time;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

//import com.copyroute.services.tika.TikaParser;

@WebService(name = "RssItemService", targetNamespace = "http://copyroute.com/services/")
@Path("/rssItemService")
@Component
public class RssItemService
{
    @Autowired  protected MongoTemplate mongoTemplate;
    @Autowired  private PlayListService playListService;


    @PostConstruct
    public void init(){
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());

    }

    @GET
    @Path("/find")
    //@Path("/find/{start}/{size}")
    public List<RssItem> find(
            @QueryParam("start") int start,
            @QueryParam("size") int size
    ) {
        return findCriteria(start, size, "","");
    }

    @GET
    @Path("/findById")
    public List<RssItem> findById(
            @QueryParam("id") String id
    ){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        List<RssItem> rssItems = mongoTemplate.find(query, RssItem.class, playListService.getCollectionName());

        List<RssItem> rssItemList = new ArrayList<RssItem>();
        rssItemList.addAll(rssItems);
        return rssItemList;
    }

    // Find By Title
    @GET
    @Path("/findByTitle")
    public List<RssItem> findByTitle(
            @QueryParam("term") String searchTerm,
            @QueryParam("start") int start,
            @QueryParam("size") int size
    ){
        PageRequest request = new PageRequest(start, size);
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(Criteria.where("title").regex(searchTerm));
        query.with(new Sort(Sort.Direction.DESC, "date"));
        return mongoTemplate.find(query, RssItem.class, playListService.getCollectionName());
    }

    @GET
    @Path("/findCriteria")
    //@Path("/find/{start}/{size}")
    public List<RssItem> findCriteria(
            @QueryParam("start") int start,
            @QueryParam("size") int size,
            @QueryParam("criteria") String criteria,
            @QueryParam("criteriaValue") String criteriaValue
    ){
        if(size < 1) size=25;
        int currentRssItemIndex = start * size;

        // Collection names start with 1---
        int currentCollectionIndex =
                (int) (playListService.getLastCollectionIndex()
                    - Math.floor(  currentRssItemIndex/ AbstractService.MAX_COLLECTION_SIZE )
                    + 1000 );

        String currentCollectionPrefix = "rssItem";
        String collectionName =  currentCollectionPrefix + currentCollectionIndex;

        PageRequest request = new PageRequest(start, size, Direction.DESC, "date");
        Query query = new Query();
        if(criteria.isEmpty() == false)
            query.addCriteria(Criteria.where(criteria).is(criteriaValue));

        //query.addCriteria(Criteria.where("age").gte(30));
        //query.with(new Sort(Sort.Direction.DESC, "date"));
        //query.skip(pageNumber * resultLimit);
        //query.limit(resultLimit);

        // Search collections in reverse-time order
        List<RssItem> resultList = new ArrayList<RssItem>();
        while(resultList.size() < size){

            // Backup Query before adding pageRequest
            Query queryCopy = query;
            queryCopy.with(request);

            // Search results
            List<RssItem> rssItems = mongoTemplate.find(queryCopy, RssItem.class, collectionName);
            resultList.addAll( rssItems );

            // If we run out of entries in this Collection
            if(resultList.size() < size)
            {
                currentCollectionIndex++;
                collectionName =  currentCollectionPrefix + currentCollectionIndex;
            }

        }
        return resultList;
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


