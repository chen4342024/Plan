package com.cc.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanActivity extends Activity {
	private ViewPager mPager;
	private List<View> listViews;
	private ImageView cursor;
	private TextView t1, t2, t3;
	private int offset = 0;
	private int currIndex = 1;
	private int bmpW;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan);
		InitImageView();
		InitTextView();
		InitViewPager();
	}

	private void InitTextView() {
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		t3 = (TextView) findViewById(R.id.text3);

		t1.setOnClickListener(new MyOnClickListener(1));
		t2.setOnClickListener(new MyOnClickListener(2));
		t3.setOnClickListener(new MyOnClickListener(3));
	}

	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.lay4, null));
		listViews.add(mInflater.inflate(R.layout.lay1, null));
		listViews.add(mInflater.inflate(R.layout.lay2, null));
		listViews.add(mInflater.inflate(R.layout.lay3, null));
		listViews.add(mInflater.inflate(R.layout.lay4, null));
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(1);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	
	private void InitImageView() {
		cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
				.getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / 3 - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);

		cursor.setImageMatrix(matrix);
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

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;
		int two = one * 2;

		@Override
		public void onPageSelected(int arg0) {
			System.out.println("--- " + arg0);
			Animation animation = null;
			switch (arg0) {
			case 0:

				mPager.setCurrentItem(arg0 + 1);
				break;
			case 4:

				mPager.setCurrentItem(arg0 - 1);
				break;
			case 1:
				if (currIndex == 2) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 1) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 3:
				if (currIndex == 1) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				break;
			}
			if (arg0 == 0) {
				currIndex = arg0 + 1;
			} else if (arg0 == 4) {
				currIndex = arg0 - 1;
			} else {
				currIndex = arg0;
			}

			if (animation != null) {
				animation.setFillAfter(true);
				animation.setDuration(300);
				cursor.startAnimation(animation);
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

}
