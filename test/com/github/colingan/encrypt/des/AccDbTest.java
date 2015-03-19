
/**
 * Tencent.com Inc.
 * Copyright (c) 1998-2015 All Rights Reserved.
 */

package com.github.colingan.encrypt.des;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;


 /**
 * @title AccDbTest
 * @description TODO 
 * @author colingan
 * @date 2015-3-19
 * @version 1.0
 */

public class AccDbTest {

  String driverClass = "net.ucanaccess.jdbc.UcanaccessDriver";
  String url = "jdbc:ucanaccess://D:\\msaccess\\infos.accdb";
  String user = "";
  String pwd = "";

  @Test
  public void testAccess() {
    Connection con = null;
    try {

      // 加载驱动器类
      Class.forName(driverClass);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      System.out.println("加载驱动器类时出现异常");
    }
    try {

      // 获取数据库连接
      con = DriverManager.getConnection(url, user, pwd);
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println("出现SQLException异常");
    }
    System.out.println("加载驱动器成功");
    // 接着就可以操作MySql数据库了
    try {
      Statement stmt = con.createStatement();
      String sql = "select * from category";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        System.out.println(rs.getLong(1));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

