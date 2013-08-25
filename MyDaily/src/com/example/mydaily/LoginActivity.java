package com.example.mydaily;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.mydaily.model.AppUser;
import com.example.mydaily.sqliteservice.UserService;
import com.example.mydaily.utils.AppSharedPreferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private int timeFlag = 0;
	private RelativeLayout main_layout = null;
	private Dialog registDialog = null;
	private LayoutInflater inflater = null;
	private Intent intent = null;
	private AppSharedPreferences asp = null;
	private UserService us = null;
	private AppUser user = null;
	private TextView notice;
	private String username, password = null;
	private EditText usernameEt, passwordEt = null;
	private CheckBox autoLogin, rememberPassword = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.login_activity);
		timeFlag = getTimeInt();
		Log.i("a", "时间段 = " + timeFlag);
		
		findViews();
		mainLayoutSetBackGround();
		asp = new AppSharedPreferences(this);
		us = new UserService(this);
		if(us.getUserCounts() == 0) {
			showRegistActivity();
		}
//		initRegistDialog();
		
	}
	

	@Override
	protected void onResume() {
		super.onResume();
		if(asp.getIsAutoLogin()) {
			autoLogin.setChecked(true);
			user = asp.getUserInfo();
			usernameEt.setText(user.getUsername());
			passwordEt.setText(user.getPassword());
			intent = new Intent(LoginActivity.this, IndexActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();
		}else {
			autoLogin.setChecked(false);
			asp.setIsAutoLogin(false);
		}
		if(asp.getIsRememberPassword()) {
			rememberPassword.setChecked(true);
			user = asp.getUserInfo();
			usernameEt.setText(user.getUsername());
			passwordEt.setText(user.getPassword());

		}else {
			rememberPassword.setChecked(false);
			asp.setIsRememberPassword(false);
			asp.clearRememberPassword();
		}
		
	}
	
	private void showRegistActivity() {
		intent = new Intent(LoginActivity.this, RegistActivity.class);
		startActivityForResult(intent, 99);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
			case R.id.registBtn:
				//registDialog.show();
				showRegistActivity();
			break;
			
			case R.id.login:
				getValues();
				
				if(validateValues()) {
					if(us.existUser(username, password)) {
						if(autoLogin.isChecked()) {
							asp.setIsAutoLogin(true);
							asp.RememberPassword(username, password);
						}else {
							asp.clearRememberPassword();
							asp.setIsAutoLogin(false);
						}
						if(rememberPassword.isChecked()) {
							asp.setIsRememberPassword(true);
							asp.RememberPassword(username, password);
						}else {
							asp.setIsRememberPassword(false);
							asp.clearRememberPassword();
						}
						intent = new Intent(LoginActivity.this, IndexActivity.class);
						startActivity(intent);
						LoginActivity.this.finish();
					}else {
						notice.setVisibility(ViewGroup.VISIBLE);
						notice.setText("用户名或密码错误，如果您没有创建用户，请点击创建新用户");
					}
				}else {
					notice.setVisibility(ViewGroup.VISIBLE);
					notice.setText("用户名或密码不能为空!");
				}
				
				break;

		}
	}
	
	private void getValues() {
		username = usernameEt.getText().toString();
		password = passwordEt.getText().toString();
	}
	
	private boolean validateValues() {
		if(username == null || "".equals(username) || password == null || "".equals(password) ) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == 100) {
//			Toast.makeText(this, data.getStringExtra("username") + data.getStringExtra("password"), 0).show();
			user = new AppUser();
			user.setUsername(data.getStringExtra("username"));
			user.setPassword(data.getStringExtra("password"));
			us.addUser(user);
			Toast.makeText(this, "添加新用户成功!", 0).show();
		}
	}
	
	
	private void initRegistDialog() {
		View view = inflater.inflate(R.layout.regist, null);
		registDialog = new AlertDialog.Builder(this)
		.setTitle("注册新用户")
		.setIcon(R.drawable.ic_level_account_selected)
		.setView(view)
		.setPositiveButton("注册", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				registDialog.dismiss();	
			}
		})
		.create();
	}
	
	private void mainLayoutSetBackGround() {
		switch (timeFlag) {
		case 1:
			main_layout.setBackgroundResource(R.drawable.bg_1);
			break;
		case 2:
			main_layout.setBackgroundResource(R.drawable.bg_2);
			break;
		case 3:
			main_layout.setBackgroundResource(R.drawable.bg_3);
			break;
		case 4:
			main_layout.setBackgroundResource(R.drawable.bg_4);
			break;
		}
	}
	
	private void findViews() {
		inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		main_layout = (RelativeLayout) this.findViewById(R.id.main_layout);
		notice = (TextView) this.findViewById(R.id.notice);
		usernameEt = (EditText) this.findViewById(R.id.username);
		passwordEt = (EditText) this.findViewById(R.id.password);
		autoLogin = (CheckBox) this.findViewById(R.id.autoLogin);
		rememberPassword = (CheckBox) this.findViewById(R.id.rememberPassword);
	}
	
	
	//根据小时判断时间点，
	/**
	 * 1 早上，2中午，3下午，4深夜
	 * @return
	 */
	private int getTimeInt() {
		Calendar c = Calendar.getInstance();
		int hour = c.getTime().getHours();
		if(hour >= 6 && hour < 11) {
			return 1;
		}else if(hour >= 11 && hour < 15) {
			return 2;
		}else if(hour >= 15 && hour < 19) {
			return 3;
		}
		return 4;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		us.closeDB();
	}
}
