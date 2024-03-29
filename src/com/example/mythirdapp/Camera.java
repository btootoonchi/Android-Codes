package com.example.mythirdapp;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {

	ImageButton ibTakePic;
	Button bSetWallpaper;
	ImageView ivReturnedPic;	
	Intent intent;
	final static int cameraData = 0;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialization();
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);
	}

	private void initialization() {
		// TODO Auto-generated method stub
		ibTakePic = (ImageButton)findViewById(R.id.ibTakePic);
		bSetWallpaper = (Button)findViewById(R.id.bSetWall);
		ivReturnedPic = (ImageView)findViewById(R.id.ivReturnedPic);
		bSetWallpaper.setOnClickListener(this);
		ivReturnedPic.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.ibTakePic:
			intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, cameraData);
			break;
		case R.id.bSetWall:
			try {
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap)extras.get("data");
			ivReturnedPic.setImageBitmap(bmp);
		}
	}

}
