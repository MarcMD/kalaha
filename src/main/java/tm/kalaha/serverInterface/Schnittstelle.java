package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Schnittstelle extends Remote {
	//Methode des Servers, die remote ausgefürhrt werden kann
	void setString(String str) throws RemoteException;
	
	String verdoppeln(String str) throws RemoteException;
	
	int verdoppeln(int i) throws RemoteException;
}
