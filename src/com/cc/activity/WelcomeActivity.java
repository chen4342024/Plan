package com.cc.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.alarm.AlarmReceiver;

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
		StartTimer();
		//initAlarm();
	}
	
	//todo
	private void initAlarm(){
	  AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this,AlarmReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    alarmManager.setRepeating(AlarmManager.RTC, 0, 60*1000, pendingIntent);
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
		timer.schedule(task, 4 * 1000, 5000);
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

}
