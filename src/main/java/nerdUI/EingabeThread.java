package nerdUI;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Thread, der von UI genutzt wird, um zeitgleich Eingaben und Ausgaben bedienen
 * zu können.
 * 
 * @author marc
 *
 */
public class EingabeThread implements Runnable {

	private UI ui;

	/**
	 * Konstruktor.
	 * 
	 * @param ui
	 *            wird benötigt, um Befehle an das UI weiter zu geben.
	 */
	public EingabeThread(UI ui) {
		this.ui = ui;
	}

	/**
	 * Warten auf Eingaben des Nutzers
	 */
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String eingabe;
		while (true) {
			eingabe = sc.next();
			try {
				if (eingabe.equalsIgnoreCase("abmelden")) {
					ui.getClient().abmelden();
				} else if (eingabe.equalsIgnoreCase("neuesSpiel") | eingabe.equalsIgnoreCase("neues spiel")) {
					ui.getClient().neuesSpiel();
				} else {
					ui.getClient().muldeSpielen(Integer.parseInt(eingabe));
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

}
