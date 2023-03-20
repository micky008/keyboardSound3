package com.msc.keyboardsound3.dao;

import com.msc.keyboardsound3.entity.Channel;
import com.msc.keyboardsound3.entity.Sound;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael
 */
public class SoundDAO {

    public static List<Sound> getByChannel(int idChannel) {
        Channel c = ChannelDAO.getById(idChannel);
        if (c == null) {
            return new ArrayList<>(0);
        }
        return c.sounds;
    }

    public static Sound getById(int idChannel, int idSound) {
        for (Sound s : getByChannel(idChannel)) {
            if (s.id == idSound) {
                return s;
            }
        }
        return null;
    }

}
