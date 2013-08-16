package com.cc.activity;

import com.cc.view.MyRadiobutton;

import android.os.Bundle;

import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity implements OnClickListener {

	
	private MyRadiobutton mplanA = null;
	private MyRadiobutton mplanB  = null;
	private MyRadiobutton mplanC  = null;
	private MyRadiobutton mplanD  = null;
	public static TabHost mTabhost = null;
	public static final String TAB_SET = "SETTING";
	public static final String TAB_CALL = "CALL";
	public static final String TAB_ALARM = "ALARM";
	public static final String TAB_STHOME = "STHOME";
	public static final String TAB_SAFE_SET = "SAFE_SET";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mplanA = (MyRadiobutton) findViewById(R.id.radio_button0);
		mplanA.setImageResource(R.drawable.call2, R.drawable.call1);
		mplanA.setTextViewText(getResources().getString(R.string.call));
		mplanA.setOnClickListener(this);
		mplanA.setCheck(1);

		mplanB = (MyRadiobutton) findViewById(R.id.radio_button1);
		mplanB.setImageResource(R.drawable.alarm2, R.drawable.alarm1);
		mplanB.setTextViewText(getResources().getString(R.string.alarm));
		mplanB.setOnClickListener(this);
		mplanB.setCheck(0);

		mplanC = (MyRadiobutton) findViewById(R.id.radio_button2);
		mplanC.setImageResource(R.drawable.sthome2, R.drawable.sthome1);
		mplanC.setTextViewText(getResources().getString(R.string.sthome));
		mplanC.setOnClickListener(this);
		mplanC.setCheck(0);

		mplanD = (MyRadiobutton) findViewById(R.id.radio_button3);
		mplanD.setImageResource(R.drawable.set2, R.drawable.set1);
		mplanD.setTextViewText(getResources().getString(R.string.setting));
		mplanD.setOnClickListener(this);
		mplanD.setCheck(0);

		mTabhost = getTabHost();

		TabSpec ts1 = mTabhost.newTabSpec(TAB_CALL).setIndicator(TAB_CALL);
		ts1.setContent(new Intent(this, PlanActivity.class));
		mTabhost.addTab(ts1);

		TabSpec ts2 = mTabhost.newTabSpec(TAB_ALARM).setIndicator(TAB_ALARM);
		ts2.setContent(new Intent(this, TestActivity.class));
		mTabhost.addTab(ts2);

		TabSpec ts3 = mTabhost.newTabSpec(TAB_STHOME).setIndicator(TAB_STHOME);
		ts3.setContent(new Intent(this, TestActivity.class));
		mTabhost.addTab(ts3);

		TabSpec ts4 = mTabhost.newTabSpec(TAB_SET).setIndicator(TAB_SET);
		ts4.setContent(new Intent(this, TestActivity.class));
		mTabhost.addTab(ts4);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.radio_button0:
			selRaido(0);
			break;
		case R.id.radio_button1:
			selRaido(1);
			break;
		case R.id.radio_button2:
			selRaido(2);
			break;
		case R.id.radio_button3:
			selRaido(3);
			break;
		}

	}

	public void selRaido(int id) {

		if (id == 0) {
			mplanA.setCheck(1);
			mplanB.setCheck(0);
			mplanC.setCheck(0);
			mplanD.setCheck(0);

			mTabhost.setCurrentTabByTag(TAB_CALL);
		} else if (id == 1) {
			mplanA.setCheck(0);
			mplanB.setCheck(1);
			mplanC.setCheck(0);
			mplanD.setCheck(0);

			mTabhost.setCurrentTabByTag(TAB_ALARM);
		} else if (id == 2) {
			mplanA.setCheck(0);
			mplanB.setCheck(0);
			mplanC.setCheck(1);
			mplanD.setCheck(0);

			mTabhost.setCurrentTabByTag(TAB_STHOME);
		} else if (id == 3) {
			mplanA.setCheck(0);
			mplanB.setCheck(0);
			mplanC.setCheck(0);
			mplanD.setCheck(1);

			mTabhost.setCurrentTabByTag(TAB_SET);
		}

	}

}
