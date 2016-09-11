package com.jgs.almacenamiento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContentProviderDatosPersonaSQLiteHelper extends SQLiteOpenHelper{

	public static final String NOMBRE_BASE_DATOS = "Personas";
	public static final String NOMBRE_TABLA_DATOSPERSONASCP = "datosPersonasCP";
	public static final String CAMPO_ID = "_id";
	public static final String CAMPO_DNI = "dni";
	public static final String CAMPO_NOMBRE = "nombre";
	public static final String CAMPO_CIUDAD = "ciudad";
	public static final String[] PROYECCION_TODOS_CAMPOS = new String[]{CAMPO_ID, CAMPO_DNI, CAMPO_NOMBRE, CAMPO_CIUDAD};
	public static final int VERSION_BASE_DATOS = 2;
	
	public ContentProviderDatosPersonaSQLiteHelper(Context context) {
		super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
		
		Log.d("ContentProviderDatosPersonaSQLiteHelper", "Constructor SQLiteHelper");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		Log.d("ContentProviderDatosPersonaSQLiteHelper", "onCreate()");
		
		db.execSQL("CREATE TABLE datosPersonasCP (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
				"dni INTEGER, " +
				"nombre TEXT, " +
				"ciudad TEXT " +
				")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.d("ContentProviderDatosPersonaSQLiteHelper", "onUpgrade()");
		
		db.execSQL("CREATE TABLE datosPersonasCP (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
				"dni INTEGER, " +
				"nombre TEXT, " +
				"ciudad TEXT " +
				")");
	}
}