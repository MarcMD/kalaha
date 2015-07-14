package tm.kalaha.server;

import java.io.Serializable;

public class Mulde implements Serializable {
	
	private static final long serialVersionUID = -6590538740262391481L;
	
	private int anzahlSteine = 4;

	public Mulde() {}

	public int getAnzahlSteine() {
		return anzahlSteine;
	}

	public void setAnzahlSteine(int anzahlSteine) {
		this.anzahlSteine = anzahlSteine;
	}
	
	/**
	 * Gibt die Anzahl der Steine zurück und leert die Mulde
	 */
	public int steineNemhen() {
		int i = anzahlSteine;
		anzahlSteine = 0;
		return i;
	}
	
	/*
	 * Fügt genau einen Stein hinzu
	 */
	public void steinHinzufügen() {
		anzahlSteine++;
	}
	
	

}
