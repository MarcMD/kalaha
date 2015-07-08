package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tm.kalaha.server.Spielbrett;

public interface RMIClientInterface extends Remote {
		
	public void spielbrettBekommen(Spielbrett spielbrett) throws RemoteException;
	
	public String getSpielerName() throws RemoteException;
}
