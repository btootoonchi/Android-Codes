package com.example.mythirdapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class Flipper extends Activity implements OnClickListener {

	ViewFlipper viewFlippy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipper);
		
		viewFlippy = (ViewFlipper)findViewById(R.id.viewFlipper1);
		viewFlippy.setOnClickListener(this);
		viewFlippy.setFlipInterval(500);
		viewFlippy.startFlipping();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		viewFlippy.showNext();
	}

}
