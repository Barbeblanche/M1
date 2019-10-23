package grapher.ui;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Interaction implements EventHandler<MouseEvent> {
	enum State {IDLE,ZOOM_OR_TRANSLATE,TRANSLATING,DEZOOM_OR_SELECT,SELECTING}
	State state;
	GrapherCanvas g;
	Point2D p;
	Point2D p2;
	public Interaction(GrapherCanvas g) {
		this.state = State.IDLE;
		this.g = g;
	}
	
	public void drawFeedBack(GraphicsContext g) {
		switch(state) {
			case SELECTING:
				g.strokeRect(p.getX(),p.getY(),p2.getX()-p.getX(),p2.getY()-p.getY());
		}
			
	}
	
	@Override
	public void handle(MouseEvent e) {
		switch(state) {
		case IDLE:
			switch(e.getEventType().getName()) {
				case "MOUSE_PRESSED":
					if (e.getButton() == MouseButton.PRIMARY) {
						p = new Point2D(e.getX(),e.getY());
						state = State.ZOOM_OR_TRANSLATE;
						break;
					}else if (e.getButton() == MouseButton.SECONDARY){
						p = new Point2D(e.getX(),e.getY());
						state = State.DEZOOM_OR_SELECT;
						break;
					}
				
			}
			break;
		case ZOOM_OR_TRANSLATE:
			switch(e.getEventType().getName()) {
				case "MOUSE_RELEASED":
					g.zoom(p,5);
					state = State.IDLE;
					break;
				case "MOUSE_DRAGGED":
					state = State.TRANSLATING;
					break;
			}
			break;
		case TRANSLATING:
			switch(e.getEventType().getName()) {
				case "MOUSE_RELEASED":
					state = State.IDLE;
					break;
				case "MOUSE_DRAGGED":
					g.translate((e.getX()-p.getX()), (e.getY()-p.getY()));
					p = new Point2D(e.getX(),e.getY());
					break;
			}
			break;
		case DEZOOM_OR_SELECT:
			switch(e.getEventType().getName()) {
				case "MOUSE_RELEASED":
					g.zoom(p,-5);
					state = State.IDLE;
					break;
				case "MOUSE_DRAGGED":
					state = State.SELECTING;
					break;
			}
			break;
		case SELECTING:
			switch(e.getEventType().getName()) {
			
				case "MOUSE_RELEASED":
					p2 = new Point2D(e.getX(),e.getY());
					g.zoom(p,p2);
					state = State.IDLE;
					g.redraw();
					break;
				case "MOUSE_DRAGGED":
					p2 = new Point2D(e.getX(),e.getY());
					g.redraw();
					break;
			}
			break;
		}
		
	}

}
