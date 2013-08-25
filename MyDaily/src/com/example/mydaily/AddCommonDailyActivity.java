package com.example.mydaily;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.mydaily.adapter.EmotionAdapter;
import com.example.mydaily.datepickerwheel.DatePickWheelDialog;
import com.example.mydaily.loaction.Location;
import com.example.mydaily.utils.AppSharedPreferences;
import com.example.mydaily.utils.NetUtils;
import com.example.mydaily.views.EmotionViewFlipper;
import com.example.mydaily.views.ImageEditTextView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

public class AddCommonDailyActivity extends Activity {
	private Intent intent = null;
	private Dialog dateDailog = null;
	private TextView tv_year, tv_month, tv_day, tv_hour, tv_minute,
			tv_address, tv_account = null;
	private EditText et_weather = null;//, et_content = null;
	private ImageEditTextView et_content = null;
	private DatePicker datePicker = null;
	private LocationClient mLocClient;
	private boolean isLocation = false;
	private ImageView btn_location, btn_spinner = null;
	private String[] addresses = null;
	private AppSharedPreferences asp = null;
	private NetUtils netUtils = null;
	private int index = 0;
	private Bitmap bm = null;
	private DatePickWheelDialog dpwd = null;
	private int[] emotion_cats = new int[]{
			R.drawable.cat1,R.drawable.cat2,R.drawable.cat3,R.drawable.cat4,
			R.drawable.cat5,R.drawable.cat6,R.drawable.cat7,R.drawable.cat8,
			R.drawable.cat9,R.drawable.cat10
	};
	
