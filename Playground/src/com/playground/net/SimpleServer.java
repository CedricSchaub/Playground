package com.playground.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
;

public class SimpleServer {
	public static void main(String[] args) {

		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(4242);
			
			while(true) {
				Socket clientSocket = serverSocket.accept();
				threadPool.execute(new Bearbeiter(clientSocket));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
