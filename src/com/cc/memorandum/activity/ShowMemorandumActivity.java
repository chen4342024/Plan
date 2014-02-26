package com.cc.memorandum.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.cc.activity.R;
import com.cc.db.Memorandum;
import com.cc.db.MemorandumDao;
import com.cc.util.DateService;

public class ShowMemorandumActivity extends Activity {
	private MemorandumDao memoDao = new MemorandumDao();
	private int memoId=-1;
	private TextView showMemoTitleText;
	private TextView showMemoContentText;
	private TextView showMemoInitTimeText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showmemorandum);
		Bundle bundle= getIntent().getExtras();
		memoId = bundle.getInt("memoId");
		Memorandum memo = memoDao.findById(this, memoId);
		showMemoTitleText= (TextView)findViewById(R.id.showMemoTitleText);
		showMemoContentText = (TextView)findViewById(R.id.showMemoContentText);
		showMemoInitTimeText=(TextView)findViewById(R.id.showMemoInitTimeText);
		
		if(memo!=null){
			showMemoTitleText.setText(memo.getTitle());
			showMemoContentText.setText(memo.getContent());
			showMemoInitTimeText.setText(DateService.getInstance().formatDateByDefault(memo.getInitTime()));
		}
	}
}
