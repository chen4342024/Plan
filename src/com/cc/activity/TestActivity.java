package com.cc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TestActivity extends Activity {
  private AddDailyPlanAcitvity myImageText;
  private TextView t2;
  private RelativeLayout layout;
  private Button button;
  private static int i = 0;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
  }
}
