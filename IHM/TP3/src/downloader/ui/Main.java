package downloader.ui;

import downloader.fc.Downloader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	public void start(Stage stage) {
		BorderPane main_pane = new BorderPane();
		VBox progress_pane = new VBox();
		ScrollPane sp = new ScrollPane();
		sp.setPrefSize(500, 500);
		HBox bas = new HBox();
		TextField url_in = new TextField("URL");
		url_in.setPrefWidth(460);
		Button dl = new Button("add");
		bas.getChildren().add(url_in);
		bas.getChildren().add(dl);
		sp.setContent(progress_pane);
		main_pane.setCenter(sp);
		main_pane.setBottom(bas);
		stage.setTitle("downloader");
		stage.setScene(new Scene(main_pane));
		stage.sizeToScene();
		stage.show();
		for(String url: getParameters().getRaw()) {
			Downloader downloader;
			ProgressBar prog;
			try {
				downloader = new Downloader(url);
				Thread p = new Thread(downloader);
				p.start();
				prog = new ProgressBar();
				prog.setPrefWidth(500);
				progress_pane.getChildren().add(prog);
			}
			catch(RuntimeException e) {
				System.err.format("skipping %s %s\n", url, e);
				continue;
			}
			downloader.progressProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					Platform.runLater(new Runnable(){
						@Override
						public void run() {
							prog.setProgress((Double)newValue);
						}
						
					});	
				}
			});
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}