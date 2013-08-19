package com.cc.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.alarm.AlarmReceiver;
import com.clj.service.PlanService;

public class WelcomeActivity extends Activity {
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private TextView textView;
	private Timer timer;
	private TimerTask task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
		initAnimal();

		// initAlarm();
		if (PlanService.isExist()) {
			Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		} else {

			Intent intent_service = new Intent(WelcomeActivity.this,
					PlanService.class);
			startService(intent_service);
			StartTimer();
		}

	}

	// todo
	private void initAlarm() {
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);
		alarmManager
				.setRepeating(AlarmManager.RTC, 0, 60 * 1000, pendingIntent);
	}

	private void initAnimal() {
		imageView1.startAnimation(AnimationUtils.loadAnimation(
				WelcomeActivity.this, R.anim.anim_in));
		imageView2.startAnimation(AnimationUtils.loadAnimation(
				WelcomeActivity.this, R.anim.anim_left));
		imageView3.startAnimation(AnimationUtils.loadAnimation(
				WelcomeActivity.this, R.anim.anim_right));
		textView.startAnimation(AnimationUtils.loadAnimation(
				WelcomeActivity.this, R.anim.alpha_text));

	}

	private void initView() {
		imageView1 = (ImageView) findViewById(R.id.welcome_image1);
		imageView2 = (ImageView) findViewById(R.id.welcome_image2);
		imageView3 = (ImageView) findViewById(R.id.welcome_image3);
		textView = (TextView) findViewById(R.id.welcome_text);
	}

	private void StartTimer() {
		if (timer == null)
			timer = new Timer();

		if (task == null) {
			task = new TimerTask() {
				@Override
				public void run() {
					Intent intent = new Intent(WelcomeActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
					StopTimer();
				}
			};
		}
		timer.schedule(task, 3 * 1000, 5000);
	}

	private void StopTimer() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}

		if (task != null) {
			task.cancel();
			task = null;
		}

	}

	// 按返回键的回调方法
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub\
		int k_code;
		int k_action;
		k_code = event.getKeyCode();
		k_action = event.getAction();
		if ((k_code == KeyEvent.KEYCODE_BACK && k_action == KeyEvent.ACTION_DOWN)
				&& event.getRepeatCount() == 0) {
			StopTimer();
			stopService();
			finish();
			
			return true;
		}

		return super.dispatchKeyEvent(event);
	}
	private void stopService()
	{
		Intent intent_service=new Intent(WelcomeActivity.this,PlanService.class);
		stopService(intent_service);
	}

}
