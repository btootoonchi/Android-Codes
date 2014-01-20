package com.example.mythirdapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener {

	Button bStart, bStartForActivity;
	EditText etSend;
	TextView tvGotAnswer;
	RelativeLayout rl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialization();
	}

	private void initialization() {
		// TODO Auto-generated method stub
		bStart = (Button)findViewById(R.id.bStartActivity);
		bStartForActivity = (Button)findViewById(R.id.bStartActivityForResult);
		tvGotAnswer = (TextView)findViewById(R.id.tvGot);
		etSend = (EditText)findViewById(R.id.etSend);
		bStart.setOnClickListener(this);
		bStartForActivity.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.bStartActivity:
			String strEditTextSend = etSend.getText().toString();
			Bundle bundle = new Bundle();
			bundle.putString("keyStrSend", strEditTextSend);
			Intent intent = new Intent(Data.this, OpenedClass.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.bStartActivityForResult:
			Intent intentResult = new Intent(Data.this, OpenedClass.class);
			startActivityForResult(intentResult, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK){
			Bundle basket = data.getExtras();
			String s = basket.getString("keyAnswer");
			tvGotAnswer.setText(s);
		}
	}

}
