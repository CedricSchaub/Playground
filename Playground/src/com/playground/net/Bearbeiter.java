package com.playground.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Bearbeiter extends Thread {

	private Socket clientSocket;

	public Bearbeiter(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {

		try {

			System.out.println("Neuer Client: " + Thread.currentThread().getId());
			String ausgabe = "";
			do {
				BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				ausgabe = br.readLine();
				System.out.println("Nachricht erhalten (von " + Thread.currentThread().getId() + "): " + ausgabe);

				if (ausgabe.equals("EXIT\n")) {
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
					pw.print("Auf Wiedersehen\n");
					pw.flush();
				} else {
					BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Nachricht senden");
					ausgabe = br2.readLine();
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
					pw.print(ausgabe + "\n");
					pw.flush();
				}
				
			} while (!ausgabe.equals("EXIT\n"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
