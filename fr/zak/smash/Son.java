package fr.zak.smash;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Son implements Runnable{

	private Mixer mixer;
//	private FloatControl sampleRateControl; 
//    private FloatControl panControl;
//    private FloatControl masterGainControl;
	private String fileS;
	
	public Son(String file){
		this.fileS = file;
	}
	
	@Override
	public void run() {
		AudioInputStream audioInputStream = null;
		try{
			//obtention d'un flux audio à partir d'un fichier (objet File)
			audioInputStream = AudioSystem.getAudioInputStream(new File(fileS));

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

//                mixer = AudioSystem.getMixer(AudioSystem.getMixerInfo()[0]);

		
		AudioFormat audioFormat = audioInputStream.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		SourceDataLine line;
		try {
			line = (SourceDataLine) /*mixer*/AudioSystem.getLine(info);

		} catch (LineUnavailableException e) {
			e.printStackTrace();
			return;
		}
//        if (line.isControlSupported(FloatControl.Type.SAMPLE_RATE)) {
//            sampleRateControl = (FloatControl) line
//                    .getControl(FloatControl.Type.SAMPLE_RATE);
//            sampleRateControl.setValue(0.1F);
//        }
//        if (line.isControlSupported(FloatControl.Type.PAN)) {
//            panControl = (FloatControl) line.getControl(FloatControl.Type.PAN);
//            panControl.setValue(0.1F);
//        }
//        if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
//            masterGainControl = (FloatControl) line
//                    .getControl(FloatControl.Type.MASTER_GAIN);
//            masterGainControl.setValue(10F);
//        }
		try {
			line.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			return;
		}
		line.start();
		try {
			byte bytes[] = new byte[1024];
			int bytesRead=0;
			while (((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1)) {
				line.write(bytes, 0, bytesRead);
			}
		} catch (IOException io) {
			io.printStackTrace();
			return;
		}
		line.stop();
		
	}

}
