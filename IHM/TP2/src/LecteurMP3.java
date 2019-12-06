
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LecteurMP3 extends BorderPane{
	VBox controles;
	BorderPane lecture;
	TreeTableView<String> musics;
	
	public LecteurMP3(Stage stage) {
		BorderPane fenetre = new BorderPane();
		musics = new TreeTableView<String>();
		TreeTableColumn<String,String> nom_music = new TreeTableColumn("Nom");
		TreeTableColumn<String,String> auteur_music = new TreeTableColumn("Auteur");
		TreeTableColumn<String,String> duree_music = new TreeTableColumn("Durée");
		
		musics.getColumns().setAll(nom_music, auteur_music, duree_music);

		Button previous_10s_b = new Button("<<");
		Button next_10s_b = new Button(">>");
		Button play_b = new Button(">");
		play_b.setPrefSize(40, 40);
		next_10s_b.setPrefSize(40, 30);
		next_10s_b.setTranslateY(5);
		previous_10s_b.setPrefSize(40, 30);
		previous_10s_b.setTranslateY(5);
		
		
		
		Button previous_b = new Button("|<");
		Button next_b = new Button(">|");
		Button stop_b = new Button("||");
		stop_b.setPrefSize(40, 30);
		next_b.setPrefSize(40, 30);
		previous_b.setPrefSize(40, 30);
		
		HBox stop = new HBox(previous_b,stop_b,next_b);
		HBox play = new HBox(previous_10s_b,play_b,next_10s_b);
		play.setMargin(play_b, new Insets(0,2,0,2));
		stop.setMargin(stop_b, new Insets(0,2,0,2));

		
		controles = new VBox(play,stop);
		controles.setMargin(play, new Insets(0,0,2,0));
		
		BorderPane progression = new BorderPane();
		Slider prog = new Slider();
		Label title = new Label("Lecteur Multimédia VLC");
		Label time = new Label("00:00");
		progression.setLeft(title);
		progression.setRight(time);
		progression.setBottom(prog);
		progression.setMargin(title, new Insets(0,30,0,0));
		Slider sound = new Slider();
		sound.setTranslateY(5);
		
		Button parameters = new Button("|||");
		ToggleButton playlist = new ToggleButton(":=");
		
		playlist.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					set_bot();
					stage.setHeight(500);
					stage.heightProperty().addListener(new ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue,
								Number newValue) {
							if ((Double)newValue<200) {
								playlist.setSelected(false);
								unset_bot();
							}
							
						}
					});
				}else {
					unset_bot();
					stage.setHeight(stage.getHeight()-musics.getHeight());
				}
				
			}
		});
		
		
		
		
		HBox more = new HBox(parameters,playlist);
		more.setMargin(parameters, new Insets(0,2,0,0));
		
		
		lecture = new BorderPane();
		lecture.setLeft(sound);
		lecture.setRight(more);
		lecture.setTop(progression);
		
		lecture.setMargin(progression, new Insets(0,0,5,0));
		
		this.setLeft(controles);
		this.setCenter(lecture);
		
		this.setPadding(new Insets(2));
		this.setMargin(lecture, new Insets(5));
	}
	
	public void set_bot() {
		this.setBottom(musics);
	}
	
	public void unset_bot() {
		this.setBottom(null);
	}

}
