package com.cc.memorandum.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cc.activity.R;
import com.cc.db.Memorandum;
import com.cc.db.MemorandumDao;
import com.cc.util.DateService;

public class MemoListActivity extends Activity {
	
	private List<Memorandum> memoList = new ArrayList<Memorandum>();
	private ListView listView = null;
	private MyAdapter adapter = null;
	private ListView lv = null;

	private EditText searchEdit = null;
	private ImageButton searchBut = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memorandum_listview);
		MemorandumDao memoDao= new MemorandumDao();
		memoList=memoDao.readAll(this, null, null, Memorandum.INIT_TIME+" desc");
		
		listView = (ListView) findViewById(R.id.listview);
		adapter = new MyAdapter();
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MemoListActivity.this,
						ShowMemorandumActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
		});
		
		// 注册右键菜单
		registerForContextMenu(listView);

		// 搜索功能
		searchEdit = (EditText) findViewById(R.id.searchEdit);
		searchBut = (ImageButton) findViewById(R.id.searchBut);
		searchBut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String selection = Memorandum.TITLE+" like ? or "+Memorandum.CONTENT+" like ? ";
				String searchStr = searchEdit.getText().toString();
				String[] selectionValues ={searchStr,searchStr};
				String orderby = Memorandum.INIT_TIME+" desc";
				MemorandumDao memoDao= new MemorandumDao();
				memoList = memoDao.readAll(MemoListActivity.this, selection, selectionValues, orderby);
				adapter.notifyDataSetChanged();
			}

		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mainMenuItem = menu.add("主页");
		mainMenuItem.setIcon(R.drawable.ic_menu_home);
		mainMenuItem.setIntent(new Intent(this, MemoListActivity.class));

		MenuItem addMenuItem = menu.add("添加");
		addMenuItem.setIcon(R.drawable.ic_menu_add);
		addMenuItem.setIntent(new Intent(this, AddMemorandumActivity.class));

		MenuItem delsomeItem = menu.add("批量删除");
		delsomeItem.setIcon(R.drawable.ic_menu_delete);
		delsomeItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				return true;
			}
		});
		return true;
	}

	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return memoList.size();
		}

		@Override
		public Object getItem(int position) {
			return memoList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LinearLayout view = (LinearLayout) getLayoutInflater().inflate(
					R.layout.listview, null);

			TextView titleTxt = (TextView) view.findViewById(R.id.titleTxt);
			titleTxt.setText(memoList.get(position).getTitle());
			TextPaint tp = titleTxt.getPaint();
			tp.setFakeBoldText(true);

			TextView timeTxt = (TextView) view.findViewById(R.id.timeTxt);
			timeTxt.setText(DateService.getInstance().formatDateByDefault(memoList.get(position).getInitTime()));
			return view;
		}

	}
}
