package tm.kalaha.server;

public class Spiel {

	private Spielbrett spielbrett = null;
	
	public Spiel() {
		spielbrett = new Spielbrett();
		spielbrett.getSpielerA().setSpielerName("marc");
		spielbrett.getSpielerB().setSpielerName("tanja");
	}
	
	public Spielbrett getSpielbrett() {
		return this.spielbrett;
	}

}
