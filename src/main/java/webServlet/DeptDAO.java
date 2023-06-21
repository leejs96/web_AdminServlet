package webServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DeptDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;

	public DeptDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");

			dataFactory = (DataSource) envContext.lookup("jdbc/mysqlpool");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<DeptVO> listdept(){
		List<DeptVO> list = new ArrayList<DeptVO>();
		try {
			con = dataFactory.getConnection();
			
			String sql = "SELECT d.dept_No, d.dept_Name, count(*) as cnt from dept as d INNER JOIN shopping_member as s ON d.dept_No = s.dept_No GROUP BY s.dept_No";
			System.out.println("preparedStatement : " + sql);
			
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				String dept_No = rs.getString("dept_No");
				String dept_Name = rs.getString("dept_Name");
				int cnt = rs.getInt("cnt");
				
				DeptVO dept = new DeptVO();
				dept.setDept_No(dept_No);
				dept.setDept_Name(dept_Name);
				dept.setCntPdept(cnt);
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
}
