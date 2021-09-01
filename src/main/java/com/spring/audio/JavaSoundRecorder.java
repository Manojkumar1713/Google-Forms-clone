package com.spring.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.LineUnavailableException;

public class JavaSoundRecorder{
	static final long RECORD_TIME = 12000; //at most 0.2 minutes
	
	TargetDataLine dataLine;
	
	AudioFormat getAudioFormat() {
		AudioFormat format= new AudioFormat(16000,8,2,true,true);
		return format;
	}
	
	protected void start() throws LineUnavailableException, IOException {
		AudioFormat format = getAudioFormat();
		DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
		if(!AudioSystem.isLineSupported(info)) {
			System.out.println("Line not supported");
			System.exit(0);
		}
		
		dataLine = (TargetDataLine) AudioSystem.getLine(info);
		dataLine.open(format);
		dataLine.start();
		
		System.out.println("Started capturing");
		
		File audio = new File("songs/recording1.wav");
		AudioInputStream audioStream = new AudioInputStream(dataLine);
		AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audio);
		
	}
	
	protected void finish() {
		dataLine.stop();
		dataLine.close();
		System.out.println("Stopped Recording");
	}
	
}