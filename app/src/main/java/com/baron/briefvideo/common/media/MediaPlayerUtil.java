package com.baron.briefvideo.common.media;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.baron.briefvideo.utils.LogUtil;
/**
 *
 * @author baron description 视频操作类 date 2016/10/12
 */
public class MediaPlayerUtil {
	// public static MediaPlayerUtil instance=null;
	MediaPlayer mediaPlayer;
	SurfaceView surface1;
	// 播放的视频路径,名称
	String path;
	// 播放进度条
	private SeekBar videoPlayProgress;
	// 播放进度
	private int postion = 0;
	// 时间控制器
	private Timer mTimer = new Timer();
	//进度条进度
	int progress_postion=0;
	/**
	 * 构造函数
	 *
	 * @param postion
	 *            视频播放位置
	 * @param path
	 *            视频路径
	 * @param surface1
	 *            SurfaceView（视频的画面通过SurfaceView显示）
	 */
	public MediaPlayerUtil(int postion, String path, SurfaceView surface1) {
		mediaPlayer = new MediaPlayer();
		this.path = path;
		this.postion = postion;
		this.surface1 = surface1;
		// 设置播放时打开屏幕
		surface1.getHolder().setKeepScreenOn(true);
		surface1.getHolder().addCallback(new SurfaceViewLis());
	}

	/**
	 * 构造函数
	 *
	 * @param postion
	 *            视频播放位置
	 * @param path
	 *            视频路径
	 * @param surface1
	 *            SurfaceView（视频的画面通过SurfaceView显示）
	 * @param videoPlayProgress
	 *            进度条显示播放进度
	 */
	public MediaPlayerUtil(int postion, String path, SurfaceView surface1,
						   SeekBar videoPlayProgress) {
		mediaPlayer = new MediaPlayer();
		this.path = path;
		this.postion = postion;
		this.videoPlayProgress = videoPlayProgress;
		this.surface1 = surface1;
		// 设置播放时打开屏幕
		surface1.getHolder().setKeepScreenOn(true);
		surface1.getHolder().addCallback(new SurfaceViewLis());
		// 开始执行定时任务刷新进度条
		mTimer.schedule(mTimerTask, 0, 1000);
		videoPlayProgress.setOnSeekBarChangeListener(myOnSeekBarChangeListener);

	}
	OnSeekBarChangeListener myOnSeekBarChangeListener=new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
									  boolean fromUser) {
			LogUtil.error("MediaPlayerUtil", "onProgressChanged");
			progress_postion=progress;
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			LogUtil.error("MediaPlayerUtil", "onStartTrackingTouch");
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			LogUtil.error("MediaPlayerUtil", "onStopTrackingTouch");
			int duration=mediaPlayer.getDuration();
			postion=progress_postion*duration/seekBar.getMax();
			mediaPlayer.seekTo(postion);
		}};
	// 开始播放任务
	public void init() throws IllegalArgumentException, SecurityException,
			IllegalStateException, IOException {
		mediaPlayer.reset();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDataSource(path);
		// 把视频输出到SurfaceView上
		mediaPlayer.setDisplay(surface1.getHolder());
		mediaPlayer.prepare();
		mediaPlayer.start();
	}

	// 暂停播放
	public void pause() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			postion = mediaPlayer.getCurrentPosition();
			LogUtil.error("mediaPlayer","mediaplayer is pause");
		} else {
			mediaPlayer.start();
			mediaPlayer.seekTo(postion);
			LogUtil.error("mediaPlayer","mediaplayer is start");
		}
	}

	// 停止播放
	public void stop() {
		if (mediaPlayer.isPlaying()) {
			// 保存当前播放的位置
			postion = mediaPlayer.getCurrentPosition();
			mediaPlayer.stop();
			// 停止广播
		}
	}

	// 停止播放并释放资源（同时必须停止定时任务，否则将报错（找不到客户端））
	public void destroy() {
		// 停止定时任务，而且一定在释放mediaPlayer之前，否则将报错（ java.lang.IllegalStateException）
		mTimer.cancel();
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();
		mediaPlayer.release();
	}

	private class SurfaceViewLis implements SurfaceHolder.Callback {
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
								   int height) {
			LogUtil.error("VideoSurface","正在播放 进度" + format + "数据2");
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			if (postion == 0) {
				try {
					init();
					mediaPlayer.seekTo(postion);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
		}
	}

	/*******************************************************
	 * 通过定时器和Handler来更新进度条
	 ******************************************************/
	TimerTask mTimerTask = new TimerTask() {
		@Override
		public void run() {
			if (mediaPlayer == null)
				return;
			if (mediaPlayer.isPlaying()) {
				handleProgress.sendEmptyMessage(0);
			}
		}
	};
	Handler handleProgress = new Handler() {

		public void handleMessage(Message msg) {
			//if (mediaPlayer.isLooping()) {// 避免处理通知时mediaPlyery已经release()
			if (mediaPlayer.isPlaying()) {// 避免收到通知时，mediaPlayer已经停止（点击返回销毁了mediaPlayer）

				int position = mediaPlayer.getCurrentPosition();

				int duration = mediaPlayer.getDuration();

				if (duration > 0) {
					if (videoPlayProgress != null) {
						long pos = videoPlayProgress.getMax() * position
								/ duration;
						videoPlayProgress.setProgress((int) pos);
					}
				}
				//	}
			}
		};
	};

}
