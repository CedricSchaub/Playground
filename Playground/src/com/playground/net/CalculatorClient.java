package com.playground.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculatorClient {
	
	public static void main(String[] args) {
		
		BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			String input = userInputReader.readLine();
			
			// Remove all whitespaces, Tabs, non visible chars ect.
			input = input.replaceAll("\\s+", "");
			
			if(!input.matches("[0-9]+[+-/*]{1}[0-9]+")) {
				System.out.println("Wrong input format");
			}
			
			byte[] payload = input.getBytes();
			byte[] lengthInBytes = NetOperations.intToByteArray(payload.length);
			
			var messageInBytes = new ByteArrayOutputStream();
			messageInBytes.write(lengthInBytes);
			messageInBytes.write(payload);
	
			Socket server = new Socket("127.0.0.1", 4242);
			
			var socketOutputStream = server.getOutputStream();
			socketOutputStream.write(messageInBytes.toByteArray());
			
			BufferedReader serverSocketReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
			String reply = serverSocketReader.readLine();
			
			System.out.println(reply);
			
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("End");
	}
}
