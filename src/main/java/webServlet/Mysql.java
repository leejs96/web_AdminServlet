package webServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Mysql {
	private PreparedStatement pstmt;
	private Connection con;
	
	private void connDB() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/shoppingmall?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
			/* stmt = con.createStatement(); */
			
			System.out.println("Mysql Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	
}
