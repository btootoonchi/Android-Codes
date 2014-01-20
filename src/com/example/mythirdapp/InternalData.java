package com.example.mythirdapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResults;
	String FILENAME ="InteralString";
	FileOutputStream fileOutput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		
		initialization();
	}

	private void initialization() {

		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		
		try {
			fileOutput = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fileOutput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.bSave:
			String stringData = sharedData.getText().toString();
			// Saving data via File
			/*File fileID = new File(FILENAME);
			try {
				fileOutput = new FileOutputStream(fileID);
				fileOutput.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				fileOutput = openFileOutput(FILENAME, Context.MODE_PRIVATE);
				fileOutput.write(stringData.getBytes());
				fileOutput.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bLoad:
			new loadSomeStuff().execute(FILENAME);
			break;
		}
	}

	public class loadSomeStuff extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			dataResults.setText(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			//super.onPreExecute();
			//f = "whatever";
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			//super.onProgressUpdate(values);
			dialog.incrementProgressBy(values[0]);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String stringCollected = null;
			FileInputStream fileInput = null;
			
			for(int i = 0; i < 20; i++){
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			
			try {
				fileInput = openFileInput(FILENAME);
				byte[] dataArray = new byte[fileInput.available()];
				while(fileInput.read(dataArray) != -1 ){
					stringCollected = new String(dataArray);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fileInput.close();
					//dataResults.setText(stringCollected);
					return stringCollected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
	}
}
