package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tm.kalaha.server.Spielbrett;

/**
 * Dieses Interface wird benutzt, um dem Client die zu implementierenden 
 * Methoden vorzuschreiben. Die Kommentierung der Methoden befindet sich in 
 * der Klasse RMIClient
 * @author marc
 *
 */
public interface RMIClientInterface extends Remote {
		
	public void getSpielbrett(Spielbrett spielbrett) throws RemoteException;
	
	public String getSpielerName() throws RemoteException;
	
	public Spielbrett getSpielbrett() throws RemoteException;
	
	public void anmelden() throws RemoteException;
	
	public void abmelden() throws RemoteException;
	
	public void muldeSpielen(int i) throws RemoteException;
	
	public void neuesSpiel() throws RemoteException;
	
	public void setMeinUI(UserInterface meinUI) throws RemoteException;
	
	public void sendeChatNachrichtAnServer(String nachricht) throws RemoteException;
	
	public void empfangechatNachrichtVonServer(String nachricht) throws RemoteException;
}
