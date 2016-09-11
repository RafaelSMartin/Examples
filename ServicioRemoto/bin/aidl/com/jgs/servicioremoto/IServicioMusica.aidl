package com.jgs.servicioremoto;

interface IServicioMusica {
	String reproduce(in String mensaje);
	void setPosicion(int ms);
	int getPosicion();
}
