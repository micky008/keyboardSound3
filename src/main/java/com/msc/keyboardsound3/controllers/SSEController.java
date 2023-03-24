package com.msc.keyboardsound3.controllers;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;

/**
 *
 * @author Michael
 */
@Singleton
@Path("sse")
public class SSEController {

    private SseBroadcaster broadcaster = new SseBroadcaster();

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String reloadAll() {
        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        OutboundEvent event = eventBuilder.name("reloadAll")
                .mediaType(MediaType.TEXT_PLAIN_TYPE)
                .data(String.class, "reload")
                .build();

        broadcaster.broadcast(event);
        return "ok";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{idChan}")
    public String reloadChanOnly(@PathParam("idChan") Integer idChan) {
        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        OutboundEvent event = eventBuilder.name("reloadChan")
                .mediaType(MediaType.TEXT_PLAIN_TYPE)
                .data(String.class, ""+idChan)
                .build();

        broadcaster.broadcast(event);
        return "ok";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{idChan}/{idSound}")
    public String play(@PathParam("idChan") Integer idChan,@PathParam("idSound") Integer idSound) {
        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        OutboundEvent event = eventBuilder.name("play")
                .mediaType(MediaType.TEXT_PLAIN_TYPE)
                .data(String.class, idChan+":"+idSound)
                .build();

        broadcaster.broadcast(event);
        return "ok";
    }

    
    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput listenToBroadcast() {
        final EventOutput eventOutput = new EventOutput();
        this.broadcaster.add(eventOutput);
        return eventOutput;
    }
}
