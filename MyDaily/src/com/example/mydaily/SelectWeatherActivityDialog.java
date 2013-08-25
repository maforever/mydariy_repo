package com.example.mydaily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SelectWeatherActivityDialog extends Activity {
	private ListView listView = null;
	private SelectWeatherSpinnerAdapter adapter = null;
	private int[] imgIds = new int[]{
			R.drawable.weather_sunny_notify,R.drawable.weather_chancerain_notify, R.drawable.weather_chancesnow_notify,
			R.drawable.weather_chancestorm_notify,R.drawable.weather_dust_notify, R.drawable.weather_fog_notify, 
			R.drawable.weather_mostlycloudy_notify,
	};
	private String[] txts = new String[] {
			"ÇçÌì", "ÓêÌì", "Ñ©Ìì", "±©Ñ©", "»Ò³¾", "Îíö²", "´ó·ç"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.select_weather);
		
		findViews();
		listViewAdapter();
	}
	
	private void findViews() {
		listView = (ListView) this.findViewById(R.id.listView);
	}
	
	private void listViewAdapter() {
		adapter = new SelectWeatherSpinnerAdapter(this, txts, imgIds, R.layout.select_weather_listview_item);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListenerImpl());
	}
	
	private class OnItemClickListenerImpl implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(SelectWeatherActivityDialog.this, AddCommonDailyActivity.class);
			intent.putExtra("weather", txts[arg2]);
			SelectWeatherActivityDialog.this.setResult(96, intent);
			SelectWeatherActivityDialog.this.finish();
		}
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.close:
				Intent intent = new Intent(SelectWeatherActivityDialog.this, AddCommonDailyActivity.class);
				this.setResult(95, intent);
				SelectWeatherActivityDialog.this.finish();
			break;
		}
	}
}








