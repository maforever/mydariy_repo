package com.example.mydaily.adapter;

import com.example.mydaily.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectLocationAdapter extends BaseAdapter {
	private int resId;
	private String[] addresses;
	private int index;
	private LayoutInflater inflater;
	
	public SelectLocationAdapter(Context context, int resId, String[] addresses, int position) {
		this.resId = resId;
		this.addresses = addresses;
		this.index = position;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return addresses.length;
	}

	@Override
	public Object getItem(int arg0) {
		return addresses[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		TextView textView = null;
		ImageView imageView = null;
		if(convertView == null) {
			convertView = inflater.inflate(resId, null);
			textView = (TextView) convertView.findViewById(R.id.txt);
			imageView = (ImageView) convertView.findViewById(R.id.img);
			ViewHolder holder = new ViewHolder(textView, imageView);
			convertView.setTag(holder);
		}else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			textView = holder.textView;
			imageView = holder.imageView;
		}
		textView.setText(addresses[position]);
			if(position == index) {
				imageView.setBackgroundResource(R.drawable.v5_0_1_poi_list_icon_selected);
			}else {
				imageView.setBackgroundResource(R.drawable.v5_0_1_poi_list_icon);
		}
		return convertView;
	}
	
	static class ViewHolder {
		public TextView textView;
		public ImageView imageView;
		public ViewHolder(TextView textView, ImageView imageView) {
			this.textView = textView;
			this.imageView = imageView;
		}
	}
	
}

















