package com.jgs.eventosteclado;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onKeyDown(int codigoTecla, KeyEvent evento) {
		super.onKeyDown(codigoTecla, evento);
		boolean procesada = true;
		
		switch (codigoTecla) {
		case KeyEvent.KEYCODE_1:
			Toast.makeText(this, "1 Pulsado", Toast.LENGTH_SHORT).show();
			break;
		case KeyEvent.KEYCODE_2:
			Toast.makeText(this, "2 Pulsado", Toast.LENGTH_SHORT).show();
			break;
		case KeyEvent.KEYCODE_3:
			Toast.makeText(this, "3 Pulsado", Toast.LENGTH_SHORT).show();
			break;
		case KeyEvent.KEYCODE_4:
			Toast.makeText(this, "4 Pulsado", Toast.LENGTH_SHORT).show();
			break;
			
		default:
			procesada = false;
			break;
		}
		return procesada;
	}
	
	@Override
	public boolean onKeyUp(int codigoTecla, KeyEvent evento) {
		super.onKeyDown(codigoTecla, evento);
		boolean procesada = true;
		
		switch (codigoTecla) {
		case KeyEvent.KEYCODE_1:
			Toast.makeText(this, "1 Soltado", Toast.LENGTH_SHORT).show();
			break;
		case KeyEvent.KEYCODE_2:
			Toast.makeText(this, "2 Soltado", Toast.LENGTH_SHORT).show();
			break;
		case KeyEvent.KEYCODE_3:
			Toast.makeText(this, "3 Soltado", Toast.LENGTH_SHORT).show();
			break;
		case KeyEvent.KEYCODE_4:
			Toast.makeText(this, "4 Soltado", Toast.LENGTH_SHORT).show();
			break;
			
		default:
			procesada = false;
			break;
		}
		return procesada;
	}
}