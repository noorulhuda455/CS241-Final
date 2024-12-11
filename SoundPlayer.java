import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    public static void playSound(String filePath) {
        try {
            // Open an audio input stream
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a sound clip resource
            Clip clip = AudioSystem.getClip();

            // Open the audio clip and load the sound
            clip.open(audioStream);
            clip.start(); // Play the sound

            // Allow the clip to play completely
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);

            clip.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}
