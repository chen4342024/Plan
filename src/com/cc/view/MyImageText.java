package com.cc.view;

import com.cc.activity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MyImageText extends FrameLayout{
  private ImageView iv;
  private TextView tv;
  public MyImageText(Context context) {
    super(context,null);

  }
  public MyImageText(Context context, AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater.from(context)
        .inflate(R.layout.myimagetext, this, true);
    iv = (ImageView) findViewById(R.id.imagetext_image);
    tv = (TextView) findViewById(R.id.imagetext_text);
  }
  
  /**
   * 设置图标资源
   */
  public void setImageResource(int resId) {
     iv.setImageResource(resId);
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

}
