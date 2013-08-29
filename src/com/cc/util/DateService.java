package com.cc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";

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

}
