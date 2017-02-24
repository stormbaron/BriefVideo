package com.baron.briefvideo.moudle;



import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.baron.briefvideo.R;
import com.baron.briefvideo.common.media.MediaPlayerUtil;
import com.baron.briefvideo.utils.LogUtil;
/**
 * 该实例中使用MediaPlayer完成播放，同时界面使用SurfaceView来实现
 *
 * 这里我们实现MediaPlayer中很多状态变化时的监听器
 *
 * 使用Mediaplayer时，也可以使用MediaController类，但是需要实现MediaController.mediaController接口
 * 实现一些控制方法。
 *
 * 然后，设置controller.setMediaPlayer(),setAnchorView(),setEnabled(),show()就可以了，
 * 这里不再实现
 *
 * @author baron
 *
 */
public class VideoSurface extends BaseActivity implements OnClickListener {
	private SurfaceView surface1;
	private TextView title;
	private ImageButton start,btn_right;
	// 当前的播放位置
	private int postion = 0;
	private String SCREEN_ORIENTATION;
	private com.baron.briefvideo.common.view.TitleBarView titlebar;
	// 播放的视频路径,名称
	private String path;
	private String Name;
	// 播放进度条 SeekBar是progress的子类，拖动条可以拖动
	private SeekBar videoPlayProgress;

	// TAG
	private String TAG = "VideoSurface";
	private MediaPlayerUtil myMediaPlayerUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//保持屏幕常亮
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
      /*  //屏幕方向默认设置
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
      */
		//将状态栏、底部的导航栏隐藏
        View decorView=getWindow().getDecorView();
        int option=View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		decorView.setSystemUiVisibility(option);

		setContentView(R.layout.video_surface);
		Intent intent = getIntent();
		path = intent.getStringExtra("path");
		Name = intent.getStringExtra("Name");
		// 判断savedInstanceState是否被传入数据
		if (savedInstanceState != null) {
			postion = savedInstanceState.getInt("postion");
		}
		getView();
		initView(postion);
		LogUtil.info(TAG, "this is onCreate");
	}
	public void getView() {
		surface1 = (SurfaceView) findViewById(R.id.video_surface);
		start = (ImageButton) findViewById(R.id.start);
		title = (TextView) findViewById(R.id.title);
		btn_right = (ImageButton) findViewById(R.id.btn_right);
		videoPlayProgress = (SeekBar) findViewById(R.id.video_play_progress);
	}

	public void initView(int postion) {
		start.setOnClickListener(this);
		btn_right.setOnClickListener(this);
		title.setText(Name);
		//设置SeekBar的样式
		myMediaPlayerUtil = new MediaPlayerUtil(postion, path, surface1,videoPlayProgress);
		videoPlayProgress.setDrawingCacheBackgroundColor(getResources().getColor(R.color.commo_text_color));

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.start:
				try {
					myMediaPlayerUtil.pause();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
				break;
			/*case R.id.pre:
				myMediaPlayerUtil.pause();
			case R.id.stop:
				myMediaPlayerUtil.stop();
				break;*/
			case R.id.btn_right:
				if ("SCREEN_ORIENTATION_LANDSCAPE".equals(SCREEN_ORIENTATION)) {
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					SCREEN_ORIENTATION = "SCREEN_ORIENTATION_PORTRAIT";
					LogUtil.info(TAG, "land");
				} else {
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					SCREEN_ORIENTATION = "SCREEN_ORIENTATION_LANDSCAPE";
					LogUtil.info(TAG, "portar");
				}
				break;
			default:
				break;
		}
	}

	// 将数据保存到outState对象中, 该对象会在重建activity时传递给onCreate方法
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// 销毁前保存进度
		outState.putInt("postion", postion);
	}

	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.info(TAG, "this is onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.info(TAG, "this is onResume");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.info(TAG, "this is onRestart");
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.info(TAG, "this is onPause");
	}

	@Override
	protected void onDestroy() {
		myMediaPlayerUtil.destroy();
		super.onDestroy();
		LogUtil.info(TAG, "this is onDestroy");
	}

	/**
	 * 启动当前Activity
	 *
	 * @param context
	 * @param path
	 *            视频路径
	 */
	public static void actionStart(Context context, String path, String Name) {
		Intent intent = new Intent();
		intent.setClass(context, VideoSurface.class);
		intent.putExtra("path", path);
		intent.putExtra("Name", Name);
		context.startActivity(intent);
	}

}