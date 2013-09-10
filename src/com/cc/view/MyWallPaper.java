package com.cc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.cc.activity.R;



public class MyWallPaper extends TextView {
  private Context context;
  private int _xDelta = 0;
  private int _yDelta = 0;
  private RelativeLayout parentLayout;
  
  private boolean isMove;
  private boolean isLongClick;
  
  private int uniqueId = 0 ;
  
  private WallPagerListener listener;
  
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
	  this.setPadding(30, 10, 45, 10);
	  this.setGravity(Gravity.CENTER);
	  this.setBackgroundResource(R.drawable.wallpaper_x2);
	  this.setOnTouchListener(wallPaperTouchListener);
	  this.setOnLongClickListener(longClickListener);
	  Log.i("parentLayout", "width"+parentLayout.getWidth());
	  Log.i("parentLayout", "height"+parentLayout.getHeight());
	}
	
	public void setPosition(int leftMargin,int topMargin){
	  RelativeLayout.LayoutParams layoutParams = (LayoutParams) this.getLayoutParams();
	  if (layoutParams == null) {
      layoutParams = new LayoutParams(this.getBackground().getMinimumWidth(), this.getBackground().getMinimumHeight());
    }
	  int maxLeftMargin = parentLayout.getWidth() - this.getWidth();
    int maxTopMargin = parentLayout.getHeight() - this.getHeight();
    
    leftMargin = caluMargin(leftMargin, 0, maxLeftMargin);
    topMargin = caluMargin(topMargin, 0, maxTopMargin);
    
	  layoutParams.leftMargin = leftMargin;
	  layoutParams.topMargin = topMargin;
	  
	  this.setLayoutParams(layoutParams);
	}
	
	private OnLongClickListener longClickListener = new OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
      if (!isMove) {
        Log.i("event", "onLongClick");
        isLongClick = true;
        if (listener != null) {
          listener.deleteWallPaper(getUniqueId(),v);
        }
      }
      return false;
    }
  };
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
          Log.i("event", "ACTION_DOWN");
          break;
        case MotionEvent.ACTION_UP:
          Log.i("event", "ACTION_UP");
          isMove = false;
          isLongClick = false;
          
          if (listener != null) {
            RelativeLayout.LayoutParams l = (RelativeLayout.LayoutParams) v.getLayoutParams();
            listener.updatePosition(getUniqueId(),l.leftMargin, l.topMargin);
          }
          break;
        case MotionEvent.ACTION_MOVE:
          if (isLongClick) {
            break;
          }
          Log.i("event", "ACTION_MOVE");
          isMove = true;
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
          return true;
        default:
        break;
      }
      // _root.invalidate();
      return false;
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

  public WallPagerListener getListener() {
    return listener;
  }

  public void setListener(WallPagerListener listener) {
    this.listener = listener;
  }

  public int getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(int uniqueId) {
    this.uniqueId = uniqueId;
  }
}

