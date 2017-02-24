package com.baron.briefvideo.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {


	//线程池
	public static ExecutorService singleThreadPool=Executors.newSingleThreadExecutor();

	//增加要执行的线程任务
	public static void doThread(IMyRunnable iMyRunnable){
		singleThreadPool.execute(iMyRunnable);
	}

	//关闭线程池将要执行的线程
	public static void shutDownAll(){
		singleThreadPool.shutdownNow();
	}


}
