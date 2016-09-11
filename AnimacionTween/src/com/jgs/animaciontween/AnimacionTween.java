package com.jgs.animaciontween;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AnimacionTween extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView texto = (TextView) findViewById(R.id.textView);
		Animation animacion = AnimationUtils.loadAnimation(this, R.anim.tweenanimation);
		texto.startAnimation(animacion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animacion_tween, menu);
		return true;
	}

}
