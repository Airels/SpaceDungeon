package sounds;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundPlayer {
    public static void play(Sound sound) {
        Thread soundPlayer = new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream is = AudioSystem.getAudioInputStream(
                        SoundPlayer.class.getResourceAsStream(sound.getPath())
                );

                clip.open(is);
                clip.start();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        soundPlayer.start();
    }
}
