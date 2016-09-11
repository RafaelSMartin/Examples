package com.jamescho.game.state;

import android.util.Log;
import android.view.MotionEvent;

import com.jamescho.framework.util.Painter;

// The LoadState now acts as an intermediate state for transitioning between two states.
public class LoadState extends State {

	private State currentState;
	private State target;
	
	// This constructor is used when using a loading screen
	public LoadState(State currentState, State target) {
		this.target = target;
		this.currentState = currentState;
		// This will print in LogCat which screen the LoadState will transition from and to.
		Log.d("debug", "Transitioning from " + currentState.toString() + " to " + target.toString());
		
	}

	@Override
	public void init() {
	}

	@Override
	public void update(float delta) {
		currentState.onExit();
		System.gc();
		target.onLoad();
		setCurrentState(target);
	}

	@Override
	public void render(Painter g) {
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		return false;
	}
	
	// Return a String describing this state.
	@Override
	public String toString() {
		return "LoadState";
	}
}