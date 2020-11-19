package sounds;

import exceptions.MissingSoundException;
import sounds.sound.SGame;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioClip extends Thread {
    private Clip clip;

    protected AudioClip(Sound sound, boolean infiniteLoop) {
        try {
            this.clip = AudioSystem.getClip();

            AudioInputStream is = AudioSystem.getAudioInputStream(
                    SoundPlayer.class.getResourceAsStream(sound.getPath())
            );

            clip.open(is);

            if (sound == SGame.MUSIC_1 || sound == SGame.MUSIC_2) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                double gain = 0.25;
                float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
            }

            if (infiniteLoop)
                clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (NullPointerException e) {
            throw new MissingSoundException(sound.getPath());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        clip.start();
    }

    @Override
    public void interrupt() {
        super.interrupt();

        clip.flush();
        clip.stop();
    }
}
