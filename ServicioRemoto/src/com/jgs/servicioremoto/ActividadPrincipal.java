package com.jgs.servicioremoto;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActividadPrincipal extends Activity {

	private IServicioMusica servicio; //1
	
	private ServiceConnection conexion = new ServiceConnection() { //2
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) { //4
			servicio = IServicioMusica.Stub.asInterface(service);
			Toast.makeText(ActividadPrincipal.this, "Conectado a Servicio", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			servicio = null;
			Toast.makeText(ActividadPrincipal.this, "Se ha perdido la conexión con el servicio", Toast.LENGTH_SHORT).show();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button botonConectar = (Button) findViewById(R.id.boton_arrancar);
		botonConectar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ActividadPrincipal.this.bindService(new Intent(ActividadPrincipal.this, ServicioRemoto.class), conexion, Context.BIND_AUTO_CREATE); //3
			}
		});
		
		Button botonReproducir = (Button) findViewById(R.id.boton_reproducir);
		botonReproducir.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					servicio.reproduce("titulo"); //5
				} catch(Exception e) { 
					Toast.makeText(ActividadPrincipal.this, e.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		Button botonAvanzar = (Button) findViewById(R.id.boton_avanzar);
		botonAvanzar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					servicio.setPosicion(servicio.getPosicion()+1000);
				} catch(Exception e) {
					Toast.makeText(ActividadPrincipal.this, e.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		Button botonDetener = (Button) findViewById(R.id.boton_detener);
		botonDetener.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					ActividadPrincipal.this.unbindService(conexion); //6
				} catch(Exception e) {
					Toast.makeText(ActividadPrincipal.this, e.toString(), Toast.LENGTH_SHORT).show();
				}
				servicio = null;
			}
		});
	}
}
