package Building;
import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Building extends StackPane implements Observer {

	/** Name of the Building object.*/
	private Text name;
	
	/** State of current Building */
	private State state;
	
	/** Rectangle structure of the Building object.*/
	private Rectangle rectangle;
	
	/**  */
	double orgSceneX, orgSceneY;
	
	/**  */
    double orgTranslateX, orgTranslateY;
	
	/**
	 * Create an interactive Building object.
	 * @param name
	 * @param onMousePressEvent
	 * @param onMouseDragEvent
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Building (	String name, 
			 			double x, double y, double width, double height){
		super();
		this.state = new State();
		this.state.addObs(this);
		this.setTranslateX(x);this.setTranslateY(y);
		this.setWidth(width);this.setHeight(height);
		this.rectangle = new Rectangle (x, y, width, height);
		this.rectangle.setFill(this.state.getColor());		
		this.name = new Text(name);
		this.setOnMousePressed(onMousePressEvent); this.setOnMouseDragged(onMouseDragEvent);
		this.getChildren().addAll(this.rectangle, this.name);
	}
	
	
	/**
	 * Set color of the rectangle (the structure) of Building object.
	 * @param color
	 */
	public void setFill(Color color) {
		this.rectangle.setFill(color);
	}
	
	/**
	 * @return String name of the Building object.
	 */
	public String getName() {
		return this.name.getText();
	}
	
	/**
	 * Set state of Building object.
	 * @param state
	 */
	public void setState(State.StateValue state) {	
		this.state.setState(state);
	}
	
	/**
	 * Get state of Building object.
	 */
	public State getState() {
		return this.state;
	}
	
	/**
	 * Event run when the mouse pressed a Building object.
	 */
	EventHandler<MouseEvent> onMousePressEvent = 
            new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Building)(t.getSource())).getTranslateX();
                orgTranslateY = ((Building)(t.getSource())).getTranslateY();
                ((Building)(t.getSource())).toFront();
            }
        };
        
    /**
     * Event run when the mouse dragged a Building object.
     */
    EventHandler<MouseEvent> onMouseDragEvent = 
        new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            ((Building)(t.getSource())).setTranslateX(newTranslateX);
            ((Building)(t.getSource())).setTranslateY(newTranslateY);
        }
    };

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Update");
		this.rectangle.setFill(this.state.getColor());
	}
}