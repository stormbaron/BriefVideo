package com.baron.briefvideo.common.thread;

import android.util.Log;

public class IMyRunnable implements Runnable{
	MyRunnableTask myRunnableTask=null;

	public IMyRunnable(MyRunnableTask myRunnableTask2){
		this.myRunnableTask=myRunnableTask2;
		Log.e("IMyRunnable", "this is new IMyRunnable");
	}

	@Override
	public void run() {
		if(myRunnableTask!=null){
			Log.e("IMyRunnable", "正在执行线程任务");
			myRunnableTask.doTask();
		}
		else{
			Log.e("IMyRunnable", "线程无任务");
		}
	}
	/**
	 * 调用者 实现的操作接口
	 * @author baron
	 *
	 */
	public interface MyRunnableTask{
		void doTask();
	}
}