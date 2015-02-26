/**
 * Baidu.com Inc. Copyright (c) 2000-2014 All Rights Reserved.
 */
package com.github.colingan.infos.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类
 * 
 * @title DateTimeUtil
 * @author Gan Jia (ganjia@baidu.com)
 * @date 2014年11月7日
 * @version 1.0
 */
public abstract class DateTimeUtil {

  private static final String FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
  private static final String FORMAT_MINUTE = "yyyy-MM-dd HH:mm";
  private static final String FORMAT_DAY = "yyyy-MM-dd";

  /**
   * 日期对象转成字符，精确到秒，格式：yyyy-MM-dd HH:mm:ss
   * 
   * @param date 待转换的日期实例
   * @return 转换后的字符串
   */
  public static final String dateToSecond(Date date) {
    return generalDateFormat(date, FORMAT_SECOND);
  }

  /**
   * 日期对象转成字符，精确到分，格式：yyyy-MM-dd HH:mm
   * 
   * @param date 待转换的日期实例
   * @return 转换后的字符串
   */
  public static final String dateToMiute(Date date) {
    return generalDateFormat(date, FORMAT_MINUTE);
  }

  /**
   * 日期对象转成字符，精确到填，格式：yyyy-MM-dd
   * 
   * @param date 待转换的日期实例
   * @return 转换后的字符串
   */
  public static final String dateToDay(Date date) {
    return generalDateFormat(date, FORMAT_DAY);
  }

  /**
   * 按指定的格式将日期对象转换成字符串
   * 
   * @param date 待转换的日期实例
   * @param format 格式
   * @return 转换后的字符串
   */
  public static final String generalDateFormat(Date date, String format) {
    SimpleDateFormat f = new SimpleDateFormat(format);
    return f.format(date);
  }
}
