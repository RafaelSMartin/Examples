package com.jgs.almacenamiento;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DOMParserActivity extends Activity {
	
	private List<Persona> listaPersonas = new ArrayList<Persona>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parser_dom);
		
		Log.d("DOMParserActivity", "Procesando archivo XML con DOM");
		Toast.makeText(this, "Procesando archivo XML con DOM", Toast.LENGTH_SHORT).show();
		
		DOMXMLParser parser = new DOMXMLParser();
		Document documento = parser.getDocument(this.getResources().openRawResource(R.raw.personas));
		
		NodeList listaNodos = documento.getElementsByTagName("persona");
		
		for(int i=0; i<listaNodos.getLength(); i++) {
			Persona aux = new Persona();
			Element elemento = (Element) listaNodos.item(i);
			aux.setDni(Integer.parseInt(parser.getValue(elemento, "dni")));
			aux.setNombre(parser.getValue(elemento, "nombre"));
			aux.setCiudad(parser.getValue(elemento, "ciudad"));
			listaPersonas.add(aux);
		}
		
		Log.d("DOMParserActivity", "Procesado archivo XML con DOM");
		Toast.makeText(this, "Procesado archivo XML con DOM", Toast.LENGTH_SHORT).show();
		
		/**
		 * Como DOM no almacena la información es necesario recurrir a otro medio para almacenar la información procesada.
		 * En este caso vamos a almacenar la información en un fichero de la memoria interna.
		 */
		
		Log.d("DOMParserActivity", "Acceso a memoria interna para escritura");
		Toast.makeText(this, "Acceso a memoria interna para escritura", Toast.LENGTH_SHORT).show();
		
		//Escribimos en el fichero
		try {
			
			OutputStreamWriter stream = new OutputStreamWriter(openFileOutput("datosDOM.txt", Context.MODE_PRIVATE));
			
			for(Persona p : listaPersonas) {
				stream.write(p.getDni() + " " + p.getNombre() + " " + p.getCiudad());
				stream.write("\r\n");
			}
			
			stream.close();

		} catch(Exception e) {
			Log.d("DOMParserActivity", "Error al escribir en el fichero");
			Log.d("DOMParserActivity", e.getMessage());
		}
		
		Log.d("DOMParserActivity", "Acceso a memoria interna para lectura");
		Toast.makeText(this, "Acceso a memoria interna para lectura", Toast.LENGTH_SHORT).show();
		
		//Leemeos del fichero
		try {
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(openFileInput("datosDOM.txt")));
			String linea = buffer.readLine();
			while(linea != null) {
				Log.d("DOMParserActivity", linea);
				Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
				linea = buffer.readLine();
			}
			buffer.close();

		} catch(Exception e) {
			Log.d("DOMParserActivity", "Error al leer del fichero");
			Log.d("DOMParserActivity", e.getMessage());
		} 
	}

	public void siguiente(View view) {
		Intent i = new Intent(this, SQLiteActivity.class);
		startActivity(i);
	}
	
	public void anterior(View view) {
		Intent i = new Intent(this, SAXParserActivity.class);
		startActivity(i);
	}
}
