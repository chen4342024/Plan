package com.cc.view;

import com.cc.activity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class MyWallPaper extends TextView {
  private Context context;
  private int _xDelta = 0;
  private int _yDelta = 0;
  private RelativeLayout parentLayout;
  
	public MyWallPaper(Context context,RelativeLayout layout) {
		this(context, null,layout);
	}

	public MyWallPaper(Context context, AttributeSet attrs,RelativeLayout layout) {
		super(context, attrs);
		this.context = context;
		this.parentLayout = layout;
		initWallPaper();
	}
	
	private void initWallPaper(){
	  this.setHeight(100);
	  this.setWidth(30);
	  this.setPadding(30, 10, 45, 10);
	  this.setGravity(Gravity.CENTER);
	  this.setBackgroundResource(R.drawable.wallpaper_x2);
	  this.setOnTouchListener(wallPaperTouchListener);
	  Log.i("parentLayout", "width"+parentLayout.getWidth());
	  Log.i("parentLayout", "height"+parentLayout.getHeight());
	}
	
	private OnTouchListener wallPaperTouchListener = new OnTouchListener() {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
      final int X = (int) event.getRawX();
      final int Y = (int) event.getRawY();
      
      switch (event.getAction() & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:
          RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
          _xDelta = X - lParams.leftMargin;
          _yDelta = Y - lParams.topMargin;
          break;
        case MotionEvent.ACTION_UP:
          break;
        case MotionEvent.ACTION_POINTER_DOWN:
          break;
        case MotionEvent.ACTION_POINTER_UP:
          break;
        case MotionEvent.ACTION_MOVE:
          RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
          
          int leftMargin = X - _xDelta;
          int topMargin = Y - _yDelta;
          
          int maxLeftMargin = parentLayout.getWidth() - v.getWidth();
          int maxTopMargin = parentLayout.getHeight() - v.getHeight();
          
          leftMargin = caluMargin(leftMargin, 0, maxLeftMargin);
          topMargin = caluMargin(topMargin, 0, maxTopMargin);
          
          layoutParams.leftMargin = leftMargin;
          layoutParams.topMargin = topMargin;
         
          v.setLayoutParams(layoutParams);
          break;
      }
      // _root.invalidate();
      return true;
    }
  };

  //限制margin不要超过范围
  private int caluMargin(int margin,int minMargin,int maxMargin){
    if (minMargin > maxMargin) {
      return -1;
    }
    margin = (margin < maxMargin)?margin:maxMargin;
    margin = (margin > 0)?margin:0;
    return margin ;
  }
	/**
	 * 设置图标资源
	 */
	public void setImageResource(int resId) {
		this.setBackgroundResource(resId);
	}

	/**
	 * 设置显示的文字
	 */
	
	
}

