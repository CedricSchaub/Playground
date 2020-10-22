package com.playground.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Calculate extends Thread {

	Socket clientSocket = null;
	
	public Calculate(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public static int getResult(String expression) {
		
		String[] data = new String[3];
		int index = -1;
		
		for(int i = 0; i < expression.length(); i++) {
			
			if( expression.charAt(i) == '+' || 
				expression.charAt(i) == '-' || 
				expression.charAt(i) == '/' || 
				expression.charAt(i) == '*') {
				index = i;
				data[0] = expression.substring(0, i);
				data[1] = expression.substring(i, i+1);			
				data[2] = expression.substring(i + 1);
			}
		}
		
		if(index == -1) {
			return 0;
		}
		
		int number1 = Integer.parseInt(data[0]);
		int number2 = Integer.parseInt(data[2]);
		char operator = data[1].charAt(0);
		
		switch(operator) {
		
			case '+': return number1 + number2;
			case '-': return number1 - number2;
			case '/': return number1 / number2;
			case '*': return number1 * number2;
		}
		
		return 0;
	}
	
	@Override
	public void run() {
		System.out.println("New Calculation Requested from Client: " + clientSocket.getLocalAddress());
		
		try {
			
			var inputStream = clientSocket.getInputStream();
			
			byte[] lengthBuffer = new byte[4];
			int bytesRead = inputStream.read(lengthBuffer, 0, 4);
			
			int length = NetOperations.byteArrayToInt(lengthBuffer);
			System.out.println("Length of message package: " + length);
			
			byte[] messageBuffer = inputStream.readNBytes(length);
			String message = new String(messageBuffer);
			
			System.out.println("Message: " + message);
			
			var outputStream = new PrintWriter(clientSocket.getOutputStream());
			
			int result = getResult(message);
			System.out.println("Result: " + result);
			
			outputStream.print(result + "\n");
			outputStream.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if(clientSocket != null) {
				try {
					this.clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
