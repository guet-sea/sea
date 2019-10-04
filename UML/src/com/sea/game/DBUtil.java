package com.sea.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DBUtil {

    private static final String URL="jdbc:mysql://localhost:3306/game?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String USER="root";
    private static final String PASSWORD="root";
    private static Connection conn=null;
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("加载驱动成功");
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
        	System.out.println("加载驱动失败");
        } catch (SQLException e) {
        	System.out.println("连接数据库失败");
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
