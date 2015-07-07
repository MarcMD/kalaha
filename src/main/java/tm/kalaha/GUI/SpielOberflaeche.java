package tm.kalaha.GUI;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.FontPosture;
import javafx.scene.shape.Circle;

@SuppressWarnings({ "restriction" })
public class SpielOberflaeche extends Application {

	Scene anmeldung = null;
	Scene aufSpielerWarten = null;
	Scene spielfeld = null;
	
		 public static void main(String[] args) {
		        launch(args);
		 }
		    
		    @Override
		    public void start(Stage hauptfenster) {
		    	hauptfenster.setTitle("Kalaha");
		    	hauptfenster.centerOnScreen();
		    	
		    	
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
		        
		        Button anmeldungBtn = new Button("Anmelden");
		        anmeldungBtn.setTextFill(Color.BROWN);
		        anmeldungBtn.setStyle(" -fx-base: #FFFFFA;");
		        
		        HBox hboxBtn = new HBox(10);
		        hboxBtn.setAlignment(Pos.BOTTOM_RIGHT);
		        hboxBtn.getChildren().add(anmeldungBtn);
		        gridAnmeldung.add(hboxBtn, 1, 4);
		        
		        anmeldung = new Scene(gridAnmeldung, 500, 300);
		        anmeldung.getStylesheets().add
                (StartGUI.class.getResource("Background.css").toExternalForm());
		        
//------------> Ende Anmeldungs Scene
		        
//------------>	Start Warte Scene
		        GridPane gridWarten = new GridPane();
    	        gridWarten.setAlignment(Pos.CENTER);
    	        gridWarten.setHgap(100);
    	        gridWarten.setVgap(100);
    	        gridWarten.setPadding(new Insets(25, 25, 25, 25));
    	        //grid.setGridLinesVisible(true);
    	        
		        Text warteText = new Text("Warten auf Spieler2 ...");
               	warteText.setFont(Font.font("Tahoma", FontWeight.BOLD, 22));
   	        	warteText.setFill(Color.BLACK);
   	        	gridWarten.add(warteText, 1, 1, 1,1);	 
   	        	
   	        	Button mockButton = new Button();
   	        	mockButton.setText("MockAction");
   	        	double r=30;
   	        	mockButton.setShape(new Circle(r));
   	        	mockButton.setMinSize(2*r, 2*r);
   	        	mockButton.setMaxSize(2*r, 2*r);
   	        	gridWarten.add(mockButton, 1, 2,1 ,1 );
   	        	
   	        	
   	        	aufSpielerWarten = new Scene(gridWarten, 400, 400);
   	        	aufSpielerWarten.getStylesheets().add
                (StartGUI.class.getResource("Background.css").toExternalForm());
//------------>	Ende Warte Scene	   
   	        	
//------------> Start Spielfeld Scene
   	        	Label spielernameA = new Label("Tanja");
                spielernameA.setTextFill(Color.web("#0099FF"));
                spielernameA.setFont(Font.font("Arial", FontWeight.NORMAL, 26));
                
                Label spielernameB = new Label("Julia");
                spielernameB.setTextFill(Color.web("#009900"));
                spielernameB.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
                
                Label istAmZug = new Label("Spieler 'DummyText' ist am Zug.");
                istAmZug.setTextFill(Color.ANTIQUEWHITE);
                istAmZug.setFont(Font.font("Arial", FontPosture.ITALIC, 22));
   	        	
                Button buttonA = new Button();
                buttonA.setText("0");
                double radiusSammelMulden =10;
                buttonA.setShape(new Circle(radiusSammelMulden));
                buttonA.getStylesheets().add
                 (StartGUI.class.getResource("ButtonBlue.css").toExternalForm());
                buttonA.setMinWidth(120);
                buttonA.setMinHeight(260);
                
                Button button1 = new Button();
                button1.setText("4");
                button1.setShape(new Circle(r));
   	        	button1.setMinSize(2*r, 2*r);
   	        	button1.setMaxSize(2*r, 2*r);
                button1.getStylesheets().add
                 (StartGUI.class.getResource("ButtonBlue.css").toExternalForm());
                button1.setMinWidth(120);
                button1.setMinHeight(120);
                //EventHandler for Buttons
                button1.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button1.setText("0");
                    }
                });
                
                Button button2 = new Button();
                button2.setText("4");
                button2.setShape(new Circle(r));
   	        	button2.setMinSize(2*r, 2*r);
   	        	button2.setMaxSize(2*r, 2*r);
                button2.getStylesheets().add
                 (StartGUI.class.getResource("ButtonBlue.css").toExternalForm());
                button2.setMinWidth(120);
                button2.setMinHeight(120);
                //EventHandler for Buttons
                button2.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button2.setText("0");
                    }
                });
                
                Button button3 = new Button();
                button3.setText("4");
                button3.setShape(new Circle(r));
   	        	button3.setMinSize(2*r, 2*r);
   	        	button3.setMaxSize(2*r, 2*r);
                button3.getStylesheets().add
                 (StartGUI.class.getResource("ButtonBlue.css").toExternalForm());
                button3.setMinWidth(120);
                button3.setMinHeight(120);
                //EventHandler for Buttons
                button3.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button3.setText("0");
                    }
                });
                
                Button button4 = new Button();
                button4.setText("4");
                button4.setShape(new Circle(r));
   	        	button4.setMinSize(2*r, 2*r);
   	        	button4.setMaxSize(2*r, 2*r);
                button4.getStylesheets().add
                 (StartGUI.class.getResource("ButtonBlue.css").toExternalForm());
                button4.setMinWidth(120);
                button4.setMinHeight(120);
                //EventHandler for Buttons
                button4.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button4.setText("0");
                    }
                });
                
                Button button5 = new Button();
                button5.setText("4");
                button5.setShape(new Circle(r));
   	        	button5.setMinSize(2*r, 2*r);
   	        	button5.setMaxSize(2*r, 2*r);
                button5.getStylesheets().add
                 (StartGUI.class.getResource("ButtonBlue.css").toExternalForm());
                button5.setMinWidth(120);
                button5.setMinHeight(120);
                //EventHandler for Buttons
                button5.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button5.setText("0");
                    }
                });
                
                Button button6 = new Button();
                button6.setText("4");
                button6.setShape(new Circle(r));
   	        	button6.setMinSize(2*r, 2*r);
   	        	button6.setMaxSize(2*r, 2*r);
                button6.getStylesheets().add
                 (StartGUI.class.getResource("ButtonBlue.css").toExternalForm());
                button6.setMinWidth(120);
                button6.setMinHeight(120);
                //EventHandler for Buttons
                button6.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button6.setText("0");
                    }
                });
                
                Button button7 = new Button();
                button7.setText("4");
                button7.setShape(new Circle(r));
   	        	button7.setMinSize(2*r, 2*r);
   	        	button7.setMaxSize(2*r, 2*r);
                button7.getStylesheets().add
                 (StartGUI.class.getResource("ButtonGreen.css").toExternalForm());
                button7.setMinWidth(120);
                button7.setMinHeight(120);
                //EventHandler for Buttons
                button7.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button7.setText("0");
                    }
                });
                
                Button button8 = new Button();
                button8.setText("4");
                button8.setShape(new Circle(r));
   	        	button8.setMinSize(2*r, 2*r);
   	        	button8.setMaxSize(2*r, 2*r);
                button8.getStylesheets().add
                 (StartGUI.class.getResource("ButtonGreen.css").toExternalForm());
                button8.setMinWidth(120);
                button8.setMinHeight(120);
                //EventHandler for Buttons
                button8.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button8.setText("0");
                    }
                });
                
                Button button9 = new Button();
                button9.setText("4");
                button9.setShape(new Circle(r));
   	        	button9.setMinSize(2*r, 2*r);
   	        	button9.setMaxSize(2*r, 2*r);
                button9.getStylesheets().add
                 (StartGUI.class.getResource("ButtonGreen.css").toExternalForm());
                button9.setMinWidth(120);
                button9.setMinHeight(120);
                //EventHandler for Buttons
                button9.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button9.setText("0");
                    }
                });
                
                Button button10 = new Button();
                button10.setText("4");
                button10.setShape(new Circle(r));
   	        	button10.setMinSize(2*r, 2*r);
   	        	button10.setMaxSize(2*r, 2*r);
                button10.getStylesheets().add
                 (StartGUI.class.getResource("ButtonGreen.css").toExternalForm());
                button10.setMinWidth(120);
                button10.setMinHeight(120);
                //EventHandler for Buttons
                button10.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button10.setText("0");
                    }
                });
                
                Button button11 = new Button();
                button11.setText("4");
                button11.setShape(new Circle(r));
   	        	button11.setMinSize(2*r, 2*r);
   	        	button11.setMaxSize(2*r, 2*r);
                button11.getStylesheets().add
                 (StartGUI.class.getResource("ButtonGreen.css").toExternalForm());
                button11.setMinWidth(120);
                button11.setMinHeight(120);
                //EventHandler for Buttons
                button11.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button11.setText("0");
                    }
                });
                
                Button button12 = new Button();
                button12.setText("4");
                button12.setShape(new Circle(r));
   	        	button12.setMinSize(2*r, 2*r);
   	        	button12.setMaxSize(2*r, 2*r);
                button12.getStylesheets().add
                 (StartGUI.class.getResource("ButtonGreen.css").toExternalForm());
                button12.setMinWidth(120);
                button12.setMinHeight(120);
                //EventHandler for Buttons
                button12.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        button12.setText("0");
                    }
                });
                
                Button buttonB = new Button();
                buttonB.setText("0");
                buttonB.setShape(new Circle(radiusSammelMulden));
                buttonB.getStylesheets().add
                 (StartGUI.class.getResource("ButtonGreen.css").toExternalForm());
                buttonB.setMinWidth(120);
                buttonB.setMinHeight(260);
                //EventHandler for Buttons
                buttonB.setOnAction(new EventHandler<ActionEvent>() {
         
                    @Override
                    public void handle(ActionEvent event) {
                        buttonB.setText("0");
                    }
                });
                
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
                gridSpielfeld.add(button1, 2, 2);
                gridSpielfeld.add(button2, 3, 2);
                gridSpielfeld.add(button3, 4, 2);
                gridSpielfeld.add(button4, 5, 2);
                gridSpielfeld.add(button5, 6, 2);
                gridSpielfeld.add(button6, 7, 2);
                
                gridSpielfeld.add(button7, 2, 3);
                gridSpielfeld.add(button8, 3, 3);
                gridSpielfeld.add(button9, 4, 3);
                gridSpielfeld.add(button10, 5, 3);
                gridSpielfeld.add(button11, 6, 3);
                gridSpielfeld.add(button12, 7, 3);
                gridSpielfeld.add(buttonB, 8, 2 , 1, 2);
                
                spielfeld = new Scene(gridSpielfeld, 1200, 700);
                spielfeld.getStylesheets().add
                (StartGUI.class.getResource("Background.css").toExternalForm());
//------------>	Ende Spielfeld Scene  
                
                
                hauptfenster.setScene(anmeldung);
                hauptfenster.show();
		        
		        anmeldungBtn.setOnAction(new EventHandler<ActionEvent>() {
		        
		        	@Override
			        public void handle(ActionEvent e) {
		        		hauptfenster.setScene(aufSpielerWarten);
		        		hauptfenster.centerOnScreen();
		        		/*
		        		 * aufSpielerWarten soll angezeigt werden, bis vom Server 
		        		 * gemeldet wird dass der 2. Spieler da ist
		        		 */

		        	}
		        });
		        
		        mockButton.setOnAction(new EventHandler<ActionEvent>() {
		            
                    @Override
                    public void handle(ActionEvent event) {
                        mockButton.setText("0");
                        hauptfenster.setScene(spielfeld);
                        hauptfenster.centerOnScreen();
                
                    }
                });
		       
		        	
		        

		    	
		    }

}
