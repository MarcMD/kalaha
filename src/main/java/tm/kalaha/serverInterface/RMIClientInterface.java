package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tm.kalaha.server.Spielbrett;

public interface RMIClientInterface extends Remote {
	
	public void sendeNachricht(String msg) throws RemoteException;
	
	public void spielbrettBekommen(Spielbrett spielbrett) throws RemoteException;
	
	public String getName() throws RemoteException;
}
