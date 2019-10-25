package grapher.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;



public class Main extends Application {
	public void start(Stage stage) {
		Fenetre f = new Fenetre(getParameters());
		stage.setTitle("grapher");
		stage.setScene(new Scene(f));
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}