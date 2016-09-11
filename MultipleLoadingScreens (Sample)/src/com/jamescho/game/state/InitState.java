package com.jamescho.game.state;

import android.util.Log;
import android.view.MotionEvent;

import com.jamescho.framework.util.Painter;
import com.jamescho.multiple.Assets;

public class InitState extends State {
	@Override
	public void init() {
		Log.v("debug", "init() called");
		Assets.load();
	}

	@Override
	public void update(float delta) {
		// Transition from this current state to a new MenuState
		setCurrentState(new LoadState(this, new DemoState1()));
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
		return "InitState";
	}
}