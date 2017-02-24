package com.baron.briefvideo.constant;

import com.baron.briefvideo.bean.Video;

import java.util.List;

public class SettingInfo {
	
	public static boolean isHashGet=false;
	public static List<Video> videoList = null;
	public static String abPath=ApplicationUtil.getcontext().getApplicationInfo().dataDir;
	public static String filePath=ApplicationUtil.getcontext().getFilesDir().getAbsolutePath();

	public  static String DATA_BASE_PATH = abPath+"/databases/video.db";
	
	public  static String DATA_BASE_PATH_4_20 = "/data/data/"+ApplicationUtil.getcontext().getPackageName()+"/databases/video.db";
	
	public  static String TABLE_VIDEO = "videoinfo_table";
	


}
