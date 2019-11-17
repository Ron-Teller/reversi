package sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffectsPlayer implements ISound {

	private boolean isEnabled = true;
	private String soundEffectsDir = "/sound/effects/";

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public void playPlayerMove() {
		String audioFile = soundEffectsDir + "click_player_move.wav";
		playSound(audioFile);
	}

	@Override
	public void playOptionToggle() {
		String audioFile = soundEffectsDir + "click_option.wav";
		playSound(audioFile);
	}

	@Override
	public void playMessageNotification() {
		String audioFile = soundEffectsDir + "message_notification.wav";
		playSound(audioFile);
	}

	@Override
	public void playHoverOnOccupiablePoint() {
		String audioFile = soundEffectsDir + "click_option.wav";
		playSound(audioFile);
	}

	private void playSound(String path) {
		if (isEnabled) {
			try {
				AudioInputStream audioStream = AudioSystem
						.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(path)));
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
				System.out.println("Error playing the audio file: "+path);
				ex.printStackTrace();
			}
		}
	}

}
