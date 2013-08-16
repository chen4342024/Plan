package com.cc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{
  private static int Version = 1;
  private Context context = null;
  
  public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
    super(context, name, factory, version);
    this.context = context;
  }
  
  public DatabaseHelper(Context context, String dbName, int ver) {
    this(context, dbName, null, ver);
    this.context = context;
  }
  public DatabaseHelper(Context context, String dbName) {
    this(context,dbName,Version);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    Toast.makeText(context,"创建数据库",Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Toast.makeText(context,"更新数据库",Toast.LENGTH_SHORT).show();
  }
  
}
