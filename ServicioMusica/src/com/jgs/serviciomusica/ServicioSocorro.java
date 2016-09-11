package com.jgs.serviciomusica;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServicioSocorro extends Service {

	private NotificationManager nm;
	private static final int ID_NOTIFICACION_CREAR = 2;
	
	@Override
	public void onCreate() {
		Notification notificacion = new Notification(R.drawable.ic_launcher, "Creando Servicio de Socorro", System.currentTimeMillis());
		
		long[] vibrate = {0,500,300,500,300,500,300,1000,300,1000,300,1000,300,500,300,500,300,500};
		notificacion.vibrate = vibrate;
		
		PendingIntent intencionPendiente = PendingIntent.getActivity(this, 0, new Intent(this, ActividadPrincipal.class), 0);
		notificacion.setLatestEventInfo(this, "Socorro", "Pidiendo ayuda", intencionPendiente);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.notify(ID_NOTIFICACION_CREAR, notificacion);
	}
	
	@Override
	public int onStartCommand(Intent intencion, int flags, int idArranque) {		
		return START_STICKY;
	}

	@Override
	public void onDestroy() {	
		nm.cancel(ID_NOTIFICACION_CREAR);
	}

	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
