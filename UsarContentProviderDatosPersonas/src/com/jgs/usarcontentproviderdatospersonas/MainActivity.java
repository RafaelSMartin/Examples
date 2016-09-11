package com.jgs.usarcontentproviderdatospersonas;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final Uri URI = Uri.parse("content://com.jgs.almacenamiento/datosPersonasCP");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView salida = (TextView) findViewById(R.id.salida);
		List<Persona> lista = new ArrayList<Persona>();
		Cursor cursor = null;
		
		Log.d("ContentProviderDatosPersonasActivity", "Obtenemos todos los registros");
		salida.append("\nObtenemos todos los registros\n");
		
		//Obtenemos todos los valores de la tabla datosPersonasCP
		try {
			cursor = getContentResolver().query(URI, null, null, null, null);
			
			if(cursor != null && cursor.moveToFirst()) {
				do {
					int dni = cursor.getInt(cursor.getColumnIndex("dni"));
					String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
					String ciudad = cursor.getString(cursor.getColumnIndex("ciudad"));
					lista.add(new Persona(dni, nombre, ciudad));
				} while(cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.e("ContentProviderDatosPersonasActivity", "Error: " + e.getMessage());
		} finally {
			if(cursor != null) {
				cursor.close();
			}
		}
		
		Log.d("ContentProviderDatosPersonasActivity", "Mostramos por pantalla todos los registros");
		salida.append("\nMostramos por pantalla todos los registros\n");
		
		//Los mostramos por pantalla
		if(lista.size() > 0) {
			salida.append("\nLista de personas\n");
			for(Persona persona : lista) {
				salida.append(persona.getDni() + " " + persona.getNombre() + " " + persona.getCiudad() + "\n");
			}
		}
	}
}
