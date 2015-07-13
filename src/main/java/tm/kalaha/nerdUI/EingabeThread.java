package tm.kalaha.nerdUI;

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

	private boolean laufen = true;
	
	private NerdUI ui;

	/**
	 * Konstruktor.
	 * 
	 * @param ui
	 *            wird benötigt, um Befehle an das UI weiter zu geben.
	 */
	public EingabeThread(NerdUI ui) {
		this.ui = ui;
	}

	/**
	 * Warten auf Eingaben des Nutzers
	 */
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String eingabe;
		while (laufen) {
			eingabe = sc.next();
			int i = -1;
			if(isInteger(eingabe)) {
				i = Integer.parseInt(eingabe) -1;
			}
			try {
				if(i>=0 && i<12) {
					ui.getClient().muldeSpielen(i);
				} else if (eingabe.equalsIgnoreCase("abmelden")) {
					ui.getClient().abmelden();
				} else if (eingabe.equalsIgnoreCase("neuesSpiel") | eingabe.equalsIgnoreCase("neues spiel")) {
					ui.getClient().neuesSpiel();
				} else if(eingabe.equalsIgnoreCase("help")) {
					System.out.println("1 - 12         Mulden spielen");
					System.out.println("abmelden       Abmelden");
					System.out.println("neuesSpiel     Neues Spiel starten");
					System.out.println("help           Hilfe anzeigen");
					System.out.print("> ");
				} else {
					System.out.println("Ungültige Eingabe");
					System.out.print("> ");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Wird genutzt um zu kontrollieren, ob die Eingabe ein Integer ist. 
	 * @param input String der vom Scanner eingelesen wurde
	 * @return true wenn die Eingabe ein Integer ist
	 */
	public boolean isInteger( String input ) {
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}

}
