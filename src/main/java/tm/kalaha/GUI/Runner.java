package tm.kalaha.GUI;

import tm.kalaha.server.Spielbrett;
import javafx.application.Platform;
@SuppressWarnings("restriction")

public class Runner extends Thread {
	
	private Spielbrett spielbrett;
	private SpielOberflaeche gui;
	
	public Runner( final Spielbrett spielbrett, final SpielOberflaeche gui) {
		this.spielbrett = spielbrett;
		this.gui = gui;
		start();
	}
	
	public void run() {
		try {
			Platform.runLater(() -> this.gui.spielernameA.setText(spielbrett.getSpielerA().getSpielerName()));
			Platform.runLater(() -> this.gui.spielernameB.setText(spielbrett.getSpielerB().getSpielerName()));
		} catch (Exception e) {
			
		}
	}
}
