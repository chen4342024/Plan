package com.cc.db;

import java.util.Date;
/**
 * 日计划
 * @author chen
 *
 */
public class DailyPlan {
  private int id;
  private String content;//内容 
  private Date initTime;//设置时间
  private boolean isFinish;//是否完成
  private int x;
  private int y;
  
  public static final String ID = "id";
  public static final String CONTENT = "content";
  public static final String INIT_TIME = "initTime";
  public static final String IS_FINISH = "isFinish";
  public static final String X = "leftMargin";
  public static final String Y = "topMargin";
  
  public final static String TABLE_NAME = "daily_plan";

  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Date getInitTime() {
    return initTime;
  }
  public void setInitTime(Date initTime) {
    this.initTime = initTime;
  }
  public boolean isFinish() {
    return isFinish;
  }
  public void setFinish(boolean isFinish) {
    this.isFinish = isFinish;
  }
  public int getX() {
    return x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return y;
  }
  public void setY(int y) {
    this.y = y;
  }
  
  
}
