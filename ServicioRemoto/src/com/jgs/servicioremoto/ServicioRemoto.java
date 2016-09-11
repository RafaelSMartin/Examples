package com.jgs.servicioremoto;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

public class ServicioRemoto extends Service {

	MediaPlayer reproductor;
	
	@Override
	public void onCreate() {
		super.onCreate();
		reproductor = MediaPlayer.create(ServicioRemoto.this, R.raw.audio);
	}
	
	private final IServicioMusica.Stub binder = new IServicioMusica.Stub() {
		
		@Override
		public void setPosicion(int ms) throws RemoteException {
			reproductor.seekTo(ms);
		}
		
		@Override
		public String reproduce(String mensaje) throws RemoteException {
			reproductor.start();
			return mensaje;
		}
		
		@Override
		public int getPosicion() throws RemoteException {
			return reproductor.getCurrentPosition();
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		return this.binder;
	}
}
