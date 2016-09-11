/*
 * Para reproducirlo en el emulador. En un dispositivo fisico no funciona por como esta construida el Path al fichero, abria que cambiarlo.
 */

package com.jgs.video;

import android.os.Bundle;
import android.app.Activity;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends Activity {

	private VideoView mVideoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		//Cargamos la pelicula
		mVideoView.setVideoPath("/data/video.mp4");
		//Añadimos los controles de videro
		mVideoView.setMediaController(new MediaController(this));
		//Iniciamos la pelicula
		mVideoView.start();
		//Le asignamos el foco
		mVideoView.requestFocus();
	}
}
