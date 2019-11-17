/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Others
 */
public class SoundEffectsPlayer {
    
    private boolean isAudible = true;
    
    public void setAudible(boolean isAudible) {
        this.isAudible = isAudible;
    }
    
    public void playPlayerMove() {
        String audioFile = getClass().getResource("/view/sound/effects/click_player_move.wav").getPath();
        playSound(audioFile);
    }
    
    public void playOptionToggle() {
        String audioFile = getClass().getResource("/view/sound/effects/click_option.wav").getPath();
        playSound(audioFile);
    }
    
    public void playPointHover() {
        String audioFile = getClass().getResource("/view/sound/effects/click_hover_point.wav").getPath();
        playSound(audioFile);
    }
    
    private void playSound(String audioFilePath) {
        if (isAudible) {
            File audioFile = new File(audioFilePath);
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();
            } catch (UnsupportedAudioFileException ex) {
                System.out.println("The specified audio file is not supported.");
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                System.out.println("Audio line for playing back is unavailable.");
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println("Error playing the audio file.");
                ex.printStackTrace();
            }
        }
    }
}
