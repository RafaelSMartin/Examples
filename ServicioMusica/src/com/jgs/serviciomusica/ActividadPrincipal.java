package com.jgs.serviciomusica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActividadPrincipal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button arrancar = (Button) findViewById(R.id.boton_arrancar);
		arrancar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(ActividadPrincipal.this, ServicioMusica.class));
			}
		});
		
		Button detener = (Button) findViewById(R.id.boton_detener);
		detener.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(ActividadPrincipal.this, ServicioMusica.class));
				stopService(new Intent(ActividadPrincipal.this, ServicioSocorro.class));
			}
		});
		
		Button socorro = (Button) findViewById(R.id.boton_socorro);
		socorro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(ActividadPrincipal.this, ServicioSocorro.class));
			}
		});
	}
}
