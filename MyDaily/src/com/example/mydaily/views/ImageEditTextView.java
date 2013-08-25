package com.example.mydaily.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Html.ImageGetter;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

public class ImageEditTextView extends EditText {

	public ImageEditTextView(Context context) {
		super(context);
	}
	
	public ImageEditTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ImageEditTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	public void setImageIntoEditText(int imgId) {
//		Editable editable = this.getEditableText();
//		String replace = System.currentTimeMillis() + "";
//		SpannableStringBuilder ss = new SpannableStringBuilder("image");
//		Drawable drawable = this.getResources().getDrawable(imgId);
//		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//		ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
//		ss.setSpan(imageSpan, 0, "image".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//		int imgLocation = this.getSelectionStart();
//		ss.getSpans(0, ss.length(), ImageSpan.class);
//		if(imgLocation < 0) {
//			editable.append(ss);
//		}else {
//			editable.insert(imgLocation, ss);
//		}
		
		Drawable d = this.getResources().getDrawable(imgId);
		d.setBounds(0, 0, 80, 80);
		ImageSpan imageSpan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
		SpannableString ss = new SpannableString("image");
		ss.setSpan(imageSpan, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		this.append(ss);
	}
	
	public void setImageIntoEditText(Bitmap bitmap) {
		Drawable d = new BitmapDrawable(bitmap);
		d.setBounds(0, 0, 80, 80);
		SpannableString ss = new SpannableString("bitmap");
		ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
		ss.setSpan(span, 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		this.append(ss);
	}

}












