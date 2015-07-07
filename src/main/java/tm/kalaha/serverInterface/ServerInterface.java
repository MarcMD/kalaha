package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	
	public void anmelden (RMIClientInterface client) throws RemoteException, ChatException;
	
	public void sendeNachricht(RMIClientInterface client, String nachricht)  throws RemoteException, ChatException;
	
	public void abmelden (RMIClientInterface client) throws RemoteException, ChatException;
	
}
