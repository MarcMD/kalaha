package tm.kalaha.server;

import java.io.Serializable;

/**
 * Stellt eine der zwoelf Mulden des Spielbretts dar. Mulde ist Serilizable und
 * kann daher mit dem Spielbrett an den RMIClient geschickt werden.
 * 
 * @author marc
 *
 */
public class Mulde implements Serializable {

	private static final long serialVersionUID = -6590538740262391481L;

	private int anzahlSteine = 4;

	/**
	 * Getter für die Anzahl der Steine
	 * @return Anzahl der Steine in der Mulde
	 */
	public int getAnzahlSteine() {
		return anzahlSteine;
	}

	/**
	 * Setter fuer die Anzahl der Steine in der Mulde
	 * @param anzahlSteine
	 */
	public void setAnzahlSteine(int anzahlSteine) {
		this.anzahlSteine = anzahlSteine;
	}
	
	/**
	 * Gibt die Anzahl der Steine zurueck und leert die Mulde
	 * @return Anzahl der genommenen Steine
	 */
	public int steineNemhen() {
		int i = anzahlSteine;
		anzahlSteine = 0;
		return i;
	}

	/*
	 * Fuegt genau einen Stein hinzu
	 */
	public void steinHinzufuegen() {
		anzahlSteine++;
	}

}
