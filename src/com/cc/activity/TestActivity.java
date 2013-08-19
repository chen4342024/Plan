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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestActivity extends Activity {
  private MyImageText myImageText;
  private TextView t2;
	private LinearLayout layout;
	private Button button;
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
		
		layout=(LinearLayout)findViewById(R.id.layout);
		button=(Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				TextView textView=new TextView(TestActivity.this);
				textView.setBackgroundResource(R.drawable.navigationbar);
				textView.setText("这是佳佳加加++++");
				textView.setGravity(Gravity.CENTER);
				layout.addView(textView);
				
			}
		});
		
		
		
	}
}
