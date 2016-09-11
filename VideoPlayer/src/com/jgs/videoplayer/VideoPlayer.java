package com.jgs.videoplayer;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/*
 * La aplicacion extiende de Activity. Ademas, implementamos cuatro interfaces que corresponden
 * a varios escuchadores de eventos.
 */
public class VideoPlayer extends Activity implements OnBufferingUpdateListener, 
	OnCompletionListener, MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {

   private MediaPlayer mediaPlayer;
   private SurfaceView surfaceView;
   private SurfaceHolder surfaceHolder;
   private EditText editText;
   private ImageButton bPlay, bPause, bStop, bLog;
   private TextView logTextView;
   private boolean pause; //indica si el usuario ha pulsado el boton correspodiente
   private String path; //indica donde esta el video en reproduccion
   private int savePos = 0; //almacena la posicion de reproduccion
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		editText = (EditText) findViewById(R.id.path);
		editText.setText("http://personales.gan.upv.es/~jtomas/video.3gp");
		logTextView = (TextView) findViewById(R.id.Log);
		
		bPlay = (ImageButton) findViewById(R.id.play);
		bPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mediaPlayer != null) {
					if(pause) {
						mediaPlayer.start();
					} else {
						playVideo();
					}
				}
			}
		});
		
		bPause = (ImageButton) findViewById(R.id.pause);
		bPause.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mediaPlayer != null) {
					pause = true;
					mediaPlayer.pause();
				}
			}
		});
		
		bStop = (ImageButton) findViewById(R.id.stop);
		bStop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mediaPlayer != null) {
					pause = false;
					mediaPlayer.stop();
				}
			}
		});
		
		bLog = (ImageButton) findViewById(R.id.logButton);
		bLog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(logTextView.getVisibility() == TextView.VISIBLE) {
					logTextView.setVisibility(TextView.INVISIBLE);
				} else {
					logTextView.setVisibility(TextView.VISIBLE);
				}
			}
		});
		
		log("");
	}
	
	/*
	 * Este metodo se encarga de obtener la ruta de reproduccion, crear un nuevo objeto MediaPlayer,
	 * luego se le asigna la ruta y la superficie de visualizacion, a continuacion, se prepara la
	 * reproduccion del video.
	 * 
	 * En caso de querer reproducir un stream desde la red, esta funcion puede tardar bastante tiempo,
	 * en tal caso es recomendable utilizar en su lugar el metodo prepareAsync() que permite continuar
	 * con la ejecucion del programa, aunque sin esperar que el video se cargue por completo.
	 * 
	 * Las siguientes tres lineas asignan a nuestro objeto varios escuchadores de eventos.
	 * 
	 * Tras preparar el tipo de audio, se situa la posicion de reproduccion a los milisegundos indicados
	 * en la variable savePos. Si se trata de una nueva reproduccion, esta variable sera 0.
	 */
	private void playVideo() {
        try {
        	pause = false;
            path = editText.getText().toString();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.prepare();
            // mediaPlayer.prepareAsync(); //Para streaming
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.seekTo(savePos);
        } catch (Exception e) {
            log("ERROR: " + e.getMessage());
     	}
	}
	
	/*
	 * Este metodo implementa la interfaz OnBufferingUpdate y se encarga de mostrar
	 * el porcentaje de obtencion de bufer de reproduccion.
	 */

	public void onBufferingUpdate(MediaPlayer arg0, int percent) {
		log("onBufferingUpdate percent: " + percent);
	}
	
	/*
	 * Este metodo implementa la interfaz OnCompletationListener y sera invocado
	 * cuando el video en reproduccion llegue al final.
	 */

	public void onCompletion(MediaPlayer mp) {
		log("onCompletation called");
	}
	
	/*
	 * Este metodo implementa la interfaz OnPreparedListener y es invocado una vez que el video
	 * ya esta preparado para su reproduccion. En este momento podemos conocer el alto y el ancho
	 * del video y ponerlo en reproduccion.
	 */

	public void onPrepared(MediaPlayer mediaPlayer) {
		log("onPrepared called");
		int mVideoWidth = mediaPlayer.getVideoWidth();
		int mVideoHeight = mediaPlayer.getVideoHeight();
		if(mVideoWidth != 0 && mVideoHeight != 0) {
			surfaceHolder.setFixedSize(mVideoWidth, mVideoHeight);
			mediaPlayer.start();
		}
	}	
	
	/*
	 * Los siguientes tres metodos implementan la interfaz SurfaceHolder.Callback.
	 * Se invocaran cuando la superficie de visualizacion se cree, cambie o se destruya.
	 */
	
	public void surfaceCreated(SurfaceHolder holder) {
		log("surfaceCreated called");
		playVideo();
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		log("surfaceChanged called");
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		log("surfaceDestroyed called");
	}
	
	/*
	 * El siguiente metodo se invoca cuando la actividad va a ser destruida. Dado que un objeto
	 * de la clase MediaPlayer consume muchos recursos, resulta interesante liberarlos lo antes
	 * posible.
	 */
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
	
	/*
	 * Los siguientes dos metodos se invocan cuando la actividad pasa a un segundo plano y cuando
	 * vuelve al primer plano. Dado que queremos que el video deje de reproducirse y continue
	 * reproduciendose en cada uno de estos casos, se invocan a los metodos pause() y start(),
	 * respectivamente. No hay que confundir esta accion con la variable pause que lo que indica
	 * es que el usuario ha pulsado el boton correspondiente.
	 */
	
	@Override
	public void onPause() {
		super.onPause();
		if(mediaPlayer != null && !pause) {
			mediaPlayer.pause();
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if(mediaPlayer != null && !pause) {
			mediaPlayer.start();
		}
	}
	
	/*
	 * Se llamara al siguiente metodo para darnos la oportunidad de guardar informacion sensible.
	 * Si, mas adelante, el usuario vuelve a la aplicacion, esta se volvera a cargar, invocandose
	 * el metodo onRestoreInstanceState donde podremos restaurar el estado perdido.
	 * En nuestro caso la informacion a guardar son las variables path y savePos, que representan
	 * el video y la posicion que estamos reproduciendo.
	 * 
	 * Estos metodos tambien se invocan cuando se voltea el telefono.
	 */
	
	@Override
	protected void onSaveInstanceState(Bundle guardarEstado) {
		super.onSaveInstanceState(guardarEstado);
		if(mediaPlayer != null) {
			int pos = mediaPlayer.getCurrentPosition();
			guardarEstado.putString("ruta", path);
			guardarEstado.putInt("posicion", pos);
		}
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle recEstado) {
		super.onRestoreInstanceState(recEstado);
		if(recEstado != null) {
			path = recEstado.getString("ruta");
			savePos = recEstado.getInt("posicion");
		}
	}
	
	/*
	 * Este metodo se utiliza para mostrar informacion sobre lo que esta pasando.
	 */
	
	private void log(String s) {
		logTextView.append(s + "\n");
	}
}
