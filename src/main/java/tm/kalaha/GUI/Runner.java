package tm.kalaha.GUI;

import tm.kalaha.client.RMIClient;
import tm.kalaha.server.Spielbrett;
import javafx.application.Platform;
@SuppressWarnings("restriction")

public class Runner extends Thread {
	
	private Spielbrett spielbrett;
	private SpielOberflaeche gui;
	private RMIClient client;
	
	public Runner( final Spielbrett spielbrett, final SpielOberflaeche gui, final RMIClient client) {
		this.spielbrett = spielbrett;
		this.gui = gui;
		this.client = client;
		start();
	}
	
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
			
			if(spielbrett.getSpielerA().isIstAmZug()){
				Platform.runLater(() -> this.gui.istAmZug.setText(spielbrett.getSpielerA().getSpielerName()));
			}else{
				Platform.runLater(() -> this.gui.istAmZug.setText(spielbrett.getSpielerB().getSpielerName()));
			}
			
			if(client.getSpielerName().equals(spielbrett.getSpielerA().getSpielerName())){
				
				Platform.runLater(() -> this.gui.fehlerAusgabe.setText(spielbrett.getSpielerA().getFehlerMeldung()));
				
			}else if(client.getSpielerName().equals(spielbrett.getSpielerB().getSpielerName())){
				
				Platform.runLater(() -> this.gui.fehlerAusgabe.setText(spielbrett.getSpielerB().getFehlerMeldung()));
			}


		} catch (Exception e) {
			
		}
	}
}
