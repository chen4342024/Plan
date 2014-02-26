package com.cc.db;

import java.util.ArrayList;
import java.util.List;

import com.cc.util.DateService;

import android.content.ContentValues;
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
		 db.close();
		return list;
	}
	
	public Memorandum findById(Context context,int id){
		DBHelper dbHelper = new DBHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(Memorandum.TABLE_NAME, null, Memorandum.ID+" = ?", new String[]{id+""},null, null, null);
		while (cursor.moveToNext()) {
			return cursorToMemorandum(cursor);
		}
		return null;
	}
	public Memorandum addMemorandum(Context context,Memorandum memo){
		ContentValues values = MemorandumToContentValues(memo);
		DBHelper dbHelper = new DBHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rows= db.insert(Memorandum.TABLE_NAME, null, values);
		
		if(rows!=-1){
			memo= readMemorandum(db, rows);
		}
		db.close();
		return memo;
	}
	
	public boolean deleteMemorandum(Context context,int id){
		DBHelper dbHelper = new DBHelper(context);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int row= db.delete(Memorandum.TABLE_NAME, Memorandum.ID, new String[]{id+""});
		db.close();
		return row==1;
	}
	
	private static Memorandum cursorToMemorandum(Cursor cursor){
		Memorandum memo = new Memorandum();
		memo.setId(cursor.getInt(cursor.getColumnIndex(Memorandum.ID)));
		memo.setTitle(cursor.getString(cursor.getColumnIndex(Memorandum.TITLE)));
		memo.setContent(cursor.getString(cursor.getColumnIndex(Memorandum.CONTENT)));
		memo.setImportance(cursor.getInt(cursor.getColumnIndex(Memorandum.IMPORTANCE)));
		String initTimeStr = cursor.getString(cursor.getColumnIndex(Memorandum.INIT_TIME));
		memo.setInitTime(DateService.getInstance().parseByDefaultPattern(initTimeStr));
		memo.setFinish(cursor.getInt(cursor.getColumnIndex(Memorandum.IS_FINISH))==0?false:true);
		String remindTimeStr = cursor.getString(cursor.getColumnIndex(Memorandum.REMIND_TIME));
		if(remindTimeStr!=null && !"".equals(remindTimeStr))
			memo.setRemindTime(DateService.getInstance().parseByDefaultPattern(remindTimeStr));
		return memo;
	}
	
	private static ContentValues MemorandumToContentValues(Memorandum memo){
		ContentValues  values= new ContentValues();
		values.put(Memorandum.TITLE, memo.getTitle());
		values.put(Memorandum.CONTENT, memo.getContent());
		values.put(Memorandum.IMPORTANCE, memo.getImportance());
		values.put(Memorandum.INIT_TIME, DateService.getInstance().formatDateByDefault(memo.getInitTime()));
		values.put(Memorandum.IS_FINISH, memo.isFinish()? 1:0);
		values.put(Memorandum.REMIND_TIME, memo.getRemindTime()==null?null:DateService.getInstance().formatDateByDefault(memo.getRemindTime()));
		return values;
	}
	
	private static Memorandum readMemorandum(SQLiteDatabase db,long id){
		Memorandum memo= null;
		 String sql = "select * from " + Memorandum.TABLE_NAME + " where " + Memorandum.ID +  " = " + id;
		 Cursor cursor = db.rawQuery(sql, null);
		 while(cursor.moveToNext()){
			 memo= cursorToMemorandum(cursor);
		 }
		 return memo;
	}
}
