package tm.kalaha.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import tm.kalaha.serverInterface.ChatException;
import tm.kalaha.serverInterface.RMIClientInterface;
import tm.kalaha.serverInterface.ServerInterface;

public class RMIClient extends UnicastRemoteObject implements RMIClientInterface, Runnable {
	
	private static final long serialVersionUID = 6487865781693539839L;
	private static final String HOST ="localhost";
	private static final String BIND_NAME = "RMI-Server";
	private String name;
	
	public RMIClient (String n) throws RemoteException {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void sendeNachricht(String msg) throws RemoteException {
		System.out.println(msg + "\n Eingabe: ");
	}
	
	public void run() {
		ServerInterface server = null; 
		//Verbindung aufbauen 
		try {
			String bindURL = "rmi://"+HOST+"/" +BIND_NAME;
			server = (ServerInterface) Naming.lookup(bindURL);
		} catch (NotBoundException e) {
			System.out.println("Server ist nicht gebunden");
			System.out.println(e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println("URL ungültig:");
			System.out.println(e.getMessage());
		} catch (RemoteException e) {
			System.out.println("Fehler während Kommunikation");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Sonstiger Fehler"); 
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		//Anmelden und chatten
		try {
			server.anmelden(this);
			
			System.out.println(server.getData().toString());

			server.abmelden(this);
		} catch (ChatException e) {
			System.out.println(e.getMessage());
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
}
