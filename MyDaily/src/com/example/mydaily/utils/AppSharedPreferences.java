package com.example.mydaily.utils;

import com.example.mydaily.model.AppUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppSharedPreferences {
	private Context context = null;
	public AppSharedPreferences(Context context) {
		this.context = context;
	}
	
	
	public boolean getIsAutoLogin() {
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		return sp.getBoolean("isAutoLogin", false);
	}
	
	public boolean getIsRememberPassword() {
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		return sp.getBoolean("isRememberPassword", false);
	}
	
	public void setIsAutoLogin(boolean flag) {
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isAutoLogin", flag);
		editor.commit();
	}
	
	public void setIsRememberPassword(boolean flag) {
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isRememberPassword", flag);
		editor.commit();
	}
	
	public AppUser getUserInfo() {
		AppUser user = new AppUser();
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		user.setUsername(sp.getString("username", null));
		user.setPassword(sp.getString("password", null));
		return user;
	}
	
	public void RememberPassword(String username, String password) {
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}
	
	public void clearRememberPassword() {
		SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("username");
		editor.remove("password");
		editor.commit();
	}
	
	public void setIsFirstOpenApp(boolean flag) {
		SharedPreferences sp = context.getSharedPreferences("app_info", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isFirstOpenApp", flag);
		editor.commit();
	}
	
	public boolean getIsFirstOpenApp() {
		SharedPreferences sp = context.getSharedPreferences("app_info", Context.MODE_PRIVATE);
		return sp.getBoolean("isFirstOpenApp", true);
	}
	
}
