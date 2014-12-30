package com.penguinsrising.services.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.penguinsrising.cdm.rss.CompanyList;

public interface Company_Repository 	
	extends MongoRepository<CompanyList, String>
{

}

