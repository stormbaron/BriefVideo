package com.baron.briefvideo.moudle;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;

import com.baron.briefvideo.R;
/**
 * 欢迎界面，定时跳转至登录界面
 * @author baron
 *
 */
public class WelcomeActivity extends BaseActivity {
	Intent intent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
	}
	protected void onResume() {
		super.onResume();
		intent = new Intent();
	   intent.setClass(WelcomeActivity.this, MainActivity.class);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				startActivity(intent);
				finish();
			}
		};
		timer.schedule(task, 3000);
	}

}
