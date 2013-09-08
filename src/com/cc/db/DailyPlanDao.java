package com.cc.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cc.util.DateService;

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
  
  public static List<DailyPlan> readTodayPlan(Context context) {
    String selection = DailyPlan.INIT_TIME + " Like ?";
    String[] selectionArgs = new String[]{DateService.getInstance().TodayStr() + "%"};
    String orderBy = DailyPlan.INIT_TIME;
    return readAll(context, selection, selectionArgs, orderBy);
  }
  
  public static DailyPlan addDailyPlan(Context context,DailyPlan dailyPlan){
    ContentValues values = DailyPlanToContentValues(dailyPlan);
    DBHelper helper = new DBHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();
    long rowId = db.insert(DailyPlan.TABLE_NAME, null, values);
    DailyPlan daiPlan = null;
    if (rowId != -1) {
      //插入成功返回数据
      daiPlan = readDailyPlan(db,lastInsertId(db));
    }
    db.close();
    //不成功返回空对象
    return daiPlan;
  }
  
  public static void updatePagerPosition(Context context,List<DailyPlan> dailyPlans){
    DBHelper helper = new DBHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();
    String sql = null;
    for (DailyPlan dailyPlan : dailyPlans) {
      ContentValues values = new ContentValues();
      values.put(DailyPlan.X, dailyPlan.getX());
      values.put(DailyPlan.Y, dailyPlan.getY());
//      sql = "update " + DailyPlan.TABLE_NAME + "set " + DailyPlan.X + " where " + DailyPlan.ID + " = " + dailyPlan.getId();
      db.update(DailyPlan.TABLE_NAME, values, DailyPlan.ID + "=?", new String[]{dailyPlan.getId()+""});
    }
    db.close();
  }
  
  private static DailyPlan readDailyPlan(SQLiteDatabase db,long id){
    DailyPlan dailyPlan = new DailyPlan();
    String sql = "select * from " + DailyPlan.TABLE_NAME + " where " + DailyPlan.ID +  " = " + id;
    Cursor cursor = db.rawQuery(sql, null);
    while(cursor.moveToNext()){
      dailyPlan = cursorToDailyPlan(cursor, dailyPlan);
    }
    return dailyPlan;
  }
  
  
  private static int lastInsertId(SQLiteDatabase db){
    Cursor cursor = db.rawQuery("select last_insert_rowid() from "+DailyPlan.TABLE_NAME,null);
    cursor.moveToFirst();
    int id = cursor.getInt(0);
    return id;
  }
  
  private static DailyPlan cursorToDailyPlan(Cursor cursor,DailyPlan dailyPlan){
    dailyPlan.setId(cursor.getInt(cursor.getColumnIndex(DailyPlan.ID)));
    dailyPlan.setContent(cursor.getString(cursor.getColumnIndex(DailyPlan.CONTENT)));
    dailyPlan.setFinish((cursor.getInt(cursor.getColumnIndex(DailyPlan.IS_FINISH))==0)?false:true);
    String initTimeStr = cursor.getString(cursor.getColumnIndex(DailyPlan.INIT_TIME));
    dailyPlan.setInitTime(DateService.getInstance().parseByDefaultPattern(initTimeStr));
    dailyPlan.setX(cursor.getInt(cursor.getColumnIndex(DailyPlan.X)));
    dailyPlan.setY(cursor.getInt(cursor.getColumnIndex(DailyPlan.Y)));
    return dailyPlan;
  }
  
  private static ContentValues DailyPlanToContentValues(DailyPlan dailyPlan){
    ContentValues values = new ContentValues();
    String dateStr = DateService.getInstance().formatDateByDefault(dailyPlan.getInitTime());
    values.put(DailyPlan.INIT_TIME,dateStr);
    values.put(DailyPlan.IS_FINISH,dailyPlan.isFinish());
    values.put(DailyPlan.CONTENT,dailyPlan.getContent());
    values.put(DailyPlan.X, dailyPlan.getX());
    values.put(DailyPlan.Y, dailyPlan.getY());
    return values;
  }
  
}
