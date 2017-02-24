package com.baron.briefvideo.common.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePUtils {
	//保存视频信息的SharedPreferences 名称
	public static String SHARE_NAME="HasDb";
	// 保存视频是否是第一次刷新的字段名称 isHasDb
	public static String SHARE_NAME_ISHasDb="isHasDb";
	public static boolean isHasDb(Context context){
		boolean isHasDb=false;
		SharedPreferences sp;
		sp = context.getSharedPreferences(SHARE_NAME, Context.MODE_WORLD_READABLE);

		String hasDbStr=sp.getString(SHARE_NAME_ISHasDb, "false");

		if("true".equalsIgnoreCase(hasDbStr)){
			isHasDb=true;
		}
		return isHasDb;
	}

	public static void SetHasDb(Context context){
		SharedPreferences sp;
		sp = context.getSharedPreferences(SHARE_NAME, Context.MODE_WORLD_READABLE);
		Editor editor = sp.edit();
		editor.putString(SHARE_NAME_ISHasDb,"true");
		editor.commit();
	}
}
