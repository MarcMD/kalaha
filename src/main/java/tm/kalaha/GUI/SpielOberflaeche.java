package tm.kalaha.GUI;

import java.rmi.RemoteException;

import javafx.application.Platform;
//import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import tm.kalaha.client.RMIClient;
import tm.kalaha.server.Spielbrett;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.FontPosture;
import javafx.scene.shape.Circle;
import javafx.geometry.Pos;

@SuppressWarnings({ "restriction" })
public class SpielOberflaeche extends Application implements SpielbrettAction {

	Scene anmeldung = null;
	Scene aufSpielerWarten = null;
	Scene spielfeld = null;
	static RMIClient client;
	Label spielernameA = null;
	Label spielernameB = null;
	Label istAmZug = null;
	Label fehlerAusgabe = null;
	Button buttonA = null;
	Button button0 = null;
	Button button1 = null;
	Button button2 = null;
	Button button3 = null;
	Button button4 = null;
	Button button5 = null;
	Button button6 = null;
	Button button7 = null;
	Button button8 = null;
	Button button9 = null;
	Button button10 = null;
	Button button11 = null;
	Button buttonB = null;
									
			
	
		 public static void main(String[] args) {
			 try {
				 client = new RMIClient("DummyName");
				 

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        launch(args);
		 }
		    
		    @Override
		    public void start(Stage hauptfenster) {
		    	
		    	hauptfenster.setTitle("Kalaha");
		    	hauptfenster.centerOnScreen();
		    	
		    	//Client bekommt dieses Objekt übergeben und kann dann
		    	//die Methode spielbrettVeraendert() aufrufen
		    	client.setMeinUI(this);
		    	
//------------> Anmeldungs Scene
		    	GridPane gridAnmeldung = new GridPane();
		        gridAnmeldung.setAlignment(Pos.CENTER);
		        gridAnmeldung.setHgap(10);
		        gridAnmeldung.setVgap(10);
		        gridAnmeldung.setPadding(new Insets(25, 25, 25, 25));
		        
		        Text grussText = new Text("Willkommen in der wunderbaren Welt des Kalaha!");
		        grussText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		        grussText.setFill(Color.ANTIQUEWHITE);
		        gridAnmeldung.add(grussText, 0, 0, 2, 1);
		        
		        Label spielernameLabel = new Label("Spielername:");
		        spielernameLabel.setTextFill(Color.ANTIQUEWHITE);
		        gridAnmeldung.add(spielernameLabel, 0, 1);

		        TextField spielerNameText = new TextField();
		        gridAnmeldung.add(spielerNameText, 1, 1);
		        
		        Label ipAdresse = new Label("IP - Adresse:");
		        ipAdresse.setTextFill(Color.ANTIQUEWHITE);
		        gridAnmeldung.add(ipAdresse, 0, 2);
		        
		        TextField ipAdresseText = new TextField();
		        gridAnmeldung.add(ipAdresseText, 1, 2);
		        
//		        DropShadow ds = new DropShadow();
//		        ds.setOffsetY(3.0f);
//		        ds.setOffsetX(5.0f);
//		        ds.setColor(Color.BROWN);
//		        
		        InnerShadow is = new InnerShadow();
		        is.setOffsetY(0.3f);
		        is.setColor(Color.BLACK);
		        
		        Button anmeldungBtn = new Button("Anmelden");
		        anmeldungBtn.setTextFill(Color.ANTIQUEWHITE);
		        anmeldungBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		        anmeldungBtn.setStyle(" -fx-base: #9F7347;");
		        anmeldungBtn.setEffect(is);
		        //anmeldungBtn.setStyle("-fx-stroke: black;");
		        //anmeldungBtn.setEffect(ds);
		        
		        Button beendenBtn = new Button("Beenden");
		        beendenBtn.setTextFill(Color.ANTIQUEWHITE);
		        beendenBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		        beendenBtn.setStyle(" -fx-base: #9F7347;");
		        beendenBtn.setEffect(is);
		        
		        HBox hboxBtn = new HBox(10);
		        hboxBtn.setAlignment(Pos.BOTTOM_RIGHT);
		        hboxBtn.getChildren().add(anmeldungBtn);
		        hboxBtn.getChildren().add(beendenBtn);
		        gridAnmeldung.add(hboxBtn, 1, 3);
		        
		        anmeldung = new Scene(gridAnmeldung, 500, 300);
		        anmeldung.getStylesheets().add
                (SpielOberflaeche.class.getResource("Background.css").toExternalForm());
		        
		        beendenBtn.setOnAction(e -> {
//			     	   Platform.exit();
		        	hauptfenster.close();
		     	});
		        
		        anmeldungBtn.setOnAction(new EventHandler<ActionEvent>() {
			        
		        	@Override
			        public void handle(ActionEvent e) {
		
		        		hauptfenster.setScene(spielfeld);
		        		hauptfenster.centerOnScreen();
						client.setSpielerName(spielerNameText.getText());
						client.setHost(ipAdresseText.getText());
						client.anmelden();


		        	}
		        });
		        
//------------> Ende Anmeldungs Scene
		        

   	        	
//------------> Start Spielfeld Scene
   	        	spielernameA = new Label(client.getSpielbrett().getSpielerA().getSpielerName());
                spielernameA.setTextFill(Color.web("#0099FF"));
                spielernameA.setFont(Font.font("Arial", FontWeight.NORMAL, 26));
                spielernameA.setStyle("-fx-border-width: 2px; -fx-border-color: white;");
                
                spielernameB = new Label(client.getSpielbrett().getSpielerB().getSpielerName());
                spielernameB.setTextFill(Color.web("#009900"));
                spielernameB.setFont(Font.font("Arial", FontWeight.NORMAL, 30));

//                String werIstDran = null;
//                if(client.getSpielbrett().getSpielerA().isIstAmZug()){
//                	werIstDran = client.getSpielbrett().getSpielerA().getSpielerName();
//                }else{
//                	werIstDran = client.getSpielbrett().getSpielerB().getSpielerName();
//                }
                
                istAmZug = new Label();
                istAmZug.setTextFill(Color.ANTIQUEWHITE);
                istAmZug.setFont(Font.font("Arial", FontPosture.ITALIC, 22));
                
                fehlerAusgabe = new Label();
                fehlerAusgabe.setTextFill(Color.web("#FF4D4D"));
                fehlerAusgabe.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                
                Label textEingabe = new Label("Eingabe :");
                textEingabe.setTextFill(Color.ANTIQUEWHITE);
                textEingabe.setFont(Font.font("Arial", FontPosture.ITALIC, 16));
                
                TextArea outputTxt;
                TextField inputTxt;
                
                outputTxt = new TextArea();
                outputTxt.setDisable(false);
                outputTxt.setEditable(false);
                outputTxt.setFocusTraversable(false);
                outputTxt.setText("TestChatText...");

                inputTxt = new TextField();
                inputTxt.setFocusTraversable(true);
      	
                buttonA = new Button();
                buttonA.setText("" + client.getSpielbrett().getSpielerA().getGewonneneSteine());
                double radiusSammelMulden =10;
                buttonA.setShape(new Circle(radiusSammelMulden));
                buttonA.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonBlue.css").toExternalForm());
                buttonA.setMinWidth(120);
                buttonA.setMinHeight(260);
                                
                button0 = new Button();
                button0.setText("" + client.getSpielbrett().getMulden()[0].getAnzahlSteine());
                double r = 30;
                button0.setShape(new Circle(r));
   	        	button0.setMinSize(2*r, 2*r);
   	        	button0.setMaxSize(2*r, 2*r);
                button0.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonBlue.css").toExternalForm());
                button0.setMinWidth(120);
                button0.setMinHeight(120);
                //EventHandler for Buttons
                button0.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        client.muldeSpielen(0);
                    }
                });
                
                button1 = new Button();
                button1.setText("" + client.getSpielbrett().getMulden()[1].getAnzahlSteine());
                button1.setShape(new Circle(r));
   	        	button1.setMinSize(2*r, 2*r);
   	        	button1.setMaxSize(2*r, 2*r);
                button1.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonBlue.css").toExternalForm());
                button1.setMinWidth(120);
                button1.setMinHeight(120);
                //EventHandler for Buttons
                button1.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(1);
                    }
                });
                
                button2 = new Button();
                button2.setText("" + client.getSpielbrett().getMulden()[2].getAnzahlSteine());
                button2.setShape(new Circle(r));
   	        	button2.setMinSize(2*r, 2*r);
   	        	button2.setMaxSize(2*r, 2*r);
                button2.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonBlue.css").toExternalForm());
                button2.setMinWidth(120);
                button2.setMinHeight(120);
                //EventHandler for Buttons
                button2.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(2);
                    }
                });
                
                button3 = new Button();
                button3.setText("" + client.getSpielbrett().getMulden()[3].getAnzahlSteine());
                button3.setShape(new Circle(r));
   	        	button3.setMinSize(2*r, 2*r);
   	        	button3.setMaxSize(2*r, 2*r);
                button3.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonBlue.css").toExternalForm());
                button3.setMinWidth(120);
                button3.setMinHeight(120);
                //EventHandler for Buttons
                button3.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(3);
                    }
                });
                
                button4 = new Button();
                button4.setText("" + client.getSpielbrett().getMulden()[4].getAnzahlSteine());
                button4.setShape(new Circle(r));
   	        	button4.setMinSize(2*r, 2*r);
   	        	button4.setMaxSize(2*r, 2*r);
                button4.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonBlue.css").toExternalForm());
                button4.setMinWidth(120);
                button4.setMinHeight(120);
                //EventHandler for Buttons
                button4.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(4);
                    }
                });
                
                button5 = new Button();
                button5.setText("" + client.getSpielbrett().getMulden()[5].getAnzahlSteine());
                button5.setShape(new Circle(r));
   	        	button5.setMinSize(2*r, 2*r);
   	        	button5.setMaxSize(2*r, 2*r);
                button5.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonBlue.css").toExternalForm());
                button5.setMinWidth(120);
                button5.setMinHeight(120);
                //EventHandler for Buttons
                button5.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(5);
                    }
                });
                
                button6 = new Button();
                button6.setText("" + client.getSpielbrett().getMulden()[6].getAnzahlSteine());
                button6.setShape(new Circle(r));
   	        	button6.setMinSize(2*r, 2*r);
   	        	button6.setMaxSize(2*r, 2*r);
                button6.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonGreen.css").toExternalForm());
                button6.setMinWidth(120);
                button6.setMinHeight(120);
                //EventHandler for Buttons
                button6.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(6);
                    }
                });
                
                button7 = new Button();
                button7.setText("" + client.getSpielbrett().getMulden()[7].getAnzahlSteine());
                button7.setShape(new Circle(r));
   	        	button7.setMinSize(2*r, 2*r);
   	        	button7.setMaxSize(2*r, 2*r);
                button7.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonGreen.css").toExternalForm());
                button7.setMinWidth(120);
                button7.setMinHeight(120);
                //EventHandler for Buttons
                button7.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(7);
                    }
                });
                
                button8 = new Button();
                button8.setText("" + client.getSpielbrett().getMulden()[8].getAnzahlSteine());
                button8.setShape(new Circle(r));
   	        	button8.setMinSize(2*r, 2*r);
   	        	button8.setMaxSize(2*r, 2*r);
                button8.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonGreen.css").toExternalForm());
                button8.setMinWidth(120);
                button8.setMinHeight(120);
                //EventHandler for Buttons
                button8.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(8);
                    }
                });
                
                button9 = new Button();
                button9.setText("" + client.getSpielbrett().getMulden()[9].getAnzahlSteine());
                button9.setShape(new Circle(r));
   	        	button9.setMinSize(2*r, 2*r);
   	        	button9.setMaxSize(2*r, 2*r);
                button9.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonGreen.css").toExternalForm());
                button9.setMinWidth(120);
                button9.setMinHeight(120);
                //EventHandler for Buttons
                button9.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(9);
                    }
                });
                
                button10 = new Button();
                button10.setText("" + client.getSpielbrett().getMulden()[10].getAnzahlSteine());
                button10.setShape(new Circle(r));
   	        	button10.setMinSize(2*r, 2*r);
   	        	button10.setMaxSize(2*r, 2*r);
                button10.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonGreen.css").toExternalForm());
                button10.setMinWidth(120);
                button10.setMinHeight(120);
                //EventHandler for Buttons
                button10.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(10);
                    }
                });
                
                button11 = new Button();
                button11.setText("" + client.getSpielbrett().getMulden()[11].getAnzahlSteine());
                button11.setShape(new Circle(r));
   	        	button11.setMinSize(2*r, 2*r);
   	        	button11.setMaxSize(2*r, 2*r);
                button11.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonGreen.css").toExternalForm());
                button11.setMinWidth(120);
                button11.setMinHeight(120);
                //EventHandler for Buttons
                button11.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                    	client.muldeSpielen(11);
                    }
                });
                
                buttonB = new Button();
                buttonB.setText("" + client.getSpielbrett().getSpielerB().getGewonneneSteine());
                buttonB.setShape(new Circle(radiusSammelMulden));
                buttonB.getStylesheets().add
                 (SpielOberflaeche.class.getResource("ButtonGreen.css").toExternalForm());
                buttonB.setMinWidth(120);
                buttonB.setMinHeight(260);
                buttonB.setStyle("-fx-stroke: firebrick; -fx-stroke-width: 2px;");
             
                
                GridPane gridSpielfeld = new GridPane();
	               
                gridSpielfeld.setAlignment(Pos.CENTER);
                gridSpielfeld.setHgap(20);
                gridSpielfeld.setVgap(20);
                gridSpielfeld.setPadding(new Insets(25, 25, 25, 25));
