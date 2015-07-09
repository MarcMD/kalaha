package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tm.kalaha.server.Daten;

public interface ServerInterface extends Remote {
	
	public void anmelden(RMIClientInterface client) throws RemoteException, KalahaException;
		
	public void abmelden(RMIClientInterface client) throws RemoteException, KalahaException;
	
	public void muldeSpielen(String spielerName, int muldenNummer) throws RemoteException, KalahaException;
	
	public void neuesSpielStarten(String spielerName) throws RemoteException;
}
