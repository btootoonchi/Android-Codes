package com.example.mythirdapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlFamily, sqlRow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		
		sqlUpdate = (Button)findViewById(R.id.bSQLUpdate);
		sqlView = (Button)findViewById(R.id.bSQLopenView);
		sqlName = (EditText)findViewById(R.id.etSQLName);
		sqlFamily = (EditText)findViewById(R.id.etSQLFamily);
		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		
		sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
		sqlModify = (Button) findViewById(R.id.bSQLmodify);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlDelete = (Button) findViewById(R.id.bSQLdelete);
		sqlGetInfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.bSQLopenView:
			Intent i = new Intent("com.example.mythirdapp.SQLVIEW");
			startActivity(i);
			break;
		case R.id.bSQLUpdate:
			
			boolean didItWork = true;
			try {
				String name = sqlName.getText().toString();
				String family = sqlFamily.getText().toString();

				HandlingSQLite entry = new HandlingSQLite(SQLiteExample.this);
				entry.open();
				entry.createEntry(name, family);
				entry.close();
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Exception!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Work!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.bGetInfo:
			try {
				String s = sqlRow.getText().toString();
				long l = Long.parseLong(s);
				HandlingSQLite handleSql = new HandlingSQLite(this);
				handleSql.open();
				String returnedName = handleSql.getName(l);
				String returnedFamily = handleSql.getFamily(l);
				handleSql.close();

				sqlName.setText(returnedName);
				sqlFamily.setText(returnedFamily);
			} catch (Exception e) {

				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Information!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		case R.id.bSQLmodify:
			try {
				String mName = sqlName.getText().toString();
				String mFamily = sqlFamily.getText().toString();
				String sRow = sqlRow.getText().toString();
				long lRow = Long.parseLong(sRow);

				HandlingSQLite ex = new HandlingSQLite(this);
				ex.open();
				ex.updateEntry(lRow, mName, mFamily);
				ex.close();
			} catch (Exception e) {

				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Modify!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		case R.id.bSQLdelete:
			try {
				String sRow1 = sqlRow.getText().toString();
				long lRow1 = Long.parseLong(sRow1);
				HandlingSQLite ex1 = new HandlingSQLite(this);
				ex1.open();
				ex1.deleteEntry(lRow1);
				ex1.close();
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Delete!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		}
	}

}
