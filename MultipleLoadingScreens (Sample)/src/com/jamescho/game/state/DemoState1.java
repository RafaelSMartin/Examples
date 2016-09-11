package com.jamescho.game.state;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.jamescho.framework.util.Painter;
import com.jamescho.multiple.Assets;

public class DemoState1 extends State {

	private Bitmap demo1;

	@Override
	public void init() {
	}

	@Override
	public void onLoad() {
		demo1 = Assets.loadBitmap("demo1.png", false);
	}

	@Override
	public void onExit() {
		Assets.unloadBitmap(demo1);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void render(Painter g) {
		g.drawImage(demo1, 0, 0);
		g.drawImage(Assets.global, 10, 40);
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		if (e.getAction() == MotionEvent.ACTION_UP) {
			// Set state to a LoadState that transitions from this state to DemoState1
			setCurrentState(new LoadState(this, new DemoState2()));
		}
		return true;
	}

	// Return a String describing this state.
	@Override
	public String toString() {
		return "DemoState1";
	}
}