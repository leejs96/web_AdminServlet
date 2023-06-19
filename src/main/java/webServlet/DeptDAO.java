package webServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO {
	private PreparedStatement pstmt;
	private Connection con;

	public List<DeptVO> listdept(){
		List<DeptVO> list = new ArrayList<DeptVO>();
		try {
			connDB();
			
			String sql = "SELECT * FROM dept";
			System.out.println("preparedStatement : " + sql);
			
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				String dept_No = rs.getString("dept_No");
				String dept_Name = rs.getString("dept_Name");
				
				DeptVO dept = new DeptVO();
				dept.setDept_No(dept_No);
				dept.setDept_Name(dept_Name);
				list.add(dept);
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void connDB() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/shoppingmall?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "!";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
			/* stmt = con.createStatement(); */
			
			System.out.println("Mysql Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
