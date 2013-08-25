package com.example.mydaily.views;

import com.example.mydaily.R;

import android.content.Context;
import android.gesture.GestureOverlayView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class EmotionViewFlipper extends ViewFlipper {

	public EmotionViewFlipper(Context context) {
		super(context);
		
		this.setBackgroundColor(Color.WHITE);
		
		LinearLayout layout = new LinearLayout(context);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(layoutParams);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);
		GridView gridView1 = new GridView(context);
		gridView1.setLayoutParams(layoutParams);
		gridView1.setNumColumns(4);
		gridView1.setId(R.id.emotion_cat_gridview1);
		
		LinearLayout linearLayout1 = new LinearLayout(context);
		linearLayout1.setGravity(Gravity.CENTER);
		linearLayout1.setLayoutParams(layoutParams);
		linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
		ImageView img1 = new ImageView(context);
		ImageView img2 = new ImageView(context);
		ImageView img3 = new ImageView(context);
		LayoutParams imagelLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		imagelLayoutParams.setMargins(0, 20, 0, 20);
		img1.setLayoutParams(imagelLayoutParams);
		img2.setLayoutParams(imagelLayoutParams);
		img3.setLayoutParams(imagelLayoutParams);
		img1.setBackgroundResource(R.drawable.guide_nowpoint);
		img2.setBackgroundResource(R.drawable.guide_point);
		img3.setBackgroundResource(R.drawable.guide_point);
		linearLayout1.addView(img1);
		linearLayout1.addView(img2);
		linearLayout1.addView(img3);
		
		layout.addView(gridView1);
		layout.addView(linearLayout1);
		
		LinearLayout layout2 = new LinearLayout(context);
		layout2.setLayoutParams(layoutParams);
		layout2.setOrientation(LinearLayout.VERTICAL);
		layout2.setGravity(Gravity.CENTER);
		GridView gridView2 = new GridView(context);
		gridView2.setLayoutParams(layoutParams);
		gridView2.setNumColumns(4);
		gridView2.setId(R.id.emotion_cat_gridview2);
		
		linearLayout1 = new LinearLayout(context);
		linearLayout1.setGravity(Gravity.CENTER);
		linearLayout1.setLayoutParams(layoutParams);
		linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
		img1 = new ImageView(context);
		img2 = new ImageView(context);
		img3 = new ImageView(context);
		img1.setLayoutParams(imagelLayoutParams);
		img2.setLayoutParams(imagelLayoutParams);
		img3.setLayoutParams(imagelLayoutParams);
		img1.setBackgroundResource(R.drawable.guide_point);
		img2.setBackgroundResource(R.drawable.guide_nowpoint);
		img3.setBackgroundResource(R.drawable.guide_point);
		linearLayout1.addView(img1);
		linearLayout1.addView(img2);
		linearLayout1.addView(img3);
		
		layout2.addView(gridView2);
		layout2.addView(linearLayout1);
		
		LinearLayout layout3 = new LinearLayout(context);
		layout3.setLayoutParams(layoutParams);
		layout3.setOrientation(LinearLayout.VERTICAL);
		layout3.setGravity(Gravity.CENTER);
		GridView gridView3 = new GridView(context);
		gridView3.setLayoutParams(layoutParams);
		gridView3.setNumColumns(4);
		gridView3.setId(R.id.emotion_cat_gridview3);
		
		linearLayout1 = new LinearLayout(context);
		linearLayout1.setGravity(Gravity.CENTER);
		linearLayout1.setLayoutParams(layoutParams);
		linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
		img1 = new ImageView(context);
		img2 = new ImageView(context);
		img3 = new ImageView(context);
		img1.setLayoutParams(imagelLayoutParams);
		img2.setLayoutParams(imagelLayoutParams);
		img3.setLayoutParams(imagelLayoutParams);
		img1.setBackgroundResource(R.drawable.guide_point);
		img2.setBackgroundResource(R.drawable.guide_point);
		img3.setBackgroundResource(R.drawable.guide_nowpoint);
		linearLayout1.addView(img1);
		linearLayout1.addView(img2);
		linearLayout1.addView(img3);
		
		layout3.addView(gridView3);
		layout3.addView(linearLayout1);
		
		this.addView(layout);
		this.addView(layout2);
		this.addView(layout3);
		
	}
	
	public EmotionViewFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}


	
}
