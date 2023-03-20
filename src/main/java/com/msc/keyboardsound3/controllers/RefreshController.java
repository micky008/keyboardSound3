package com.msc.keyboardsound3.controllers;

import com.msc.keyboardsound3.config.ConfigReader;
import java.io.FileNotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michael
 */
@Path("/refresh")
public class RefreshController {
    
    @GET
    public Response refresh() {
        try {
            ConfigReader.initConfig();
        } catch (FileNotFoundException ex) {
            System.out.println("refresh error to loading cinfug file");
            System.out.println(ex.getMessage());
            return Response.status(500).build();
        }
        return Response.noContent().build();
    }
    
}
