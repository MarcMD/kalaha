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
		System.out.println("Client meldet sich an");
		if(angemeldet(client)) {
			//TODO Fehlermeldung
			System.out.println("Gewählter Name ist schon vergeben");
			throw new KalahaException("Spielername ist schon vergeben");
		} else {
			if(clientA == null) {
				System.out.println("ClientA wird angemeldet: "+client.getSpielerName());
				clientA = client;
				meinSpiel.getSpielbrett().getSpielerA().setSpielerName(client.getSpielerName());
			} else if(clientB == null && !clientA.equals(client)) {
				System.out.println("ClientB wird angemeldet: "+client.getSpielerName());
				clientB = client;
				meinSpiel.getSpielbrett().getSpielerB().setSpielerName(client.getSpielerName());
			} else {
				System.out.println("Bereits zwei Spieler angemeldet");
				throw new KalahaException("Bereits zwei Spieler angemeldet");
			}
		}
		sendeSpielbrett();
	}
	
	private synchronized boolean angemeldet(RMIClientInterface client) throws RemoteException {
		if(clientA != null) {
			if(clientA.equals(client)) {
				return true;
			}
		}
		if(clientB != null) {
			if(clientB.equals(client)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public synchronized void abmelden(RMIClientInterface client) {
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
			
	public synchronized void sendeSpielbrett() {
		
			try {
				System.out.println("Spielbrett an ClientA schicken");
				clientA.spielbrettBekommen(meinSpiel.getSpielbrett());
				System.out.println("Spielbrett an ClientA geschickt");
			} catch (Exception e) {
				System.out.println("Spielbrett konnte nicht an ClientA geschickt werden. ClientA wurde nicht gefunden.");			
			}
			try {
				System.out.println("Spielbrett an ClientB schicken");
				clientB.spielbrettBekommen(meinSpiel.getSpielbrett());
				System.out.println("Spielbrett an ClientB geschickt");
			} catch (Exception e) {
				System.out.println("Spielbrett konnte nicht an ClientB geschickt werden. ClientB wurde nicht gefunden.");			
			}
	}


	@Override
	public synchronized void muldeSpielen(String spielerName, int muldenNummer) throws RemoteException, KalahaException {
		System.out.println(spielerName + " spielt Mulde "+muldenNummer);
		meinSpiel.muldeSpieln(spielerName, muldenNummer);
		sendeSpielbrett();
	}


	@Override
	public synchronized void neuesSpielStarten(String spielerName) {
		//TODO Meldung ausgeben
		System.out.println("Neues Spiel wurde gestartet von " +spielerName);
		meinSpiel.neuesSpielStarten(spielerName);
		sendeSpielbrett();
	}
}
