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
            System.out.println("���������ɹ�");
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("���ݿ����ӳɹ�");
        } catch (ClassNotFoundException e) {
        	System.out.println("��������ʧ��");
        } catch (SQLException e) {
        	System.out.println("�������ݿ�ʧ��");
        }
    }
    public static Connection getConnection(){
        return conn;
    }
}
