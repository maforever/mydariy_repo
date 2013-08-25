package com.example.mydaily.fragment;


import com.example.mydaily.AddCommonDailyActivity;
import com.example.mydaily.R;
import com.example.mydaily.WelcomeActivity;
import com.example.mydaily.utils.NetUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CommonDailyFragment extends Fragment {
	private ImageView btn_search = null;
	private TextView btn_cancelsearch = null;
	private LinearLayout title_center = null;
	private RelativeLayout title_right, title_right2, title_center2 = null;
	private View view = null;
	private Intent intent = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.commondailyfragment, container, false);
		findViews();
		btn_search.setOnClickListener(new OnClickListenerImpl());
		btn_cancelsearch.setOnClickListener(new OnClickListenerImpl());
		return view;
	}
	
	public void findViews() {
		title_center = (LinearLayout) view.findViewById(R.id.title_center);
		title_center2 = (RelativeLayout) view.findViewById(R.id.title_center2);
		title_right = (RelativeLayout) view.findViewById(R.id.title_right);
		title_right2 = (RelativeLayout) view.findViewById(R.id.title_right2);
		btn_search = (ImageView) view.findViewById(R.id.btn_search);
		btn_cancelsearch = (TextView) view.findViewById(R.id.btn_searchcancel);
	}
	
	private class OnClickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btn_search:
					title_center.setVisibility(ViewGroup.GONE);
					title_right.setVisibility(ViewGroup.GONE);
					title_center2.setVisibility(ViewGroup.VISIBLE);
					title_right2.setVisibility(ViewGroup.VISIBLE);
				break;
				case R.id.btn_searchcancel:
					title_center.setVisibility(ViewGroup.VISIBLE);
					title_right.setVisibility(ViewGroup.VISIBLE);
					title_center2.setVisibility(ViewGroup.GONE);
					title_right2.setVisibility(ViewGroup.GONE);
					break;
				
			}
		}
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.commondailyfragment_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.c1:
			intent = new Intent(this.getActivity().getApplicationContext(), AddCommonDailyActivity.class);
			startActivity(intent);
			this.getActivity().overridePendingTransition(R.anim.activity_up, R.anim.steady);
			break;
		case R.id.c2:
			
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}























