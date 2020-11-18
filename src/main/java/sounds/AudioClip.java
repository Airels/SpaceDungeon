package sounds;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioClip extends Thread {
    private Sound sound;
    private boolean infiniteLoop;
    private Clip clip;

    protected AudioClip(Sound sound, boolean infiniteLoop) {
        this.sound = sound;
        this.infiniteLoop = infiniteLoop;
    }

    @Override
    public void run() {
        super.run();

        try {
            clip = AudioSystem.getClip();
            AudioInputStream is = AudioSystem.getAudioInputStream(
                    SoundPlayer.class.getResourceAsStream(sound.getPath())
            );

            clip.open(is);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double gain = 0.25;
            float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);

            clip.start();

            if (infiniteLoop)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();

        clip.stop();
    }
}
