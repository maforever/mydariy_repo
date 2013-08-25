package com.example.mydaily.sqliteservice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mydaily.model.AppUser;
import com.example.mydaily.utils.DBOpenHelper;

public class UserService {
	private DBOpenHelper dbOpenHelper = null;
	private SQLiteDatabase db = null;
	public UserService(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
		db = dbOpenHelper.getReadableDatabase();
	}
	
	public int getUserCounts() {
		Cursor cursor = db.rawQuery("select count(*) from app_users", null);
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		Log.i("a", "app_users count = " + count	);
		return count;
	}
	
	public void addUser(AppUser user) {
		db.execSQL("insert into app_users(username, password) values(?, ?) ", new String[]{user.getUsername(),user.getPassword()});
	}
	
	public boolean existUser(String username, String password) {
		Cursor cursor = db.rawQuery("select count(*) from app_users where username = ? and password = ?", new String[]{username, password});
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		return count > 0 ? true : false;
	}
	
	public void closeDB() {
		db.close();
	}
}
