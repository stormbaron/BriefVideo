package com.baron.briefvideo.moudle;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.util.Log;

/**
 * @author baron
 */
public class ActivityController {
	
	public static List<Activity> activities;

	public static void addActivity(Activity activity) {
		if (activities != null) {
			activities.add(activity);
		} else {
			activities = new ArrayList<Activity>();
			activities.add(activity);
		}
		Log.d("ActivityController", "add:"+activity.toString());
	}

	public static void removeActivity(Activity activity) {
		activities.remove(activity);
		Log.d("ActivityController", "remove:"+activity.toString());
	}

	public static void finishAllActivity() {
		for (Activity activity : activities) {
			activity.finish();
		}
	}

}
