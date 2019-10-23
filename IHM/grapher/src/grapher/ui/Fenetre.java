package grapher.ui;

import java.util.ArrayList;

import javafx.application.Application.Parameters;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class Fenetre extends Scene {
	SplitPane sp;
	GrapherCanvas gc;
	BorderPane bp;
	ListView<String> lv;
	String fct;
	
	public Fenetre(Parameters params) {
		bp = new BorderPane();
		lv = new ListView<String>();
		lv.getChildrenUnmodifiable().add(new Label("tan(x)"));
		lv.getChildrenUnmodifiable().add(new Label("sin(x)"));
		
		lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        fct = newValue;
		    }
		});
		bp.setLeft(lv);
		;
	}
}
