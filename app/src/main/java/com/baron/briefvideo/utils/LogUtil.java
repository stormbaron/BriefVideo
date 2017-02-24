package com.baron.briefvideo.utils;

import android.util.Log;

public class LogUtil {
public static boolean ISCAN_LOG_ERROR=true;
public static boolean ISCAN_LOG_DEBUG=true;
public static boolean ISCAN_LOG_INFO=true;
public static boolean ISCAN_LOG_WARN=true;

public static void error(String TAG,String content){
	if(ISCAN_LOG_ERROR==true)
	  Log.e(TAG, content);
}
public static void debug(String TAG,String content){
	if(ISCAN_LOG_DEBUG==true)
	Log.d(TAG, content);
}
public static void info(String TAG,String content){
	if(ISCAN_LOG_INFO==true)
	Log.i(TAG, content);
}
public static  void warn(String TAG,String content){
	if(ISCAN_LOG_WARN==true)
	Log.w(TAG, content);
}
}
