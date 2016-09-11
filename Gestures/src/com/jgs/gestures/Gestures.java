package com.jgs.gestures;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;

public class Gestures extends Activity implements OnGesturePerformedListener{

	private GestureLibrary libreria;
	private TextView salida;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		libreria = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if(!libreria.load()) {
			finish();
		}
		GestureOverlayView gesturesView = (GestureOverlayView) findViewById(R.id.gestures);
		gesturesView.addOnGesturePerformedListener(this);
		salida = (TextView) findViewById(R.id.salida);
	}

	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		ArrayList<Prediction> predictions = libreria.recognize(gesture);
		salida.setText("");
		for(Prediction prediction : predictions) {
			salida.append(prediction.name + " " + prediction.score + "\n");
		}
	}
}
