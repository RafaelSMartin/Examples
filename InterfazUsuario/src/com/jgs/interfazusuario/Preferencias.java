package com.jgs.interfazusuario;

import android.app.Activity;
import android.os.Bundle;

public class Preferencias extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenciasFragment()).commit(); //Las preferencias
	}
}
