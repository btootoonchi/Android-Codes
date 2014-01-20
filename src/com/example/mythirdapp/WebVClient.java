package com.example.mythirdapp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebVClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
		//return super.shouldOverrideUrlLoading(view, url);
		view.loadUrl(url);
		return true;
	}

}
