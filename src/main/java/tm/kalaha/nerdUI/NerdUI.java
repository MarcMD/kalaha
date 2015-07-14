package tm.kalaha.nerdUI;

import java.rmi.RemoteException;
import java.util.Scanner;

import tm.kalaha.GUI.SpielbrettAction;
import tm.kalaha.client.RMIClient;
import tm.kalaha.serverInterface.RMIClientInterface;

/**
 * Ein Konsolen UI für das Kalaha Spiel wird hier implementiert. Zusätzlich zu
 * dieser Klasse wird die Klasse EingabeThread genutzt, um zeitgleich Eingaben
 * und Ausgaben bearbeiten zu können.
 * 
 * @author marc
 *
 */
public class NerdUI implements SpielbrettAction {

	Scanner sc;
	String spielerName;
	RMIClientInterface client;

	public static void main(String[] args) {
		new NerdUI();
	}

	/**
	 * Konstruktor. Der Nutzer wird mit selbst gewähltem Nutzernamen angemeldet
	 * (Scanner). Anschließend wird dieses Objekt dem RMIClient als
	 * Schnittstelle übergeben, sodass RMIClient diesem Objekt (durch Aufruf der
	 * Methode spielbrettVeraendert()) sagen kann, dass da Spielbrett neu
	 * eingelesen werden soll.
	 */
	public NerdUI() {
		// Sacnner nimmt Eingabe des Nutzers auf
		sc = new Scanner(System.in);

		// Nutzer anmelden
		System.out.println("Bitte anmelden");
		System.out.print("> ");
		spielerName = sc.next();
		try {
			client = new RMIClient(spielerName);
			client.anmelden();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		/**
		 * Diese Klasse implementiert das Interface "SpielbrettAction", das von
		 * RMIClient genutzt werden kann. RMIClient wird eine Schnittstelle
		 * geboten, in der er beliebigen UI sagen kann, das Spielbrett neu
		 * einzulesen. Hier wird RMIClient mit dieser Klasse verbunden.
		 */
		try {
			client.setMeinUI(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		this.spielbrettVeraendert();

		// Ein Thread der auf Eingaben des Nutzers wartet
		EingabeThread eingabe = new EingabeThread(this);
		eingabe.run();
	}

	/**
	 * Diese Methode wird vom client aufgerufen wenn sich das Spielbrett
	 * verändert hat. Dann wird das Spielbrett hier neu ausgegeben. Die Methode
	 * wird durch die Schnittstelle SpielbrettAction vorgegeben.
	 */
	public void spielbrettVeraendert() {
		try {
			// Line break
			System.out.println("");
			// Ausgabe der Fehlermeldungen
			if (client.getSpielerName().equals(client.getSpielbrett().getSpielerA().getSpielerName())) {
				if (client.getSpielbrett().getSpielerA().getFehlerMeldung() != null) {
					System.out.println(client.getSpielbrett().getSpielerA().getFehlerMeldung());
				}
			}
			if (client.getSpielerName().equals(client.getSpielbrett().getSpielerB().getSpielerName())) {
				if (client.getSpielbrett().getSpielerB().getFehlerMeldung() != null) {
					System.out.println(client.getSpielbrett().getSpielerB().getFehlerMeldung());
				}
			}

			// Ausgabe der Spielernamen
			// Wenn Spieler A am Zug ist, wird >> vor seinem Namen gezeigt
			if (client.getSpielbrett().getSpielerA().isIstAmZug()) {
				System.out.print(">> ");
			} else
				System.out.print("   ");
			// Ausgabe des Spielernames (A) und der Anzahl gewonnener Steine
			System.out.println("SpielerA: " + client.getSpielbrett().getSpielerA().getSpielerName() + " ("
					+ client.getSpielbrett().getSpielerA().getGewonneneSteine() + ")");
			// Wenn Spieler B am Zug ist, wird >> vor seinem Namen gezeigt
			if (client.getSpielbrett().getSpielerB().isIstAmZug()) {
				System.out.print(">> ");
			} else {
				System.out.print("   ");
			}
			// Ausgabe des Spielernames (B) und der Anzahl gewonnener Steine
			System.out.println("SpielerB: " + client.getSpielbrett().getSpielerB().getSpielerName() + " ("
					+ client.getSpielbrett().getSpielerB().getGewonneneSteine() + ")");

			// Ausgabe des Spielbretts
			for (int i = 0; i < 6; i++) {
				int steine = getClient().getSpielbrett().getMulden()[i].getAnzahlSteine();
				if (steine <= 10) {
					System.out.print(getClient().getSpielbrett().getMulden()[i].getAnzahlSteine() + " ");
				} else {
					System.out.print(getClient().getSpielbrett().getMulden()[i].getAnzahlSteine() + "  ");
				}
			}
			// Line break
			System.out.println("");
			for(int i=11; i>5;i--) {
				int steine = getClient().getSpielbrett().getMulden()[i].getAnzahlSteine();
				if (steine <= 10) {
					System.out.print(getClient().getSpielbrett().getMulden()[i].getAnzahlSteine() + " ");
				} else {
					System.out.print(getClient().getSpielbrett().getMulden()[i].getAnzahlSteine() + "  ");
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// Line break
		System.out.println("");
		System.out.print("> ");
	}

	/**
	 * Wird von der Klasse EingabeThread genutzt, um direkt auf dem Client
	 * Methoden ausführen zu können.
	 * 
	 * @return Gibt eine Referenz auf dem Client zurück
	 */
	public RMIClientInterface getClient() {
		return this.client;
	}

}
