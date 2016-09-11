package com.jgs.eventostactil;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		boolean multiTouch = getPackageManager().hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
		Log.d("MainActivity", "Muti-Touch: " + multiTouch);
		
		TextView zonaRoja = (TextView) findViewById(R.id.zonaTouch);
		zonaRoja.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		TextView zonaTouch = (TextView) findViewById(R.id.zonaTouch);
		
		String acciones[] = {"ACTION_DOWN", "ACTION_UP", "ACTION_MOVE", "ACTION_CANCEL", 
				"ACTION_OUTSIDE", "ACTION_POINTER_DOWN", "ACTION_POINTER_UP"};
		int accion = event.getAction();
		int codigoAcion = accion & MotionEvent.ACTION_MASK;
		zonaTouch.append(acciones[codigoAcion]);
		for(int i=0; i<event.getPointerCount(); i++) {
			zonaTouch.append(" puntero:" + event.getPointerId(i) + " x:" + event.getX(i) + " y:" + event.getY(i));
		}
		zonaTouch.append("\n");
		
		return true;
	}
	
	public void limpiarTexto(View view) {
		TextView zonaTouch = (TextView) findViewById(R.id.zonaTouch);
		zonaTouch.setText("");
	}
}