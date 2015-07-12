package nerdUI;

import java.rmi.RemoteException;
import java.util.Scanner;

import tm.kalaha.GUI.SpielbrettAction;
import tm.kalaha.client.RMIClient;
import tm.kalaha.serverInterface.RMIClientInterface;

/**
 * Ein Konsolen UI für das Kalaha Spiel wird hier implementiert.
 * 
 * @author marc
 *
 */
public class UI implements SpielbrettAction {

	Scanner sc;
	String spielerName;
	RMIClientInterface client;

	public static void main(String[] args) {
		new UI();
	}

	/**
	 * Konstruktor. Der Nutzer wird mit selbst gewähltem Nutzernamen angemeldet
	 * (Scanner). Anschließend wird dieses Objekt dem RMIClient als
	 * Schnittstelle übergeben, sodass RMIClient diesem Objekt (durch Aufruf der
	 * Methode spielbrettVeraendert()) sagen kann, dass da Spielbrett neu
	 * eingelesen werden soll.
	 */
	public UI() {
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
			// Ausgabe der Spielernamen
			System.out.println("SpielerA: " + client.getSpielbrett().getSpielerA().getSpielerName());
			System.out.println("SpielerB: " + client.getSpielbrett().getSpielerB().getSpielerName());

			// Ausgabe des Spielbretts
			for (int i = 0; i < 12; i++) {
				int steine = getClient().getSpielbrett().getMulden()[i].getAnzahlSteine();
				if (steine <= 10) {
					System.out.print(getClient().getSpielbrett().getMulden()[i].getAnzahlSteine() + " ");
				} else {
					System.out.print(getClient().getSpielbrett().getMulden()[i].getAnzahlSteine() + "  ");
				}
				if (i == 5) {
					// Line break
					System.out.println("");
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// Line break
		System.out.println("");
		System.out.print("> ");
	}

	public RMIClientInterface getClient() {
		return this.client;
	}

}
