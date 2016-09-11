package com.jgs.almacenamiento;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteGestionarBD extends SQLiteOpenHelper {

/**
 * Metodos para crear y mantener la base de datos.
 */
	
	/*
	 * El constructor de la clase se limita a llamar al construcotr heredado con el perfil:
	 * 
	 * SQLiteOpenHelper(Context context, String name, CursorFactory factory, int version)
	 * 
	 * Los parametros se describen a continuacion:
	 * 
	 * 	context: contexto usado para abrir o crear la base de datos.
	 * 	name: nombre de la base de datos que se creara.
	 * 	factory: se utiliza para crear un objeto de tipo cursor. No tiene porque ser necesario
	 * 	version: numero de version de la base de datos empezando desde 1. En el caso de que la
	 * 			base de datos actual tenga una version mas antigua se llamara a onUpgrade() para
	 * 			que actualice la base de datos.
	 */
	public SQLiteGestionarBD(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	/*
	 * Invocado cuando sea necesario crear la base de datos.
	 * En este caso solo creamos una tabla.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE datosPersonas (" +
				"dni INTEGER PRIMARY KEY, " +
				"nombre TEXT, " +
				"ciudad TEXT " +
				")");
	}

	/*
	 * En este caso no se ha implementado. Si mas adelante decides crear una nueva estructura
	 * para la base de datos, tendriamos que indicar un numero de version superior, por ejemplo
	 * la 2. Cuando se ejecute el codigo sobre un sistema donde se dispone de una base de datos
	 * con la version 1, sera invocando el metodo onUpgrade(). En el tendriamos que escribir
	 * los comandos necesarios para transformar la antigua base de datos en la nueva, tratando
	 * de conservar la informacion de la version anterior.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//En caso de una nueva version habria que actualizar las tablas
	}

/**
 * Metodos para manipular la base de datos
 */	
	
	/*
	 * Comienza obteniendo una referencia a nuestra base de datos utilizando getWritableDatabase().
	 * Con ella ejecuta el comando SQL para almacenar una nueva fila en la tabla datosPersona.
	 */
	public void addPersona(int dni, String nombre, String ciudad) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("INSERT INTO datosPersonas (dni, nombre, ciudad) VALUES ("+dni+", '"+nombre+"', '"+ciudad+"')");
	}
	
	/*
	 * Comienza obteniendo una referencia a nuestra base de datos utilizando getReadableDatabase().
	 * Realiza una consulta utilizando el metodo rawQuery(), con la que obtiene un cursor que utiliza
	 * para leer todas las filas devueltas en la consulta.
	 */
	public List<String> getListaDatosPersonas() {
		List<String> lista = new ArrayList<String>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM datosPersonas ORDER BY dni", null);
		while(cursor.moveToNext()) {
			lista.add(cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getString(2));
		}
		cursor.close();
		return lista;
	}
}
