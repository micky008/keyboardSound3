package com.msc.keyboardsound3.config;

import com.msc.keyboardsound3.entity.Config;
import com.google.gson.Gson;
import com.msc.keyboardsound3.entity.Channel;
import com.msc.keyboardsound3.entity.Sound;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Michael
 */
public class ConfigReader {

    private static Config instance;

    public static Config getInstance() {
        return instance;
    }

    public static Config initConfig() throws FileNotFoundException {
        File file = new File("config.json");
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        FileReader fr = new FileReader(file);
        Gson gson = new Gson();
        Config config = gson.fromJson(fr, Config.class);
        IOUtils.closeQuietly(fr);
        int i = 1, j = 1;
        for (Channel c : config.channels) {
            c.id = i++;
            for (Sound s : c.sounds) {
                s.id = j++;
            }
        }
        instance = config;
        return config;
    }

}
