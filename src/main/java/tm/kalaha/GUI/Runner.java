package tm.kalaha.GUI;

import tm.kalaha.client.RMIClient;
import tm.kalaha.server.Spielbrett;
import javafx.application.Platform;

@SuppressWarnings("restriction")

/**
 * 
 * @author Tanja
 * 
 *         Die Runner Klasse wurde geschrieben um die GUI nach Veränderungen zu
 *         aktualisieren.
 *
 */
public class Runner extends Thread {

	private Spielbrett spielbrett;
	private SpielOberflaeche gui;
	private RMIClient client;

	public Runner(final Spielbrett spielbrett, final SpielOberflaeche gui, final RMIClient client) {
		this.spielbrett = spielbrett;
		this.gui = gui;
		this.client = client;
		start();
	}

	/**
	 * In der Methode wird festgelegt, welche Elemente auf welche Art und Weise
	 * aktualisiert werden sollen.
	 */
	public void run() {
		try {
			Platform.runLater(() -> this.gui.spielernameA.setText(spielbrett.getSpielerA().getSpielerName()));
			Platform.runLater(() -> this.gui.spielernameB.setText(spielbrett.getSpielerB().getSpielerName()));

			Platform.runLater(() -> this.gui.buttonA.setText("" + spielbrett.getSpielerA().getGewonneneSteine()));
			Platform.runLater(() -> this.gui.button0.setText("" + spielbrett.getMulden()[0].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button1.setText("" + spielbrett.getMulden()[1].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button2.setText("" + spielbrett.getMulden()[2].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button3.setText("" + spielbrett.getMulden()[3].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button4.setText("" + spielbrett.getMulden()[4].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button5.setText("" + spielbrett.getMulden()[5].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button6.setText("" + spielbrett.getMulden()[6].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button7.setText("" + spielbrett.getMulden()[7].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button8.setText("" + spielbrett.getMulden()[8].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button9.setText("" + spielbrett.getMulden()[9].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button10.setText("" + spielbrett.getMulden()[10].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.button11.setText("" + spielbrett.getMulden()[11].getAnzahlSteine()));
			Platform.runLater(() -> this.gui.buttonB.setText("" + spielbrett.getSpielerB().getGewonneneSteine()));

			/**
			 * Vor der Aktualisierung der Ausgabe welcher Spieler am Zug ist
			 * muss zuvor geprüft werden, welcher Spieler am Zug ist.
			 */
			if (spielbrett.getSpielerA().isIstAmZug()) {
				Platform.runLater(() -> this.gui.istAmZug
						.setText("Spieler '" + spielbrett.getSpielerA().getSpielerName() + "' ist am Zug."));
			} else {
				Platform.runLater(() -> this.gui.istAmZug
						.setText("Spieler '" + spielbrett.getSpielerB().getSpielerName() + "' ist am Zug."));
			}

			/**
			 * Die Abfrage stellt sicher, dass nur der Spieler, der einen Fehler
			 * begangen hat, die entsprechende Meldung erhält.
			 */
			if (client.getSpielerName().equals(spielbrett.getSpielerA().getSpielerName())) {

				Platform.runLater(() -> this.gui.fehlerAusgabe.setText(spielbrett.getSpielerA().getFehlerMeldung()));

			} else if (client.getSpielerName().equals(spielbrett.getSpielerB().getSpielerName())) {

				Platform.runLater(() -> this.gui.fehlerAusgabe.setText(spielbrett.getSpielerB().getFehlerMeldung()));
			}

		} catch (Exception e) {

		}

	}
}
