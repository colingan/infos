package com.github.colingan.encrypt.des;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class DbTest {

	String driverClass = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3360/infos";
	String user = "info_write";
	String pwd = "12345678";

	@Test
	public void test2() {
		Connection con = null;
		try {

			// 加载驱动器类
			Class.forName("com.mysql.jdbc.Driver");
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
			String sql = "select * from infos.category";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getLong(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
