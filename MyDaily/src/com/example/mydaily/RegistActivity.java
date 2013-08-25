package com.example.mydaily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistActivity extends Activity {
	private EditText usernameEt, passwordEt, confirmPasswordEt = null;
	private String username, password, confirmPassword = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.regist);
		
		findViews();
		
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.ok:
			getValues();
			switch (validateValues()) {
			case 1:
				Toast.makeText(this, "用户名或密码不能为空", 0).show();
				break;
			case 2:
				Toast.makeText(this, "两次输入的密码不正确", 0).show();
				break;
			case 3:
				Intent intent = new Intent();
				intent.putExtra("username", username);
				intent.putExtra("password", password);
				this.setResult(100, intent);
				RegistActivity.this.finish();
				break;
			}
			
			break;
		case R.id.no:
			RegistActivity.this.finish();
			break;
		}
	}
	
	private int validateValues() {
		if(username == null || "".equals(username) || password == null || "".equals(password) || confirmPassword == null || "".equals(confirmPassword)) {
			return 1;
		}else if(!password.equals(confirmPassword)) {
			return 2;
		}
		return 3;
	}
	
	private void getValues() {
		username = this.usernameEt.getText().toString();
		password = this.passwordEt.getText().toString();
		confirmPassword = this.confirmPasswordEt.getText().toString();
	}
	
	private void findViews() {
		usernameEt = (EditText) this.findViewById(R.id.username);
		passwordEt = (EditText) this.findViewById(R.id.password);
		confirmPasswordEt = (EditText) this.findViewById(R.id.confirmPassword);
	}
}
