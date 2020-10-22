package com.playground.net;

import java.rmi.RemoteException;

public class RMIKlasse implements RMIInterface {

	@Override
	public String nachrichtSenden(String clientMessage) throws RemoteException {
		// TODO Auto-generated method stub
		return "Die empfangene Nachricht: " + clientMessage;
	}
	

}
