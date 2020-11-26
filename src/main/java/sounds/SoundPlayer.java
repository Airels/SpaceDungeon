package sounds;

import exceptions.MissingSoundException;

import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    private final static Map<Sound, AudioClip> soundsPlayed = new HashMap<>();

    public static void infinitePlay(Sound sound) {
        play(sound, true);
    }

    public static void play(Sound sound) {
        play(sound, false);
    }

    private static void play(Sound sound, boolean infiniteLoop) {
        if (sound.getPath() == null) {
            throw new MissingSoundException(sound.getPath());
        }

        AudioClip soundPlayer = new AudioClip(sound, infiniteLoop);

        soundPlayer.start();

        soundsPlayed.put(sound, soundPlayer);
    }

    public static void stop(Sound sound) {
        if (soundsPlayed.containsKey(sound)) {
            soundsPlayed.get(sound).interrupt();
            soundsPlayed.remove(sound);
        }
    }
}
