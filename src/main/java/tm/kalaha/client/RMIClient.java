package tm.kalaha.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import tm.kalaha.server.Spielbrett;
import tm.kalaha.serverInterface.KalahaException;
import tm.kalaha.serverInterface.RMIClientInterface;
import tm.kalaha.serverInterface.ServerInterface;
import tm.kalaha.serverInterface.UserInterface;

/**
 * Diese Klasse wird benutzt, um den Spieler mit dem Server zu verbinden. Diese
 * Klasse kann bspw. von SpielOberflaeche verwendet werden.
 * 
 * @author marc
 *
 */
public class RMIClient extends UnicastRemoteObject implements RMIClientInterface, Runnable {

	// Fuer die RMI Kommunikation benoetigte Veriablen
	private static final long serialVersionUID = 6487865781693539839L;
	private String host = "localhost";
	private static final String BIND_NAME = "RMI-Server";
	String bindURL = null;
	ServerInterface server = null;

	// Interface (wird implementiert von SpielOberflaeche), das benutz wird,
	// um Aenderungen am Spielbrett an die GUI zu kommunizieren
	private UserInterface meinUI;

	// Spielername. Wird benutzt, um den Spieler eindeutig am Server zu
	// identifizieren
	private String spielerName;
	// Spielbrett. Wird vom Server veraendert und an die Cients geschickt.
	private Spielbrett spielbrett = new Spielbrett();

	// Die jeweils aktuellste Chat Nachricht
	private String neueNachricht = null;

	/**
	 * Konstruktor. Setzt den Spielernamen.
	 * 
	 * @param n
	 *            spielerName
	 * @throws RemoteException
	 */
	public RMIClient(String n) throws RemoteException {
		spielerName = n;
	}

	/**
	 * Setter fuer den host (hier kann bspw. 'localhost' als Hostname gesetzt
	 * werden)
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Getter fuer den spielerName
	 */
	public String getSpielerName() {
		return spielerName;
	}

	/**
	 * Setter fuer den spielerName
	 * 
	 * @param spielerName
	 */
	public void setSpielerName(String spielerName) {
		this.spielerName = spielerName;
	}

	/**
	 * Getter fuer das spielbrett
	 */
	public Spielbrett getSpielbrett() {
		return spielbrett;
	}

	/**
	 * Getter fuer ein UserInterface (z.B. SpielOberflaeche)
	 */
	public UserInterface getMeinUI() {
		return meinUI;
	}

	/**
	 * Getter fuer aktuelle Nachricht im Chat
	 * 
	 * @return neueNachricht
	 */
	public String getNeueNachricht() {
		return neueNachricht;
	}

	/**
	 * Setter fuer neue Nachicht im Chat
	 * 
	 * @param neueNachricht
	 */
	public void setNeueNachricht(String neueNachricht) {
		this.neueNachricht = neueNachricht;
	}

	/**
	 * Hier wird die Verbindung mit dem RMIServer aufgebaut.
	 * server.anmelden(this) wird aufgerufen, um den Spieler zu dem Spiel
	 * hinzuzufuegen bzw. anzumelden. Der Aufruf dieser Methode erfolgt in der
	 * Regel von der GUI.
	 */
	public void anmelden() {
		try {
			bindURL = "rmi://" + host + "/" + BIND_NAME;
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

		try {
			System.out.println("Host: " + host);
			server.anmelden(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (KalahaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wird aufgerufen, um eine Mulde zu spielen. Die Nummer der Mulde muss
	 * uebergeben werden. Der Aufruf dieser Methode erfolgt in der Regel von der
	 * GUI.
	 */
	public void muldeSpielen(int muldenNummer) {
		try {
			server.muldeSpielen(this.getSpielerName(), muldenNummer);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (KalahaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hier kann der Spieler vom Spiel und vom Server abgemeldet werden. Der
	 * Aufruf dieser Methode erfolgt in der Regel von der GUI.
	 */
	public void abmelden() {
		try {
			server.abmelden(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (KalahaException e) {
			e.printStackTrace();
		}
	}

	public void run() {
	}

	/**
	 * Getter fuer das Spielbrett. Besonderheit: Hier wird durch den Aufruf der
	 * Methode spielbrettVeraendert(spielbrett) der GUI das neue Spielbrett
	 * uebergeben.
	 */
	@Override
	public synchronized void getSpielbrett(Spielbrett spielbrett) throws RemoteException {
		this.spielbrett = spielbrett;
		meinUI.spielbrettVeraendert(this.spielbrett);
	}

	/**
	 * Hier bekommt der Client eine Referenz auf das GUI Objekt. Diese Referenz
	 * wird benutzt, um der GUI mitzuteilen wenn das Spielbrett veraendert wurde
	 * 
	 * @param meinUI
	 */
	public void setMeinUI(UserInterface meinUI) {
		this.meinUI = meinUI;
	}

	/**
	 * Hier wird eine neues Spiel auf dem Server gestartet
	 */
	@Override
	public void neuesSpiel() throws RemoteException {
		server.neuesSpielStarten(spielerName);
	}

	/**
	 * Eine Chat Nachricht vom Server empfangen und dem UI die neue Nachricht
	 * geben.
	 */
	@Override
	public void empfangechatNachrichtVonServer(String nachricht) throws RemoteException {
		this.setNeueNachricht(nachricht);
		meinUI.chatVeraendert(nachricht);
	}

	/**
	 * Eine Chat Nachricht an den Server senden.
	 */
	@Override
	public void sendeChatNachrichtAnServer(String nachricht) throws RemoteException {
		server.chatNachrichtVonClientEmpfangen(spielerName, nachricht);
	}
}
