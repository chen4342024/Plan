package com.cc.db;

import java.util.ArrayList;
import java.util.List;

import com.cc.util.DateService;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MemorandumDao {
	public List<Memorandum> readAll(Context context,String selection, String[] selectionArgs, String orderBy){
		DBHelper dbHelper = new DBHelper(context);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		List<Memorandum> list = new ArrayList<Memorandum>();
		db= dbHelper.getReadableDatabase();
		cursor= db.query(Memorandum.TABLE_NAME, null, selection, selectionArgs, null, null, orderBy);
		 while(cursor.moveToNext()){
			 list.add(cursorToMemorandum(cursor));
		 }
		return list;
	}
	private Memorandum cursorToMemorandum(Cursor cursor){
		Memorandum memo = new Memorandum();
		memo.setId(cursor.getInt(cursor.getColumnIndex(Memorandum.ID)));
		memo.setTitle(cursor.getColumnName(cursor.getColumnIndex(Memorandum.TITLE)));
		memo.setContent(cursor.getString(cursor.getColumnIndex(Memorandum.CONTENT)));
		memo.setImportance(cursor.getInt(cursor.getColumnIndex(Memorandum.IMPORTANCE)));
		String initTimeStr = cursor.getString(cursor.getColumnIndex(Memorandum.INIT_TIME));
		memo.setInitTime(DateService.getInstance().parseByDefaultPattern(initTimeStr));
		memo.setFinish(cursor.getInt(cursor.getColumnIndex(Memorandum.IS_FINISH))==0?false:true);
		String remindTimeStr = cursor.getString(cursor.getColumnIndex(Memorandum.REMIND_TIME));
		memo.setRemindTime(DateService.getInstance().parseByDefaultPattern(remindTimeStr));
		return memo;
	}
}
