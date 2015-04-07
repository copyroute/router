package com.copyroute.webservices.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.copyroute.cdm.rss.CompanyList;

public interface Company_Repository 	
	extends MongoRepository<CompanyList, String>
{

}

