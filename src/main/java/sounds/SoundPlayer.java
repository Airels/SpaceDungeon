package sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    private final static Map<Sound, Thread> playingAudio = new HashMap<>();

    public static void infinitePlay(Sound sound) {
        play(sound, true);
    }

    public static void play(Sound sound) {
        play(sound, false);
    }

    private static void play(Sound sound, boolean infiniteLoop) {
        Thread soundPlayer = new AudioClip(sound, infiniteLoop);

        soundPlayer.start();

        playingAudio.put(sound, soundPlayer);
    }

    public static void stop(Sound sound) {
        if (playingAudio.containsKey(sound)) {
            playingAudio.get(sound).interrupt();
            playingAudio.remove(sound);
        }
    }
}
