package com.cc.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cc.util.DateService;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DailyPlanDao {
  private static final String TAG = "DailyPlanDao";

  public static List<DailyPlan> readAll(Context context,String selection, String[] selectionArgs, String orderBy) {
    DBHelper helper = new DBHelper(context);
    SQLiteDatabase db = null;
    Cursor cursor = null;
    List<DailyPlan> dailyPlanList = new ArrayList<DailyPlan>();
    db = helper.getReadableDatabase();
    cursor = db.query(DailyPlan.TABLE_NAME, null, selection, selectionArgs, null, null, orderBy);
    while(cursor.moveToNext()){
      DailyPlan dailyPlan = new DailyPlan();
      dailyPlanList.add(cursorToDailyPlan(cursor,dailyPlan));
    }
    db.close();
    return dailyPlanList;
  }
  
  public static boolean addDailyPlan(Context context,DailyPlan dailyPlan){
    ContentValues values = new ContentValues();
    String dateStr = DateService.getInstance().formatDateByDefault(dailyPlan.getInitTime());
    values.put(DailyPlan.INIT_TIME,dateStr);
    values.put(DailyPlan.IS_FINISH,dailyPlan.isFinish());
    values.put(DailyPlan.CONTENT,dailyPlan.getContent());
    DBHelper helper = new DBHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();
    long rowId = db.insert(DailyPlan.TABLE_NAME, null, values);
    return rowId != -1;
  }
  
  private static DailyPlan cursorToDailyPlan(Cursor cursor,DailyPlan dailyPlan){
    dailyPlan.setId(cursor.getLong(cursor.getColumnIndex(DailyPlan.ID)));
    dailyPlan.setContent(cursor.getString(cursor.getColumnIndex(DailyPlan.CONTENT)));
    dailyPlan.setFinish((cursor.getInt(cursor.getColumnIndex(DailyPlan.IS_FINISH))==0)?false:true);
    String initTimeStr = cursor.getString(cursor.getColumnIndex(DailyPlan.INIT_TIME));
    dailyPlan.setInitTime(new Date(initTimeStr));
    return dailyPlan;
  }
 

  
  
}
