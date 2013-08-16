package com.cc.view;

import com.cc.activity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class MyRadiobutton extends RelativeLayout {
	private ImageView iv;
	private TextView tv;
	private int iSel = -1;
	private int sel_resID = 0;
	private int no_sel_resID = 0;
	private int old_iSel = -1;

	public MyRadiobutton(Context context) {
		super(context, null);
	}

	public MyRadiobutton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context)
				.inflate(R.layout.myradiobutton, this, true);
		iv = (ImageView) findViewById(R.id.iv_up);
		tv = (TextView) findViewById(R.id.tv_up);
	}

	/**
	 * 设置图标资源
	 */
	public void setImageResource(int sel_Id, int no_sel_Id) {
		// iv.setImageResource(resId);
		sel_resID = sel_Id;
		no_sel_resID = no_sel_Id;
	}

	/**
	 * 设置显示的文字
	 */
	public void setTextViewText(String text) {
		tv.setText(text);
	}

	public void setTextViewTextColor(int color) {
		tv.setTextColor(color);
	}

	public void setBkcolor(int color) {
		this.setBackgroundColor(color);
	}

	public void setCheck(int ic) {
		iSel = ic;
		if (old_iSel != iSel) {
			old_iSel = iSel;
			if (iSel == 1) {
				iv.setImageResource(sel_resID);
				this.setBackgroundResource(R.drawable.radio_sel);
			} else {
				iv.setImageResource(no_sel_resID);
				this.setBackgroundResource(R.drawable.none);
			}
		}
	}

	public int isCheck() {
		return iSel;
	}
}