	private int[] emotion_peoples = new int[]{
		R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,
		R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8,
		R.drawable.p9,R.drawable.p10,
	};
	private int[] emotion_jiong = new int[] {
		R.drawable.jiong1,R.drawable.jiong2,R.drawable.jiong3,R.drawable.jiong4,
		R.drawable.jiong5,R.drawable.jiong6,R.drawable.jiong7,R.drawable.jiong8,
		R.drawable.jiong9,R.drawable.jiong10,R.drawable.jiong11,R.drawable.jiong12,
	};
	private PopupWindow popupWindow = null;
	private ImageView popWindowParent = null;
	private int[] locations = new int[2];
	private int screenHeight;
	private EmotionViewFlipper flipper = null;
	private EmotionAdapter emotionAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.addcommondaily);

		DisplayMetrics dm = this.getResources().getDisplayMetrics();
		screenHeight = dm.heightPixels;
		findViews();

		
		
		checkEtContent();
		et_content.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				checkEtContent();
			}
		});
		
		initDateViews();

		mLocClient = ((Location) getApplication()).mLocationClient;
		((Location) getApplication()).tv_address = tv_address;
		((Location) getApplication()).et_content = et_content;
		((Location) getApplication()).btn_location = btn_location;
		((Location) getApplication()).btn_spinner = btn_spinner;
	}

	//计算内容框中输入的字数
	private void checkEtContent() {
		if(et_content.getText().toString() == null || "".equals(et_content.getText().toString())) {
			tv_account.setText("还没有任何记录");
		}else {
			String accountStr = this.getResources().getString(R.string.account);
			accountStr = String.format(accountStr, et_content.getText().toString().length());
			tv_account.setText(accountStr);
		}
	}
	
	private void initDatePickWheelDialog() {
		dpwd = new DatePickWheelDialog.Builder(this)
		.setPositiveButton("确定", new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar c = dpwd
						.getSetCalendar();
				Toast.makeText(AddCommonDailyActivity.this, getFormatTime(c), 0).show();
				dpwd.dismiss();
			}
		})
		.create();
	}
	
	public static String getFormatTime(Calendar c) {
		String parten = "00";
		DecimalFormat decimal = new DecimalFormat(parten);
		// 设置日期的显示
		Calendar calendar = c;
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		return year + "-" + decimal.format(month + 1) + "-"
				+ decimal.format(day) + " " + decimal.format(hour) + ":"
				+ decimal.format(minute);

	}

	
	//向内容框中添加图片
	private void setImageInToEditView(String imgName, int imgId) {
		SpannableString ss = new SpannableString(et_content.getText() + "[smile]");
		Editable editable = et_content.getEditableText();
		int cursorLocation = et_content.getSelectionStart();
		Toast.makeText(AddCommonDailyActivity.this, "光标当前的位置  " + cursorLocation, 0).show();
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), imgId);
		Drawable drawable = new BitmapDrawable(bitmap);
		ss.setSpan(new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE), cursorLocation, et_content.getText().length() + "[smile]".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth() + 20, drawable.getIntrinsicHeight() + 20);
		editable.insert(cursorLocation, ss);
	}
	
	
	// 设置相关参数
	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setServiceName("com.baidu.location.service_v2.9");
		option.setPoiExtraInfo(true);
		option.setAddrType("all");
		option.setScanSpan(0); // 设置定位模式，小于1秒则一次定位;大于等于1秒则定时定位
		// option.setScanSpan(3000);
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
		option.setPoiNumber(10);
		option.disableCache(true);
		mLocClient.setLocOption(option);
	}

	private void findViews() {
		tv_year = (TextView) this.findViewById(R.id.year);
		tv_month = (TextView) this.findViewById(R.id.month);
		tv_day = (TextView) this.findViewById(R.id.days);
		tv_hour = (TextView) this.findViewById(R.id.hour);
		tv_minute = (TextView) this.findViewById(R.id.minute);
		tv_account = (TextView) this.findViewById(R.id.count);
		et_weather = (EditText) this.findViewById(R.id.weather);
		et_content =  (ImageEditTextView) this.findViewById(R.id.content);
		btn_location = (ImageView) this.findViewById(R.id.btn_location);
		btn_spinner = (ImageView) this.findViewById(R.id.btn_spinner);
		tv_address = (TextView) this.findViewById(R.id.address);
		popWindowParent = (ImageView) this.findViewById(R.id.divider_line);
	}

	private void initDateViews() {
		Calendar c = Calendar.getInstance();
		tv_year.setText(c.get(c.YEAR) + "");
		tv_month.setText(c.get(c.MONTH) + 1 + "");
		tv_day.setText(c.get(c.DAY_OF_MONTH) + "");
		tv_hour.setText(c.get(c.HOUR_OF_DAY) + "");
		tv_minute.setText(c.get(c.MINUTE) + "");
	}

	private void initPopWindow() {
		flipper = new EmotionViewFlipper(this);
		GridView gridView = (GridView) flipper.findViewById(R.id.emotion_cat_gridview1);
		emotionAdapter = new EmotionAdapter(this, emotion_cats);
		gridView.setAdapter(emotionAdapter);
		
		GridView gridView2 = (GridView) flipper.findViewById(R.id.emotion_cat_gridview2);
		emotionAdapter = new EmotionAdapter(this, emotion_peoples);
		gridView2.setAdapter(emotionAdapter);
		
		GridView gridView3 = (GridView) flipper.findViewById(R.id.emotion_cat_gridview3);
		emotionAdapter = new EmotionAdapter(this, emotion_jiong);
		gridView3.setAdapter(emotionAdapter);
		
		gridView.setOnTouchListener(new FlipperOnTouchListener());
		gridView2.setOnTouchListener(new FlipperOnTouchListener());
		gridView3.setOnTouchListener(new FlipperOnTouchListener());
		
		gridView.setOnItemClickListener(new GriviewOnItemClickListener(0));
		gridView2.setOnItemClickListener(new GriviewOnItemClickListener(1));
		gridView3.setOnItemClickListener(new GriviewOnItemClickListener(2));
		
		popupWindow = new PopupWindow(flipper, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		popWindowParent.getLocationInWindow(locations);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		Log.i("a", "locations[0] = " + locations[0] + " locations[1] = " + locations[1]);
	}
	
	private class GriviewOnItemClickListener implements OnItemClickListener {
		private int gridflag = 0;
		public GriviewOnItemClickListener(int grid_flag) {
			this.gridflag = grid_flag;
		}
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (this.gridflag) {
			case 0:
				et_content.setImageIntoEditText(emotion_cats[arg2]);
				break;
			case 1:
				et_content.setImageIntoEditText(emotion_peoples[arg2]);
				break;
			case 2:
				et_content.setImageIntoEditText(emotion_jiong[arg2]);
				break;
			}
		}
	}
	
	private class FlipperOnTouchListener implements OnTouchListener {
		float x1 = 0f, x2 = 0f;
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				x1 = event.getX();
				break;
			case MotionEvent.ACTION_UP:
				x2 = event.getX();
				if(x1 - x2 < -15) {
					flipper.showPrevious();
				}else if(x1 - x2 > 15) {
					flipper.showNext();
				}
				break;
			}
			return false;
		}
		
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
//			intent = new Intent(AddCommonDailyActivity.this,
//					IndexActivity.class);
//			AddCommonDailyActivity.this.finish();
//			startActivity(intent);
			this.finish();
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
				overridePendingTransition(0, R.anim.activity_down);
			}
			break;
		case R.id.btn_date:
			initDatePickWheelDialog();
			dpwd.show();
