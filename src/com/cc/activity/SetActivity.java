package com.cc.activity;

import com.clj.service.PlanService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SetActivity extends Activity implements OnClickListener {

  private RelativeLayout plan_is_remind_layout;
  private ImageView plan_is_remind_image;
  private RelativeLayout memo_is_remind_layout;
  private ImageView memo_is_remind_image;
  private RelativeLayout remind_way_layout;
  private TextView remind_way_text;
  private LinearLayout remind_way_hide_layout;
  private TextView ring_text;
  private TextView shake_text;
  private RelativeLayout plan_count_layout;
  private TextView plan_count_text;
  private LinearLayout plan_count_hide_layout;
  private TextView five_text;
  private TextView six_text;
  private TextView seven_text;
  private TextView eight_text;
  private TextView nine_text;
  private TextView ten_text;
  private RelativeLayout remind_time_layout;
  private TextView remind_time_text;
  private LinearLayout remind_time_hide_layout;
  private TextView time_eight_text;
  private TextView time_nine_text;
  private TextView time_ten_text;
  private RelativeLayout remind_music_layout;
  private RelativeLayout password_set_layout;
  private boolean bl_plan;
  private boolean bl_memo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_set);
    initView();
    initData();
    initListener();
  }

  @Override
  protected void onPause() {
    // TODO Auto-generated method stub
    super.onPause();
    PlanService.instance().WriteVal("plan_remind", bl_plan);
    PlanService.instance().WriteVal("memo_remind", bl_memo);
    PlanService.instance().WriteVal("remind_way", remind_way_text.getText().toString());
    PlanService.instance().WriteVal("plan_count", plan_count_text.getText().toString());
    PlanService.instance().WriteVal("remind_time", remind_time_text.getText().toString());
    PlanService.instance().SaveParams();
  }

  private void initListener() {
    plan_is_remind_layout.setOnClickListener(this);
    memo_is_remind_layout.setOnClickListener(this);
    remind_way_layout.setOnClickListener(this);
    ring_text.setOnClickListener(this);
    shake_text.setOnClickListener(this);
    plan_count_layout.setOnClickListener(this);
    five_text.setOnClickListener(this);
    six_text.setOnClickListener(this);
    seven_text.setOnClickListener(this);
    eight_text.setOnClickListener(this);
    nine_text.setOnClickListener(this);
    ten_text.setOnClickListener(this);
    remind_time_layout.setOnClickListener(this);
    time_eight_text.setOnClickListener(this);
    time_nine_text.setOnClickListener(this);
    time_ten_text.setOnClickListener(this);
    remind_music_layout.setOnClickListener(this);
    password_set_layout.setOnClickListener(this);
  }

  private void initData() {
    bl_plan = PlanService.instance().ReadVal("plan_remind", true);
    bl_memo = PlanService.instance().ReadVal("memo_remind", true);
    if (bl_plan) {
      plan_is_remind_image.setBackgroundResource(R.drawable.set_select);
    } else {
      plan_is_remind_image.setBackgroundResource(R.drawable.set_unselect);
    }

    if (bl_memo) {
      memo_is_remind_image.setBackgroundResource(R.drawable.set_select);
    } else {
      memo_is_remind_image.setBackgroundResource(R.drawable.set_unselect);
    }
    remind_way_text.setText(PlanService.instance().ReadVal("remind_way", "响铃"));
    plan_count_text.setText(PlanService.instance().ReadVal("plan_count", "5条计划"));
    remind_time_text.setText(PlanService.instance().ReadVal("remind_time", "晚上8点"));

  }

  private void initView() {
    plan_is_remind_layout = (RelativeLayout) findViewById(R.id.plan_is_remind_layout);
    plan_is_remind_image = (ImageView) findViewById(R.id.plan_is_remind_image);
    memo_is_remind_layout = (RelativeLayout) findViewById(R.id.memo_is_remind_layout);
    memo_is_remind_image = (ImageView) findViewById(R.id.memo_is_remind_image);
    remind_way_layout = (RelativeLayout) findViewById(R.id.remind_way_layout);
    remind_way_text = (TextView) findViewById(R.id.remind_way_text);
    remind_way_hide_layout = (LinearLayout) findViewById(R.id.remind_way_hide_layout);
    plan_count_layout = (RelativeLayout) findViewById(R.id.plan_count_layout);
    plan_count_text = (TextView) findViewById(R.id.plan_count_text);
    plan_count_hide_layout = (LinearLayout) findViewById(R.id.plan_count_hide_layout);
    five_text = (TextView) findViewById(R.id.five_text);
    six_text = (TextView) findViewById(R.id.six_text);
    seven_text = (TextView) findViewById(R.id.seven_text);
    eight_text = (TextView) findViewById(R.id.eight_text);
    nine_text = (TextView) findViewById(R.id.nine_text);
    ten_text = (TextView) findViewById(R.id.ten_text);
    remind_time_layout = (RelativeLayout) findViewById(R.id.remind_time_layout);
    remind_time_text = (TextView) findViewById(R.id.remind_time_text);
    remind_time_hide_layout = (LinearLayout) findViewById(R.id.remind_time_hide_layout);
    time_eight_text = (TextView) findViewById(R.id.time_eight_text);
    time_nine_text = (TextView) findViewById(R.id.time_nine_text);
    time_ten_text = (TextView) findViewById(R.id.time_ten_text);
    ring_text = (TextView) findViewById(R.id.ring_text);
    shake_text = (TextView) findViewById(R.id.shake_text);
    remind_music_layout = (RelativeLayout) findViewById(R.id.remind_music_layout);
    password_set_layout = (RelativeLayout) findViewById(R.id.password_set_layout);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.remind_way_layout:
        remind_way_layout.setVisibility(View.GONE);
        remind_way_hide_layout.setVisibility(View.VISIBLE);

        break;
      case R.id.ring_text:
        remind_way_text.setText("响铃");
        remind_way_layout.setVisibility(View.VISIBLE);
        remind_way_hide_layout.setVisibility(View.GONE);
        break;
      case R.id.shake_text:
        remind_way_text.setText("振动");
        remind_way_layout.setVisibility(View.VISIBLE);
        remind_way_hide_layout.setVisibility(View.GONE);
        break;
      case R.id.plan_is_remind_layout:
        if (bl_plan) {
          plan_is_remind_image.setBackgroundResource(R.drawable.set_unselect);
          bl_plan = false;
        } else {
          plan_is_remind_image.setBackgroundResource(R.drawable.set_select);
          bl_plan = true;
        }
        break;
      case R.id.memo_is_remind_layout:
        if (bl_memo) {
          memo_is_remind_image.setBackgroundResource(R.drawable.set_unselect);
          bl_memo = false;
        } else {
          memo_is_remind_image.setBackgroundResource(R.drawable.set_select);
          bl_memo = true;
        }

        break;

      case R.id.five_text:
        plan_count_text.setText("5条计划");
        plan_count_hide_layout.setVisibility(View.GONE);
        plan_count_layout.setVisibility(View.VISIBLE);
        break;
      case R.id.six_text:
        plan_count_text.setText("6条计划");
        plan_count_hide_layout.setVisibility(View.GONE);
        plan_count_layout.setVisibility(View.VISIBLE);
        break;
      case R.id.seven_text:
        plan_count_text.setText("7条计划");
        plan_count_hide_layout.setVisibility(View.GONE);
        plan_count_layout.setVisibility(View.VISIBLE);
        break;
      case R.id.eight_text:
        plan_count_text.setText("8条计划");
        plan_count_hide_layout.setVisibility(View.GONE);
        plan_count_layout.setVisibility(View.VISIBLE);
        break;
      case R.id.nine_text:
        plan_count_text.setText("9条计划");
        plan_count_hide_layout.setVisibility(View.GONE);
        plan_count_layout.setVisibility(View.VISIBLE);
        break;
      case R.id.ten_text:
        plan_count_text.setText("10条计划");
        plan_count_hide_layout.setVisibility(View.GONE);
        plan_count_layout.setVisibility(View.VISIBLE);
        break;
      case R.id.plan_count_layout:
        plan_count_layout.setVisibility(View.GONE);
        plan_count_hide_layout.setVisibility(View.VISIBLE);
        break;
      case R.id.remind_time_layout:
        remind_time_layout.setVisibility(View.GONE);
        remind_time_hide_layout.setVisibility(View.VISIBLE);
        break;
      case R.id.time_eight_text:
        remind_time_text.setText("晚上8点");
        remind_time_layout.setVisibility(View.VISIBLE);
        remind_time_hide_layout.setVisibility(View.GONE);
        break;
      case R.id.time_nine_text:
        remind_time_text.setText("晚上9点");
        remind_time_layout.setVisibility(View.VISIBLE);
        remind_time_hide_layout.setVisibility(View.GONE);
        break;
      case R.id.time_ten_text:
        remind_time_text.setText("晚上10点");
        remind_time_layout.setVisibility(View.VISIBLE);
        remind_time_hide_layout.setVisibility(View.GONE);
        break;
      case R.id.remind_music_layout:
        Toast.makeText(SetActivity.this, "点击了提醒音乐设置", Toast.LENGTH_LONG).show();
        break;
      case R.id.password_set_layout:
        Toast.makeText(SetActivity.this, "点击了密码设置", Toast.LENGTH_LONG).show();
        break;
      default:
        break;
    }

  }
}
