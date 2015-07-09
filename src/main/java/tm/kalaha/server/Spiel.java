package tm.kalaha.server;

public class Spiel {

	private Spielbrett spielbrett = null;
	
	public Spiel() {
		spielbrett = new Spielbrett();
	}
	
	public Spielbrett getSpielbrett() {
		return this.spielbrett;
	}

	public Spielbrett muldeSpieln(String spielerName, int muldenNummer) {
		spielbrett.getMulden()[muldenNummer].setAnzahlSteine(0);
		return spielbrett;
	}
	
	public boolean istSpielZuEine() {
		return false;
	}
	
	public void werHatGewonnen() {
		//mach was
	}

	public Spielbrett neuesSpielStarten(String spielerName) {
		//TODO Meldung setzen
		Spielbrett spielbrettAlt = this.spielbrett;
		spielbrett = new Spielbrett();
		spielbrett.getSpielerA().setSpielerName(spielbrettAlt.getSpielerA().getSpielerName());
		spielbrett.getSpielerB().setSpielerName(spielbrettAlt.getSpielerB().getSpielerName());
		return spielbrett;
	}
}
