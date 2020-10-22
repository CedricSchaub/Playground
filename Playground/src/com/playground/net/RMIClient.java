package com.playground.net;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

	public static void main(String[] args) {
		
		try {
			
			Registry registry = LocateRegistry.getRegistry();
			RMIInterface server = (RMIInterface) registry.lookup("RMIBeispiel");
			
			String result = server.nachrichtSenden("message from client");
			System.out.println("Antwort Server: " + result);
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
