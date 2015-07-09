package tm.kalaha.GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.RowConstraints;

@SuppressWarnings({ "restriction" })
public class HauptGUI extends Application{
	
	 public static void main(String[] args) {
	        launch(args);
	      
	    }
	 
	 
	public void start(Stage spielStage) throws Exception {
		
		spielStage.setTitle("Kalaha");
		
		Button buttonA = new Button();
        buttonA.setText("0");
        buttonA.setStyle("-fx-font: 22 arial; -fx-base: #FFFF66;");
        buttonA.setMinWidth(120);
        buttonA.setMinHeight(260);
        
        Button button1 = new Button();
        button1.setText("4");
        button1.setStyle("-fx-font: 22 arial; -fx-base: #FFFF66;");
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
        button2.setStyle("-fx-font: 22 arial; -fx-base: #FFFF66;");
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
        button3.setStyle("-fx-font: 22 arial; -fx-base: #FFFF66;");
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
        button4.setStyle("-fx-font: 22 arial; -fx-base: #FFFF66;");
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
        button5.setStyle("-fx-font: 22 arial; -fx-base: #FFFF66;");
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
        button6.setStyle("-fx-font: 22 arial; -fx-base: #FFFF66;");
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
        button7.setStyle("-fx-font: 22 arial; -fx-base: #FF1919;");
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
        button8.setStyle("-fx-font: 22 arial; -fx-base: #FF1919;");
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
        button9.setStyle("-fx-font: 22 arial; -fx-base: #FF1919;");
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
        button10.setStyle("-fx-font: 22 arial; -fx-base: #FF1919;");
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
        button11.setStyle("-fx-font: 22 arial; -fx-base: #FF1919;");
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
        button12.setStyle("-fx-font: 22 arial; -fx-base: #FF1919;");
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
        buttonB.setStyle("-fx-font: 22 arial; -fx-base: #FF1919;");
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
        grid.setGridLinesVisible(true);
        
        grid.add(buttonA, 1, 1, 1, 2);
        grid.add(button1, 2, 1);
        grid.add(button2, 3, 1);
        grid.add(button3, 4, 1);
        grid.add(button4, 5, 1);
        grid.add(button5, 6, 1);
        grid.add(button6, 7, 1);
        
        grid.add(button7, 2, 2);
        grid.add(button8, 3, 2);
        grid.add(button9, 4, 2);
        grid.add(button10, 5, 2);
        grid.add(button11, 6, 2);
        grid.add(button12, 7, 2);
        grid.add(buttonB, 8, 1 , 1, 2);
        
        
        
        Scene scene = new Scene(grid, 300, 275);
        spielStage.setScene(scene);
        spielStage.setMinHeight(700);
        spielStage.setMinWidth(1200);
        scene.getStylesheets().add
         (StartGUI.class.getResource("Background.css").toExternalForm());
//        spielStage.show();
		
		
	}
	
	

}
