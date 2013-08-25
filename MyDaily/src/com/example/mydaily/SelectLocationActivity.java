package com.example.mydaily;

import com.example.mydaily.adapter.SelectLocationAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SelectLocationActivity extends Activity {
	private Intent intent = null;
	private SelectLocationAdapter adapter = null;
	private ListView listView = null;
	private String[] addresses = null;
	private String currentAddress = null;
	private int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.select_loaction_activity);
		
		addresses = this.getIntent().getStringArrayExtra("addresses");
		currentAddress = this.getIntent().getStringExtra("currentAddress");
		index = this.getIntent().getIntExtra("index", 0);
		listView = (ListView) this.findViewById(R.id.listView);
		listViewAdapter();
		listView.setOnItemClickListener(new OnItemClickListenerImpl());
	}
	
	private class OnItemClickListenerImpl implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			intent = new Intent(SelectLocationActivity.this, AddCommonDailyActivity.class);
			intent.putExtra("currentAddress", addresses[arg2]);
			intent.putExtra("index", arg2);
			setResult(80, intent);
			SelectLocationActivity.this.finish();
			overridePendingTransition(R.anim.steady, R.anim.activity_down);
		}
	}
	
	private void listViewAdapter() {
		adapter = new SelectLocationAdapter(this, R.layout.select_location_listview_item, addresses, index);
		listView.setAdapter(adapter);
	}

	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			intent = new Intent(SelectLocationActivity.this, AddCommonDailyActivity.class);
			intent.putExtra("currentAddress", currentAddress);
			setResult(90, intent);
			SelectLocationActivity.this.finish();
			overridePendingTransition(0, R.anim.activity_down);
			break;
		}
	}
}
