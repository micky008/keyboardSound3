package com.msc.keyboardsound3;

import com.msc.keyboardsound3.entity.Config;
import com.msc.keyboardsound3.config.ConfigReader;
import java.io.FileNotFoundException;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.gson.JsonGsonFeature;
import org.glassfish.jersey.media.sse.SseFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Michael
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Config conf = ConfigReader.initConfig();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(conf.ws.port).build();
        ResourceConfig config = new ResourceConfig();
        config.packages("com.msc.keyboardsound3.controllers");
        config.packages("com.msc.keyboardsound3.providers");
        config.register(SseFeature.class);
        config.register(JsonGsonFeature.class);
        System.out.println("Server started on " + conf.ws.port);
        GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

    }

}
