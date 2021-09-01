package com.spring.audio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import com.musicg.fingerprint.FingerprintManager;
import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.fingerprint.FingerprintSimilarityComputer;
import com.musicg.wave.Wave;


public class Compare{
	public static String resultSong = null;
	public float co = 0, result = 0;
	
	public void match(Map<String,String> songs) throws FileNotFoundException {
		InputStream f1 = null,f2 = null;
		String filename = "songs/recording1.wav";
		f1 = new FileInputStream(filename);
		Wave w1 = new Wave(f1);
		
		Iterator<String> itr = songs.keySet().iterator();
		
		while(itr.hasNext()) {
			String temp = itr.next();
			f2 = new FileInputStream(temp);
			Wave w2 = new Wave(f2);
			byte[] firstFingerPrint = new FingerprintManager().extractFingerprint(w1);
		    byte[] secondFingerPrint = new FingerprintManager().extractFingerprint(w2);
		    // Compare fingerprints
		    FingerprintSimilarity fingerprintSimilarity = new FingerprintSimilarityComputer(firstFingerPrint, secondFingerPrint).getFingerprintsSimilarity();
		    System.out.println("Similarity score = " + fingerprintSimilarity.getScore());
			co = fingerprintSimilarity.getScore() * 100;
			if(co >result && co >=10) {
				result = co;
				resultSong = temp;
			}
		}
		System.out.print(songs.get(resultSong));
	}
}