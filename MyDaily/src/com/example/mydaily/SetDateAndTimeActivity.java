package com.example.mydaily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class SetDateAndTimeActivity extends Activity {
	private Intent intent = null;
	private DatePicker datePicker = null;
	private TimePicker timePicker = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.setdateactivity);
		
		findViews();
		
	}
	
	private void findViews() {
		datePicker = (DatePicker) this.findViewById(R.id.datePicker);
		timePicker = (TimePicker) this.findViewById(R.id.timePicker);
		timePicker.setIs24HourView(false);
	}

	public void btnClick(View view) {
		intent = new Intent(SetDateAndTimeActivity.this, AddCommonDailyActivity.class);
		switch (view.getId()) {
		case R.id.ok:
				intent.putExtra("year", datePicker.getYear() + "");
				intent.putExtra("month", datePicker.getMonth() + 1 + "");
				intent.putExtra("day", datePicker.getDayOfMonth() + "");
				intent.putExtra("hour", timePicker.getCurrentHour() + "");
				intent.putExtra("minute", timePicker.getCurrentMinute() + "");
 				setResult(99, intent);
			break;
		case R.id.cancel:
		case R.id.close:
				setResult(98, intent);
			break;
		}
		SetDateAndTimeActivity.this.finish();
	}
	
}










