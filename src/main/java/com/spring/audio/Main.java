package com.spring.audio;

import java.io.IOException;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;

public class Main{
	public static Thread stop;
	public static JavaSoundRecorder recorder = null;
	public static void main(String[] args) throws LineUnavailableException, IOException {
		recorder = new JavaSoundRecorder();
			stop = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(JavaSoundRecorder.RECORD_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					recorder.finish();
				}
				
			});
			
		stop.start();
		recorder.start();
		
		
		Map<String, String> songs = new HashMap<>();
		songs.put("songs/despacito.wav","Luis Fonsi - Despacito ft. Daddy Yankee"); 
		songs.put("songs/duburu.wav","Duburu Lamissi");
		songs.put("songs/Hanthane.wav","Hanthane Kandhu");
		songs.put("songs/kar.wav","Kar Gayi Chull");   
		songs.put("songs/shape.wav","Shape of You"); 
		
		new Compare().match(songs);
		
		if(Compare.resultSong == null) {
			System.out.println("Song Not Found");
		}
		else {
			System.out.println("Song Found");
		}
		
	}
}