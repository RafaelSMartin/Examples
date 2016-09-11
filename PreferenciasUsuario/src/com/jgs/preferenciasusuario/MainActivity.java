package com.jgs.preferenciasusuario;

import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View boton1 = findViewById(R.id.button1);
		boton1.setOnClickListener((OnClickListener) this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.preferencias:
			lanzarPreferencias();
			break;

		default:
			break;
		}
		
		return true;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			mostrarPreferencias();
			break;

		default:
			break;
		}
	}
	
	private void lanzarPreferencias() {
		Intent i = new Intent(this, Preferencias.class);
		startActivity(i);
	}
	
	private void mostrarPreferencias() {
		SharedPreferences preferencias = getSharedPreferences("com.jgs.preferenciasusuario_preferences", MODE_PRIVATE);
		String cadena = "CheckBox: " + preferencias.getBoolean("checkBox", true) + "\n" +
						"EditTextPreference: " + preferencias.getString("editTextPreference", "*") + "\n" +
						"ListPreference: " + preferencias.getString("listPreference", "*") + "\n" +
						"MultiSelectedListPreference: " + preferencias.getStringSet("multiSelectedListPreference", new HashSet<String>()) + "\n" +
						"SwitchPreference: " + preferencias.getBoolean("switchPreference", true) + "\n" +
						"RingtonePreference: " + preferencias.getString("ringtonePreference", "*") + "\n";
		Toast.makeText(this, cadena, Toast.LENGTH_LONG).show();
	}
}