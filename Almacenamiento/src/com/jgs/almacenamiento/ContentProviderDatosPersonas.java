package com.jgs.almacenamiento;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class ContentProviderDatosPersonas extends ContentProvider {

	/*
	 *Establecemos una uri para identificar nuestro ContentProvider.
	 *Esta nos servira como raiz para identificar todos los elementos de nuestra aplicacion a traves de este ContentProvider.
	 */
	public static final String AUTORIDAD = "com.jgs.almacenamiento"; //Lo utilizaremos para registrar nuestro ContentProvider en el Manifest 
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTORIDAD + "/" + ContentProviderDatosPersonaSQLiteHelper.NOMBRE_TABLA_DATOSPERSONASCP);
	
	public static final int TODOS_ELEMENTOS = 1;
	public static final int UN_ELEMENTO = 2;
	/*
	 *Este objeto nos sirve para analizar las URIs de los recursos que se piden y poder actuar en consecuencia.
	 *
	 *Es habitual inicializar el objeto UriMatcher en un bloque estatico ya que sera utilizado durante toda la vida del ContentProvider y no va a cambiar.
	 *
	 *Cuando se inicializa el objeto UriMatcher se le van agregando todas aquellas URIs susceptibles de ser manejadas por nuestro ContentProvider.
	 *En este caso hemos creado la instancia agregando la URI No_MATCH y a continuacion hemos agragado los recursos que vamos a manejar.
	 *Es importante destacar que cuando añadimos una URI solo añadimos la parte de AUTORIDAD y no el portocolo (en este caso "content://").
	 *En este caso hemos agragado dos URIs:
	 *1) Identifica todos los registros.
	 *2) Identifica un registro en concreto. 
	 */
	private static UriMatcher URI_MATCHER = null;
	static {
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(AUTORIDAD, ContentProviderDatosPersonaSQLiteHelper.NOMBRE_TABLA_DATOSPERSONASCP, TODOS_ELEMENTOS);
		URI_MATCHER.addURI(AUTORIDAD, ContentProviderDatosPersonaSQLiteHelper.NOMBRE_TABLA_DATOSPERSONASCP+"/#", UN_ELEMENTO);
	}
	
	/*
	 * Convencion para establecer los MIME types distinguiendo entre:
	 * 1) Todos los recursos.
	 * 2) Un recurso concreto.
	 */
	public static final String MIME_TYPE_TODOS_ITEM = "vnd.android.cursor.dir/vnd.com.jgs.almacenamientoContentProvider";
	public static final String MIME_TYPE_UN_ITEM = "vnd.android.cursor.item/vnd.com.jgs.almacenamientoContentProvider";
	
	/*
	 * Informacion relativa a la base de datos
	 */
	public static final String BASE_DATOS = ContentProviderDatosPersonaSQLiteHelper.NOMBRE_BASE_DATOS;
	public static final String TABLA = ContentProviderDatosPersonaSQLiteHelper.NOMBRE_TABLA_DATOSPERSONASCP;
	private ContentProviderDatosPersonaSQLiteHelper dbHelper;
	private SQLiteDatabase baseDatos;
	
	/*
	 * Comenzamos obteniendo la referencia a la base de datos.
	 * 
	 * Este metodo devuelve true si la base de datos existe y se ha podido abrir.
	 */
	@Override
	public boolean onCreate() {
		
		Log.d("ContentProviderDatosPersonas", "Metodo onCreate()");			
		
		//Obtenemos una referencia a la base de datos. Si no existe se crea.
		dbHelper = new ContentProviderDatosPersonaSQLiteHelper(this.getContext());
		//Almacenamos la referencia en nuestra variable en modo escritura
		baseDatos = dbHelper.getWritableDatabase();
		//Devolvemos el valor booleano del resultado de que nuestra referencia esta apuntando a algo y que la base de datos esta accesible
		return baseDatos != null && baseDatos.isOpen();
	}
	
	/*
	 * Establecemos los MIME types que va a manejar nuestro ContentProvider.
	 * En general existe una convencion sobre como establecer los MIME types distinguiendo entre un recurso unico y una coleccion de recursos.
	 */
	@Override
	public String getType(Uri uri) {
		switch (URI_MATCHER.match(uri)) {
		case TODOS_ELEMENTOS:	
			return MIME_TYPE_TODOS_ITEM;
		case UN_ELEMENTO:	
			return MIME_TYPE_UN_ITEM;
		default:
			throw new IllegalArgumentException("Metodo getType() URI incorrecta: " + uri);
		}
	}
	
