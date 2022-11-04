import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//Sounds class that manages all app audio...
public class Sounds {
    
    //Takes a filepath parameter and plays that sound...
    public static void playSound(String file) {
        try {
            File musicPath = new File(SunDevilPizza.resourcesPath + file); //Gets sound file
            if (musicPath.exists()) { //If sound file exists...
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
    
    //Plays button click
    public static void playButtonClick() {
        try {
            File musicPath = new File(SunDevilPizza.resourcesPath + "click.wav"); //Gets sound file
            if (musicPath.exists()) { //If sound file exists...
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
