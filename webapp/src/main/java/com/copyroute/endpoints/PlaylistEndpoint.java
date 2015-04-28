package com.copyroute.endpoints;
import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.rss.PlayList;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by flatline on 4/23/15.
 */

@Path("/playlistservice/")
public class PlaylistEndpoint {

    long currentId = 123;
    static Map<String, PlayList> playlists = new HashMap<String, PlayList>();

    public PlaylistEndpoint() {
//        init();
    }

    @PostConstruct
    final void init(){

        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
        Statics.Log("\n\n\n");
        PlayList c = new PlayList();
        c.setName("John");
        c.setId("123");
        playlists.put(c.getId(), c);
    }

    @GET
    @Path("/customers/{id}/")
    @Produces( "application/json" )
    public PlayList getPlaylist(@PathParam("id") String id) {
        System.out.println("PlaylistId is: " + id);
//        long idNumber = Long.parseLong(id);
        PlayList c = playlists.get(id);
        System.out.println("Playlist : " + playlists.size());
        System.out.println("Playlist : " + c.getName());
        return c;
    }


}
