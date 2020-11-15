package sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {
    private final static Map<Sound, Thread> playingAudio = new HashMap<>();

    public static void play(Sound sound) {
        Thread soundPlayer = new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream is = AudioSystem.getAudioInputStream(
                        SoundPlayer.class.getResourceAsStream(sound.getPath())
                );

                clip.open(is);
                clip.start();

                while (!Thread.interrupted()) {
                    // BLOCK
                }

                clip.stop();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
