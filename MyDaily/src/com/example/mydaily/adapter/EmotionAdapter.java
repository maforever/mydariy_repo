package com.example.mydaily.adapter;

import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class EmotionAdapter extends BaseAdapter {
	private int[] imgIds;
	private Context context;
	private GifView gifView = null;
	public EmotionAdapter(Context context, int[] imgIds) {
		this.context = context;
		this.imgIds = imgIds;
	}
	
	@Override
	public int getCount() {
		return imgIds.length;
	}

	@Override
	public Object getItem(int arg0) {
		return imgIds[arg0];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
//			convertView = new ImageView(context);
			gifView = new GifView(context);
			gifView.setGifImageType(GifImageType.COVER);
			gifView.setShowDimension(300, 300);
		}
//		convertView.setBackgroundResource(imgIds[position]);
		gifView.setBackgroundResource(imgIds[position]);
		return gifView;
	}
	
}
