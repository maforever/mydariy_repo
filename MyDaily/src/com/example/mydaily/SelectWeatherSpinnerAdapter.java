package com.example.mydaily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectWeatherSpinnerAdapter extends BaseAdapter {
	private Context context;
	private String[] txts;
	private int[] imgIds;
	private LayoutInflater inflater = null;
	private int resId;
	public SelectWeatherSpinnerAdapter(Context context, String[] txts, int[] imgIds, int resId) {
		this.context = context;
		this.txts = txts;
		this.imgIds = imgIds;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.resId = resId;
	}
	
	@Override
	public int getCount() {
		return txts.length;
	}

	@Override
	public Object getItem(int arg0) {
		return txts[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView txt = null;
		ImageView img = null;
		if(convertView == null) {
			ViewHolder holder = null;
			convertView = inflater.inflate(resId, null);
			txt = (TextView) convertView.findViewById(R.id.txt);
			img = (ImageView) convertView.findViewById(R.id.img);
			holder = new ViewHolder();
			holder.txt = txt;
			holder.img = img;
			convertView.setTag(holder);
		}else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			txt = holder.txt;
			img = holder.img;
		}
		txt.setText(txts[position]);
		img.setImageResource(imgIds[position]);
		return convertView;
	}

	static class ViewHolder {
		public TextView txt;
		public ImageView img;
		public ViewHolder() {
			
		}
	}
	
}















