package tm.kalaha;

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



@SuppressWarnings({ "restriction" })
public class BoardTest extends Application {

	 public static void main(String[] args) {
	        launch(args);
	    }
	    
	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("Hello World!");
	        
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
	        
	        Text scenetitle = new Text("Willkommen in der wunderbaren Welt des Kahala!");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        scenetitle.setFill(Color.ANTIQUEWHITE);
	        grid.add(scenetitle, 0, 0, 2, 1);

	        Label userName = new Label("Spielername:");
	        userName.setTextFill(Color.ANTIQUEWHITE);
	        grid.add(userName, 0, 1);

	        TextField userTextField = new TextField();
	        grid.add(userTextField, 1, 1);

	        
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
	                actiontarget.setFill(Color.FIREBRICK);
	                actiontarget.setText("Everything worked better than expected!");
	            }
	        });

	        Scene scene = new Scene(grid, 300, 275);
	        primaryStage.setScene(scene);
	        primaryStage.setMinHeight(300);
	        primaryStage.setMinWidth(500);
	        scene.getStylesheets().add
	         (BoardTest.class.getResource("Background.css").toExternalForm());
	        primaryStage.show();
	    }

}
