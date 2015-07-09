package tm.kalaha.server;

import java.io.Serializable;

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

}
