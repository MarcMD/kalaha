package tm.kalaha.server;

import java.io.Serializable;

public class DatenInDaten implements Serializable {

	private static final long serialVersionUID = -1368773295615725537L;
	
	private String meinString;
	
	public DatenInDaten(String meinString) {
		this.meinString = meinString;
	}
	
	public String toString() {
		return meinString;
	}

}
