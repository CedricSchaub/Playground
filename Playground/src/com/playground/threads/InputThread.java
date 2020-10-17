package com.playground.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputThread extends Thread {

	@Override
	public void run() {
		System.out.println("InputThread started.");
		
		try {
			InputStreamReader inputStream = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(inputStream);
			
			String eingabe = br.readLine();
			System.out.println("Eingabe1: " + eingabe);
			
			eingabe = br.readLine();
			System.out.println("Eingabe2: " + eingabe);
			
			eingabe = br.readLine();
			System.out.println("Eingabe3: " + eingabe);
			
			eingabe = br.readLine();
			System.out.println("Eingabe4: " + eingabe);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
