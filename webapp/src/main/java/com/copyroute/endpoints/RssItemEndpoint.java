package com.copyroute.endpoints;

import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.rss.PlayList;
import com.copyroute.cdm.rss.RssItem;
import com.copyroute.cdm.rss.RssItemList;
import com.copyroute.services.news.RssItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by flatline on 4/23/15.
 */

@Path("/rssitemlist/")
@Produces({"application/xml","application/json"})
@Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
public class RssItemEndpoint {

    @Qualifier("rssItemService")
    @Inject
    private RssItemService rssItemService;

    List<String> categoryList;

    public RssItemEndpoint() {
//        init();
    }

    @PostConstruct
    final void init(){
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
        categoryList = rssItemService.getCategoryList().getItems();
    }

    @GET
    @Path("/")
    @Produces( "application/json" )
    public String index() {
        System.out.println("Index");
        return "Index";
    }

    @GET
    @Path("/{id}/")
    @Produces( "application/json" )
    public RssItemList getPlaylist(@PathParam("id") String id) {
        RssItemList rssItemList = new RssItemList();
        RssItem c = new RssItem();
        c.setTitle(id);

        rssItemList.getItems().add(c);
        return rssItemList;
    }


    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView getMultiSearch(
            @RequestParam(value = "start", defaultValue = "0", required = false ) int start,
            @RequestParam(value = "size", defaultValue = "10", required = false ) int size,
            @RequestParam(value = "term", defaultValue = "", required = false ) String term,
            HttpServletRequest request)
    {
        List<RssItem> rssLists = rssItemService.find(start, size);

        ModelAndView mav = new ModelAndView();
        mav.addObject("CategoryList", categoryList);
        mav.addObject("Category", "" );
        mav = Statics.setPageData(request, mav, "Real Time Global News Source", "index", start, size, term);
        mav.addObject("RssItems", rssLists);

        mav.setViewName("rssViewerMulti");
        return mav;
    }

}
