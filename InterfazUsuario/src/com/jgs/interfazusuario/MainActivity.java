package com.jgs.interfazusuario;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View boton1 = findViewById(R.id.button1);
		boton1.setOnClickListener((OnClickListener) this);
		View boton2 = findViewById(R.id.button2);
		boton2.setOnClickListener((OnClickListener) this);
		View boton3 = findViewById(R.id.button3);
		boton3.setOnClickListener((OnClickListener) this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Intent i = new Intent(this, Boton1.class);
			startActivity(i);
			break;
		case R.id.button2:
			abrirNuevaActividad();
			break;			
		case R.id.button3:
			abrirPreferencias();
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(this, Preferencias.class));
			return true;
		case R.id.desarrolladoPor:
			startActivity(new Intent(this, DesarrolladoPor.class));
			return true;			

		default:
			break;
		}
		return false;
	}
	
	private void abrirNuevaActividad() {
		new AlertDialog.Builder(this).setTitle(R.string.boton2).setItems(R.array.niveles, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int i) {
				lanzar(i);
			}
			
		}).show();
	}
	
	private void lanzar(int i) {
		Log.d("Interfaz usuario", "Opcion elegida: "+i);
	}
	
	private void abrirPreferencias() {
		startActivity(new Intent(this, Preferencias.class));
	}
}
