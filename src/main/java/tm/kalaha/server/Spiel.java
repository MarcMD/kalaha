package tm.kalaha.server;

public class Spiel {

	private Spielbrett spielbrett = null;

	/**
	 * Konstruktor
	 */
	public Spiel() {
		spielbrett = new Spielbrett();
	}

	/**
	 * Getter. Kann bspw. vom Server aufgerufen werden, um das Spielbrett zu
	 * bekommen, das vom Server an den Client ge- sendet und dort angezeigt
	 * werden kann.
	 * 
	 * @return da verwendete Spielbrett
	 * 
	 */
	public Spielbrett getSpielbrett() {
		return this.spielbrett;
	}

	/**
	 * Diese Methode wird aufgerufen, um einen Zug eines Spielers zu starten.
	 * Hierzu wird dem Spiel der spielernName und die Nummer der Mulde (im
	 * Mulden-Array) übergeben. Die Steine werden dann nach Kalaha-Regeln
	 * verteilt. Der Spieler bekommt die durch den Zug gewonnenen Steine
	 * 
	 * @param spielerName
	 *            muss übergeben werden, damit die Methode den Spieler kennt
	 * @param nummerMulde
	 *            Zeiger auf die Mulde, die gespielt werden soll
	 */
	public Spielbrett muldeSpieln(String spielerName, int muldenNummer) {
		//Den Spieler bestimmen, der momentan am Zug ist 
		Spieler aktuellerSpieler = null; 
		if(spielbrett.getSpielerA().isIstAmZug()) {
			aktuellerSpieler = spielbrett.getSpielerA();
		} else if(spielbrett.getSpielerB().isIstAmZug()) {
			aktuellerSpieler = spielbrett.getSpielerB();
		}
		
		//Fehlermeldung wird bei jedem neuen Zug wieder zurückgesetzt
		spielbrett.getSpielerA().setFehlerMeldung(null);
		spielbrett.getSpielerB().setFehlerMeldung(null);
		
		//Prüfen, ob der Spieler am Zug ist
		if(aktuellerSpieler.getSpielerName().equals(spielerName)) {
//TODO Prüfen, ob Spieler dieses Feld spielen darf. Wenn nicht --> Fehlermeldung ausgeben
			//Steine verteilen
			int steine = spielbrett.getMulden()[muldenNummer].steineNemhen();
			int zeiger = muldenNummer;
			
			//Solange noch Steine da sind, gehe zum naechsten Feld 
			//und verteile jeweils einen Stein
			while (steine >0) {
				zeiger++;
				if(zeiger>11) {
					zeiger =0;
				}
				spielbrett.getMulden()[zeiger].steinHinzufügen();
				steine--;
			}
			
//TODO Prüfen, ob ein Spieler Steine gewonnen hat
			
			//nächster Spieler ist dran
			if(aktuellerSpieler.equals(spielbrett.getSpielerA())) {
				spielbrett.getSpielerA().setIstAmZug(false);
				spielbrett.getSpielerB().setIstAmZug(true);
			} else if(aktuellerSpieler.equals(spielbrett.getSpielerB())) {
				spielbrett.getSpielerB().setIstAmZug(false);
				spielbrett.getSpielerA().setIstAmZug(true);
			}
			
		} else {
			//Wenn der Zug von einem Spieler ausgeführt wurde, obwohl dieser Spieler nicht 
			//am Zug ist, bekommt dieser Spieler eine Fehlermeldung
			if(spielbrett.getSpielerA().getSpielerName().equals(spielerName)) {
				spielbrett.getSpielerA().setFehlerMeldung("Du bist nicht am Zug");
			} else if(spielbrett.getSpielerB().getSpielerName().equals(spielerName)) {
				spielbrett.getSpielerB().setFehlerMeldung("Du bist nicht am Zug");
			}
		}
		
		return spielbrett;
	}

	/**
	 * Wird lokal nach jedem Zug aufgerufen, um zu prüfen, ob das Spiel zu Ende
	 * ist.
	 * 
	 * @return true wenn das Spiel zu Ende ist, false wenn das Spiel nicht zu
	 *         Ende ist
	 */
	public boolean istSpielZuEnde() {
		return false;
	}

	/**
	 * Wird aufgerufen wenn das Spiel zu Ende ist und ein Spieler gewonnen hat.
	 * Setzt den Gewinner.
	 */
	public void bestimmeGewinner() {
		// mach was
	}

	/**
	 * Startet ein neues Spiel, indem das alte Spielbrett auf null gesetzt wird.
	 * SpielerName wird benutzt, um im nächsten Spiel eine Meldung auszugeben,
	 * welcher Spieler das neue Spiel gestartet hat.
	 */
	public Spielbrett neuesSpielStarten(String spielerName) {
		// TODO Meldung setzen
		Spielbrett spielbrettAlt = this.spielbrett;
		spielbrett = new Spielbrett();
		spielbrett.getSpielerA().setSpielerName(spielbrettAlt.getSpielerA().getSpielerName());
		spielbrett.getSpielerB().setSpielerName(spielbrettAlt.getSpielerB().getSpielerName());
		return spielbrett;
	}
}
