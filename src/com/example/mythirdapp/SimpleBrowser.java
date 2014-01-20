package com.example.mythirdapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	EditText url;
	WebView webBrowser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		webBrowser = (WebView)findViewById(R.id.wvBrowser);
		
		webBrowser.getSettings().setJavaScriptEnabled(true);
		webBrowser.getSettings().setLoadWithOverviewMode(true);
		webBrowser.getSettings().setUseWideViewPort(true);
		
		webBrowser.setWebViewClient(new WebVClient());
		try{
			webBrowser.loadUrl("http://www.google.com");
		} catch(Exception e){
			e.printStackTrace();
		}
		url = (EditText)findViewById(R.id.etURL);
		
		Button go = (Button) findViewById(R.id.bGo);
		Button back = (Button) findViewById(R.id.bBack);
		Button refresh = (Button) findViewById(R.id.bRefresh);
		Button forward = (Button) findViewById(R.id.bForward);
		Button clearHistory = (Button) findViewById(R.id.bHistory);
		
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.bGo:
			String theWebSite = url.getText().toString();
			webBrowser.loadUrl(theWebSite);
			//hiding the Keyboard after using an EditText
			InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
		case R.id.bBack:
			if(webBrowser.canGoBack())
				webBrowser.goBack();
			break;
		case R.id.bForward:
			if(webBrowser.canGoForward())
				webBrowser.goForward();
			break;
		case R.id.bRefresh:
			webBrowser.reload();
			break;
		case R.id.bHistory:
			webBrowser.clearHistory();
			break;
		}
	}

}
