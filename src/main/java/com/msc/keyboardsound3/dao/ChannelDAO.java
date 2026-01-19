package com.msc.keyboardsound3.dao;

import com.msc.keyboardsound3.config.ConfigReader;
import com.msc.keyboardsound3.entity.Channel;

/**
 *
 * @author Michael
 */
public class ChannelDAO {

    public static Channel getById(String id) {
        for (Channel c : ConfigReader.getInstance().channels) {
            if (c.id.equals(id) ) {
                return c;
            }
        }
        return null;
    }

}
