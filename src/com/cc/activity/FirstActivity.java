package com.cc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FirstActivity extends Activity implements OnClickListener {
  private TextView tv_dailyPlan;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first);
    tv_dailyPlan = (TextView) findViewById(R.id.add_daily_plan);
    tv_dailyPlan.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.add_daily_plan:
        Intent addDailyPlanIntent = new Intent(this,AddDailyPlanAcitvity.class);
        startActivity(addDailyPlanIntent);
        break;

      default:
        break;
    }

  }
}
