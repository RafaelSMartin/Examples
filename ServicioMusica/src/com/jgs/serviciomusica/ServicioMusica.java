package com.jgs.serviciomusica;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class ServicioMusica extends Service {

	private NotificationManager nm;
	private static final int ID_NOTIFICACION_CREAR = 1;
	
	MediaPlayer reproductor;
	
	@Override
	public void onCreate() {

		Notification notificacion = new Notification(R.drawable.ic_launcher, "Creando Servicio de Música", System.currentTimeMillis());
		
		notificacion.defaults |= Notification.DEFAULT_SOUND;

		PendingIntent intencionPendiente = PendingIntent.getActivity(this, 0, new Intent(this, ActividadPrincipal.class), 0);
		notificacion.setLatestEventInfo(this, "Reproduciendo música", "Información adicional", intencionPendiente);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.notify(ID_NOTIFICACION_CREAR, notificacion);
		
		Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();
		reproductor = MediaPlayer.create(this, R.raw.audio);
	}
	
	@Override
	public int onStartCommand(Intent intencion, int flags, int idArranque) {
		Toast.makeText(this, "Servicio arrancado " + idArranque, Toast.LENGTH_SHORT).show();
		reproductor.start();		
		return START_STICKY;
	}
	
//	@Override
//	public void onStart(Intent intent, int startId) {
//		Toast.makeText(this, "Servicio arrancado " + startId, Toast.LENGTH_SHORT).show();
//		reproductor.start();
//	}
	
	@Override
	public void onDestroy() {
		
		nm.cancel(ID_NOTIFICACION_CREAR);
		
		Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show();
		reproductor.stop();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
