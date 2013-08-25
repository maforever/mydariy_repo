package com.example.mydaily;

import java.util.ArrayList;

import com.example.mydaily.adapter.MyFragmentViewPagerAdapter;
import com.example.mydaily.fragment.BabyGrowDailyFragment;
import com.example.mydaily.fragment.BillDailyFragment;
import com.example.mydaily.fragment.CommonDailyFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IndexActivity extends FragmentActivity {
	private FragmentManager fm = null;
//	private FragmentTransaction ft = null;
	private Fragment fragment = null;
	private TextView commonDailyTv, babyGrowDailyTv, billDailyTv, shareTv, configTv = null;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private ViewPager pager = null;
	private ImageView iv_bottom_image = null;
	private Resources resources = null;
	private int bottomLineWidth, offset, position_one, position_two, position_three, position_four, position_five, currIndex = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.index_activity);
		resources = this.getResources();
		fm = this.getSupportFragmentManager();
		findViews();
		initWidth();
		initTextView();
		initViewPager();
	}

	private void findViews() {
		commonDailyTv = (TextView) this.findViewById(R.id.btn_common);
		babyGrowDailyTv = (TextView) this.findViewById(R.id.btn_grow);
		billDailyTv = (TextView) this.findViewById(R.id.btn_bill);
		shareTv = (TextView) this.findViewById(R.id.btn_share);
		configTv = (TextView) this.findViewById(R.id.btn_config);
		pager = (ViewPager) this.findViewById(R.id.viewPager);
		iv_bottom_image = (ImageView) this.findViewById(R.id.iv_bottom_line);
	}
	
	
	private void initTextView() {
		commonDailyTv.setOnClickListener(new OnClickListenerImpl(0));
		babyGrowDailyTv.setOnClickListener(new OnClickListenerImpl(1));
		billDailyTv.setOnClickListener(new OnClickListenerImpl(2));
		shareTv.setOnClickListener(new OnClickListenerImpl(3));
		configTv.setOnClickListener(new OnClickListenerImpl(4));
	}
	
	private class OnClickListenerImpl implements  OnClickListener {
		private int index = 0;
		public OnClickListenerImpl(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {	
			pager.setCurrentItem(index);
		}
	}
	
	private void initViewPager() {
		Fragment commonDailyFragment = new CommonDailyFragment();
		Fragment babyGrowDailyFragment = new BabyGrowDailyFragment();
		Fragment billDailyFragment = new BillDailyFragment();
		fragments.add(commonDailyFragment);
		fragments.add(babyGrowDailyFragment);
		fragments.add(billDailyFragment);
		
		pager.setAdapter(new MyFragmentViewPagerAdapter(fm, fragments));
		pager.setOnPageChangeListener(new OnPageChangerListenerImpl());
		pager.setCurrentItem(0);
	}
	
    private void initWidth() {
        bottomLineWidth = iv_bottom_image.getLayoutParams().width;
        Log.i("a", "cursor imageview width=" + bottomLineWidth);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        
//      offset = (int) ((screenW / 5.0 - bottomLineWidth) / 2);
        offset = 16;
        Log.i("a", "offset=" + offset);

        position_one = (int) (screenW / 5.0);
        position_two = position_one * 2;
        position_three = position_one * 3;
        position_four = position_one * 4;
        position_five = position_one * 5;
        Log.i("a", "screeW = " + screenW + " screenW / 5.0 = " + screenW / 5.0 + " bottomLineWidth / 2 = " + bottomLineWidth / 2);
        Log.i("a", "position_one = " + position_one + " position_two = " + position_two + " position_three = " + position_three + " position_four = " + position_four + " position_five = " + position_five);
    }
	
	private class OnPageChangerListenerImpl implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one , 0, 0, 0);
                    babyGrowDailyTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, 0, 0, 0);
                    billDailyTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, 0, 0, 0);
                    shareTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if(currIndex == 4) {
                	animation = new TranslateAnimation(position_four, 0, 0, 0);
                	configTv.setTextColor(resources.getColor(R.color.lightwhite));
                }
                commonDailyTv.setTextColor(resources.getColor(R.color.blue));
				break;
			case 1:
				if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_one, 0, 0);
                    commonDailyTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_one, 0, 0);
                    billDailyTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_one, 0, 0);
                    shareTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if(currIndex == 4) {
                	animation = new TranslateAnimation(position_four, position_one, 0, 0);
                	configTv.setTextColor(resources.getColor(R.color.lightwhite));
                }
                babyGrowDailyTv.setTextColor(resources.getColor(R.color.blue));
				break;
			case 2:
				if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, position_two, 0, 0);
                    commonDailyTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_two, 0, 0);
                    babyGrowDailyTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_two, 0, 0);
                    shareTv.setTextColor(resources.getColor(R.color.lightwhite));
                } else if(currIndex == 4) {
                	animation = new TranslateAnimation(position_four, position_two, 0, 0);
                	configTv.setTextColor(resources.getColor(R.color.lightwhite));
                }
                billDailyTv.setTextColor(resources.getColor(R.color.blue));
				break;
			case 3:
				break;
			case 4:
				break;
			}
			
			currIndex = arg0;
			Log.i("a", "currIndex = " + currIndex);
			animation.setFillAfter(true);
			animation.setDuration(300);
			iv_bottom_image.startAnimation(animation);
			
		}
	}
	
	
}

















