package com.cc.db;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {
  private DatabaseHelper dbHelper;
  private SQLiteDatabase db = null;
  private Context context;

  public final static String DB_NAME = "planDB";
  public final static String TABLE_YEAR_PLAN = "year_plan";
  public final static String TABLE_MONTH_PLAN = "month_plan";
  public final static String TABLE_WEEK_PLAN = "week_plan";
  public final static String TABLE_DAY_PLAN = "day_plan";

  public MyDatabase(Context context) {
    this.context = context;
    createDB();
  }

  private void createDB() {
    dbHelper = new DatabaseHelper(context, DB_NAME);
    db = dbHelper.getWritableDatabase();
  }

  public void insert(String tableName, ContentValues insertValues) {
    db.insert(tableName, null, insertValues);
  }

  public void update(String tableName, ContentValues updateValues, String whereClause, String[] whereArgs) {
    String whereClauseStr = whereClause + "=?";
    db.update(tableName, updateValues, whereClauseStr, whereArgs);
  }

  public void delete(String tableName, String whereClause, String[] whereArgs) {
    db.delete(tableName, whereClause, whereArgs);
  }
}
