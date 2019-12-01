

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;



public class Main extends Application {
	public void start(Stage stage) {
		LecteurMP3 mp3 = new LecteurMP3(stage);
		stage.setTitle("VLC");
		stage.setScene(new Scene(mp3));
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}