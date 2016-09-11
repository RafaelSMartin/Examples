package com.jgs.vistas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MiVista extends View {

	public MiVista(Context context, AttributeSet attrs) {
		super(context);
	}

	@Override
	protected void onSizeChanged(int ancho, int alto, int ancho_anterior, int alto_anterior) {
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		
		canvas.drawText("Esta zona es el canvas de la vista sobre el que podemos pintar", 0f, 300f, paint);
	}
}
