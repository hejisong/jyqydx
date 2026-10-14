package com.ywjc.glsyjs.sys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {

//	驱动地址
	public static String driverClasspgsql="oracle.jdbc.OracleDriver";  
//	连接数据库
	public static String jdbcUrlpgsql="jdbc:oracle:thin:@172.16.5.23:1521/nappdb";  
//	数据库用户名
	public static String usernamepgsql="ctread";  
//	数据库密码
	public static String passwordpgsql="CTREAD"; 
	
	public static Connection conn = null;
	public static Statement pgsqlst = null;
	public static ResultSet rshr = null;
	
	public static void main(String[] args) throws Exception {
		 Class.forName(driverClasspgsql);
         conn=DriverManager.getConnection(jdbcUrlpgsql, usernamepgsql, passwordpgsql);
         
         try {
 			pgsqlst=conn.createStatement();
 			ResultSet rs=pgsqlst.executeQuery("select * from ctwrite.t_person ");
 			String split="";

 			while(rs.next()) {
 				System.out.println(rs.getString("USERID")+ "---,");

 			}
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
	}
	
}
