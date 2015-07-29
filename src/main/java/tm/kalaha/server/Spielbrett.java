package tm.kalaha.server;

import java.io.Serializable;

/**
 * Stellt das Spielbrett mit beiden Spielern und zwoelf Mulden dar. Spielbrett
 * ist Serilizable und kann daher an den RMIClient geschickt werden.
 * 
 * Diese Klasse enthält lediglich zwoelf Mulden bzw. ein entsprechendes Array, zwei Spieler 
 * (spielerA und spielerB) sowie Getter und Setter fuer diese Variablen. 
 * 
 * @author marc
 *
 */
public class Spielbrett implements Serializable {

	private static final long serialVersionUID = -2338904243684188502L;

	private Mulde[] mulden = new Mulde[12];
	private Spieler spielerA = new Spieler();
	private Spieler spielerB = new Spieler();

	/**
	 * Konstruktor. 
	 * Zwoelf Mulden werden in ein Array geschrieben. 
	 * SpielerA bekommt den ersten Zug. 
	 */
	public Spielbrett() {
		for (int i = 0; i < 12; i++) {
			mulden[i] = new Mulde();
		}
		spielerA.setIstAmZug(true);
		spielerB.setIstAmZug(false);
	}

	public Mulde[] getMulden() {
		return mulden;
	}

	public void setMulden(Mulde[] mulden) {
		this.mulden = mulden;
	}

	public Spieler getSpielerA() {
		return spielerA;
	}

	public void setSpielerA(Spieler spielerA) {
		this.spielerA = spielerA;
	}

	public Spieler getSpielerB() {
		return spielerB;
	}

	public void setSpielerB(Spieler spielerB) {
		this.spielerB = spielerB;
	}

}
