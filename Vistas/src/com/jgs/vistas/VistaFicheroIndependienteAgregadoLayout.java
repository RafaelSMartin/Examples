package com.jgs.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VistaFicheroIndependienteAgregadoLayout extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.vista_fichero_independiente_agregado_layout);
	}
	
	public void volverActivity(View view) {
		Intent i = new Intent(this, VistaFicheroIndependiente.class);
		startActivity(i);
	}
}
