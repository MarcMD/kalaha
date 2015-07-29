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


/**
 * Diese Klasse stellt den RMIServer dar. Die Kommunikation mit den Clients und die 
 * Verwaltund des Spiels wird ueber diese Klasse realisiert. 
 * @author marc
 *
 */
public class RMIServer extends UnicastRemoteObject implements ServerInterface {

	// Fuer die RMI Kommunikation benoetigte Veriablen
	private static final long serialVersionUID = 4480875342963577221L;
	private static final String HOST = "localhost";
	private static final String SERVICE_NAME = "RMI-Server";
	
	//Spiel enthaelt die Spiellogik und das jeweils aktuelle 
	//Abbild des Spielbrettes liefern
	private Spiel meinSpiel = new Spiel();
		
	//Speichern der angemeldeten Clients
	private RMIClientInterface clientA = null;
	private RMIClientInterface clientB = null;
	
	
	/**
	 * Konstruktor. 
	 * Anmelden an der RMIRegistry. 
	 * @throws RemoteException
	 */
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
	
	/**
	 * main.
	 * Eine neue Instanz dieser Klasse wird geschaffen. 
	 * @param args
	 */
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

	/**
	 * Von von Clients aufgerufen, im sich anzumelden. Clients werden gespeichert. 
	 * Außerdem werden die Namen der Clients zum Spiel hinzugefuegt. 
	 * Aufruf von sendeSpielbrett() um den veraenderten Zustand des Spielbretts an 
	 * die Clients zu senden.
	 */
	@Override
	public synchronized void anmelden(RMIClientInterface client) throws RemoteException, KalahaException {
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
	
	/**
	 * Pruefen, ob ein client bereits angemeldet ist. Wird von der anmelden-Methode benutzt. 
	 * @param client 
	 * @return true wenn der client schon angemeldet ist. sonst false. 
	 * @throws RemoteException
	 */
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

	/**
	 * Kann von Clients aufgerufen werden, um sich vom Spiel anzumelden. 
	 * Aufruf von sendeSpielbrett() um den veraenderten Zustand des Spielbretts an 
	 * die Clients zu senden.
	 */
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
	}
			
	/**
	 * Wenn das Spielbrett von der Spiellogik (die Klasse Spiel) veraendert 
	 * wurde, kann diese Methode aufgerufen werden. Das neue Spielbrett 
	 * wird dann an die Clients gesendet. 
	 */
	public synchronized void sendeSpielbrett() {
		
			System.out.print("Spielbrett schicken ... ");
			try {
				clientA.getSpielbrett(meinSpiel.getSpielbrett());
				System.out.print("An ClientA geschickt ... ");
			} catch (Exception e) {
				System.out.print("ClientA wurde nicht gefunden ... ");			
			}
			try {
				clientB.getSpielbrett(meinSpiel.getSpielbrett());
				System.out.println("An ClientB geschickt. ");
			} catch (Exception e) {
				System.out.println("ClientB wurde nicht gefunden. ");			
			}
	}


	/**
	 * Wird von einem Client aufgerufen, um eine Mulde zu spielen. 
	 * Der Aufruf wird hier an die Spiellogik weiter gegeben. 
	 */
	@Override
	public synchronized void muldeSpielen(String spielerName, int muldenNummer) throws RemoteException, KalahaException {
		System.out.println(spielerName + " spielt Mulde "+muldenNummer);
		meinSpiel.muldeSpieln(spielerName, muldenNummer);
		sendeSpielbrett();
	}


	/**
	 * Wrid von einem Client aufgerufen, um ein neues Spiel zu starten. 
	 * Der Aufruf wird hier an die Spiellogik weiter gegeben. 
	 */
	@Override
	public synchronized void neuesSpielStarten(String spielerName) {
		System.out.println("Neues Spiel wurde gestartet von " +spielerName);
		meinSpiel.neuesSpielStarten(spielerName);
		sendeSpielbrett();
	}

	/**
	 * Diese Methode wird von einem Client aufgerufen, um eine Nachricht an den Server
	 * zu schicken. Der Server verteilt die Nachricht in dieser Methode an alle Clients
	 * durch den Methodenaufruf chatNachrichtAnClientSenden(spielerName+": "+nachricht);
	 */
	@Override
	public void chatNachrichtVonClientEmpfangen(String spielerName, String nachricht) throws RemoteException {
		chatNachrichtAnClientSenden(spielerName+": "+nachricht);
	}

	/**
	 * Die uebergebene Nachricht wird an alle Clients geschickt. 
	 */
	@Override
	public void chatNachrichtAnClientSenden(String nachricht) throws RemoteException {
		System.out.println("Chat-Nachricht an schicken: "+nachricht);
		try {
			clientA.empfangechatNachrichtVonServer(nachricht);
		} catch (Exception e) {
			System.out.println("Nachricht konnte nacht an clientA geschickt werden. ClientA wurde nicht gefunden.");
		}
		try {
			clientB.empfangechatNachrichtVonServer(nachricht);
		} catch (Exception e) {
			System.out.println("Nachricht konnte nacht an clientB geschickt werden. ClientB wurde nicht gefunden.");
		}
	}

}
