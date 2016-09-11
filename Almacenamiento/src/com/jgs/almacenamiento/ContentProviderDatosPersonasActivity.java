package com.jgs.almacenamiento;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ContentProviderDatosPersonasActivity extends Activity {
	
//	private static final Uri URI = Uri.parse("content://com.jgs.almacenamiento/datosPersonasCP"); //Esta linea es equivalente
	private static final Uri URI = ContentProviderDatosPersonas.CONTENT_URI;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_provider_datos_personas);
		
		TextView salida = (TextView) findViewById(R.id.salida);
		List<Persona> lista = new ArrayList<Persona>();
		Cursor cursor = null;
		
		//Comenzamos mirando si existe algun registro en la tabla datosPersonasCP		
		try {
			cursor = getContentResolver().query(URI, null, null, null, null);
			
			//Si lo hay
			if(cursor.getCount() > 0) {
				//Los borramos todos
				getContentResolver().delete(URI, null, null);
				Log.d("ContentProviderDatosPersonasActivity", "Eliminados todos los registros");
				salida.append("\nEliminados todos los registros\n");
			}
		} catch (Exception e) {
			Log.e("ContentProviderDatosPersonasActivity", "Error: " + e.getMessage());
		} finally {
			if(cursor != null) {
				cursor.close();
			}
		}
		
		Log.d("ContentProviderDatosPersonasActivity", "Insertamos algunos registros");
		salida.append("\nInsertamos algunos registros\n");
		
		//Creamos algunos valores para probar
		ContentValues registro1 = new ContentValues();
		registro1.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI, 30);
		registro1.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_NOMBRE, "Juan");
		registro1.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_CIUDAD, "Córdoba");
		
		ContentValues registro2 = new ContentValues();
		registro2.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI, 31);
		registro2.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_NOMBRE, "Ana");
		registro2.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_CIUDAD, "Sevilla");
		
		ContentValues registro3 = new ContentValues();
		registro3.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI, 32);
		registro3.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_NOMBRE, "Antonio");
		registro3.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_CIUDAD, "Málaga");
		
		//Insertamos un registro de una vez
		getContentResolver().insert(URI, registro1);
		
		//Insertamos varios registros de una vez
		getContentResolver().bulkInsert(URI, new ContentValues[]{registro2, registro3});
		
		Log.d("ContentProviderDatosPersonasActivity", "Obtenemos todos los registros");
		salida.append("\nObtenemos todos los registros\n");
		
		//Obtenemos todos los valores de la tabla datosPersonasCP
		try {
			cursor = getContentResolver().query(URI, null, null, null, null);
			
			if(cursor != null && cursor.moveToFirst()) {
				do {
					int dni = cursor.getInt(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI));
					String nombre = cursor.getString(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_NOMBRE));
					String ciudad = cursor.getString(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_CIUDAD));
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
		
		Log.d("ContentProviderDatosPersonasActivity", "Obtenemos el registro con dni = 31");
		salida.append("\nObtenemos el registro con dni = 31\n");
		
		//Obtenemos todos los valores de la tabla datosPersonasCP
		try {
			cursor = getContentResolver().query(URI, null, "dni='31'", null, null);
			
			if(cursor != null && cursor.moveToFirst()) {
				lista.clear(); //Limpiamos la lista
				do {
					int dni = cursor.getInt(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI));
					String nombre = cursor.getString(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_NOMBRE));
					String ciudad = cursor.getString(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_CIUDAD));
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
		
		Log.d("ContentProviderDatosPersonasActivity", "Mostramos por pantalla ese registro");
		salida.append("\nMostramos por pantalla ese registro\n");
		
		//Los mostramos por pantalla
		if(lista.size() > 0) {
			salida.append("\nPersona encontrada\n");
			for(Persona persona : lista) {
				salida.append(persona.getDni() + " " + persona.getNombre() + " " + persona.getCiudad() + "\n");
			}
		}
		
		Log.d("ContentProviderDatosPersonasActivity", "Actualizamos el dni a 88 de las personas que vivan en Sevilla");
		salida.append("\nActualizamos el dni a 88 de las personas que vivan en Sevilla\n");
		
		ContentValues valor = new ContentValues();
		valor.put(ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI, 88);
		getContentResolver().update(URI, valor, "ciudad='Sevilla'", null);
		
		Log.d("ContentProviderDatosPersonasActivity", "Obtenemos todos los registros");
		salida.append("\nObtenemos todos los registros\n");
		
		//Obtenemos todos los valores de la tabla datosPersonasCP
		try {
			cursor = getContentResolver().query(URI, null, null, null, null);
			
			if(cursor != null && cursor.moveToFirst()) {
				lista.clear(); //Limpiamos la lista
				do {
					int dni = cursor.getInt(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI));
					String nombre = cursor.getString(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_NOMBRE));
					String ciudad = cursor.getString(cursor.getColumnIndex(ContentProviderDatosPersonaSQLiteHelper.CAMPO_CIUDAD));
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
	
	public void anterior(View view) {
		Intent i = new Intent(this, ContentProviderCallLogActivity.class);
		startActivity(i);
	}
}