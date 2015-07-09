package tm.kalaha.server;

import java.io.Serializable;

public class Daten implements Serializable {
	

	private static final long serialVersionUID = -1818823037087669426L;
	public int i;
	public int j;
	
	public Daten(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public String toString() {
		return "i = "+i+" j = "+j;
	}

}
