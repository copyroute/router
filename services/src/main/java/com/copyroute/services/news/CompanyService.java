package com.copyroute.services.news;

import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.rss.CompanyList;
import com.copyroute.cdm.rss.DataSource;
import com.copyroute.cdm.rss.PlayList;
import com.copyroute.cdm.rss.RssItem;
import com.copyroute.services.mongo.Company_Repository;
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
import javax.ws.rs.QueryParam;
import java.util.Collections;
import java.util.List;

/**
 * Created by flatline on 8/22/15.
 */

@WebService(name = "CompanyService", targetNamespace = "http://copyroute.com/services/")
@Component
@Path("/companyService")
public class CompanyService   {

    @Autowired  protected MongoTemplate mongoTemplate;
    @Autowired  private Company_Repository companyRepo;
    @Autowired  private PlayListService playListService;

    CompanyList companyList;

    @PostConstruct
    public void init(){
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
        getCompanyList();
    }

    public CompanyList createUniqueCompanyList(PlayList playlist)
    {
        Statics.Log(" === Creating CompanyList === ");
        CompanyList companyList = new CompanyList();
        for(DataSource ds: playlist.getDataSources()){
            String company = ds.getCompany();

            // If Unique
            if(companyList.getItems().contains(company) == false){
                companyList.getItems().add(company);
            }
        }
        Collections.sort(companyList.getItems());
        companyRepo.deleteAll();
        companyRepo.save(companyList);
        return companyList;
    }


    // List of Companies ==================================================
    @GET
    @Path("/getCompanyList")
    public CompanyList getCompanyList(){
        if(companyList == null){
            List<CompanyList> companyLists = companyRepo.findAll();
            if(companyLists.size() > 0){
                companyList = companyLists.get(0);
            }
            Statics.Log("Found Companies : " + companyList.getItems().size());
        }
        return companyList;
    }

    // Find By Company ==================================================
    @GET
    @Path("/findCompanyList")
    public List<RssItem> findByCompany(
            @QueryParam("company") String company,
            @QueryParam("start") int start,
            @QueryParam("size") int size
    )
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("company").is(company));
        PageRequest request = new PageRequest(start, size,Sort.Direction.DESC, "date");
        query.with(request);
        return mongoTemplate.find(query, RssItem.class, playListService.getCollectionName());
    }


    // Find By Company And Channel ==================================================
    @GET
    @Path("/findCompanyAndChannelList")
    public List<RssItem> findByCompanyAndChannel(
            @QueryParam("company") String company,
            @QueryParam("channel") String channel,
            @QueryParam("start") int start,
            @QueryParam("size") int size
    )
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("company").is(company));
        query.addCriteria(Criteria.where("channel").is(channel));
        PageRequest request = new PageRequest(start, size,Sort.Direction.DESC, "date");
        query.with(request);

        return mongoTemplate.find(query, RssItem.class, playListService.getCollectionName());
    }


}
