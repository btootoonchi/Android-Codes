package com.example.mythirdapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity{

	MediaPlayer mySong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		mySong = MediaPlayer.create(Splash.this, R.raw.shabbegolestan);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean musicStatus = getPrefs.getBoolean("checkbox", true);
		
		if(musicStatus == true)
			mySong.start();
		
		Thread timer = new Thread(){
			public void run() {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					//Intent openStartingPoint = new Intent("com.example.mythirdapp.STARTINGPOINT");
					//Intent openStartingPoint = new Intent("com.example.mythirdapp.MAINACTIVITY");
					Intent openStartingPoint = new Intent("com.example.mythirdapp.MENU");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mySong.release();
		finish();
	}
	
	

}