//			intent = new Intent(AddCommonDailyActivity.this,
//					SetDateAndTimeActivity.class);
//			startActivityForResult(intent, 100);
			break;
		case R.id.btn_weather:
			intent = new Intent(AddCommonDailyActivity.this,
					SelectWeatherActivityDialog.class);
			startActivityForResult(intent, 97);
			break;
		case R.id.btn_location:
			
			netUtils = new NetUtils(this);
			if(!netUtils.isNetConnected()) {
				Toast.makeText(this, "请检查网络环境是否正常", 0).show();
				return;
			}
			
			if (!isLocation) {
				tv_address.setText("正在定位...");
				setLocationOption();
				mLocClient.start();
				isLocation = true;
				

			} else {
				btn_location
						.setBackgroundResource(R.drawable.v5_7_publisher_poi_button_on_white);
				isLocation = false;
				tv_address.setText("指定位置");
				btn_spinner.setVisibility(ViewGroup.GONE);
				mLocClient.stop();
			}
			break;
		case R.id.btn_spinner:
			if(isLocation) {
				addresses = ((Location)getApplication()).addresses;
				intent = new Intent(AddCommonDailyActivity.this, SelectLocationActivity.class);
				intent.putExtra("addresses", addresses);
				intent.putExtra("currentAddress", tv_address.getText().toString());
				intent.putExtra("index", index);
				startActivityForResult(intent, 100);
				if(Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
					overridePendingTransition(R.anim.activity_up, R.anim.steady);
				}
			}
			
//			startActivity(intent);
//			AddCommonDailyActivity.this.finish();
//			overridePendingTransition(R.anim.activity_up, R.anim.steady);
			break;
		case R.id.btn_selectimg:
			intent = new Intent(AddCommonDailyActivity.this, SelectPhotoDialog.class);
			startActivityForResult(intent, 79);
			break;
			
		case R.id.btn_selectemotion:
			initPopWindow();
			popupWindow.showAtLocation(popWindowParent, Gravity.BOTTOM, 0, screenHeight - locations[1]);
			break;
		}
	}

	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 99) {
			tv_year.setText(data.getStringExtra("year"));
			tv_month.setText(data.getStringExtra("month"));
			tv_day.setText(data.getStringExtra("day"));
			tv_hour.setText(data.getStringExtra("hour"));
			tv_minute.setText(data.getStringExtra("minute"));
		}
		if (resultCode == 96) {
			et_weather.setText(data.getStringExtra("weather"));
		}
		if(resultCode == 90) {
			tv_address.setText(data.getStringExtra("currentAddress"));
		}
		if(resultCode == 80) {
			tv_address.setText(data.getStringExtra("currentAddress"));
			index = data.getIntExtra("index", 0);
		}
		
		
		if(resultCode == 77) {
			//album
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			intent.setType("image/jpeg");
			startActivityForResult(intent, 60);
			//Toast.makeText(this, "" + data.getStringExtra("flag"), 0).show();
		}
		if(resultCode == 76) {
			//camera
			intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivityForResult(intent, 61);
			//Toast.makeText(this, "" + data.getStringExtra("flag"), 0).show();
		}
		
		ContentResolver cr = this.getContentResolver();
		
		if(requestCode == 60) {
			Uri uri = data.getData();
				
				String[] proj = {MediaStore.Images.Media.DATA};
				Cursor cursor = managedQuery(uri, proj, null, null, null);
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				String path = cursor.getString(column_index);
				
				
				
				et_content.setImageIntoEditText(compressBitmap(path));
		}
		if(requestCode == 61) {
			Bundle bundle = data.getExtras();
			bm = (Bitmap) bundle.get("data");
			et_content.setImageIntoEditText(bm);
		}
		
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	private Bitmap compressBitmap(String path) {
		Bitmap bm = null;
		
		File file = new File(path);
		
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		
		
		if(file.length() < 20480 ) {
			opts.inSampleSize = 4;
		}else  if(file.length() < 51200) {
			opts.inSampleSize = 6;
		}else  if(file.length() < 307200) {
			opts.inSampleSize = 8;
		}else  if(file.length() < 1048576) {
			opts.inSampleSize = 10;
		}else {
			opts.inSampleSize = 12;
		}
		
		opts.inJustDecodeBounds = false;
		
		bm = BitmapFactory.decodeFile(path, opts);
		
		return bm;
	}
	
}
