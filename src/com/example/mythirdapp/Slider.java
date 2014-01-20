package com.example.mythirdapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener{

	SlidingDrawer slidingDrawer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		
		Button button1 = (Button)findViewById(R.id.bHandle1);
		Button button2 = (Button)findViewById(R.id.bHandle2);
		Button button3 = (Button)findViewById(R.id.bHandle3);
		Button button4 = (Button)findViewById(R.id.bHandle4);
		CheckBox checkBox = (CheckBox)findViewById(R.id.cbSlidable);
		checkBox.setOnCheckedChangeListener(this);
		slidingDrawer = (SlidingDrawer)findViewById(R.id.slidingD);
		slidingDrawer.setOnDrawerOpenListener(this);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
		// TODO Auto-generated method stub
		if(compoundButton.isChecked()){
			slidingDrawer.lock();
		} else {
			slidingDrawer.unlock();
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.bHandle1:
			slidingDrawer.open();
			break;
		case R.id.bHandle2:
			break;
		case R.id.bHandle3:
			slidingDrawer.toggle();
			break;
		case R.id.bHandle4:
			slidingDrawer.close();
			break;
		}
	}

	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mp = MediaPlayer.create(this, R.raw.explosion);
		mp.start();
	}

}