//              grid.setGridLinesVisible(true);
                
                gridSpielfeld.add(spielernameA, 1, 1, 1, 1);
                gridSpielfeld.add(spielernameB, 8, 1, 1, 1);
                gridSpielfeld.add(istAmZug, 4, 1, 4, 1);
                
                gridSpielfeld.add(buttonA, 1, 2, 1, 2);
                gridSpielfeld.add(button0, 7, 2);
                gridSpielfeld.add(button1, 6, 2);
                gridSpielfeld.add(button2, 5, 2);
                gridSpielfeld.add(button3, 4, 2);
                gridSpielfeld.add(button4, 3, 2);
                gridSpielfeld.add(button5, 2, 2);
                
                gridSpielfeld.add(button6, 2, 3);
                gridSpielfeld.add(button7, 3, 3);
                gridSpielfeld.add(button8, 4, 3);
                gridSpielfeld.add(button9, 5, 3);
                gridSpielfeld.add(button10, 6, 3);
                gridSpielfeld.add(button11, 7, 3);
                gridSpielfeld.add(buttonB, 8, 2 , 1, 2);
                
                gridSpielfeld.add(fehlerAusgabe, 3, 4, 4, 1);
                gridSpielfeld.add(textEingabe, 2, 6);
                
                gridSpielfeld.add(outputTxt, 3, 5, 4, 1);
                gridSpielfeld.add(inputTxt, 3, 6, 4, 1);
                
                spielfeld = new Scene(gridSpielfeld, 1200, 700);
                spielfeld.getStylesheets().add
                (SpielOberflaeche.class.getResource("Background.css").toExternalForm());
//------------>	Ende Spielfeld Scene  
                
                
                hauptfenster.setScene(anmeldung);
                hauptfenster.show();

		    }

			@Override
		    public void spielbrettVeraendert(Spielbrett spielbrett) {
				System.out.println("spielbrettVeraendert wurde aufgerufen");
				new Runner(spielbrett, this, client);
			}

}
