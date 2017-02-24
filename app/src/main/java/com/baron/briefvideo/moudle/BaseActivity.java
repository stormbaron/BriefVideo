package com.baron.briefvideo.moudle;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity Base
 *
 * @author baron
 *
 */
public class BaseActivity extends Activity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityController.addActivity(this);
	}

	
	protected void onDestroy() {
		super.onDestroy();
		ActivityController.removeActivity(this);
	}

	/**
	 * 启动当前Activity,供其他Activity调用
	 *
	 * @param context
	 * @param data1
	 *            该Activity需要的参数
	 */
	public static void actionStart(Context context, String data1) {
		Intent intent = new Intent();
		intent.setClass(context, BaseActivity.class);
		intent.putExtra("data1", data1);
		context.startActivity(intent);
	}
}
