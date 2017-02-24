package com.baron.briefvideo.common.db;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.baron.briefvideo.bean.Video;
import com.baron.briefvideo.common.media.AbstructProvider;
import com.baron.briefvideo.common.media.VideoProvider;
import com.baron.briefvideo.constant.SettingInfo;
import com.baron.briefvideo.utils.ImageUtils;
import com.baron.briefvideo.utils.LogUtil;

public class DBService {

    static List<Video> listVideos = null;
    static boolean HasDb = true;

    public static boolean isHasCreateDb() {

        return HasDb;
    }

    /**
     * 获取视频信息（判断本地是否存在数据库没有则直接获取并新建）
     *
     * @return
     */

    public static List<Video> getVideoInfo(Context context) {
        if (SharePUtils.isHasDb(context)) {
            listVideos = getVideoInfoFromDb();
        } else {
            listVideos = getVideoInfoFromPhone(context);
            saveVideoInfoToDb(listVideos);
            SharePUtils.SetHasDb(context);
        }


      /* if(SettingInfo.videoList!=null){
         listVideos=SettingInfo.videoList;
	   }
	   else{
		   listVideos= getVideoInfoFromPhone(context);
		   SettingInfo.videoList= listVideos;
	   }*/


        return listVideos;
    }

    /**
     * 获取并刷新视频信息
     *
     * @return
     */

    public static List<Video> getVideoInfo_refresh(Context context) {
        listVideos = getVideoInfoFromPhone(context);
        refreVideoInfoToDb(listVideos);
        return listVideos;
    }

    /**
     * 从本地数据库获取视频信息
     *
     * @return
     */
    public static List<Video> getVideoInfoFromDb() {
        if (isHasCreateDb()) {

            Cursor cursor = DbUtils.selectInfo(DbUtils.openOrCreateDatabase(SettingInfo.DATA_BASE_PATH), SettingInfo.TABLE_VIDEO);
            Video video;
            listVideos = new ArrayList<Video>();
            while (cursor.moveToNext()) {
                video = new Video();
                video.setTitle(cursor.getString(cursor
                        .getColumnIndex("title")));
                video.setImagePath(cursor.getString(cursor
                        .getColumnIndex("imagePath")));
                video.setPath(cursor.getString(cursor
                        .getColumnIndex("path")));
                video.setSize(cursor.getLong(cursor
                        .getColumnIndex("size")));
                video.setDuration(cursor.getLong(cursor
                        .getColumnIndex("duration")));
                LogUtil.error("DBService", "GetSize：" + video.getSize());
                LogUtil.error("DBService", "GetDuration：" + video.getDuration());
                listVideos.add(video);
            }
        }

        return listVideos;
    }

    /**
     * 获取视频信息（从本地视屏中直接获取）
     *
     * @return
     */

    public static List<Video> getVideoInfoFromPhone(Context context) {

        // 在主类中使用如下代码来获取最终得到的视频相关信息集合</p>
        AbstructProvider provider = new VideoProvider(context);

        listVideos = provider.getList();

        return listVideos;
    }

    /**
     * 保存视频信息
     *
     * @return
     */

    public static void saveVideoInfoToDb(List<Video> listvideo) {
        String DATA_PATH = null;

        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            DATA_PATH = SettingInfo.DATA_BASE_PATH;
        } else {
            DATA_PATH = SettingInfo.DATA_BASE_PATH_4_20;
        }
        //有可能文件夹不存在
		/* File f=ApplicationUtil.getcontext().getDatabasePath("video.db").getParentFile();

		 if(f.exists()==false)f.mkdirs();   //注意是mkdirs()有个s 这样可以创建多重目录。

         DATA_PATH=f.getPath()+"/video.db";*/

        File f_ = new File(DATA_PATH);
        if (!f_.getParentFile().exists()) {
            Log.e("DBService", "文件夹不存在，新建一个");
            f_.getParentFile().mkdirs();
        }

        SQLiteDatabase db = DbUtils.openOrCreateDatabase(DATA_PATH);

        DbUtils.createTable_VideoInfo(db);

        for (int i = 0; i < listvideo.size(); i++) {
            Log.e("DBService", "保存视频" + i);
            Log.e("DBService", "保存图片路径名：" + listvideo.get(i).getImagePath());
            DbUtils.insert(db, SettingInfo.TABLE_VIDEO, listvideo.get(i).getTitle(), listvideo.get(i).getPath(), listvideo.get(i).getImagePath(), listvideo.get(i).getSize(), 0, listvideo.get(i).getDuration());
        }

    }

    /**
     * 刷新视频信息
     *
     * @return
     */

    public static void refreVideoInfoToDb(List<Video> listvideo) {
        String DATA_PATH = null;

        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            DATA_PATH = SettingInfo.DATA_BASE_PATH;
        } else {
            DATA_PATH = SettingInfo.DATA_BASE_PATH_4_20;
        }
        //有可能文件夹不存在
		/* File f=ApplicationUtil.getcontext().getDatabasePath("video.db").getParentFile();

		 if(f.exists()==false)f.mkdirs();   //注意是mkdirs()有个s 这样可以创建多重目录。

         DATA_PATH=f.getPath()+"/video.db";*/

        File f_ = new File(DATA_PATH);
        if (!f_.getParentFile().exists()) {
            Log.e("DBService", "文件夹不存在，新建一个");
            f_.getParentFile().mkdirs();
        }

        SQLiteDatabase db = DbUtils.openOrCreateDatabase(DATA_PATH);
        DbUtils.deleteContent(db, SettingInfo.TABLE_VIDEO);

        for (int i = 0; i < listvideo.size(); i++) {

            LogUtil.error("DBService", "保存图片路径名：" + listvideo.get(i).getImagePath());
            LogUtil.error("DBService", "Size：" + listvideo.get(i).getSize());
            LogUtil.error("DBService", "Duration：" + listvideo.get(i).getDuration());
            DbUtils.insert(db, SettingInfo.TABLE_VIDEO, listvideo.get(i).getTitle(), listvideo.get(i).getPath(), listvideo.get(i).getImagePath(), listvideo.get(i).getSize(), 0, listvideo.get(i).getDuration());
        }

    }

}
