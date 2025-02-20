package downloader.ui;

import downloader.fc.Downloader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	VBox progress_pane;
	public void start(Stage stage) {
		BorderPane main_pane = new BorderPane();
		BorderPane bas = new BorderPane();
		progress_pane = new VBox();
		ScrollPane sp = new ScrollPane();
		progress_pane.setPrefWidth(600);
		sp.setPrefSize(600, 370);
		TextField url_in = new TextField("URL");
		Button add_dl = new Button("add");
		sp.setContent(progress_pane);
		main_pane.setTop(sp);
		bas.setCenter(url_in);
		bas.setRight(add_dl);
		main_pane.setBottom(bas);
		stage.setTitle("downloader");
		Scene sc = new Scene(main_pane,600,400);
		stage.setScene(sc);
		stage.sizeToScene();
		stage.setResizable(false);
		stage.show();
		for(String url: getParameters().getRaw()) {
			handleDownload(url);
		}
		add_dl.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String url_dl = url_in.getText();
				handleDownload(url_dl);
				
			}
		});
		
	}
	
	public void handleDownload(String url) {
		Downloader downloader;
		ProgressBar prog;
		try {
			downloader = new Downloader(url);
			Thread p = new Thread(downloader);
			p.start();
			prog = new ProgressBar();
			Button delete = new Button("X");
			Button pause = new Button("||");
			HBox hb = new HBox(prog,delete,pause);
			progress_pane.getChildren().add(hb);
			prog.setPrefWidth(progress_pane.getPrefWidth()-50);
			downloader.progressProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					/*Platform.runLater(new Runnable(){
						@Override
						public void run() {
							prog.setProgress((Double)newValue);
						}
						
					});	*/
					System.out.println((Double)downloader.getProgress());
					prog.setProgress((Double)downloader.getProgress());
					//L'utilisation de Task rend le fonctionnement plus simple car nous n'avons plus besoin des méthodes 
					// pour mettre à jour le progress du download, c'est gérer automatiquement.
				}
			});
			delete.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					progress_pane.getChildren().remove(hb);
					p.interrupt();
				}
			});
			pause.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					if (pause.getText()=="||") {
						pause.setText(">");
						//TODO
						//Question 6 non-traitée
					}else {
						pause.setText("||");
						//TODO
					}
				}
			});
		}
		catch(RuntimeException e) {
			System.err.format("skipping %s %s\n", url, e);
		}
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}