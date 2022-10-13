import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
    static Clip clip;
    
    public static void playSound(String filepath) {
        try {
            File musicPath = new File(filepath);
            
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void playButtonClick() {
        try {
            File musicPath = new File("click.wav");
            
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
