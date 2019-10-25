package grapher.ui;


import java.util.ArrayList;

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
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Fenetre extends SplitPane {
	GrapherCanvas gc;
	BorderPane bp;
	String fct;
	
	public Fenetre(Parameters params) {
		this.gc = new GrapherCanvas();
		bp = new BorderPane();
		
		for(String param: params.getRaw()) {
			gc.functions.add(FunctionFactory.createFunction(param));
		}
		
		
		Button plus = new Button(" + ");
		Button moins = new Button(" - ");
		ToolBar tb = new ToolBar(plus,moins);
		ListView<String> lv = new ListView<String>();
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
		
		bp.setCenter(lv);
		bp.setBottom(tb);
		this.getItems().addAll(bp,this.gc);
	}
}
