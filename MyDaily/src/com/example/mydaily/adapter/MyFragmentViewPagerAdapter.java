package com.example.mydaily.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

public class MyFragmentViewPagerAdapter extends FragmentPagerAdapter {
	private FragmentManager fm = null;
	private ArrayList<Fragment> fragments = null;
	public MyFragmentViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
		super(fm);
		this.fm = fm;
		this.fragments = fragments;
	}
	
	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
	
	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

}
