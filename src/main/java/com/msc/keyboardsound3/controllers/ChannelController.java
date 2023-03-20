package com.msc.keyboardsound3.controllers;

import com.msc.keyboardsound3.entity.Config;
import com.msc.keyboardsound3.config.ConfigReader;
import com.msc.keyboardsound3.dto.ChannelDTO;
import com.msc.keyboardsound3.helpers.Converter;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Michael
 */
@Path("/channel")
public class ChannelController {

    @GET
    public List<ChannelDTO> getAllChannels() {
        Config conf = ConfigReader.getInstance();
        return Converter.toChannelsDTO(conf.channels);
    }

}
