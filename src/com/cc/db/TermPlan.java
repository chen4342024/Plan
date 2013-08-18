package com.cc.db;

import java.util.Date;
/**
 * 长期/短期 目标
 * @author chen
 *
 */
public class TermPlan {
  private long id;
  private String content;//内容 
  private Date initTime;//设置时间
  private float progress;//是否完成
  private int termPlanType;//目标类型（长期/短期）
  
  public static final String ID = "id";
  public static final String CONTENT = "content";
  public static final String INIT_TIME = "initTime";
  public static final String PROGRESS = "progress";
  public static final int LONG_TERM_PLAN = 1; // 长期目标
  public static final int SHORT_TERM_PLAN = 0;//短期目标
  public final static String TABLE_NAME = "term_plan";
 
  public long getId() {
    return id;
  }
  public void setId(long id) {
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
  public int getTermPlanType() {
    return termPlanType;
  }
  public void setTermPlanType(int termPlanType) {
    this.termPlanType = termPlanType;
  }
  
}
