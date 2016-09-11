package com.jamescho.game.state;

import android.view.MotionEvent;
import com.jamescho.framework.util.Painter;
import com.jamescho.multiple.GameMainActivity;

public abstract class State {
	public void setCurrentState(State newState) {
		GameMainActivity.sGame.setCurrentState(newState);
	}

	public abstract void init();
	
	// Create a new method called onExit() to be implemented by concrete State classes.
	// This method will automatically be called during a transition to a new state (e.g. MenuState to PlayState).
	// The onExit() method of the current state will be called before the onLoad() method of the new state is called.
	public void onExit() {
	}
	
	// This method should initialize any Assets specific to a state.
	public void onLoad() {
	}

	public abstract void update(float delta);

	public abstract void render(Painter g);

	public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);
	
	// Require that each State class implement a toString() method which will provide some information about that state in String format.
	public abstract String toString();
}