package Building;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EditableName extends TextField {

	/** Functions use onUpdate and onFinish of EditableName. */
	private Runnable onUpdate,onFinish;
	
	/**
	 * Instantiate EditableName textField with listeners onUpdate and onFinish.
	 * @param onUpdate
	 * @param onFinish
	 */
	EditableName(Runnable onUpdate, Runnable onFinish){
		// Initialize functions.
		this.onUpdate = onUpdate;
		this.onFinish = onFinish;
		// Add listeners.
		this.setOnAction(onTextFieldAction);
		this.focusedProperty().addListener(onLostFocusListener);
		this.setOnKeyPressed(onEnterPressListener);
	}
	
	/**
	 * Event when an action is perform on EditableName.
	 */
	EventHandler<ActionEvent> onTextFieldAction = 
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					onUpdate.run();
				}
	};
	
	/**
	 * Event when an action is perform on LostFocus on EditableName.
	 */
	ChangeListener<Boolean> onLostFocusListener = 
			new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					// TODO Auto-generated method stub
					if (! newValue) {
						onFinish.run();
					}
				}
	};
	
	/**
	 * Event when an action is perform on press enter key on EditableName.
	 */
	EventHandler<KeyEvent> onEnterPressListener = 
			new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (event.getCode().equals(KeyCode.ENTER)){
						onFinish.run();
		            }
				}
	};
}