package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.*;

public interface DatenInterface extends Remote {
	
	public void verdoppleI() throws RemoteException;
	
	public String toString();

}
