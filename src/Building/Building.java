package Building;

import java.util.Observable;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
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
	
	/** TextField which appear on double click. */
	private EditableName editableName;
	
	/** Position into scene values. */
	double orgSceneX, orgSceneY;
	
	/** Translation values. */
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
		// Instantiate state of current building.
		this.state = new State();
		// Observer use to update color when state change.
		this.state.addObs(this); 
		// Set position.
		this.setTranslateX(x);this.setTranslateY(y);
		// Set size.
		this.setWidth(width);this.setHeight(height);
		// Set rectangle structure.
		this.rectangle = new Rectangle (x, y, width, height);
		// Set color structure.
		this.rectangle.setFill(this.state.getColor());
		// Set name of building.
		this.name = new Text(name);
		// Set move listener.
		this.setOnMousePressed(onMousePressEvent); this.setOnMouseDragged(onMouseDragEvent); 
		// Set on doubleClick on text/name listener.
		this.name.setOnMouseClicked(onDoubleClickEvent);
		// Set textField when onDoubleClick on text.
		this.editableName = new EditableName(()->updateEditableName(),()->finishTextField()); 
		this.getChildren().addAll(this.rectangle, this.name);
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * Run when state change into State.java.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.rectangle.setFill(this.state.getColor());
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
    
    /**
     * Event run on double click on Text name.
     */    
	EventHandler<MouseEvent> onDoubleClickEvent = 
			new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					if(event.getButton().equals(MouseButton.PRIMARY)){
			            if(event.getClickCount() == 2){
			            	initEditableName();
			            }
			        }
				}
	};
    
    /**
     * Run on init of EditableName.
     */
    private void initEditableName() {
    	editableName.setText(name.getText());  
	    this.getChildren().add(editableName);  
	    editableName.selectAll();  
	    editableName.requestFocus();
    }
    
    /**
     * Run on update of EditableName.
     */    
    private void updateEditableName() {
	    name.setText(editableName.getText());  
    }
    
    /**
     * Run on finish of EditableName.
     */ 
    private void finishTextField() {
    	System.out.println("OnFinish");
    	name.setText(editableName.getText()); 	  
    	if (this.getChildren().get(this.getChildren().size()-1) instanceof TextField) {
    		this.getChildren().remove(this.getChildren().size()-1);
    	}
    }
}