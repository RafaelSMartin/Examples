package com.jgs.almacenamiento;

public class Persona {
	
	private int dni;
	private String nombre;
	private String ciudad;
	
	public Persona() {
		this.dni = 0;
		this.nombre = "";
		this.ciudad = "";
	}
	
	public Persona(int dni, String nombre, String ciudad) {
		this.dni = dni;
		this.nombre = nombre;
		this.ciudad = ciudad;
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	@Override
	public String toString() {
		return "Dni: " + dni + "\nNombre: " + nombre + "\nCiudad: " + ciudad;
	}
}