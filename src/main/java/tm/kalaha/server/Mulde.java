package tm.kalaha.server;

import java.io.Serializable;

public class Mulde implements Serializable {
	
	private static final long serialVersionUID = -6590538740262391481L;
	
	private int anzahlSteine = 4;

	public Mulde() {
		// TODO Auto-generated constructor stub
	}

	public int getAnzahlSteine() {
		return anzahlSteine;
	}

	public void setAnzahlSteine(int anzahlSteine) {
		this.anzahlSteine = anzahlSteine;
	}
	
	

}
