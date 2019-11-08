package grapher.ui;


import java.util.ArrayList;
import java.util.Optional;
import java.util.Vector;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;
import javafx.application.Application.Parameters;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Fenetre extends SplitPane {
	GrapherCanvas gc;
	BorderPane bp;
	ListView<String> lv;
	protected Vector<Function> functions = new Vector<Function>();
	
	public Fenetre(Parameters params) {
		this.gc = new GrapherCanvas(functions);
		bp = new BorderPane();
		
		for(String param: params.getRaw()) {
			functions.add(FunctionFactory.createFunction(param));
		}
		this.gc.setFunctions(functions);
		
		Button plus = new Button(" + ");
		Button moins = new Button(" - ");
		ToolBar tb = new ToolBar(plus,moins);
		lv = new ListView<String>();
		lv.getItems().addAll(params.getRaw());

		lv.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	for (String f : lv.getSelectionModel().getSelectedItems()) {
	        		if (!gc.bold.contains(f)){
	        			gc.bold.add(FunctionFactory.createFunction(f));
	        		}else {
	        			gc.bold.remove(FunctionFactory.createFunction(f));
	        		}
	        	}
	            gc.redraw();
	            gc.bold.clear();
	        }
	    });
		
		plus.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				ajouterFonction();
			}
		});
		moins.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				supprimerFonction();	
			}
		});
		bp.setCenter(lv);
		bp.setBottom(tb);
		this.getItems().addAll(bp,this.gc);
	}
	public void ajouterFonction() {
		TextInputDialog nvFct = new TextInputDialog();
		nvFct.setContentText("Nouvelle expression : ");
		nvFct.setHeaderText("Expression");
		nvFct.setTitle("Expression");
		Optional<String> result = nvFct.showAndWait();
		if (!result.isPresent()) {
			return;
		}
		try {
			functions.add(FunctionFactory.createFunction(result.get()));
			gc.setFunctions(functions);
			gc.redraw();
			lv.getItems().add(result.get());
		} catch(RuntimeException re) {
			Alert a = new Alert(AlertType.ERROR,"\"" + result.get() + "\"" + " : Expression non valide");
			a.showAndWait();
		}
	}
	
	public void supprimerFonction() {
		int index = lv.getSelectionModel().getSelectedIndex();
		if(index == -1) {
			Alert a = new Alert(AlertType.ERROR,"Selectionne une expression stp");
			a.showAndWait();
			return;
		}
		functions.remove(index);
		lv.getItems().remove(index);
		gc.setFunctions(functions);
		gc.redraw();
	}

}
