package com.jgs.almacenamiento;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jgs.almacenamiento.Persona;

public class SAXXMLHandler extends DefaultHandler {

	private List<Persona> listaPersonas;
	private Persona persona;
	private String aux;
	
	public SAXXMLHandler() {
		listaPersonas = new ArrayList<Persona>();
	}
	
	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}
	
	//Eventos Handler
	@Override
	public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atr) throws SAXException {
		aux = "";
		if(nombreCualif.equals("persona")) {
			persona = new Persona();
		}
	}
	
	@Override
	public void characters(char ch[], int comienzo, int longitud) {
		aux = new String(ch, comienzo, longitud);
	}
	
	@Override
	public void endElement(String uri, String nombreLocal, String nombreCualif) throws SAXException {
		if(nombreCualif.equalsIgnoreCase("persona")) {
			listaPersonas.add(persona);
		} else if(nombreCualif.equalsIgnoreCase("dni")) {
			persona.setDni(Integer.parseInt(aux));
		} else if(nombreCualif.equalsIgnoreCase("nombre")) {
			persona.setNombre(aux);
		} else if(nombreCualif.equalsIgnoreCase("ciudad")) {
			persona.setCiudad(aux);
		}
	}
}
