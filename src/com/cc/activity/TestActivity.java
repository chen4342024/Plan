package com.cc.activity;
import java.util.List;
import com.cc.db.DailyPlan;
import com.cc.db.DailyPlanDao;
import com.cc.view.MyImageText;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestActivity extends Activity {
  private MyImageText myImageText;
  private TextView t2;
	private FrameLayout layout;
	private Button button;
	private static int i = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
//		TextView t1 = (TextView) findViewById(R.id.test1);
//		List<DailyPlan> dailyPlans = DailyPlanDao.readAll(this, null, null, null, null);
//		t1.setText("123123");
//		myImageText = (MyImageText) findViewById(R.id.image_text);
//		myImageText.setImageResource(R.drawable.cloud);
//		myImageText.setTextViewText("这个只是测试而已。。不要当�);
//		t2 = new TextView(this);
//		t2.setText("测试测试测试测试");
//		t2.setBackgroundResource(R.drawable.cloud);
		
		layout=(FrameLayout)findViewById(R.id.layout);
		button=(Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {  
				TextView textView=new TextView(TestActivity.this);
				textView.setBackgroundResource(R.drawable.wallpaper_x2);
				textView.setText("今天是个好日子,今天是个好日子,今天是个好日子今天是今天是个好日子今天是个好日。今天是个好日子。今天是个好日子"+i+i+i);
				i++;
				textView.setHeight(200);
				textView.setWidth(80);
				textView.setPadding(40, 10, 55, 10);
				LayoutParams layoutParams = new LayoutParams(200,300);
				
				textView.setLayoutParams(layoutParams);
				textView.setGravity(Gravity.CENTER);
				
				layout.addView(textView);
				
			}
		});
		
		
		
	}
}
