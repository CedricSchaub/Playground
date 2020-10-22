package com.playground.net;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
	
	public static void main(String[] args) {
		
		try {
			
			RMIInterface server = new RMIKlasse();
			RMIInterface stub = (RMIInterface)UnicastRemoteObject.exportObject((RMIInterface) server, 0);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("RMIBeispiel", stub);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
