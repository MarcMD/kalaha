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
		spielbrett.getMulden()[muldenNummer].setAnzahlSteine(0);
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
