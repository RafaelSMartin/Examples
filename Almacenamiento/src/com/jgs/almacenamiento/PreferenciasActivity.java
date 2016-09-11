package com.jgs.almacenamiento;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/*
 * MainActivity la utilizamos para probar como escribir y leer datos de las preferencias.
 * El resto de clases si tienen un nombre más descriptivo de su tarea.
 */
public class PreferenciasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Escribimos en el archivo de preferencias
		SharedPreferences preferencias = this.getSharedPreferences("datos", Context.MODE_PRIVATE);
		Editor editor = preferencias.edit();
		editor.putString("dato1", "Juan Córdoba");
		editor.putString("dato2", "Ana Sevilla");
		editor.putString("dato3", "Antonio Málaga");
		editor.commit();
		
		//Leemos del archivo de preferencias
		String dato1 = preferencias.getString("dato1", "");
		String dato2 = preferencias.getString("dato2", "");
		String dato3 = preferencias.getString("dato3", "");
		
		//Mostramos los resultados por pantalla y en el log
		Log.d("MainActivity", dato1);
		Toast.makeText(this, dato1, Toast.LENGTH_SHORT).show();
		Log.d("MainActivity", dato2);
		Toast.makeText(this, dato2, Toast.LENGTH_SHORT).show();
		Log.d("MainActivity", dato3);
		Toast.makeText(this, dato3, Toast.LENGTH_SHORT).show();
	}
	
	public void siguiente(View view) {
		Intent i = new Intent(this, FicherosActivity.class);
		startActivity(i);
	}
}