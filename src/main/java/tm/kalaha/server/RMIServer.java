package tm.kalaha.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import tm.kalaha.serverInterface.KalahaException;
import tm.kalaha.serverInterface.RMIClientInterface;
import tm.kalaha.serverInterface.ServerInterface;


public class RMIServer extends UnicastRemoteObject implements ServerInterface {

	private static final long serialVersionUID = 4480875342963577221L;
	private static final String HOST = "localhost";
	private static final String SERVICE_NAME = "RMI-Server";
	
	private Spiel meinSpiel = new Spiel();
		
	//Speichern der angemeldeten Clients
	private RMIClientInterface clientA = null;
	private RMIClientInterface clientB = null;
	
	public RMIServer() throws RemoteException {
		String bindURL = null;
		try {
			bindURL = "rmi://" +HOST + "/" + SERVICE_NAME;
			LocateRegistry.createRegistry(1099);
			Naming.rebind(bindURL, this);			
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

	//TODO Testen
	@Override
	public synchronized void anmelden(RMIClientInterface client) throws RemoteException, KalahaException {
		
		if(angemeldet(client)) {
			//TODO Fehlermeldung
			System.out.println("Name ist schon vergeben");
			throw new KalahaException("Spielername ist schon vergeben");
		} else {
			if(clientA == null) {
				clientA = client;
				meinSpiel.getSpielbrett().getSpielerA().setSpielerName(client.getSpielerName());
			} else if(clientB == null && !clientA.getSpielerName().equals(client.getSpielerName())) {
				clientB = client;
				meinSpiel.getSpielbrett().getSpielerB().setSpielerName(client.getSpielerName());
			} else {
				throw new KalahaException("Bereits zwei Spieler angemeldet");
			}
		}
		sendeSpielbrett();
	}
	
	private synchronized boolean angemeldet(RMIClientInterface client) throws RemoteException {
		if(clientA != null) {
			if(client.getSpielerName().equals(clientA.getSpielerName())) {
				return true;
			}
		}
		if(clientB != null) {
			if(client.getSpielerName().equals(clientB.getSpielerName())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public synchronized void abmelden(RMIClientInterface client) throws RemoteException, KalahaException {
		if(clientA.equals(client)) {
			clientA = null;
			meinSpiel.getSpielbrett().getSpielerA().setSpielerName(null);
		} else if(clientB.equals(client)) {
			clientB = null;
			meinSpiel.getSpielbrett().getSpielerB().setSpielerName(null);
		}
		sendeSpielbrett();
		//		String msg = null;
//		if(!angemeldet(client.getSpielerName())) {
//			msg = "Client " +client.getSpielerName() + " ist nicht angemeldet.";
//			throw new KalahaException(msg);
//		}
//		msg = client.getSpielerName() + " hat sich abgemeldet.";
//		clients.remove(client);
//		for(RMIClientInterface c: clients) {
//			c.sendeNachricht(msg);
//		}
//		printStatus();		
	}


	private void printStatus() throws RemoteException {
//		Calendar cal = GregorianCalendar.getInstance();
//		String msg = cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "Uhr: ";
//		msg += clients.size() + "User aktuell online:";
//		for(RMIClientInterface c: clients) {
//			msg += c.getSpielerName() + " ";
//		}
//		System.out.println(msg);
	}
			
	private void sendeSpielbrett() throws RemoteException {
		 clientA.spielbrettBekommen(meinSpiel.getSpielbrett());
		 clientB.spielbrettBekommen(meinSpiel.getSpielbrett());
	}


	@Override
	public synchronized void muldeSpielen(String spielerName, int muldenNummer) throws RemoteException, KalahaException {
		meinSpiel.muldeSpieln(spielerName, muldenNummer);
		sendeSpielbrett();
	}


	@Override
	public synchronized void neuesSpielStarten(String spielerName) throws RemoteException {
		//TODO Meldung ausgeben
		meinSpiel.neuesSpielStarten(spielerName);
		sendeSpielbrett();
	}
	
	
}
