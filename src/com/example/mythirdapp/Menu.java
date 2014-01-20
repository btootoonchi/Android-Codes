package com.example.mythirdapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	String classes[] = { "MainActivity", "TextPlay", "Email", "Camera", "Data"
			, "Graphic", "GraphicSurface", "Sound", "Slider", "Tabs", "SimpleBrowser"
			, "Flipper", "SharedPrefs", "InternalData", "ExternalData", "SQLiteExample", "example5" 
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//end full screen 
		
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String mnuString = classes[position];
		try {
			//Class myClass = Class.forName("com.example.mythirdapp.MainActivity");
			Class myClass = Class.forName("com.example.mythirdapp." + mnuString);
			Intent myIntent = new Intent(Menu.this, myClass);
			startActivity(myIntent);
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}
	

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.activity_main, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// return super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case R.id.mnuAboutUs:
			Intent intentAboutUs = new Intent("com.example.mythirdapp.ABOUT");
			startActivity(intentAboutUs);
			break;
		case R.id.mnuPreferences:
			Intent intentPreference = new Intent("com.example.mythirdapp.PREFS");
			startActivity(intentPreference);
			break;
		case R.id.mnuExit:
			finish();
			break;
		}
		
		return false;
	}
	
}
