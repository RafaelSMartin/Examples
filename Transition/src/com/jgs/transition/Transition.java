package com.jgs.transition;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.view.Menu;
import android.widget.ImageView;

public class Transition extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView image = new ImageView(this);
		setContentView(image);
		TransitionDrawable transition = (TransitionDrawable) getResources().getDrawable(R.drawable.transition);
		image.setImageDrawable(transition);
		transition.startTransition(10000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transition, menu);
		return true;
	}

}