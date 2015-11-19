package com.copyroute.services.news;

import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.rss.CategoryList;
import com.copyroute.cdm.rss.DataSource;
import com.copyroute.cdm.rss.PlayList;
import com.copyroute.cdm.rss.RssItem;
import com.copyroute.services.mongo.Category_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

@WebService(name = "CategoryService", targetNamespace = "http://copyroute.com/services/")
@Component
@Path("/categoryService")
public class CategoryService  {

    @Autowired  private Category_Repository categoryRepo;
    @Autowired  private RssItemService rssItemService;

    private CategoryList categoryList;


    @PostConstruct
    public void init(){
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
        getCategoryList();
    }

    public CategoryList createUniqueCategoryList(PlayList playlist)
    {
        Statics.Log(" === Creating CategoryList === ");
        CategoryList categoryList = new CategoryList();
        for(DataSource ds: playlist.getDataSources()){
            String category = ds.getCategory();

            // If Unique
            if(categoryList.getItems().contains(category) == false){
                categoryList.getItems().add(category);
            }
        }
        Collections.sort(categoryList.getItems());
        categoryRepo.deleteAll();
        categoryRepo.save(categoryList);
        return categoryList;
    }


    @GET
    @Path("/getCategoryList")
    public CategoryList getCategoryList(){
        if(categoryList == null){
            List<CategoryList> categoryLists = categoryRepo.findAll();
            if(categoryLists.size() > 0){
                categoryList = categoryLists.get(0);
            }
            Statics.Log("Found Categories : " + categoryList.getItems().size());
        }
        return categoryList;
    }


    // Find By Category ==================================================
    @GET
    @Path("/findCategory")
    public List<RssItem> findByCategory(
            @QueryParam("category") String category,
            @QueryParam("start") int start,
            @QueryParam("size") int size
    ){
        return rssItemService.findCriteria(start, size, "category", category);
    }


}
