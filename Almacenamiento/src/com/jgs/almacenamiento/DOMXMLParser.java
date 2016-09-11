package com.jgs.almacenamiento;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;

public class DOMXMLParser {

	//Devuelve el documento XML completo
	public Document getDocument(InputStream inputStream) {
		Document documento = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource(inputStream);
			documento = db.parse(inputSource);
		} catch(ParserConfigurationException e) {
			Log.d("DOMXMLParser", "ParserConfigurationException: " + e.getMessage());
		} catch(SAXException e) {
			Log.d("DOMXMLParser", "SAXException: " + e.getMessage());
		} catch(IOException e) {
			Log.d("DOMXMLParser", "IOException: " + e.getMessage());
		}
		
		return documento;
	}
	
	public String getValue(Element item, String name) {
		NodeList nodos = item.getElementsByTagName(name);
		return this.getTextNodeValue(nodos.item(0));
	}
	
	private final String getTextNodeValue(Node nodo) {
		Node hijo;
		if(nodo != null) {
			if(nodo.hasChildNodes()) {
				hijo = nodo.getFirstChild();
				while(hijo != null) {
					if(hijo.getNodeType() == Node.TEXT_NODE) {
						return hijo.getNodeValue();
					}
					hijo = hijo.getNextSibling();
				}
			}
		}
		return "";
	}
}
