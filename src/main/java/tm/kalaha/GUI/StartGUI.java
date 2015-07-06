package tm.kalaha.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;



@SuppressWarnings({ "restriction" })
public class StartGUI extends Application {
	
	static Spieler spielerA = new Spieler();
	static Spieler spielerB = new Spieler();
	String werIstDran = null;
	

	 public static void main(String[] args) {
	        launch(args);
	       
	        
	 }
	    
	    @Override
	    public void start(Stage anmeldungsStage) {
	        anmeldungsStage.setTitle("Kalaha");
	        spielerA.setIstAmZug(true);
	        
	        //Hello World Code-----Start
//	        Button btn = new Button();
//	        btn.setText("Say 'Hello World'");
//	        //EventHandler for Buttons
//	        btn.setOnAction(new EventHandler<ActionEvent>() {
//	 
//	            @Override
//	            public void handle(ActionEvent event) {
//	                System.out.println("Hello World!");
//	            }
//	        });
//	        
//	        StackPane root = new StackPane();
//	        root.getChildren().add(btn);
//	        primaryStage.setScene(new Scene(root, 300, 250));
	        //------End

	        //GridLayout
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        //grid.setGridLinesVisible(true);
	        
	        Text scenetitle = new Text("Willkommen in der wunderbaren Welt des Kalaha!");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        scenetitle.setFill(Color.ANTIQUEWHITE);
	        grid.add(scenetitle, 0, 0, 2, 1);

	        Label userName = new Label("Spielername:");
	        userName.setTextFill(Color.ANTIQUEWHITE);
	        grid.add(userName, 0, 1);

	        TextField spielerNameText = new TextField();
	        grid.add(spielerNameText, 1, 1);

	        
	        Button btn = new Button("Anmelden");
	        btn.setTextFill(Color.BROWN);
	        btn.setStyle(" -fx-base: #FFFFFA;");
	        HBox hbBtn = new HBox(10);
	        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	        hbBtn.getChildren().add(btn);
	        grid.add(hbBtn, 1, 4);
	        
	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 6);
	        
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent e) {
	            	Stage spielStage = new Stage();
	            	//
	            	//
	            	//
	            	//Stage Code von HauptGUI-Klasse
	            	spielStage.setTitle("Kalaha");
	            	
	            	spielerA.setName(spielerNameText.getText());
					spielerB.setName("SpielerB");
					
					VBox topContainer = new VBox();  //Creates a container to hold all Menu Objects.
					MenuBar mainMenu = new MenuBar();  //Creates our main menu to hold our Sub-Menus.
					
					topContainer.getChildren().add(mainMenu);

					
					final Menu menuSpiel = new Menu("Spiel");
					 final Menu menuOptionen = new Menu("Optionen");
					 final Menu menuHilfe = new Menu("Hilfe");
					 
					 mainMenu.getMenus().addAll(menuSpiel, menuOptionen, menuHilfe);
	            	
	            	Label spielernameA = new Label(spielerA.getName());
	                spielernameA.setTextFill(Color.web("#FFFF66"));
	                spielernameA.setFont(new Font("Arial", 30));
	                
	                Label spielernameB = new Label("Spieler B");
	                spielernameB.setTextFill(Color.web("#FF1919"));
	                spielernameB.setFont(new Font("Arial", 30));
	                
	                //Backend-Code
	                //Variablen müssen pro Zug angepasst werden
	                
	                if(spielerA.isIstAmZug()){
	                	werIstDran = spielerA.getName();
	                }else{
	                	werIstDran = spielerB.getName();
	                }
	                //Ende Backend-Code
	                
	                
	                Label istAmZug = new Label("Spieler '" + werIstDran +"' ist am Zug.");
	                istAmZug.setTextFill(Color.ANTIQUEWHITE);
	                istAmZug.setFont(new Font ("Arial", 22));
	        		
	        		Button buttonA = new Button();
	                buttonA.setText("0");
	                buttonA.getStylesheets().add
	                 (StartGUI.class.getResource("ButtonBlue.css").toExternalForm());
	                buttonA.setMinWidth(120);
	                buttonA.setMinHeight(260);
	                
	                Button button1 = new Button();
	                button1.setText("4");
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
	                button7.getStylesheets().add
	                 (StartGUI.class.getResource("ButtonRed.css").toExternalForm());
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
	                button8.getStylesheets().add
	                 (StartGUI.class.getResource("ButtonRed.css").toExternalForm());
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
	                button9.getStylesheets().add
	                 (StartGUI.class.getResource("ButtonRed.css").toExternalForm());
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
	                button10.getStylesheets().add
	                 (StartGUI.class.getResource("ButtonRed.css").toExternalForm());
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
	                button11.getStylesheets().add
	                 (StartGUI.class.getResource("ButtonRed.css").toExternalForm());
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
	                button12.getStylesheets().add
	                 (StartGUI.class.getResource("ButtonRed.css").toExternalForm());
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
	                buttonB.getStylesheets().add
	                 (StartGUI.class.getResource("ButtonRed.css").toExternalForm());
	                buttonB.setMinWidth(120);
	                buttonB.setMinHeight(260);
	                //EventHandler for Buttons
	                buttonB.setOnAction(new EventHandler<ActionEvent>() {
	         
	                    @Override
	                    public void handle(ActionEvent event) {
	                        buttonB.setText("0");
	                    }
	                });
	                
	                GridPane grid = new GridPane();
	               
	                grid.setAlignment(Pos.CENTER);
	                grid.setHgap(20);
	                grid.setVgap(20);
	                grid.setPadding(new Insets(25, 25, 25, 25));
	              //  grid.setGridLinesVisible(true);
	                
	                grid.add(spielernameA, 1, 1, 1, 1);
	                grid.add(spielernameB, 8, 1, 1, 1);
	                grid.add(istAmZug, 4, 1, 4, 1);
	                
	                grid.add(buttonA, 1, 2, 1, 2);
	                grid.add(button1, 2, 2);
	                grid.add(button2, 3, 2);
	                grid.add(button3, 4, 2);
	                grid.add(button4, 5, 2);
	                grid.add(button5, 6, 2);
	                grid.add(button6, 7, 2);
	                
	                grid.add(button7, 2, 3);
	                grid.add(button8, 3, 3);
	                grid.add(button9, 4, 3);
	                grid.add(button10, 5, 3);
	                grid.add(button11, 6, 3);
	                grid.add(button12, 7, 3);
	                grid.add(buttonB, 8, 2 , 1, 2);
	                
	              
	                
	                
	                
	                Scene scene = new Scene(grid, 300, 275);
	                
	                spielStage.setScene(scene);
	               
	                spielStage.setMinHeight(700);
	                spielStage.setMinWidth(1200);
	                scene.getStylesheets().add
	                 (StartGUI.class.getResource("Background.css").toExternalForm());
	                spielStage.show();
	                
	                GridPane gridWarten = new GridPane();
	    	        gridWarten.setAlignment(Pos.CENTER);
	    	        gridWarten.setHgap(100);
	    	        gridWarten.setVgap(100);
	    	        gridWarten.setPadding(new Insets(25, 25, 25, 25));
	    	        //grid.setGridLinesVisible(true);
	                
	                Stage wartenAufSpieler2 = new Stage();
	                wartenAufSpieler2.setTitle("Warten auf Gegenspieler");
	                wartenAufSpieler2.initOwner(spielStage); //BLockiert spielStage
	                wartenAufSpieler2.initModality(Modality.WINDOW_MODAL);//BLockiert spielStage
	                wartenAufSpieler2.show();
	                
	                
	                Scene sceneWarten = new Scene(gridWarten,300,275);
	                wartenAufSpieler2.setScene(sceneWarten);
	                wartenAufSpieler2.setMinHeight(800);
	                wartenAufSpieler2.setMinWidth(1300);
	             
	                sceneWarten.getStylesheets().add
	                 (StartGUI.class.getResource("WarteBackground.css").toExternalForm());
	                Text warteText = new Text("Warten auf Spieler2 ...");
	    	        warteText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
	    	        warteText.setFill(Color.BLACK);
	                gridWarten.add(warteText, 4, 2, 2,1);	                
	                anmeldungsStage.hide();
	            	
	                actiontarget.setFill(Color.FIREBRICK);
	                actiontarget.setText("Everything worked better than expected!");
	            }
	        });

	        Scene scene = new Scene(grid, 300, 275);
	        anmeldungsStage.setScene(scene);
	        anmeldungsStage.setMinHeight(300);
	        anmeldungsStage.setMinWidth(500);
	        scene.getStylesheets().add
	         (StartGUI.class.getResource("Background.css").toExternalForm());
	        anmeldungsStage.show();
	    }

}
