package com.msc.keyboardsound3.controllers;

import com.msc.keyboardsound3.entity.Config;
import com.msc.keyboardsound3.config.ConfigReader;
import com.msc.keyboardsound3.dto.ChannelDTO;
import com.msc.keyboardsound3.entity.Channel;
import com.msc.keyboardsound3.helpers.Converter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Michael
 */
@Path("/channel")
public class ChannelController {

    @GET
    public Map<String, ChannelDTO> getAllChannels() {
        Config conf = ConfigReader.getInstance();
        Map map = new LinkedHashMap();
        for (Channel c : conf.channels){
            map.put(c.id, Converter.toChannelDTO(c));
        }
        return map;
    }

}
