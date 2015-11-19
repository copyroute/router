package com.copyroute.services.news;

import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.rss.RssItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Set;

/**
 * Created by flatline on 8/22/15.
 */
@WebService(name = "AbstractService", targetNamespace = "http://copyroute.com/services/")
@Path("/AbstractService")
@Component
public class AbstractService {

    public final static int MAX_COLLECTION_SIZE = 100000;

    @Autowired  protected MongoTemplate mongoTemplate;

    @PostConstruct
    public void init(){
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
//        mergeCollections();
//        dropCollections(1970, 2020, "rssItem");
//        dropCollections(0, 1000, "rssItem");
//        splitCollection("rssItemsFullList", "rssItem");

        // Map Reduce
//		mapReduceCategories();

    }


    // Alive?! ==================================================
    @GET
    @Path("/alive")
    public String alive() {
        return this.getClass().toString() + " is alive";
    }

    // =================================================
    // Collection Management ===========================
    // =================================================

    public boolean ensureIndex(String sourceCollection, String index){
        mongoTemplate.getCollection(sourceCollection).ensureIndex(index);
        return true;
    }

    public boolean drop(String collectionName){
        mongoTemplate.dropCollection(collectionName);
        return true;
    }
    public int count(String collectionName){
        int collectionCount = (int) mongoTemplate.getCollection(collectionName).count();
        return collectionCount;
    }

    public boolean dropCollections(int counter, int end, String prefix){
        while (counter < end) {
            drop( prefix + counter);
            counter++;
        }
        return true;
    }

    // =================================================
    // CRUD ============================================
    // =================================================

    public boolean insert(List<?> rssItems, String collectionName){
        mongoTemplate.insert(rssItems, collectionName);
        return true;
    }

    public void delete(String id, Class clazz) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), clazz);
    }

    // Merge numbered collections
    public boolean mergeCollections(){
        Statics.Log("CollectionList : ====================================");
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        for(String collectionName : collectionNames) {
            Statics.Log(collectionName);
        }
        Statics.Log("=====================================================");

        // Merge
        String destinationCollection = "rssItemsFullList";
        mongoTemplate.dropCollection(destinationCollection);
        for(String collectionName : collectionNames) {
            if(collectionName.contains("rssItem") && (collectionName.length() == 11)) {
                mergeCollection(collectionName, destinationCollection);
            }
        }
        return true;
    }

    public boolean mergeCollection(String sourceCollection, String destinationCollection)
    {

        Statics.Log("Merging : ====================================");
        Statics.Log(sourceCollection);
        Statics.Log(destinationCollection);

        ensureIndex(sourceCollection, "date");

        int collectionCount = count(sourceCollection);
        int pageNumber = 0;
        int resultLimit = 500;

        // While results exits
        while( pageNumber * resultLimit < collectionCount ) {
            List<RssItem> rssItems = getCollection(pageNumber, resultLimit, sourceCollection);
            insert(rssItems, destinationCollection);
            pageNumber++;
        }
        Statics.Log("==========================================");
        return true;
    }

    public boolean splitCollection(String sourceCollection, String destinationPrefix)
    {
        Statics.Log("Splitting : ====================================");
        Statics.Log(sourceCollection);
        Statics.Log(destinationPrefix);

        ensureIndex(sourceCollection, "date");


        int currentCollectionCount = 0;
        int currentCollectionPostfix = 0;
        String destinationCollection = destinationPrefix + currentCollectionPostfix;

        // While results exits
        int pageNumber = 0;
        int resultLimit = 100;
        int sourceCollectionCount = count(sourceCollection);
        while( pageNumber * resultLimit <  sourceCollectionCount ) {

            // Get next page & increment counter
            List<RssItem> rssItems = getCollection(pageNumber, resultLimit, sourceCollection);
            pageNumber++;

            // If collection if full, move to the next one
            if( currentCollectionCount >= MAX_COLLECTION_SIZE ){
                currentCollectionCount = 0;
                currentCollectionPostfix++;
                destinationCollection = destinationPrefix + currentCollectionPostfix;
                Statics.Log("Creating Collection : " + destinationCollection);
            }

            // Insert RssItem batch
            insert(rssItems, destinationCollection);
            currentCollectionCount += rssItems.size();
        }
        Statics.Log("==========================================");
        return true;
    }

    // Get
    public List<RssItem> getCollection(int pageNumber, int resultLimit, String sourceCollection){
        PageRequest pageRequest = new PageRequest(pageNumber, resultLimit, Sort.Direction.ASC, "date");
        Query query = new Query();
        query.with(pageRequest);
        List<RssItem> rssItems = mongoTemplate.find(query, RssItem.class, sourceCollection);
        return rssItems;
    }

//	public void mapReduceCategories(){
////		Statics.Log(" === Running Map Reduce === ");
////		MapReduceResults<ValueObject> results =
////				mongoTemplate.mapReduce(
////						"rssItem",
////						"classpath:/resources/js/mapReduce/map.js",
////						"classpath:/resources/js/mapReduce/reduce.js",
////						options().outputCollection("aaa_example_out"),
////						ValueObject.class);
////
////		for (ValueObject valueObject : results) {
////		  Statics.Log(valueObject.toString());
////		}
//	}

//	public void aggregate(){
////		Aggregation agg = new Aggregation(
////			    pipelineOP1(),
////			    pipelineOP2(),
////			    pipelineOPn()
////			);
////		AggregationResults<OutputType> results = mongoTemplate.aggregate(agg, "rssItem", OutputType.class);
////		List<OutputType> mappedResult = results.getMappedResults()
//	}


}
