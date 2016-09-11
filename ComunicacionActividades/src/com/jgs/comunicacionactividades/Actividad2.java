package com.jgs.comunicacionactividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Actividad2 extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad2);
		
		View botonAceptar = findViewById(R.id.button3);
		botonAceptar.setOnClickListener((OnClickListener) this);
		View botonCancelar = findViewById(R.id.button4);
		botonCancelar.setOnClickListener((OnClickListener) this);		
		
		Bundle extras = getIntent().getExtras();
		String nombre = extras.getString("nombre");
		String apellidos = extras.getString("apellidos");
		String dni = extras.getString("dni");
		String telefono = extras.getString("telefono");
		String email = extras.getString("email");
		
		TextView datos = (TextView) findViewById(R.id.textView6);
		datos.setText("DATOS PERSONALES\n" +
				"Nombre: " + nombre + "\n" +
				"Apellidos: " + apellidos + "\n" +
				"Dni: " + dni + "\n" +
				"Teléfono: " + telefono + "\n" +
				"E-mail: " + email);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button3:
			aceptar(v);
			break;
		case R.id.button4:
			cancelar(v);
			break;

		default:
			break;
		}
	}
	
	public void aceptar(View view) {
		Intent intent = new Intent();
		intent.putExtra("respuesta", "aceptar");
		setResult(0, intent);
		finish();
	}
	
	public void cancelar(View view) {
		Intent intent = new Intent();
		intent.putExtra("respuesta", "aceptar");
		setResult(1, intent);
		finish();
	}
}
