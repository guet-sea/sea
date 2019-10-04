package com.sea.game;

import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		
		
		MySQL mySQL=new MySQL();
		mySQL.search("1234");
		try {
			
			mySQL.update("1600300830", "2", "100");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
		


}
