package com.copyroute.services.finance.accounts;

import com.copyroute.cdm.finance.Expense;
import com.copyroute.services.news.AbstractService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flatline on 8/22/15.
 */
public class ExpenseService extends AbstractService {

    // Find All : Sort By DateMonth  ==================================================
    @GET
    @Path("/find")
    //@Path("/find/{start}/{size}")
    public List<Expense> find(
            @QueryParam("start") int start,
            @QueryParam("size") int size
    ) {
        if (size < 1) size = 25;
        PageRequest request = new PageRequest(start, size, Sort.Direction.DESC, "date");
        Query query = new Query();
        return find(request, query, Expense.class);
    }

    public List<Expense> find(PageRequest pageRequest, Query query, Class clazz)
    {
        // Search collections in reverse-time order
        List<Expense> resultList = new ArrayList<>();
        while(resultList.size() < pageRequest.getPageSize()){

            // Backup Query before adding pageRequest
            Query queryCopy = query;
            queryCopy.with(pageRequest);

            // Search results
            List<Expense> expenses = mongoTemplate.find(queryCopy, clazz);
            resultList.addAll( expenses );

        }
        return resultList;
    }

    @POST
    @Path("/save")
    public Expense save(Expense expense){
        mongoTemplate.save(expense);
        return expense;
    }
}
