package com.example.mythirdapp;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class Sound extends Activity implements OnClickListener, OnLongClickListener {

	SoundPool soundPool;
	int explosion = 0;
	MediaPlayer mediaPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view = new View(this);
		view.setOnClickListener(this);
		view.setOnLongClickListener(this);
		setContentView(view);
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = soundPool.load(this, R.raw.explosion, 1);
		mediaPlayer = MediaPlayer.create(this, R.raw.backgroundsi);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(explosion != 0)
			soundPool.play(explosion, 1, 1, 0, 0, 1);
	}

	@Override
	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		mediaPlayer.start();
		return false;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mediaPlayer.release();
	}

}
