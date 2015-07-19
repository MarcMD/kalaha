package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	
	public void anmelden(RMIClientInterface client) throws RemoteException, KalahaException;
		
	public void abmelden(RMIClientInterface client) throws RemoteException, KalahaException;
	
	public void muldeSpielen(String spielerName, int muldenNummer) throws RemoteException, KalahaException;
	
	public void neuesSpielStarten(String spielerName) throws RemoteException;
	
	public void chatNachrichtVonClientEmpfangen(String spielerName, String nachricht) throws RemoteException;
	
	public void chatNachrichtAnClientSenden(String nachricht) throws RemoteException;

}
