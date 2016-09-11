package com.jgs.almacenamiento;

import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ContentProviderCallLogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_provider_call_log);
		
		TextView salida = (TextView) findViewById(R.id.salida);
		
		//Accedemos al ContentProvider identificandolo a través de su URI
		Uri callLog = Uri.parse("content://call_log/calls");
		
/**
 * Leemos el contenido del ContentProvider		
 */
		Log.d("ContentProviderCallLogActivity", "Leemos el contenido del ContentProvider");
		
		//Obtenemos la informacion realizando una query
		Cursor cursor = getContentResolver().query(callLog, null, null, null, null);
		
		String cadena;
		//Obtenemos la información del cursor
		while(cursor.moveToNext()) {
			cadena = "";
			cadena = "Fecha: " + DateFormat.format("dd/MM/yyyy k:mm", cursor.getLong(cursor.getColumnIndex(Calls.DATE))).toString() + ", ";
			cadena = cadena + "Duración: " + cursor.getString(cursor.getColumnIndex(Calls.DURATION)) + ", ";
			cadena = cadena + "Número: " + cursor.getString(cursor.getColumnIndex(Calls.NUMBER)) + ", ";
			cadena = cadena + "Tipo: " + cursor.getString(cursor.getColumnIndex(Calls.TYPE)) + "\n";
			Log.d("ContentProviderCallLogActivity", cadena);
			salida.append(cadena);
		}
		cursor.close();
		
/**
 * Escribimos en el ContentProvider		
 */
		Log.d("ContentProviderCallLogActivity", "Escribimos en el ContentProvider");
		
		//Creamos un valor para insertar en el ContentProvider
		ContentValues registro = new ContentValues();
		registro.put(Calls.DATE, new Date().getTime());
		registro.put(Calls.NUMBER, "957123456");
		registro.put(Calls.DURATION, "15");
		registro.put(Calls.TYPE, Calls.INCOMING_TYPE);
		
		//Insertamos el valor
		getContentResolver().insert(callLog, registro);

/**
 * Leemos el contenido del ContentProvider para mostrar el/los registro/s insertado/s por nosotros	
 */		
		
		//Obtenemos la informacion realizando una query para ver que se ha insertado
		cursor = getContentResolver().query(callLog, null, "number='957123456'", null, null);
		
		salida.append("\nRegistro insertado por nosotros\n");
		//Mostramos la informacion
		while(cursor.moveToNext()) {
			cadena = "Fecha: " + DateFormat.format("dd/MM/yyyy k:mm", cursor.getLong(cursor.getColumnIndex(Calls.DATE))).toString() + ", ";
			cadena = cadena + "Duración: " + cursor.getString(cursor.getColumnIndex(Calls.DURATION)) + ", ";
			cadena = cadena + "Número: " + cursor.getString(cursor.getColumnIndex(Calls.NUMBER)) + ", ";
			cadena = cadena + "Tipo: " + cursor.getString(cursor.getColumnIndex(Calls.TYPE)) + "\n";
			Log.d("ContentProviderCallLogActivity", cadena);
			salida.append(cadena);
		}
		
/**
 * Borramos del ContentProvider		
 */
		
		Log.d("ContentProviderCallLogActivity", "Borramos en el ContentProvider");

		//Este ejemplo borraría todos los registros del historial de llamadas.
		//Observa que utilizamos la URI Calls.CONTENT_URI (constante) en lugar de definirla como hemos hecho antes "content://call_log/calls". Es equivalente
		//getContentResolver().delete(Calls.CONTENT_URI, null, null);
		
		//En este caso vamos a borrar todos los registros que tienen el valor number = 957123456
		getContentResolver().delete(callLog, "number='957123456'", null);
		
/**
 * Leemos el contenido del ContentProvider para verificar el borrado
 */			
		
		//Obtenemos la informacion realizando una query para ver que se ha insertado
		cursor = getContentResolver().query(callLog, null, null, null, null);
		
		salida.append("\nLista tras el borrado\n");
		//Mostramos la informacion
		while(cursor.moveToNext()) {
			cadena = "Fecha: " + DateFormat.format("dd/MM/yyyy k:mm", cursor.getLong(cursor.getColumnIndex(Calls.DATE))).toString() + ", ";
			cadena = cadena + "Duración: " + cursor.getString(cursor.getColumnIndex(Calls.DURATION)) + ", ";
			cadena = cadena + "Número: " + cursor.getString(cursor.getColumnIndex(Calls.NUMBER)) + ", ";
			cadena = cadena + "Tipo: " + cursor.getString(cursor.getColumnIndex(Calls.TYPE)) + "\n";
			Log.d("ContentProviderCallLogActivity", cadena);
			salida.append(cadena);
		}
		
/**
 * Actualizamos valores del ContentProvider		
 */
		
		Log.d("ContentProviderCallLogActivity", "Actualizamos en el ContentProvider");
		
		//Creamos un ContentValues
		ContentValues valor = new ContentValues();
		//Le indicamos que la duracion sea 999
		valor.put(Calls.DURATION, 999);
		//Actualizamos la duracion a 999 en todos los registros que tengan duracion 11
		getContentResolver().update(callLog, valor, "duration=11", null);
		
/**
 * Leemos el contenido del ContentProvider para verificar la actualización
 */			
		
		//Obtenemos la informacion realizando una query para ver que se ha insertado
		cursor = getContentResolver().query(callLog, null, null, null, null);
		
		salida.append("\nLista tras la actualización\n");
		//Mostramos la informacion
		while(cursor.moveToNext()) {
			cadena = "Fecha: " + DateFormat.format("dd/MM/yyyy k:mm", cursor.getLong(cursor.getColumnIndex(Calls.DATE))).toString() + ", ";
			cadena = cadena + "Duración: " + cursor.getString(cursor.getColumnIndex(Calls.DURATION)) + ", ";
			cadena = cadena + "Número: " + cursor.getString(cursor.getColumnIndex(Calls.NUMBER)) + ", ";
			cadena = cadena + "Tipo: " + cursor.getString(cursor.getColumnIndex(Calls.TYPE)) + "\n";
			Log.d("ContentProviderCallLogActivity", cadena);
			salida.append(cadena);
		}
		
	}
	
	public void siguiente(View view) {
		Intent i = new Intent(this, ContentProviderDatosPersonasActivity.class);
		startActivity(i);
	}
	
	public void anterior(View view) {
		Intent i = new Intent(this, SQLiteActivity.class);
		startActivity(i);
	}
}