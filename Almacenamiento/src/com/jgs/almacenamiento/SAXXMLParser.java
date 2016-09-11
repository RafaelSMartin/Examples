package com.jgs.almacenamiento;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.jgs.almacenamiento.Persona;

public class SAXXMLParser {

	public static List<Persona> parse(InputStream is) {
		
		List<Persona> listaPersonas = null;
		
		try {
			
			//Creamos un XMLReader para SAXParser
			XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			//Creamos un SAXXMLHandler
			SAXXMLHandler saxHandler = new SAXXMLHandler();
			//Almacenamos el handler en XMLReader
			xmlReader.setContentHandler(saxHandler);
			//Arrancamos el proceso
			xmlReader.parse(new InputSource(is));
			//Obtenemos la lista de personas
			listaPersonas = saxHandler.getListaPersonas();
			
		} catch(Exception e) {
			Log.d("SAXXMLParser", "parse() fallo"); 
		}
		
		//Retornamos la lista
		return listaPersonas;
	}
}
