package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {
	
	void sendeNachricht(String msg) throws RemoteException;
	
	public String getName() throws RemoteException;
}
