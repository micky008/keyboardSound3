package com.msc.keyboardsound3.dao;

import com.msc.keyboardsound3.config.ConfigReader;
import com.msc.keyboardsound3.entity.Channel;
import com.msc.keyboardsound3.entity.Sound;

/**
 *
 * @author Michael
 */
public class SoundDAO {

    public static Sound getById(String idSound) {
        for (Channel c : ConfigReader.getInstance().channels) {
            for (Sound s : c.sounds) {
                if (s.id.equals(idSound)) {
                    return s;
                }
            }
        }
        return null;
    }

}
