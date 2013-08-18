package com.cc.db;

import java.util.Date;

public class Memorandum {
  private long id;
  private String content;//内容 
  private int importance;//重要程度
  private Date initTime;//设置时间
  private Date remindTime;//提醒时间
  private boolean isFinish;//是否完成
  
  public static final String ID = "id";
  public static final String CONTENT = "content";
  public static final String INIT_TIME = "initTime";
  public static final String REMIND_TIME = "remindTime";
  public static final String IS_FINISH = "isFinish";
  public final static String TABLE_NAME = "memorandum";
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getImportance() {
    return importance;
  }
  public void setImportance(int importance) {
    this.importance = importance;
  }
  public Date getInitTime() {
    return initTime;
  }
  public void setInitTime(Date initTime) {
    this.initTime = initTime;
  }
  public Date getRemindTime() {
    return remindTime;
  }
  public void setRemindTime(Date remindTime) {
    this.remindTime = remindTime;
  }
  public boolean isFinish() {
    return isFinish;
  }
  public void setFinish(boolean isFinish) {
    this.isFinish = isFinish;
  }
  
  
}