package tm.kalaha.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import tm.kalaha.serverInterface.ChatException;
import tm.kalaha.serverInterface.RMIClientInterface;
import tm.kalaha.serverInterface.ServerInterface;


public class RMIServer extends UnicastRemoteObject implements ServerInterface {

	private static final long serialVersionUID = 4480875342963577221L;
	private static final String HOST = "localhost";
	private static final String SERVICE_NAME = "RMI-Server";
	
	//Speichern der angemeldeten Clients
	private Vector<RMIClientInterface> clients = null;
	
	public RMIServer() throws RemoteException {
		String bindURL = null;
		try {
			bindURL = "rmi://" +HOST + "/" + SERVICE_NAME;
			LocateRegistry.createRegistry(1099);
			Naming.rebind(bindURL, this);
			
			clients = new Vector<RMIClientInterface>();
			System.out.println("RMI-Server gebunden unter dem Namen: " +SERVICE_NAME);
			System.out.println("RMI-Server ist bereit ...");
		} catch (MalformedURLException e) {
			System.out.println("Ungültige URL: " +bindURL);
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	

	public static void main(String[] args) {
		try {
			new RMIServer();
		} catch (RemoteException e) {
			System.out.println("Fehler während der Erzeugung des Server-Objektes");
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}


	@Override
	public synchronized void anmelden(RMIClientInterface client) throws RemoteException, ChatException {
		String msg = null;
		
		if(angemeldet(client.getName())) {
			msg = client.getName() + "schon vergeben";
			throw new ChatException(msg);
		}
		
		clients.add(client);
		
		msg = "Willkommen auf RMIChat. Zum Abmelden \"Exit\" eingeben.";
		client.sendeNachricht(msg);
		
		for(RMIClientInterface c: clients) {
			msg = "\n" + c.getName() + " hat sich angemeldet.";
			c.sendeNachricht(msg);
		}
		printStatus();		
	}


	@Override
	public synchronized void sendeNachricht(RMIClientInterface client, String nachricht) throws RemoteException, ChatException {
		String msg = null;
		if(!angemeldet(client.getName())) {
			msg = "Client " +client.getName() + " ist nicht angemeldet.";
			throw new ChatException(msg);
		}
		
		msg = client.getName() + " schreibt: " + nachricht;
		
		for(RMIClientInterface c: clients) {
			c.sendeNachricht("\n" +msg);
		}
	}


	@Override
	public synchronized void abmelden(RMIClientInterface client) throws RemoteException, ChatException {
		String msg = null;
		if(!angemeldet(client.getName())) {
			msg = "Client " +client.getName() + " ist nicht angemeldet.";
			throw new ChatException(msg);
		}
		msg = client.getName() + " hat sich abgemeldet.";
		clients.remove(client);
		for(RMIClientInterface c: clients) {
			c.sendeNachricht(msg);
		}
		printStatus();		
	}


	private void printStatus() throws RemoteException {
		Calendar cal = GregorianCalendar.getInstance();
		String msg = cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "Uhr: ";
		msg += clients.size() + "User aktuell online:";
		for(RMIClientInterface c: clients) {
			msg += c.getName() + " ";
		}
		System.out.println(msg);
	}
	
	private boolean angemeldet(String name) throws RemoteException {
		for(RMIClientInterface c: clients) {
			if(name.equalsIgnoreCase(c.getName())) {
				return true;
			}
		}
		return false;
	}
}
