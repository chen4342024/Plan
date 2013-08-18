package com.cc.activity;

import java.util.List;

import com.cc.db.DailyPlan;
import com.cc.db.DailyPlanDao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		TextView t1 = (TextView) findViewById(R.id.test1);
		List<DailyPlan> dailyPlans = DailyPlanDao.readAll(this, null, null, null, null);
		t1.setText("123123");
	}
}
