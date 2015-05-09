package com.copyroute.endpoints;
import com.copyroute.cdm.global.Statics;
import com.copyroute.cdm.rss.PlayList;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;

/**
 * Created by flatline on 4/23/15.
 */

@Path("/playlist/")
@Consumes("application/json")
@Produces("application/json")
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
    @Path("/")
    @Produces( "application/json" )
    public String index() {
        System.out.println("Index");
        return "Index";
    }

    @GET
    @Path("/{id}/")
    @Produces( "application/json" )
    public PlayList getPlaylist(@PathParam("id") String id) {
        PlayList c = playlists.get(id);
        System.out.println("Playlist : " + id + playlists.size() + c.getName());
        return c;
    }


}
