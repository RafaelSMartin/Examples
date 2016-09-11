package com.jgs.listview;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {

	private final Activity actividad;
	private final List<String> titulo;
	private final List<String> subtitulo;
	
	public Adaptador(Activity actividad, List<String> titulo, List<String> subtitulo) {
		super();
		this.actividad = actividad;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
	}
	
	@Override
	public int getCount() {
		return titulo.size();
	}

	@Override
	public Object getItem(int position) {
		return titulo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = actividad.getLayoutInflater();
		View vista = inflater.inflate(R.layout.elemento_lista, null, true);
		
		ImageView imagen = (ImageView) vista.findViewById(R.id.imagen_lista);
		imagen.setImageResource(R.drawable.button_red);
		TextView titulo = (TextView) vista.findViewById(R.id.titulo);
		titulo.setText(this.titulo.get(position));
		TextView subtitulo = (TextView) vista.findViewById(R.id.subtitulo);
		subtitulo.setText(this.subtitulo.get(position));
		
		return vista;
	}

}
