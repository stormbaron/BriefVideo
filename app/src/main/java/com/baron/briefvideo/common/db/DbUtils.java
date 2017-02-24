package com.baron.briefvideo.common.db;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.baron.briefvideo.constant.ApplicationUtil;
import com.baron.briefvideo.utils.LogUtil;

public class DbUtils {


	public static String abPath=ApplicationUtil.getcontext().getFilesDir().getAbsolutePath();
	public  static String TABLE_VIDEO = "videoinfo_table";
	public  static String DATA_BASE_PATH = abPath+"/com.briefvideo.db/databases/video.db";


	public static SQLiteDatabase openOrCreateDatabase(String dataPath) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dataPath,
				null);
		return db;
	}

	public  static void createTable_VideoInfo(SQLiteDatabase db) {
		// 创建表SQL语句
		String videoInfo_Table = "create table videoinfo_table(_id integer primary key autoincrement,title text,path text,imagePath text,size real,position real,duration real)";
		// 执行SQL语句
		Log.e(" DbUtils", "创建数据表");
		db.execSQL(videoInfo_Table);
	}

	public  static void deleteContent(SQLiteDatabase db,String tabel) {
		String sql="DELETE FROM "+tabel;
		db.execSQL(sql);
	}

	public  static void createTable(SQLiteDatabase db) {
		// 创建表SQL语句
		String videoInfo_Table = "create table videoinfo_table(_id integer primary key autoincrement,title text,path text,imagePath text,size real,position real,duration real)";
		// 执行SQL语句
		db.execSQL(videoInfo_Table);
	}
	public  static void insert(SQLiteDatabase db, String table, String title,
							   String path, String imagePath, long size, long position,
							   long duration) {
		// 实例化常量值
		ContentValues cValue = new ContentValues();
		cValue.put("title", title);
		cValue.put("path", path);
		cValue.put("imagePath", imagePath);
		cValue.put("size", size);
		LogUtil.error("DBService", "intSize："+size);
		LogUtil.error("DBService", "intDuration："+duration);
		cValue.put("position", position);
		cValue.put("duration", duration);
		db.insert(table, null, cValue);
	}

	public static Cursor selectInfo(SQLiteDatabase db, String table) {
		/*Cursor 是查询后的遍历返回集
		 * Cursor 的方法 getCount() 获得总的数据项数 isFirst() 判断是否第一条记录
		 * isLast() 判断是否最后一条记录 moveToFirst() 移动到第一条记录 moveToLast() 移动到最后一条记录
		 * move(int offset) 移动到指定记录 moveToNext() 移动到下一条记录 moveToPrevious()
		 * 移动到上一条记录 getColumnIndexOrThrow(String columnName) 根据列名称获得列索引
		 * getInt(int columnIndex) 获得指定列索引的int类型值 getString(int columnIndex)
		 * 获得指定列缩影的String类型值
		 */
		return db.query(table, null, null, null, null, null, null, null);
	}
}
