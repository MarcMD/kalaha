package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tm.kalaha.server.Daten;

public interface ServerInterface extends Remote {
	
	public void anmelden (RMIClientInterface client) throws RemoteException, KalahaException;
		
	public void abmelden (RMIClientInterface client) throws RemoteException, KalahaException;
		
}
