package com.cc.db;

import java.util.Date;
/**
 * 日计划
 * @author chen
 *
 */
public class DailyPlan {
  private long id;
  private String content;//内容 
  private Date initTime;//设置时间
  private boolean isFinish;//是否完成
  
  public static final String ID = "id";
  public static final String CONTENT = "content";
  public static final String INIT_TIME = "initTime";
  public static final String IS_FINISH = "isFinish";
  public final static String TABLE_NAME = "daily_plan";

  
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
  
  
}
