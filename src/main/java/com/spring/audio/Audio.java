package com.spring.audio;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class Audio{
	public static void main(String[] args) {
		try {
			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			if(! AudioSystem.isLineSupported(info)) {
				System.out.println("Line not supported");
			}
			else {
				System.out.println("ok");
			}
			final TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(info);
			targetLine.open();
			
			System.out.println("Started Recording ");
			
			targetLine.start();
			Thread thread = new Thread() {
				@Override
				public void run() {
					AudioInputStream audioStream = new AudioInputStream(targetLine);
					File audio = new File("recording19.wav");
					try {
						AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audio);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Stopped Recording");
				}
			};
			thread.start();
			Thread.sleep(11000);
			targetLine.stop();
			targetLine.close();
			System.out.println("Ended Recording");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}