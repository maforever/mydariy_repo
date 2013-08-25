package com.example.mydaily.fragment;

import com.example.mydaily.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BillDailyFragment extends Fragment {
	private TextView titleTv = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.billdailyfragment, container, false);
		titleTv = (TextView) view.findViewById(R.id.title);
		titleTv.setText("’Àƒø»’÷æ");
		return view;
	}
	
}
