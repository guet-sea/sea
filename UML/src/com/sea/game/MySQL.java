package com.sea.game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class MySQL {
	java.sql.Connection conn;
	
	public ArrayList<String> search (String USER) throws SQLException {
		
		conn= DBUtil.getConnection();
		PreparedStatement pst = null;
		String user=null;
		String password=null;
		String level=null;
		String score=null;
		String s="SELECT * FROM gameinformation WHERE `user`=?";
//		pst.setString(1, USER);
		try {
			 pst=conn.prepareStatement(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pst.setString(1, USER);
		
		ResultSet rs=pst.executeQuery();
	    //处理数据库的返回结果(使用ResultSet类)
	    while(rs.next()){
	        System.out.println(rs.getString("user"));
	        System.out.println(rs.getString("password"));
	        System.out.println(rs.getString("level"));
	        System.out.println(rs.getString("score"));
	        user=rs.getString("user");
	        password=rs.getString("password");
	        level=rs.getString("level");
	        score=rs.getString("score");        
	    }
	    ArrayList<String> al=new ArrayList<String>();
	    if(user!=null) {
	    	al.add(user);
		    al.add(password);
		    al.add(level);
		    al.add(score);
	    }
	    //关闭资源
	    rs.close();
	    pst.close();
	    return al;
	}
	public void update(String USER,String LEVEL,String SCORE ) throws SQLException {

		conn= DBUtil.getConnection();
		PreparedStatement pst = null;
		String s="update gameinformation set `level`=?,score=?where `user`="+"'"+USER+"'";
		try {
			 pst=conn.prepareStatement(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pst.setString(1,LEVEL);
		pst.setString(2,SCORE);
		pst.execute();
	    pst.close();
	    conn.close();
	    System.out.println("记录保存成功");
	}
	public void newUser(String USER,String PASSWORD) throws SQLException {
		java.sql.Connection conn;
		conn= DBUtil.getConnection();
		PreparedStatement pst = null;
		String s="insert into gameinformation(`user`,`password`,`level`,score) values(?,?,1,0)";
		try {
			 pst=conn.prepareStatement(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pst.setString(1,USER);
		pst.setString(2,PASSWORD);
		pst.execute();
	    pst.close();
//	    conn.close();
	    System.out.println("注册成功");
	}
//	public static void main(String[] args) throws SQLException {
//		new MySQL().search("1234");
//	}
}
