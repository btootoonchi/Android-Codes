package com.example.mythirdapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		
		HandlingSQLite handleSql = new HandlingSQLite(this);
		handleSql.open();
		String data = handleSql.getData();
		handleSql.close();
		tv.setText(data);
	}

}
