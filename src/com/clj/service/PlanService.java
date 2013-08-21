package com.clj.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

public class PlanService extends Service {

	private static PlanService instance;
	SharedPreferences sharedPreferences;
	SharedPreferences.Editor edit;

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
	public boolean ReadVal(String name, boolean defaultval) {
		boolean retval;
		synchronized (instance) {
			retval = sharedPreferences.getBoolean(name, defaultval);
		}
		return retval;
	}

	public void WriteVal(String name, boolean val) {
		synchronized (instance) {
			edit.putBoolean(name, val);
		}
	}

	public String ReadVal(String name, String defaultval) {
		String retval;

		synchronized (instance) {
			retval = sharedPreferences.getString(name, defaultval);
		}
		return retval;
	}

	public void WriteVal(String Name, String val) {
		synchronized (instance) {
			edit.putString(Name, val);
		}
	}

	public void SaveParams() {
		synchronized (instance) {
			edit.commit();
		}
	}


	// 以后要实例单例对象可以在onCreate方法内实例，只要服务不被销毁只会执行一次
	@Override
	public void onCreate() {

		super.onCreate();

		instance = this;
		sharedPreferences = getSharedPreferences("CCset", MODE_WORLD_WRITEABLE);
		edit = sharedPreferences.edit();

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
