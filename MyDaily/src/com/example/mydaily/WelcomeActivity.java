package com.example.mydaily;

import com.example.mydaily.R.anim;
import com.example.mydaily.utils.DBOpenHelper;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SlidingDrawer;

public class WelcomeActivity extends Activity {
	private DBOpenHelper openHelper = null;
	private SQLiteDatabase db = null;
	private ImageView img_back = null;
	private Animation anim_fadeIn, anim_fadeOut, anim_fadeScale = null;
	private Drawable d1, d2, d3 = null;
	private Intent intent = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome);
        
        
        openHelper = new DBOpenHelper(this);
        db = openHelper.getReadableDatabase();
        
        findViews();
        init();
        setListeners();
    }
    
    public void findViews() {
    	this.img_back = (ImageView) this.findViewById(R.id.img);
    }
    
    public void init() {
    	anim_fadeIn = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.v5_0_1_guide_welcome_fade_in);
    	anim_fadeIn.setDuration(1000);
    	anim_fadeOut = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.v5_0_1_guide_welcome_fade_out);
    	anim_fadeOut.setDuration(1000);
    	anim_fadeScale = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.v5_0_1_guide_welcome_fade_in_scale);
    	anim_fadeScale.setDuration(6000);
    	d1 = this.getResources().getDrawable(R.drawable.v5_0_1_guide_pic1);
    	d2 = this.getResources().getDrawable(R.drawable.v5_0_1_guide_pic2);
    	d3 = this.getResources().getDrawable(R.drawable.v5_0_1_guide_pic3);
    	img_back.setImageDrawable(d1);
    	img_back.startAnimation(anim_fadeIn);
    }
    
    public void setListeners() {
    	anim_fadeIn.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				img_back.startAnimation(anim_fadeScale);
			}
		});
    	
    	anim_fadeScale.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				img_back.startAnimation(anim_fadeOut);
			}
		});
    	
    	anim_fadeOut.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if(img_back.getDrawable() == d1) {
					img_back.setImageDrawable(d2);
				}else if(img_back.getDrawable() == d2) {
					img_back.setImageDrawable(d3);
				}else if(img_back.getDrawable() == d3) {
					img_back.setImageDrawable(d1);
				}
				img_back.startAnimation(anim_fadeIn);
			}
		});
    	
    	img_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(WelcomeActivity.this, LoginActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
				overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
			}
		});
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	db.close();
    }
    
    
}
