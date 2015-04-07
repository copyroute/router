package com.copyroute.services.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.copyroute.cdm.rss.ChannelList;

public interface Channel_Repository 	
	extends MongoRepository<ChannelList, String>
{

}
