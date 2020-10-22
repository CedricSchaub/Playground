package com.playground.net;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
	String nachrichtSenden(String clientMessage) throws RemoteException;
}
