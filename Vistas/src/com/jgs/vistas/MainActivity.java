package com.jgs.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void siguienteActivity(View view) {
		Intent i = new Intent(this, VistaClaseInternaDeActivity.class);
		startActivity(i);
	}
}