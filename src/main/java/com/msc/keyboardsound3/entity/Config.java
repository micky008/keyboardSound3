package com.msc.keyboardsound3.entity;

import com.msc.keyboardsound3.entity.Channel;
import java.util.List;

/**
 *
 * @author Michael
 */
public class Config {

    public WS ws;
    public List<Channel> channels;

    public static class WS {

        public int port;
    }

}
