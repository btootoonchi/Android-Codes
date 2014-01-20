package com.example.mythirdapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener{

	TextView tvQuestion, tvTest;
	Button bReturnData;
	RadioGroup rgSelectionList;
	String strGotBundle, strSetData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialization();
		/*Bundle getBundle = getIntent().getExtras();
		strGotBundle = getBundle.getString("keyStrSend");
		tvQuestion.setText(strGotBundle);*/
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String getName = getPrefs.getString("name", "The first Android application ...");
		String getListValues = getPrefs.getString("list", "4");
		if(getListValues.contentEquals("1"))
			tvQuestion.setText(getName);
		
	}

	private void initialization() {
		// TODO Auto-generated method stub
		tvQuestion = (TextView)findViewById(R.id.tvQuestions);
		tvTest = (TextView)findViewById(R.id.tvText);
		bReturnData = (Button)findViewById(R.id.bReturn);
		bReturnData.setOnClickListener(this);
		rgSelectionList = (RadioGroup)findViewById(R.id.rgAnswers);
		rgSelectionList.setOnCheckedChangeListener(this); 
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("keyAnswer", strSetData);
		person.putExtras(backpack);
		setResult(RESULT_OK, person);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup rGroup, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId){
		case R.id.rYes:
			strSetData = "YES!";
			break;
		case R.id.rNo:
			strSetData = "NO!";
			break;
		case R.id.rMaybe:
			strSetData = "MEYBE";
			break;
		}
		tvTest.setText(strSetData);
	}

}
