package com.cc.activity;

import java.util.List;

import com.cc.db.DailyPlan;
import com.cc.db.DailyPlanDao;
import com.cc.view.MyImageText;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity {
  private MyImageText myImageText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		TextView t1 = (TextView) findViewById(R.id.test1);
		List<DailyPlan> dailyPlans = DailyPlanDao.readAll(this, null, null, null, null);
		t1.setText("123123");
		System.out.println("测试");
		System.out.println("测试");
		myImageText = (MyImageText) findViewById(R.id.image_text);
		myImageText.setImageResource(R.drawable.cloud);
		myImageText.setTextViewText("这个只是测试而已。。不要当真");
	}
}
