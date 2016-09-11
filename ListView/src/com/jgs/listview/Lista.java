package com.jgs.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

public class Lista extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);
		
		List<String> lista1 =  new ArrayList<String>();
		lista1.add("Juan");
		lista1.add("Ana");
		lista1.add("Antonio");
		
		List<String> lista2 =  new ArrayList<String>();
		lista2.add("Córdoba");
		lista2.add("Sevilla");
		lista2.add("Madrid");

		setListAdapter(new Adaptador(this, lista1, lista2));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
