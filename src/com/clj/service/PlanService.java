package com.clj.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PlanService extends Service {

	private static PlanService instance;

	// 判断服务是否存在
	public static boolean isExist() {
		return instance != null;
	}

	// 只要服务存在，就可以取得服务的对象
	public static PlanService instance() {
		if (instance != null)
			return instance;
		return null;
	}

	// 以后要实例单例对象可以在onCreate方法内实例，只要服务不被销毁只会执行一次
	@Override
	public void onCreate() {

		super.onCreate();

		instance = this;

	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onDestroy() {

		if (instance != null)
			instance = null;
		super.onDestroy();
	}

}
