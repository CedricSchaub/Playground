package com.playground.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("EXIT eingeben um die Kommunikation mit dem Server zu beenden.");
			
			Socket socket = new Socket("127.0.0.1", 4242);
			String nachricht = "";
			
			do {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Nachricht eingeben: ");
				nachricht = br.readLine();
				
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				pw.print(nachricht + "\n");
				pw.flush();
			
				BufferedReader br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String antwort = br.readLine();
				System.out.println("Nachricht erhalten: " + antwort);
				
				
			} while(!nachricht.equals("EXIT"));
			
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
