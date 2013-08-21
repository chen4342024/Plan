package com.cc.view;







import com.cc.activity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CornerListView extends ListView {

    public CornerListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public CornerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    
    public CornerListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub

        switch (ev.getAction()) {
        
        case MotionEvent.ACTION_DOWN:
        	System.out.println("执行了按下");
            int x = (int) ev.getX();
            int y = (int) ev.getY();
            int itemnum = pointToPosition(x, y);

            if (itemnum == AdapterView.INVALID_POSITION){
            	System.out.println("偶尔执行下");
                break;
            }
            else {
                if (itemnum == 0) {
                	
                    if (itemnum == (getAdapter().getCount()-1)) {
                    	
                        setSelector(R.drawable.list_corner_round);
                    } else {
                        setSelector(R.drawable.list_corner_round_top);
                    }
                } else if (itemnum == (getAdapter().getCount() - 1))
                    setSelector(R.drawable.list_corner_round_bottom);
                else {
                    setSelector(R.drawable.list_corner_shape);
                }
            }

            break;
        case MotionEvent.ACTION_UP:
        	System.out.println("执行了");
            break;
          case MotionEvent.ACTION_MOVE:
        	
        		break;
        }
      
       return super.onInterceptTouchEvent(ev);
    }

    // 固定listview 的总体高度
    public void setListViewHeightBasedOnChildren() {
        ListAdapter listAdapter = getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight =0;

        int itemCount = listAdapter.getCount();
        if (0 == itemCount) {
            return;
        }

        for (int i = 0; i < itemCount; i++) {
            View listItem = listAdapter.getView(i, null, this);
            listItem.measure(0, 0);
            int itemHeight = listItem.getMeasuredHeight();
            totalHeight += itemHeight;
        }

        ViewGroup.LayoutParams params = getLayoutParams();
       
        params.height = totalHeight + (getDividerHeight() * (itemCount - 1));
        setLayoutParams(params);
    }


}
