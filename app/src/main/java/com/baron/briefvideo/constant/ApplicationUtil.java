package com.baron.briefvideo.constant;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

public class ApplicationUtil extends Application {
	private static Context context;

	public static Context getcontext() {
		return context;
	}

	@Override
	public void onCreate() {
		context = getApplicationContext();
		LeakCanary.install(this);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
