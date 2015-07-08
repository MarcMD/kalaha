package tm.kalaha.server;

import java.io.Serializable;

public class Spielbrett implements Serializable {
	
	private static final long serialVersionUID = -2338904243684188502L;
	
	private Mulde[] mulden = new Mulde[12];
	private Spieler spielerA = new Spieler();
	private Spieler spielerB = new Spieler();

	public Spielbrett() {}

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
