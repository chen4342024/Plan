package com.cc.memorandum.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cc.activity.R;
import com.cc.db.Memorandum;
import com.cc.db.MemorandumDao;
import com.cc.util.DateService;

public class AddMemorandumActivity extends Activity {
	private EditText titleEdit = null;
	private TextView setTimeTxt = null;
	private Button setTimeBut = null;
	private EditText contentEdit = null;
	private Button okBut = null;
	private Button cancelBut = null;
	private MemorandumDao memoDao= new MemorandumDao();

	private Calendar calendar = Calendar.getInstance();
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	private DatePicker datePicker = null;
	private TimePicker timePicker = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memorandum_addview);
		titleEdit = (EditText) findViewById(R.id.titleEdit);
		setTimeTxt = (TextView) findViewById(R.id.setTimeTxt);
		setTimeBut = (Button) findViewById(R.id.setTimeBut);
		contentEdit = (EditText) findViewById(R.id.contentEdit);
		okBut = (Button) findViewById(R.id.okBut);
		cancelBut = (Button) findViewById(R.id.cancelBut);

		setTimeTxt.setText(sdf.format(calendar.getTime()));
		MyListener listener = new MyListener();
		setTimeBut.setOnClickListener(listener);
		okBut.setOnClickListener(listener);
		cancelBut.setOnClickListener(listener);
		
	}
	
	class MyListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(v==cancelBut){
				Intent intent = new Intent(AddMemorandumActivity.this,
						MemoListActivity.class);
				startActivity(intent);
			}else if(v==setTimeBut){
				Log.i("test","setTimeBtn Click");
				Builder builder  = new Builder(AddMemorandumActivity.this);
				builder.setTitle("选择时间");
				LinearLayout settimeView = (LinearLayout)getLayoutInflater().inflate(R.layout.settime, null);
				timePicker = (TimePicker)settimeView.findViewById(R.id.timepicker);
				datePicker =(DatePicker)settimeView.findViewById(R.id.datepicker);
				timePicker.setIs24HourView(true);
				builder.setView(settimeView);
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
						setTimeTxt.setText(sdf.format(calendar.getTime()));
					}
				});
				builder.setNegativeButton("取消", null);
				builder.show();
			}else if(v==okBut){
				List<Memorandum> memoList = new ArrayList<Memorandum>();
				memoList = memoDao.readAll(AddMemorandumActivity.this, null, null, Memorandum.INIT_TIME+" desc");
				String titleStr = titleEdit.getText().toString();
				if(titleStr== null || "".equals(titleStr.trim())){
					titleStr = "(无标题)";
				}
				String timeStr = setTimeTxt.getText().toString();
				String contentStr = contentEdit.getText().toString();
				Memorandum memo = new Memorandum();
				memo.setTitle(titleStr);
				memo.setFinish(false);
				memo.setContent(contentStr);
				memo.setInitTime(DateService.getInstance().parse(timeStr, DateService.DATE_FORMAT_TWO));
				memoDao.addMemorandum(AddMemorandumActivity.this, memo);
				
			}
		}
		
	}
}
