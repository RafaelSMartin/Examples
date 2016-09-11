package com.jgs.vistas;

import com.jgs.vistas.VistaClaseInternaDeActivity.MiVista;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class VistaFicheroIndependiente extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.vista_fichero_independiente);
		addContentView(new MiVista(this), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}
	
	public void siguienteActivity(View view) {
		Intent i = new Intent(this, VistaFicheroIndependienteAgregadoLayout.class);
		startActivity(i);
	}
	
	public void volverActivity(View view) {
		Intent i = new Intent(this, VistaClaseInternaDeActivity.class);
		startActivity(i);
	}
}
