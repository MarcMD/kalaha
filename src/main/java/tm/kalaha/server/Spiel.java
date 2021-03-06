package tm.kalaha.server;

/**
 * Diese Klasse verkoerpert die Spiellogik. 
 * @author marc
 *
 */
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
	 * bekommen, das vom Server an den Client gesendet und dort angezeigt
	 * werden kann.
	 * 
	 * @return das verwendete Spielbrett
	 * 
	 */
	public Spielbrett getSpielbrett() {
		return this.spielbrett;
	}

	/**
	 * Diese Methode wird aufgerufen, um einen Zug eines Spielers zu starten.
	 * Hierzu wird dem Spiel der spielernName und die Nummer der Mulde (im
	 * Mulden-Array) uebergeben. Die Steine werden dann nach Kalaha-Regeln
	 * verteilt. Der Spieler bekommt die durch den Zug gewonnenen Steine.
	 * 
	 * @param spielerName
	 *            muss uebergeben werden, damit die Methode den Spieler kennt
	 * @param nummerMulde
	 *            Zeiger auf die Mulde, die gespielt werden soll
	 */
	public Spielbrett muldeSpieln(String spielerName, int muldenNummer) {

		// Den Spieler bestimmen, der momentan am Zug ist
		Spieler aktuellerSpieler = null;
		if (spielbrett.getSpielerA().isIstAmZug()) {
			aktuellerSpieler = spielbrett.getSpielerA();
		} else if (spielbrett.getSpielerB().isIstAmZug()) {
			aktuellerSpieler = spielbrett.getSpielerB();
		}

		//Nur wenn noch kein Spieler gewonnen hat, kann das weiter gespielt werden
		if (!spielbrett.getSpielerA().isHatGewonnen() && !spielbrett.getSpielerB().isHatGewonnen()) {

			// Fehlermeldung wird bei jedem neuen Zug wieder zurueckgesetzt
			spielbrett.getSpielerA().setFehlerMeldung(null);
			spielbrett.getSpielerB().setFehlerMeldung(null);

			// Pruefen, ob der Spieler am Zug ist
			if (aktuellerSpieler.getSpielerName().equals(spielerName)) {

				// Pruefen, ob der Spieler diese Mulde spielen darf
				// SpielerA darf 0-5 spielen, SpielerB darf 6-11 spielen
				if (spielerDarfFeldSpielen(aktuellerSpieler, muldenNummer)) {
					
					//Pruefen ob die gespielte Mulde leer ist (Leere Mulden duerfen nicht gespielt werden)
					if(spielbrett.getMulden()[muldenNummer].getAnzahlSteine()==0) {
						aktuellerSpieler.setFehlerMeldung("Du kannst keine leeren Mulden spielen");
					} else {

						// Steine verteilen
						int steine = spielbrett.getMulden()[muldenNummer].steineNemhen();
						int zeiger = muldenNummer;
	
						// Solange noch Steine da sind, gehe zum naechsten Feld
						// und verteile jeweils einen Stein
						while (steine > 0) {
							zeiger++;
							if (zeiger > 11) {
								zeiger = 0;
							}
							spielbrett.getMulden()[zeiger].steinHinzufuegen();
							steine--;
						}
	
						// Pruefen, ob der aktuelle Spieler Steine gewonnen hat
						// Steine koennen gewonnen werden, wenn in dem Feld 3 oder 4
						// Steine sind und
						// der Spieler das Feld nicht spielen darf bzw. es sich um
						// ein Feld des anderen Spielers handelt
						while (((spielbrett.getMulden()[zeiger].getAnzahlSteine() == 3)
								|| (spielbrett.getMulden()[zeiger].getAnzahlSteine()) == 4)
								&& !spielerDarfFeldSpielen(aktuellerSpieler, zeiger)) {
							aktuellerSpieler.steineGewinnen(spielbrett.getMulden()[zeiger].steineNemhen());
							zeiger--;
							if (zeiger < 0) {
								zeiger = 11;
							}
						}
	
						// Pruefen ob das Spiel zu Ende ist und Gewinner setzen
						if (istSpielZuEnde(aktuellerSpieler)) {
							spielbrett.getSpielerA()
									.setFehlerMeldung(aktuellerSpieler.getSpielerName() + " hat das Spiel gewonnen");
							spielbrett.getSpielerB()
									.setFehlerMeldung(aktuellerSpieler.getSpielerName() + " hat das Spiel gewonnen");
							if(spielbrett.getSpielerA().isHatGewonnen()) {
								spielbrett.getSpielerA().setFehlerMeldung(spielbrett.getSpielerA().getSpielerName() + " hat das Spiel gewonnen");
								spielbrett.getSpielerB().setFehlerMeldung(spielbrett.getSpielerA().getSpielerName() + " hat das Spiel gewonnen");
							} else if(spielbrett.getSpielerB().isHatGewonnen()) {
								spielbrett.getSpielerA().setFehlerMeldung(spielbrett.getSpielerB().getSpielerName() + " hat das Spiel gewonnen");
								spielbrett.getSpielerB().setFehlerMeldung(spielbrett.getSpielerB().getSpielerName() + " hat das Spiel gewonnen");
							}
						}
	
						// naechster Spieler ist dran
						if (aktuellerSpieler.equals(spielbrett.getSpielerA())) {
							spielbrett.getSpielerA().setIstAmZug(false);
							spielbrett.getSpielerB().setIstAmZug(true);
						} else if (aktuellerSpieler.equals(spielbrett.getSpielerB())) {
							spielbrett.getSpielerB().setIstAmZug(false);
							spielbrett.getSpielerA().setIstAmZug(true);
						}
					}
				} else {
					aktuellerSpieler.setFehlerMeldung("Dieses Feld kann nicht von dir gespielt werden");
				}

			} else {
				// Wenn der Zug von einem Spieler ausgefuehrt wurde, obwohl
				// dieser Spieler nicht
				// am Zug ist, bekommt dieser Spieler eine Fehlermeldung
				if (spielbrett.getSpielerA().getSpielerName().equals(spielerName)) {
					spielbrett.getSpielerA().setFehlerMeldung("Du bist nicht am Zug");
				} else if (spielbrett.getSpielerB().getSpielerName().equals(spielerName)) {
					spielbrett.getSpielerB().setFehlerMeldung("Du bist nicht am Zug");
				}
			}
		}

		return spielbrett;
	}

	/**
	 * Prueft, ob der Spieler aktuellerSpieler die Mulde muldenNummer spielen
	 * darf. SpielerA darf die Mulden 0-5 bzw. 1-6 spielen SpielerB darf die
	 * Mulden 6-11 bzw. 7-12 spielen
	 * 
	 * @param aktuellerSpieler
	 * @param muldenNummer
	 * @return true wenn der Spieler die angegebene Mulde spielen darf
	 */
	private boolean spielerDarfFeldSpielen(Spieler aktuellerSpieler, int muldenNummer) {
		if (aktuellerSpieler.equals(spielbrett.getSpielerA())) {
			// Spieler A darf die Felder 0-5 spielen
			if (muldenNummer >= 0 && muldenNummer <= 5) {
				return true;
			}
		} else if (aktuellerSpieler.equals(spielbrett.getSpielerB())) {
			// Spieler B darf die Felder 6-11 spielen
			if (muldenNummer >= 6 && muldenNummer <= 11) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Wird lokal nach jedem Zug aufgerufen, um zu pruefen, ob das Spiel zu Ende
	 * ist.
	 * 
	 * @param aktuellerSpieler
	 * @return true wenn das Spiel zu Ende ist
	 */	
	public boolean istSpielZuEnde(Spieler aktuellerSpieler) {

		boolean alleFelderLeer = false;
		// Pruefen, ob der jeweils andere Spieler im naechsten Zug noch Steine zur
		// Verfuegung hat
		if (aktuellerSpieler.equals(spielbrett.getSpielerB())) {
			alleFelderLeer = true;
			for (int i = 0; i <= 5; i++) {
				if (spielbrett.getMulden()[i].getAnzahlSteine() > 0) {
					alleFelderLeer = false;
				}
			}
			if (alleFelderLeer) {
				bestimmeGewinner().setHatGewonnen(true);;
			}
		}
		if (aktuellerSpieler.equals(spielbrett.getSpielerA())) {
			alleFelderLeer = true;
			for (int i = 6; i <= 11; i++) {
				if (spielbrett.getMulden()[i].getAnzahlSteine() > 0) {
					alleFelderLeer = false;
				}
			}
			if (alleFelderLeer) {
				bestimmeGewinner().setHatGewonnen(true);
			}
		}
		return alleFelderLeer;
	}

	/**
	 * Wird von istSpielZuEnde benutzt um zu bestimmen, wer das Spiel gewonnen hat
	 * und prueft, welcher Spieler die meisten Steine gewonnen hat. 
	 */
	private Spieler bestimmeGewinner() {
		if(spielbrett.getSpielerA().getGewonneneSteine() > spielbrett.getSpielerB().getGewonneneSteine()) {
			return spielbrett.getSpielerA();
		} else {
			return spielbrett.getSpielerB();
		}
	}

	/**
	 * Startet ein neues Spiel, indem das alte Spielbrett auf null gesetzt wird.
	 * SpielerName wird benutzt, um im naechsten Spiel eine Meldung auszugeben,
	 * welcher Spieler das neue Spiel gestartet hat.
	 * 
	 * @param spielerName
	 * @return das neue Spielbrett
	 */
	public Spielbrett neuesSpielStarten(String spielerName) {
		Spielbrett spielbrettAlt = this.spielbrett;
		spielbrett = new Spielbrett();
		spielbrett.getSpielerA().setSpielerName(spielbrettAlt.getSpielerA().getSpielerName());
		spielbrett.getSpielerB().setSpielerName(spielbrettAlt.getSpielerB().getSpielerName());
		spielbrett.getSpielerA().setFehlerMeldung("Neues Spiel wurde gestartet");
		spielbrett.getSpielerB().setFehlerMeldung("Neues Spiel wurde gestartet");
		return spielbrett;
	}
}
