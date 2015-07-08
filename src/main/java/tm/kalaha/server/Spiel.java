package tm.kalaha.server;

public class Spiel {

	private Spielbrett spielbrett = null;
	
	public Spiel() {
		spielbrett = new Spielbrett();
	}
	
	public Spielbrett getSpielbrett() {
		return this.spielbrett;
	}

}
