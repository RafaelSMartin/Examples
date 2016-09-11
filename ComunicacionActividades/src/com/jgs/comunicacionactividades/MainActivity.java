package com.jgs.comunicacionactividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View botonAceptar = findViewById(R.id.button1);
		botonAceptar.setOnClickListener((OnClickListener) this);
		View botonCancelar = findViewById(R.id.button2);
		botonCancelar.setOnClickListener((OnClickListener) this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			enviarDatos();
			break;
		case R.id.button2:
			limpiarCampos();
			break;			

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		TextView texto = (TextView) findViewById(R.id.textView7);
		if(requestCode==88 && resultCode==0) {
			texto.setText("Los datos son correctos");
		} else if(requestCode==88 && resultCode==1) {
			texto.setText("Los datos NO son correctos");
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void enviarDatos() {
		EditText nombre = (EditText) findViewById(R.id.editText1);
		EditText apellidos = (EditText) findViewById(R.id.editText2);
		EditText dni = (EditText) findViewById(R.id.editText3);
		EditText telefono = (EditText) findViewById(R.id.editText4);
		EditText email = (EditText) findViewById(R.id.editText5);
		Intent intent = new Intent(this, Actividad2.class);
		intent.putExtra("nombre", nombre.getText().toString());
		intent.putExtra("apellidos", apellidos.getText().toString());
		intent.putExtra("dni", dni.getText().toString());
		intent.putExtra("telefono", telefono.getText().toString());
		intent.putExtra("email", email.getText().toString());
		startActivityForResult(intent, 88);
	}
	
	private void limpiarCampos() {
		EditText nombre = (EditText) findViewById(R.id.editText1);
		nombre.setText("");
		EditText apellidos = (EditText) findViewById(R.id.editText2);
		apellidos.setText("");
		EditText dni = (EditText) findViewById(R.id.editText3);
		dni.setText("");
		EditText telefono = (EditText) findViewById(R.id.editText4);
		telefono.setText("");
		EditText email = (EditText) findViewById(R.id.editText5);
		email.setText("");
	}
}
