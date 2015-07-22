package tm.kalaha.serverInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tm.kalaha.GUI.SpielbrettAction;
import tm.kalaha.server.Spielbrett;

public interface RMIClientInterface extends Remote {
		
	public void spielbrettBekommen(Spielbrett spielbrett) throws RemoteException;
	
	public String getSpielerName() throws RemoteException;
	
	public Spielbrett getSpielbrett() throws RemoteException;
	
	public void anmelden() throws RemoteException;
	
	public void abmelden() throws RemoteException;
	
	public void muldeSpielen(int i) throws RemoteException;
	
	public void neuesSpiel() throws RemoteException;
	
	public void setMeinUI(SpielbrettAction meinUI) throws RemoteException;
	
	public void sendeChatNachrichtAnServer(String nachricht) throws RemoteException;
	
	public void empfangechatNachrichtVonServer(String nachricht) throws RemoteException;
}
