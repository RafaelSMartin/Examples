package com.jgs.almacenamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class FicherosActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lectura_escritura_ficheros);
		
/**
 * Lectura y escritura en un fichero de la memoria interna del dispositivo
 */
		Log.d("LecturaEscrituraFicheros", "Acceso a memoria interna para escritura");
		Toast.makeText(this, "Acceso a memoria interna para escritura", Toast.LENGTH_SHORT).show();
		
		//Escribimos en el fichero
		try {
			
			OutputStreamWriter stream = new OutputStreamWriter(openFileOutput("datos.txt", Context.MODE_PRIVATE));
			stream.write("Juan Córdoba");
			stream.write("\r\n");
			stream.write("Ana Sevilla");
			stream.write("\r\n");
			stream.write("Antonio Málaga");
			stream.close();

		} catch(Exception e) {
			Log.d("LecturaEscrituraFicheros", "Error al escribir en el fichero");
			Log.d("LecturaEscrituraFicheros", e.getMessage());
		}
		
		Log.d("LecturaEscrituraFicheros", "Acceso a memoria interna para lectura");
		Toast.makeText(this, "Acceso a memoria interna para lectura", Toast.LENGTH_SHORT).show();
		
		//Leemeos del fichero
		try {
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(openFileInput("datos.txt")));
			String linea = buffer.readLine();
			while(linea != null) {
				Log.d("LecturaEscrituraFicheros", linea);
				Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
				linea = buffer.readLine();
			}
			buffer.close();

		} catch(Exception e) {
			Log.d("LecturaEscrituraFicheros", "Error al leer del fichero");
			Log.d("LecturaEscrituraFicheros", e.getMessage());
		} 
	
/**
 * Lectura y escritura en un fichero de la memoria externa del dispositivo
 */
			
		//Obtenemos el estado de la memoria externa (tarjeta SD)
		String estadoSD = Environment.getExternalStorageState();
		Log.d("LecturaEscrituraFicheros", "Estado SD: " + estadoSD);
		Toast.makeText(this, "Estado SD: " + estadoSD, Toast.LENGTH_SHORT).show();
		
		//Comprobamos si tenemos acceso a la memoria externa para lectura y escritura
		if(estadoSD.equals(Environment.MEDIA_MOUNTED)) {
			
			Log.d("LecturaEscrituraFicheros", "Acceso a memoria externa para escritura");
			Toast.makeText(this, "Acceso a memoria externa para escritura", Toast.LENGTH_SHORT).show();
			
			// Ecribimos en el fichero
			try {
			
				//Obtenemos la ruta donde el sistema accede a la memoria externa (tarjeta SD)
				File pathSD = Environment.getExternalStorageDirectory();
				File pathFichero = new File(pathSD + "/Android/data/com.jgs.almacenamiento/files/datosExterna.txt");
				
				OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream(pathFichero));
				stream.write("Juan Córdoba");
				stream.write("\r\n");
				stream.write("Ana Sevilla");
				stream.write("\r\n");
				stream.write("Antonio Málaga");
				stream.close();
			
			} catch(Exception e) {
				Log.d("LecturaEscrituraFicheros", "Error al escribir en la memoria SD");
				Log.d("LecturaEscrituraFicheros", e.getMessage());
			}
		
		} else {
			Log.d("LecturaEscrituraFicheros", "No tiene acceso a memoria externa para escritura");
			Toast.makeText(this, "No tiene acceso a memoria externa para escritura", Toast.LENGTH_SHORT).show();
		}
		
		//Comprobamos si tenemos acceso a la memoria externa para lectura y esctitura o solo para lectura
		if(estadoSD.equals(Environment.MEDIA_MOUNTED) || estadoSD.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

			Log.d("LecturaEscrituraFicheros", "Acceso a memoria externa para lectura");
			Toast.makeText(this, "Acceso a memoria externa para lectura", Toast.LENGTH_SHORT).show();
			
			//Leemos del fichero
			try {
			
				//Obtenemos la ruta donde el sistema accede a la memoria externa (tarjeta SD)
				File pathSD = Environment.getExternalStorageDirectory();
				File pathFichero = new File(pathSD + "/Android/data/com.jgs.almacenamiento/files/datosExterna.txt");
				
				BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(pathFichero)));
				String linea = buffer.readLine();
				while(linea != null) {
					Log.d("LecturaEscrituraFicheros", linea);
					Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
					linea = buffer.readLine();
				}
				buffer.close();
			
			} catch(Exception e) {
				Log.d("LecturaEscrituraFicheros", "Error al leer de la memoria SD");
				Log.d("LecturaEscrituraFicheros", e.getMessage());
			}
			
		} else {
			Log.d("LecturaEscrituraFicheros", "No tiene acceso a memoria externa para escritura");
			Toast.makeText(this, "No tiene acceso a memoria externa para escritura", Toast.LENGTH_SHORT).show();
		}
		
/**
 * Lectura de un fichero almacenado en los recursos de la aplicacion
 */		
		
		Log.d("LecturaEscrituraFicheros", "Acceso a fichero en los recursos");
		Toast.makeText(this, "Acceso a fichero en los recursos", Toast.LENGTH_SHORT).show();
		
		//Leemeos del fichero
		try {
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.datos)));
			String linea = buffer.readLine();
			while(linea != null) {
				Log.d("LecturaEscrituraFicheros", linea);
				Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
				linea = buffer.readLine();
			}
			buffer.close();

		} catch(Exception e) {
			Log.d("LecturaEscrituraFicheros", "Error al leer del fichero");
			Log.d("LecturaEscrituraFicheros", e.getMessage());
		} 
	}
	
	public void siguiente(View view) {
		Intent i = new Intent(this, SAXParserActivity.class);
		startActivity(i);
	}
	
	public void anterior(View view) {
		Intent i = new Intent(this, PreferenciasActivity.class);
		startActivity(i);
	}
}
