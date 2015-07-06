package tm.kalaha;

public class Spieler {
	
	private String name;
	private boolean istAmZug;

	public Spieler() {
		// TODO Auto-generated constructor stub
		istAmZug=false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIstAmZug() {
		return istAmZug;
	}

	public void setIstAmZug(boolean istAmZug) {
		this.istAmZug = istAmZug;
	}

}
