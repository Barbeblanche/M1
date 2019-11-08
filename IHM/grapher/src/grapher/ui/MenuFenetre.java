package grapher.ui;

import javafx.application.Application.Parameters;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MenuFenetre extends BorderPane{
	Fenetre f;
	
	public MenuFenetre(Parameters params) {
		f = new Fenetre(params);
		Menu expression = new Menu("Expressions");
		MenuItem ajouter = new MenuItem("ajouter");
		MenuItem supprimer = new MenuItem("supprimer");
		expression.getItems().add(ajouter);
		expression.getItems().add(supprimer);
		MenuBar menu = new MenuBar(expression);
		this.setCenter(f);
		this.setTop(menu);
		
		
		ajouter.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        f.ajouterFonction();
		    }
		});
		supprimer.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        f.supprimerFonction();
		    }
		});
		
		ajouter.setAccelerator(new KeyCodeCombination(KeyCode.N,KeyCombination.CONTROL_DOWN));
		supprimer.setAccelerator(new KeyCodeCombination(KeyCode.BACK_SPACE,KeyCombination.CONTROL_DOWN));
	}
}
