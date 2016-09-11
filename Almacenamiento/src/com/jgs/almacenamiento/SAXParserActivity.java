package com.jgs.almacenamiento;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jgs.almacenamiento.Persona;

public class SAXParserActivity extends Activity {

	List<Persona> listaPersonas = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parser_sax);
		
		Log.d("SAXParserActivity", "Procesando archivo XML con SAX");
		Toast.makeText(this, "Procesando archivo XML con SAX", Toast.LENGTH_SHORT).show();
		
		listaPersonas = SAXXMLParser.parse(this.getResources().openRawResource(R.raw.personas));
		
		Log.d("SAXParserActivity", "Procesado archivo XML con SAX");
		Toast.makeText(this, "Procesado archivo XML con SAX", Toast.LENGTH_SHORT).show();
		
		/**
		 * Como SAX no almacena la información es necesario recurrir a otro medio para almacenar la información procesada.
		 * En este caso vamos a almacenar la información en un fichero de la memoria interna.
		 */
		
		Log.d("SAXParserActivity", "Acceso a memoria interna para escritura");
		Toast.makeText(this, "Acceso a memoria interna para escritura", Toast.LENGTH_SHORT).show();
		
		//Escribimos en el fichero
		try {
			
			OutputStreamWriter stream = new OutputStreamWriter(openFileOutput("datosSAX.txt", Context.MODE_PRIVATE));
			
			for(Persona p : listaPersonas) {
				stream.write(p.getDni() + " " + p.getNombre() + " " + p.getCiudad());
				stream.write("\r\n");
			}
			
			stream.close();

		} catch(Exception e) {
			Log.d("SAXParserActivity", "Error al escribir en el fichero");
			Log.d("SAXParserActivity", e.getMessage());
		}
		
		Log.d("SAXParserActivity", "Acceso a memoria interna para lectura");
		Toast.makeText(this, "Acceso a memoria interna para lectura", Toast.LENGTH_SHORT).show();
		
		//Leemeos del fichero
		try {
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(openFileInput("datosSAX.txt")));
			String linea = buffer.readLine();
			while(linea != null) {
				Log.d("SAXParserActivity", linea);
				Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
				linea = buffer.readLine();
			}
			buffer.close();

		} catch(Exception e) {
			Log.d("SAXParserActivity", "Error al leer del fichero");
			Log.d("SAXParserActivity", e.getMessage());
		} 
		
	}
	
	public void siguiente(View view) {
		Intent i = new Intent(this, DOMParserActivity.class);
		startActivity(i);
	}
	
	public void anterior(View view) {
		Intent i = new Intent(this, FicherosActivity.class);
		startActivity(i);
	}
}
