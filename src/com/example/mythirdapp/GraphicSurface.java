package com.example.mythirdapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GraphicSurface extends Activity implements OnTouchListener {

	GraphicSurfaceView ourSurfaceView;
	float x, y, sX, sY, fX, fY, dX, dY, aniX, aniY, scaledX, scaledY;
	Bitmap ballBitmap, plusBitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new GraphicSurfaceView(this);
		ourSurfaceView.setOnTouchListener(this);
		//first
		x = 0;
		y = 0;
		//second
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		//third
		dX = dY = aniX = aniY = scaledX = scaledY = 0;
		ballBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);//first
		plusBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plus);//second
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.Pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.Resume();
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		// TODO Auto-generated method stub
		
		//fifth
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//first
		x = motionEvent.getX();
		y = motionEvent.getY();
		
		//second
		switch(motionEvent.getAction()){
		case MotionEvent.ACTION_DOWN:
			sX = motionEvent.getX();
			sY = motionEvent.getY();
			dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;//forth
			break;
		case MotionEvent.ACTION_UP:
			fX = motionEvent.getX();
			fY = motionEvent.getY();
			//third
			dX = fX-sX;
			dY = fY-sY;
			scaledX = dX/30;
			scaledY = dY/30;
			//forth
			x = y =0;
			break;
		}
		return true;
	}

	public class GraphicSurfaceView extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread surfaceViewThread = null;
		boolean isRunning = false;
		
		public GraphicSurfaceView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			ourHolder = getHolder();
			surfaceViewThread = new Thread(this);
			surfaceViewThread.start();
		}

		public void Pause(){
			isRunning = false;
			while(true){
				try {
					surfaceViewThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			surfaceViewThread = null;
		}
		
		public void Resume(){
			isRunning = true;
			surfaceViewThread = new Thread(this);
			surfaceViewThread.start();
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				if(!ourHolder.getSurface().isValid()) {
					continue;
				}
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				
				if (x != 0 && y != 0){
					canvas.drawBitmap(ballBitmap, x-(ballBitmap.getWidth()/2), y-(ballBitmap.getHeight()/2), null);
				}	
				if (fX != 0 && fY != 0){//second
					//canvas.drawBitmap(ballBitmap, x-(ballBitmap.getWidth()/2)-aniX,  y-(ballBitmap.getHeight()/2)-aniY, null);//third
					canvas.drawBitmap(ballBitmap, fX-(ballBitmap.getWidth()/2)-aniX,  fY-(ballBitmap.getHeight()/2)-aniY, null);//forth
					canvas.drawBitmap(plusBitmap, fX-(plusBitmap.getWidth()/2), fY-(plusBitmap.getHeight()/2), null);
				}
				if (sX != 0 && sY != 0){//second
					canvas.drawBitmap(plusBitmap, sX-(plusBitmap.getWidth()/2), sY-(plusBitmap.getHeight()/2), null);
				}
				
				//third
				aniX = aniX + scaledX;
				aniY = aniY + scaledY;
				
				//first
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}
	
}


