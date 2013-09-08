package com.cc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class DateService {
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
  public static final String TODAY_FORMAT = "yyyy-MM-dd";

  private static DateService dateService;

  private DateService() {
  }

  public static DateService getInstance() {
    if (dateService == null) {
      dateService = new DateService();
    }
    return dateService;
  }

  public Date now() {
    Date curDate = new Date();// 获取当前时间
    return curDate;
  }
  
  public String nowStr(){
    SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    String strDate = formatter.format(now());
    return strDate;
  }
  
  public String TodayStr(){
    SimpleDateFormat formatter = new SimpleDateFormat(TODAY_FORMAT);
    String strDate = formatter.format(now());
    return strDate;
  }
  
  public String formatDate(Date date,String formatPattern){
    SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
    String strDate = formatter.format(date);
    return strDate;
  }
  
  public String  formatDateByDefault(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    String strDate = formatter.format(date);
    return strDate;
  }
  
  public Date parse(String dateString, String pattern) throws ParseException {
    DateFormat df = new SimpleDateFormat(pattern);
    return df.parse(dateString);
  }
  
  public Date parseByDefaultPattern(String dateString) {
    DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    try {
      return df.parse(dateString);
    } catch (ParseException e) {
      Log.e("error","ParseException--->"+e.getMessage());
      return new Date();
    }
  }

}
