package Building;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.paint.Color;

public class State {
	
	/** State of current Building */
	public enum StateValue {OK, KO, UNKNOWN};
	private StateValue state;
	
	/** Color of the state */
	private Color color;
	private final Color COLOR_KO = 		Color.RED;
	private final Color COLOR_OK = 		Color.GREEN;
	private final Color COLOR_UNKNOWN = Color.YELLOW;


	private class Obs extends Observable {
		// If you want easily to do many actions in one function
		public void update() {
			try {
				setChanged(); // Inform obs is changed
				notifyObservers(); // Inform observers
			} catch (Exception e) {
				// Display Error?
			}
		}
	}
	private Obs obs = null;
	
	/**
	 * Instantiate a new state.
	 */
	State(){
		this.state = StateValue.UNKNOWN;
		this.color = COLOR_UNKNOWN;
		obs = new Obs();
	}
	
	/** Subscribe observer to obs (observable) */
	public void addObs(Observer observer) {
		obs.addObserver(observer);
	}
	
	/**
	 * Set state and color.
	 * @param state
	 */
	public void setState(StateValue state) {
		this.state = state;
		switch (this.state) {
		case OK:
			color = COLOR_OK;
			break;
		case KO:
			color = COLOR_KO;
			break;
		default:
			color = COLOR_UNKNOWN;
			break;
		}
		obs.update();
	}
	
	/**
	 * @return Color of state.
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * @return Current state.
	 */
	public StateValue getState() {
		return this.state;
	}
}
