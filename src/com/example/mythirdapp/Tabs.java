package com.example.mythirdapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {

	TabHost tabHost;
	TextView tvShowResults;
	long lngStart, lngStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		tabHost = (TabHost)findViewById(R.id.tabhost);
		Button newTab = (Button) findViewById(R.id.bAddTab);
		Button bStart = (Button) findViewById(R.id.bStartWatch);
		Button bStop = (Button) findViewById(R.id.bStopWatch);
		tvShowResults = (TextView) findViewById(R.id.tvShowResults);
		
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);

		newTab.setOnClickListener(this);
		
		tabHost.setup();
		TabSpec specs = tabHost.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		tabHost.addTab(specs);
		specs = tabHost.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		tabHost.addTab(specs);
		specs = tabHost.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		tabHost.addTab(specs);
		lngStart = 0;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.bAddTab:

			TabSpec ourSpec = tabHost.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() {

				public View createTabContent(String tag) {
					// TODO Auto-generated method stub

					TextView text = new TextView(Tabs.this);
					text.setText("You've created a new Tab!");
					return (text);
				}
			});
			ourSpec.setIndicator("New");
			tabHost.addTab(ourSpec);

			break;
		case R.id.bStartWatch:
			lngStart = System.currentTimeMillis();
			break;
		case R.id.bStopWatch:
			lngStop = System.currentTimeMillis();

			if (lngStart != 0) {
				long result = lngStop - lngStart;
				int millis = (int) result;
				int seconds = (int) result / 1000;
				int minutes = seconds / 60;
				millis = millis % 100;
				seconds = seconds % 60;

				tvShowResults.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
			}
			break;
		}
	}

}
