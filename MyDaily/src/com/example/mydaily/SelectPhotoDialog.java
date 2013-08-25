package com.example.mydaily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectPhotoDialog extends Activity {
	private Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.selectphotodialog);
	}
	
	
	public void btnClick(View view) {
		intent = new Intent();
		switch (view.getId()) {
		case R.id.album:
			intent.putExtra("flag", "album");
			setResult(77, intent);
			SelectPhotoDialog.this.finish();
			break;
		case R.id.camera:
			intent.putExtra("flag", "camera");
			setResult(76, intent);
			SelectPhotoDialog.this.finish();
			break;
		case R.id.close:
			SelectPhotoDialog.this.finish();
			break;
		}
	}
}
