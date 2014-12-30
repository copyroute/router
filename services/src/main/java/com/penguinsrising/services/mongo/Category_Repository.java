package com.penguinsrising.services.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.penguinsrising.cdm.rss.CategoryList;

public interface Category_Repository 
	extends MongoRepository<CategoryList, String>//, QueryDslPredicateExecutor<FeedListType>
{

	//public DataListType getDataList(String name) {
		//BasicDBObject dbObject = new BasicDBObject();
		//dbObject.put("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE));
		// return mongoTemplate.findOne( new BasicQuery(dbObject), DataListType.class );
		//return dataListRepo.findAll().get(0);
	//}

}
	
	
