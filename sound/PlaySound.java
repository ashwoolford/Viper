
package sound;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class PlaySound implements Runnable{
    private final File sound;
    public PlaySound(File sound){
        this.sound = sound;
    }
    
    public void PlaySound(){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            
            Thread.sleep(clip.getMicrosecondLength()/1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        PlaySound();
        
    }
    
    
    
}
