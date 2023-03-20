package com.msc.keyboardsound3.helpers;

import com.msc.keyboardsound3.dto.ChannelDTO;
import com.msc.keyboardsound3.dto.SoundDTO;
import com.msc.keyboardsound3.entity.Channel;
import com.msc.keyboardsound3.entity.Sound;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael
 */
public class Converter {

    public static ChannelDTO toChannelDTO(Channel chan) {
        ChannelDTO dto = new ChannelDTO();
        dto.channel = chan.channel;
        dto.id = chan.id;
        return dto;
    }

    public static SoundDTO toSoundDTO(Sound snd) {
        SoundDTO dto = new SoundDTO();
        dto.id = snd.id;
        dto.name = snd.name;
        return dto;
    }

    public static List<SoundDTO> toSoundsDTO(List<Sound> sounds) {
        if (sounds.isEmpty()) {
            return new ArrayList<>(0);
        }
        List l = new ArrayList(sounds.size());
        for (Sound c : sounds) {
            l.add(toSoundDTO(c));
        }
        return l;
    }

    public static List<ChannelDTO> toChannelsDTO(List<Channel> chans) {
        if (chans.isEmpty()) {
            return new ArrayList<>(0);
        }
        List l = new ArrayList(chans.size());
        for (Channel c : chans) {
            l.add(toChannelDTO(c));
        }
        return l;
    }
}
