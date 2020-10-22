package com.playground.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculatorServer {
	
	
	
	public static void main(String[] args) {
		
		final int PORT = 4242;	
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		try {
			System.out.println("CalculatorServer is listening on port " + PORT);
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while(true) {
			
				
				Socket clientSocket = serverSocket.accept();
			
				executor.execute(new Calculate(clientSocket));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
