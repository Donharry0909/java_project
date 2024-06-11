import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class melody {
    Clip musicClip;
    URL url[] = new URL[2];

    public melody(){
        url[0] = getClass().getResource("/music/theme.wav");
        url[1] = getClass().getResource("/music/clear.mp3");
    }
    public void play(int i, boolean music){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
            Clip clip = AudioSystem.getClip();

            if(music){
                musicClip = clip;
            }

            clip.open(ais);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event){
                    if(event.getType() == LineEvent.Type.STOP){
                        clip.close();
                    }
                }
            });
            ais.close();
            clip.start();
        }catch(Exception e){

        }
    }

    public void loop(){
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        musicClip.stop();
        musicClip.close();
    }
}
