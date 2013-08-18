package com.cc.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DailyPlanDao {
  private static final String TAG = "DailyPlanDao";

  public static List<DailyPlan> readAll(Context context,String selection, String[] selectionArgs, String orderBy, String limit) {
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
    return dailyPlanList;
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
