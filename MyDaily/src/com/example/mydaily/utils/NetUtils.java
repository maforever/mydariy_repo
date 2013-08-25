package com.example.mydaily.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class NetUtils {
	private Context context;
	private ConnectivityManager connectivityManager = null;
	public NetUtils(Context context) {
		this.context = context;
	}
	
	public boolean isNetConnected() {
		connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if(info.getState() == State.CONNECTED) {
			return true;
		}
		return false;
	}
	
}
