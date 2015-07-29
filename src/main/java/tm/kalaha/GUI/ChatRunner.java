package tm.kalaha.GUI;

import tm.kalaha.client.RMIClient;
import javafx.application.Platform;

/**
 * @author Tanja
 * 
 *         Die Chat-Runner Klasse wurde geschrieben um den Chat nach
 *         Veränderungen zu aktualisieren.
 */
public class ChatRunner extends Thread {

	private String nachricht;
	private SpielOberflaeche gui;
	private RMIClient client;

	public ChatRunner(final String nachricht, final SpielOberflaeche gui, final RMIClient client) {
		this.nachricht = nachricht;
		this.gui = gui;
		this.client = client;
		start();
	}

	@SuppressWarnings("restriction")
	public void run() {

		try {
			Platform.runLater(
					() -> this.gui.outputTxt.setText(client.getNeueNachricht() + "\n" + gui.outputTxt.getText()));
			Platform.runLater(() -> this.gui.inputTxt.setText(""));

		} catch (Exception e) {

		}
	}

}
