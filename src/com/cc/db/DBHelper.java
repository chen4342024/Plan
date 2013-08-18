package com.cc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
  private static int Version = 1;
  private Context context = null;
  private static final String DB_NAME = "plan";
 
  // 日计划表
  private static final String DAILY_PLAN_CREATE = 
      "create table " + DailyPlan.TABLE_NAME
      + " ( " 
      + DailyPlan.ID + " INTEGER PRIMARY KEY,"
      + DailyPlan.INIT_TIME + " varchar(20),"
      + DailyPlan.CONTENT + " varchar(500)," 
      + DailyPlan.IS_FINISH + " varchar(1)" 
      + ")";
  //周计划表
  private static final String WEEKLY_PLAN_CREATE = 
      "create table " + WeeklyPlan.TABLE_NAME
      + " ( " 
      + WeeklyPlan.ID + " INTEGER PRIMARY KEY,"
      + WeeklyPlan.INIT_TIME + " varchar(20),"
      + WeeklyPlan.CONTENT + " varchar(500)," 
      + WeeklyPlan.PROGRESS + " INTEGER" 
      + ")";
  //短期/长期计划表
  private static final String TERM_PLAN_CREATE = 
      "create table " + TermPlan.TABLE_NAME
      + " ( " 
      + TermPlan.ID + " INTEGER PRIMARY KEY,"
      + TermPlan.INIT_TIME + " varchar(20),"
      + TermPlan.CONTENT + " varchar(500)," 
      + TermPlan.PROGRESS + " INTEGER" 
      + ")";
  //备忘录表
  private static final String MEMORANDUM_CREATE = 
      "create table " + Memorandum.TABLE_NAME
      + " ( " 
      + Memorandum.ID + " INTEGER PRIMARY KEY,"
      + Memorandum.INIT_TIME + " varchar(20),"
      + Memorandum.REMIND_TIME + " varchar(20),"
      + Memorandum.CONTENT + " varchar(500)," 
      + Memorandum.IS_FINISH + " INTEGER" 
      + ")";
  
  public DBHelper(Context context, String name, CursorFactory factory, int version) {
    super(context, name, factory, version);
    this.context = context;
  }

  public DBHelper(Context context, String dbName, int ver) {
    this(context, dbName, null, ver);
    this.context = context;
  }

  public DBHelper(Context context, String dbName) {
    this(context, dbName, Version);
    this.context = context;
  }
  
  public DBHelper(Context context) {
    this(context, DB_NAME, Version);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    Log.i("dbHelper", "创建数据库");
    db.execSQL(DAILY_PLAN_CREATE);
    db.execSQL(WEEKLY_PLAN_CREATE);
    db.execSQL(TERM_PLAN_CREATE);
    db.execSQL(MEMORANDUM_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.i("dbHelper", "更新数据库");
  }

}
