package tm.kalaha.server;

import java.io.Serializable;

/**
 * Stellt einen Spieler dar. 
 * Spieler ist Serilizable und
 * kann daher mit dem Spielbrett an den RMIClient geschickt werden.
 * 
 * Ein Spieler hat: 
 * - spielerName
 * - gewonnenenSteine
 * - fehlerMeldung
 * Ein Spieler kann am Zug sein oder nicht am Zug sein. Dieser Sachverhalt wird durch die Variable
 * istAmZug (boolean) verkoerpert. 
 * Ein Spieler kann gewonnen haben oder (noch) nicht gewonnen haben. Dieser Sachverhalt wurde durch die
 * Variable hatGewonnen (boolean) verkoerpert. 
 * 
 * Neben den genannten Variablen finden sich in dieser Klasse entsprechende Getter und Setter. 
 * 
 * Zudem findet sich in dieser Klasse die Methode steineGewinnen(int steine), die die übergebene 
 * Anzahl (von Steinen) zu der bereits gewonnen Anzahl von Steinen hinzufügt. 
 * 
 * @author marc
 *
 */
public class Spieler implements Serializable {
	
	private static final long serialVersionUID = -3442781897113668309L;
	
	private String spielerName = null;
	private int gewonneneSteine = 0;
	private boolean istAmZug;
	private String fehlerMeldung = null;
	private boolean hatGewonnen = false;

	public Spieler() {}

	public String getSpielerName() {
		return spielerName;
	}

	public void setSpielerName(String spielerName) {
		this.spielerName = spielerName;
	}

	public int getGewonneneSteine() {
		return gewonneneSteine;
	}

	public void setGewonneneSteine(int gewonneneSteine) {
		this.gewonneneSteine = gewonneneSteine;
	}

	public boolean isIstAmZug() {
		return istAmZug;
	}

	public void setIstAmZug(boolean istAmZug) {
		this.istAmZug = istAmZug;
	}

	public String getFehlerMeldung() {
		return fehlerMeldung;
	}

	public void setFehlerMeldung(String fehlerMeldung) {
		this.fehlerMeldung = fehlerMeldung;
	}

	public boolean isHatGewonnen() {
		return hatGewonnen;
	}

	public void setHatGewonnen(boolean hatGewonnen) {
		this.hatGewonnen = hatGewonnen;
	}
	
	/**
	 * Fügt dem Spieler die anzahl der in dem Zug gewonnen Steine hinzu
	 * @param steine gewonnene Steine
	 */
	public void steineGewinnen(int steine) {
		this.gewonneneSteine = this.gewonneneSteine + steine;
	}

}
