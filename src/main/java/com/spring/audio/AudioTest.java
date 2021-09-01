package com.spring.audio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.TargetDataLine;

import org.apache.commons.math3.complex.Complex;




public class AudioTest {

	public static void main(String[] args) {
		try {
			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
			DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
			TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);

			line.open(format);
			line.start();
			System.out.println("Started");
			byte[] buffer = new byte[8192];
			OutputStream out = new ByteArrayOutputStream();
			Thread thread = new Thread() {
				@Override
				public void run() {
					AudioInputStream audioStream = new AudioInputStream(line);
					File audio = new File("recording1.wav");
					int count = line.read(buffer, 0, buffer.length);
					if (count > 0) {
						try {
							AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audio);
							out.write(buffer, 0, count);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					System.out.println("Stopped Recording");
				}
			};	
			thread.start();
			Thread.sleep(12000);
			System.out.println(Arrays.toString(buffer));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}	

}
