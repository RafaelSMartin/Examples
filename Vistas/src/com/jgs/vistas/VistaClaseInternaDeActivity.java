package com.jgs.vistas;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class VistaClaseInternaDeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.vista_clase_interna_de_activity);
		addContentView(new MiVista(this), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}
	
	static public class MiVista extends View {
		
		public MiVista(Context context) {
			super(context);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {

			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setTextSize(20);
			
			canvas.drawText("Esta zona es el canvas de la vista sobre el que podemos pintar", 0f, 300f, paint);
		}
	}
	
	public void siguienteActivity(View view) {
		Intent i = new Intent(this, VistaFicheroIndependiente.class);
		startActivity(i);
	}
	
	public void volverActivity(View view) {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}
}