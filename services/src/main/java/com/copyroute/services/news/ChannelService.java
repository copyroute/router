package com.copyroute.services.news;

import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.rss.ChannelList;
import com.copyroute.cdm.rss.DataSource;
import com.copyroute.cdm.rss.PlayList;
import com.copyroute.services.mongo.Channel_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.ws.rs.Path;
import java.util.Collections;

/**
 * Created by flatline on 8/22/15.
 */

@WebService(name = "ChannelService", targetNamespace = "http://copyroute.com/services/")
@Path("/channelService")
@Component
public class ChannelService  {

    @Autowired
    private Channel_Repository channelRepo;

    @PostConstruct
    public void init(){
        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());

    }

    public ChannelList createUniqueChannelList(PlayList playlist)
    {
        Statics.Log(" === Creating ChannelList === ");
        ChannelList channelList = new ChannelList();
        for(DataSource ds: playlist.getDataSources()){
            String channel = ds.getChannel();

            // If Unique
            if(channelList.getItems().contains(channel) == false){
                channelList.getItems().add(channel);
            }
        }
        Collections.sort(channelList.getItems());
        channelRepo.deleteAll();
        channelRepo.save(channelList);
        return channelList;
    }

}
