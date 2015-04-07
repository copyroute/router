package com.copyroute.services.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.copyroute.cdm.rss.PlayList;

public interface PlayList_Repository
	extends MongoRepository<PlayList, String>//, QueryDslPredicateExecutor<FeedListType>
{

	//public DataListType getDataList(String name) {
		//BasicDBObject dbObject = new BasicDBObject();
		//dbObject.put("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE));
		// return mongoTemplate.findOne( new BasicQuery(dbObject), DataListType.class );
		//return dataListRepo.findAll().get(0);
	//}

}
	