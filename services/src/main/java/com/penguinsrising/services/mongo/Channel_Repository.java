package com.penguinsrising.services.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.penguinsrising.cdm.rss.ChannelList;

public interface Channel_Repository 	
	extends MongoRepository<ChannelList, String>
{

}
