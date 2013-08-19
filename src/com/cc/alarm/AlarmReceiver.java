package com.cc.alarm;

import java.text.SimpleDateFormat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
  private boolean isOpen = false;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		/**
		 * 闹钟 测试
		 */
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  Log.i("AlarmReceiver", formatter.format(System.currentTimeMillis()));
	  if (isOpen) {
	    Intent activityIntent = new Intent(context,showSchedule.class);
      activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(activityIntent);
    }
	}

}
