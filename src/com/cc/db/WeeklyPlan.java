package com.cc.db;

import java.util.Date;
/**
 * 周计划
 * @author chen
 *
 */
public class WeeklyPlan {
  private int id;
  private String content;//内容 
  private Date initTime;//设置时间
  private float progress;//进度
  
  public static final String ID = "id";
  public static final String CONTENT = "content";
  public static final String INIT_TIME = "initTime";
  public static final String PROGRESS = "progress";
  public final static String TABLE_NAME = "weekly_plan";
 
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public float getProgress() {
    return progress;
  }
  public void setProgress(float progress) {
    this.progress = progress;
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
}
