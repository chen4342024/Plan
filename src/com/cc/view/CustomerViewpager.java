package com.cc.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cc.activity.R;

public class CustomerViewpager extends LinearLayout {

  private ViewPager mPager;
  private List<View> listViews;
  private List<View> viewsToAdd;

  private int currentPage = 0;
  private int firstPage = 0;
  private int lastPage = 0;
  private List<ImageView> dotImageViews;
  
  private int offset = 0;
  private int currIndex = 1;
  private int bmpW;

  private Context context;

  public CustomerViewpager(Context context, List<View> views) {
    this(context, null, views);
  }

  public CustomerViewpager(Context context, AttributeSet attrs, List<View> views) {
    super(context, attrs);
    this.context = context;
    this.viewsToAdd = views;
    LayoutInflater.from(context).inflate(R.layout.customer_viewpage, this, true);
    InitViewPager();
  }

  private void InitViewPager() {
    mPager = (ViewPager) findViewById(R.id.customerPager);
    listViews = new ArrayList<View>();
    LayoutInflater mInflater = LayoutInflater.from(context);
    listViews.add(mInflater.inflate(R.layout.lay4, null));
    for (View view : this.viewsToAdd) {
      listViews.add(view);
    }
    listViews.add(mInflater.inflate(R.layout.lay4, null));
    mPager.setAdapter(new MyPagerAdapter(listViews));
    mPager.setCurrentItem(1);
    mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    initPage();
  }
  
  private void initPage(){
    this.firstPage = 0;
    this.lastPage = listViews.size()-1;//因为索引从0开始，故需要减一
    this.currentPage = 1;
    initDotsView(currentPage);
  }
  
  /**
   * 初始化导航小圆点
   * @param currentPage
   */
  private void initDotsView(int currentPage) {
    LinearLayout showPageLayout = (LinearLayout) findViewById(R.id.showPage);
    dotImageViews = new ArrayList<ImageView>();
    int allPageView = this.lastPage - 1;//减去第0页
    int currentPageViewIndex = currentPage - 1;
    for (int i = 0; i < allPageView; i++) {
      ImageView dot = new ImageView(context);
      if (i == currentPageViewIndex) {
        dot.setBackgroundResource(R.drawable.page_now);
      }else {
        dot.setBackgroundResource(R.drawable.page);
      }
      dotImageViews.add(dot);
    }
    for (ImageView dot : dotImageViews) {
      showPageLayout.setBackgroundColor(Color.rgb(214, 211, 214));
      showPageLayout.addView(dot);
    }
  }
  
  private void resetDotsView(int currentPage){
    int dotIndex = currentPage - 1;
    for (int i = 0; i < dotImageViews.size(); i++) {
      if(i == dotIndex){
        dotImageViews.get(i).setBackgroundResource(R.drawable.page_now);
      }else {
        dotImageViews.get(i).setBackgroundResource(R.drawable.page);
      }
    }
  }

  public class MyPagerAdapter extends PagerAdapter {
    public List<View> mListViews;

    public MyPagerAdapter(List<View> mListViews) {
      this.mListViews = mListViews;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
      ((ViewPager) arg0).removeView(mListViews.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    @Override
    public int getCount() {
      return mListViews.size();
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
      ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
      return mListViews.get(arg1);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
      return arg0 == (arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
      return null;
    }

    @Override
    public void startUpdate(View arg0) {
    }
  }

  public class MyOnPageChangeListener implements OnPageChangeListener {

    int one = offset * 2 + bmpW;
    int two = one * 2;

    @Override
    public void onPageSelected(int arg0) {
      System.out.println("--- " + arg0);
      if (arg0 == firstPage) {
        mPager.setCurrentItem(arg0 + 1);
        currIndex = arg0 + 1;
      } else if (arg0 == lastPage) {
        mPager.setCurrentItem(arg0 - 1);
        currIndex = arg0 - 1;
      } else {
        currIndex = arg0;
      }
      resetDotsView(currIndex);
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }
  }
}
