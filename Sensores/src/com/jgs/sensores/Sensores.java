package com.jgs.sensores;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Sensores extends Activity implements SensorEventListener{

	private TextView salida;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		salida = (TextView) findViewById(R.id.salida);
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
		
		for(Sensor sensor : listaSensores) {
			log(sensor.getName());
		}
		
		//Obtener la lectura de cada uno de los sensores
		listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
		if(!listaSensores.isEmpty()) {
			Sensor orientationSensor = listaSensores.get(0);
			sensorManager.registerListener((SensorEventListener) this, orientationSensor, SensorManager.SENSOR_DELAY_UI);
		}
		listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if(!listaSensores.isEmpty()) {
			Sensor acelerometerSensor = listaSensores.get(0);
			sensorManager.registerListener((SensorEventListener) this, acelerometerSensor, SensorManager.SENSOR_DELAY_UI);
		}
		listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
		if(!listaSensores.isEmpty()) {
			Sensor magneticSensor = listaSensores.get(0);
			sensorManager.registerListener((SensorEventListener) this, magneticSensor, SensorManager.SENSOR_DELAY_UI);
		}
		listaSensores = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);
		if(!listaSensores.isEmpty()) {
			Sensor temperatureSensor = listaSensores.get(0);
			sensorManager.registerListener((SensorEventListener) this, temperatureSensor, SensorManager.SENSOR_DELAY_UI);
		}
	}
	
	private void log(String string) {
		salida.append(string + "\n");
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int precision) {}

	@Override
	public void onSensorChanged(SensorEvent event) {
		//Cada sensor puede provocar que un thread principal pase por aqui,
		//asi que sincronizamos el acceso.
		synchronized (this) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ORIENTATION:
				for(int i=0; i<3; i++) {
					log("Orientacion " + i + ": " + event.values[i]);
				}
				break;
			case Sensor.TYPE_ACCELEROMETER:
				for(int i=0; i<3; i++) {
					log("Acelerometro " + i + ": " + event.values[i]);
				}
			case Sensor.TYPE_MAGNETIC_FIELD:
				for(int i=0; i<3; i++) {
					log("Magnetismo " + i + ": " + event.values[i]);
				}				
			default:
				for(int i=0; i<event.values.length; i++) {
					log("Temperatura " + i + ": " + event.values[i]);
				}					
			}
		}
	}
}
