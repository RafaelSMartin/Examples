package com.jgs.grabadora;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Grabadora extends Activity {

	private MediaRecorder mediaRecorder; //Objeto utilizado para grabacion
	private MediaPlayer mediaPlayer; //Objeto utilizado para reproduccion
	//Identifica el nombre del fichero donde se guardara la grabacion.
	//Este fichero se almacenara en una carpeta especialmente creada para nuestra aplicacion.
	private static String fichero = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp";
	
	//Botones
	private Button bGrabar;
	private Button bDetener;
	private Button bReproducir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Creamos los botones
		bGrabar = (Button) findViewById(R.id.bGrabar);
		bDetener = (Button) findViewById(R.id.bDetener);
		bReproducir = (Button) findViewById(R.id.bReproducir);
		
		//Deshabilitamos los siguientes botones de inicio
		bDetener.setEnabled(false);
		bReproducir.setEnabled(false);
	}
	
	/*
	 * Los siguientes tres metodos seran ejecutados al pulsar los botones de nuestro layout.
	 */

	public void grabar(View view) {
		
		//Deshabilitamos los siguientes botones de inicio
		bGrabar.setEnabled(false);
		bDetener.setEnabled(true);
		bReproducir.setEnabled(false);
		
		mediaRecorder = new MediaRecorder();
		mediaRecorder.setOutputFile(fichero);
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		
		try {
			mediaRecorder.prepare();
		} catch(IOException e) {
			Log.e("Grabadora", "Fallo de grabacion");
		}
		mediaRecorder.start();
	}
	
	public void detenerGrabacion(View view) {
		
		//Deshabilitamos los siguientes botones de inicio
		bGrabar.setEnabled(true);
		bDetener.setEnabled(false);
		bReproducir.setEnabled(true);
		
		mediaRecorder.stop();
		mediaRecorder.release();
		Log.e("Fichero", fichero);
	}
	
	public void reproducir(View view) {
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(fichero);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch(IOException e) {
			Log.e("Grabadora", "Fallo de reproduccion");
		}
	}
}
