package com.jgs.almacenamiento;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SQLiteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite);
		
		Log.d("SQLiteActivity", "Creamos u obtenemos la referencia a la base de datos");
		Toast.makeText(this, "Creamos u obtenemos la referencia a la base de datos", Toast.LENGTH_SHORT).show();
		
		//Creamos la base de datos
		SQLiteGestionarBD db = new SQLiteGestionarBD(this, "Personas", null, 2);
		
		Log.d("SQLiteActivity", "Añadimos los registros a la base de datos");
		Toast.makeText(this, "Añadimos los registros a la base de datos", Toast.LENGTH_SHORT).show();
		
		//Añadimos los registros a la base de datos
		try {
			db.addPersona(30, "Juan", "Córdoba");
			db.addPersona(31, "Ana", "Sevilla");
			db.addPersona(32, "Antonio", "Málaga");
		} catch(SQLiteConstraintException e) {
			Log.d("SQLiteActivity", "Ya existe un registro con esa PK");
		} catch(SQLException e) {
			Log.d("SQLiteActivity", "Error al insertar en la BD: " + e.getMessage());
		}
		
		Log.d("SQLiteActivity", "Leemos los registros de la base de datos");
		Toast.makeText(this, "Leemos los registros de la base de datos", Toast.LENGTH_SHORT).show();
			
		//Obtenemos el listado de personas
		List<String> lista = db.getListaDatosPersonas();
		
		//Mostramos los valores en el Log y en la pantalla del dispositivo
		for(String fila : lista) {
			Log.d("SQLiteActivity", fila);
			Toast.makeText(this, fila, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void siguiente(View view) {
		Intent i = new Intent(this, ContentProviderCallLogActivity.class);
		startActivity(i);
	}
	
	public void anterior(View view) {
		Intent i = new Intent(this, DOMParserActivity.class);
		startActivity(i);
	}
}