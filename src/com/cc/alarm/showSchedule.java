package com.cc.alarm;

import com.cc.activity.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class showSchedule extends Activity implements OnClickListener{
	private TextView showSchedule;
	private MediaPlayer mediaPlayer;
	private  Vibrator vib ;
	private Button btnSubmit;
	private long[] pattern = {50, 1000, 50, 2000};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showschedule);
		showSchedule = (TextView)findViewById(R.id.showSchedule);
		btnSubmit = (Button)findViewById(R.id.btnSubmit);
		
		mediaPlayer = MediaPlayer.create(this, R.raw.ring);
		vib = (Vibrator)this.getApplication().getSystemService(VIBRATOR_SERVICE);
		
		AudioManager volMgr = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE); 
		switch(volMgr.getRingerMode()){
		case AudioManager.RINGER_MODE_NORMAL:
			vib.vibrate(5000);
			if(vib !=null){
				Log.d("myDebug", "null");
			}
			vib.vibrate(pattern, 2);
			mediaPlayer.start();break;
		case AudioManager.RINGER_MODE_VIBRATE:
//		vib.vibrate(5000);
			vib.vibrate(pattern, 2);
			break;
		}
		showSchedule.setText("测试而已。。不要当真");
		btnSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnSubmit:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.stop();
			}
			if(null!=vib){   
				vib.cancel();   
			}   
			finish();
		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
		if(null!=vib){
			vib.cancel(); 
		}
		return true;
	}
	
	
}
