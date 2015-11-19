package com.copyroute.services.news;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.copyroute.cdm.rss.CategoryList;
import com.copyroute.cdm.rss.ChannelList;
import com.copyroute.cdm.rss.CompanyList;
import com.copyroute.cdm.rss.DataSource;
import com.copyroute.cdm.rss.PlayList;
import com.copyroute.cdm.global.Statics;
//import com.copyroute.services.amqp.Amqp_Service;
import com.copyroute.services.mongo.Category_Repository;
import com.copyroute.services.mongo.Channel_Repository;
import com.copyroute.services.mongo.Company_Repository;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@WebService(name = "CsvLoaderService", targetNamespace = "http://copyroute.com/services/")
@Path("/csvLoaderService")
@Component
public class CsvLoaderService
{
	// Load CSV file, and split on : Catagory, Company, Description, URI
    @GET
	public PlayList LoadPlaylist(
            @QueryParam("feedListName") String feedListName,
            @QueryParam("filename") String filename
    ){
		PlayList userFeedList = new PlayList();
        userFeedList.setName(feedListName);

		List<DataSource> dsList = new ArrayList() ;
		try {
		    InputStream inputStream = null;
		    try {
				Statics.Log(" === Loading File === ");
			    Resource resource = new ClassPathResource(filename);
			    inputStream = resource.getInputStream();
			    Scanner scanner = new Scanner(inputStream);
			    while (scanner.hasNext()) {
			    	String[] props = scanner.nextLine().split(",");
			    	if(props.length >= 4){
			    		userFeedList.getDataSources().add(
			    				createDataSource(props[0], props[1], props[2], props[3]) );
			    	}
			    }
			    scanner.close();
			    inputStream.close();
		    }
		    finally {if (inputStream != null) {inputStream.close();}}
		} catch (IOException e) {Statics.Log(e.getMessage());}

		return userFeedList;

	}

	//  Catagory, Company, Description, URI
	private DataSource createDataSource(String category, String company, String title, String uri, String... tags){
		DataSource dataSource = new DataSource();
		dataSource.setCategory(category.trim());
		dataSource.setCompany(company.trim());
		dataSource.setChannel(title.trim());
		dataSource.setUri(uri.trim());
		Statics.Log(" === Creating DataSource : " + dataSource.getUri());
        return dataSource;
	}

}