/**
 * Metodos que nos permiten consultar, insertar, borrar y modificar registros en la base de datos.	
 */
	
	/*
	 * Este metodo recibe como parametros:
	 * 1) La URI que identifica el recurso que queremos obtener.
	 * 2) Un Array de String con los nombres de los campos que queremos obtener.
	 * 3) Un Array con las condiciones de la consulta.
	 * 4) Los valores para cada una de las condiciones.
	 * 5) Una expresion de ordenacion para los registros.
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		Cursor cursor = null;

		try {

			switch (URI_MATCHER.match(uri)) {
				//En el caso de querer todos lo elementos no hacemos nada.
				case TODOS_ELEMENTOS:
					cursor = baseDatos.query(TABLA, projection, selection, selectionArgs, null, null, null, sortOrder);
					break;
				//En caso de querer un elemento concreto añadimos al WHERE de la consulta que filtre por el dni
				case UN_ELEMENTO:
					String id = "_id=" + uri.getLastPathSegment();
					cursor = baseDatos.query(TABLA, projection, id, selectionArgs, null, null, null, sortOrder);
					break;
			}

		} catch(IllegalArgumentException e) {
			Log.d("ContentProviderDatosPersonas", "Metodo query() error: " + e.getMessage());	
		}

		return cursor;
	}
	
	/*
	 * Este metodo debe devolver la URI del registro que se ha insertado en la base de datos.
	 * 
	 * NOTA: se deberia comprobar la validez de la URI antes de insertar el registro en la base de datos. No se ha hecho porque es un ejemplo.
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long registro = 0;
		Uri nuevoRegistro = null;
		
		//Añadimos un bloque try porque si no puede procesar el values producira una Excepcion
		try {
			if(URI_MATCHER.match(uri) == TODOS_ELEMENTOS) {
				//Realizamos la insercion del registro en la base de datos y nos devuelve el identificador del nuevo registro.
				registro = baseDatos.insert(TABLA, null, values);
			}
		} catch(IllegalArgumentException e) {
			Log.e("ContentProviderDatosPersonas", "Metodo insert(), argumento no admitido: " + e.getMessage());
		} catch (Exception e) {
			Log.e("ContentProviderDatosPersonas", "Metodo insert(), error desconocido: " + e.getMessage());
		}

		//Si el registro ha sido insertado
		if(registro > 0) {
			//Construimos la URI del recurso sumandole a la URI del ContentProvider el id del recurso.
			nuevoRegistro = ContentUris.withAppendedId(CONTENT_URI, registro);
		} else {
			Log.e("ContentProviderDatosPersonas", "Metodo insert(), error al insertar el registro: " + registro);
		}
		
		return nuevoRegistro;
	}
	
	/*
	 * Devuelve el numero de registros actualizados.
	 * 
	 * Los parametros que recibe son:
	 * 1) La URI que identifica el recurso que se quiere actualizar.
	 * 2) Los valores que se quieren actualizar.
	 * 3) Las condiciones de la actualizacion.
	 * 4) Los valores de esas condiciones.
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		
		int filasAfectadas = 0;
		
		//Distinguimos el tipo de URI que recibimos para ver si actualizamos todos los registros de la TABLA con los valores que recibimos
		//en el ContentValues o si solo vamos a modificar un solo registo
		switch (URI_MATCHER.match(uri)) {
			case TODOS_ELEMENTOS:
				filasAfectadas = baseDatos.update(TABLA, values, selection, selectionArgs);
				break;
			case UN_ELEMENTO:
				String id = uri.getPathSegments().get(1);
				//Si solo es un registro añadimos el filtro de su identificador
				filasAfectadas = baseDatos.update(TABLA, values, ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI + "=" + id, selectionArgs);
				break;
	
			default:
				throw new IllegalArgumentException("Metodo uodate() URI incorrecta: " + uri);
		}
		
		return filasAfectadas;
	}
	
	/*
	 * Devuelve el numero de registros eliminados.
	 * 
	 * Su funcionamiento es igual que el metodo update() solo que ahora utilizamos delete()
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		int filasAfectadas = 0;
		
		switch (URI_MATCHER.match(uri)) {
			case TODOS_ELEMENTOS:
				filasAfectadas = baseDatos.delete(TABLA, selection, selectionArgs);
				break;
			case UN_ELEMENTO:
				String id = uri.getPathSegments().get(1);
				filasAfectadas = baseDatos.delete(TABLA, ContentProviderDatosPersonaSQLiteHelper.CAMPO_DNI + "=" + id, selectionArgs);
				break;
	
			default:
				throw new IllegalArgumentException("Metodo delete() URI incorrecta: " + uri);
		}
		
		return filasAfectadas;
	}

}
