package webServlet;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Date;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private ResultSet rs = null;
	private boolean login = false;

	private DataSource dataFactory;
	
	public MemberDAO() {

		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");

			dataFactory = (DataSource) envContext.lookup("jdbc/mysqlpool");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			/* connDB(); */
			
			con = dataFactory.getConnection();
			
			String sql = "SELECT * FROM shopping_member INNER JOIN dept ON shopping_member.dept_No = dept.dept_No ORDER BY member_name ASC";
			System.out.println("preparedStatement : " + sql);

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				String member_id = rs.getString("member_id");
				String pw = rs.getString("member_pw");
				String name = rs.getString("member_name");
				String dept_Name = rs.getString("dept_Name");
				String gender = rs.getString("member_gender");
				String birth_y = rs.getString("member_birth_y");
				String birth_m = rs.getString("member_birth_m");
				String birth_d = rs.getString("member_birth_d");
				String birth_gn = rs.getString("member_birth_gn");
				String HP1 = rs.getString("HP1");
				String HP2 = rs.getString("HP2");
				String HP3 = rs.getString("HP3");
				String SMS_YN = rs.getString("SMS_YN");
				String email1 = rs.getString("email1");
				String email2 = rs.getString("email2");
				String emailsts_YN = rs.getString("emailsts_YN");
				String DBzipcode = rs.getString("zipcode");
				String DBjibun_addr = rs.getString("jibun_addr");
				String DBroad_addr = rs.getString("road_addr");
				String DBrest_addr = rs.getString("rest_addr");
				Date joindate = rs.getDate("joindate");

				MemberVO vo = new MemberVO();
				vo.setMember_id(member_id);
				vo.setMember_pw(pw);
				vo.setMember_name(name);
				vo.setDept_Name(dept_Name);
				vo.setMember_gender(gender);
				vo.setMember_birth_y(birth_y);
				vo.setMember_birth_m(birth_m);
				vo.setMember_birth_d(birth_d);
				vo.setMember_birth_SL(birth_gn);
				vo.setHP1(HP1);
				vo.setHP2(HP2);
				vo.setHP3(HP3);
				vo.setSMS_YN(SMS_YN);
				vo.setEmail1(email1);
				vo.setEmail2(email2);
				vo.setEmailsts_YN(emailsts_YN);
				vo.setZipcode(DBzipcode);
				vo.setJibun_addr(DBjibun_addr);
				vo.setRoad_addr(DBroad_addr);
				vo.setRest_addr(DBrest_addr);
				vo.setJoindate(joindate);
				list.add(vo);
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void joinMember(MemberVO memberVO) {
		try {
			/* connDB(); */
			con = dataFactory.getConnection();

			String id = memberVO.getMember_id();
			String pwd = memberVO.getMember_pw();
			String name = memberVO.getMember_name();
			String gender = memberVO.getMember_gender();
			String birth_y = memberVO.getMember_birth_y();
			String birth_m = memberVO.getMember_birth_m();
			String birth_d = memberVO.getMember_birth_d();
			String birth_SL = memberVO.getMember_birth_SL();
			String hp1 = memberVO.getHP1();
			String hp2 = memberVO.getHP2();
			String hp3 = memberVO.getHP3();
			String sms = memberVO.getSMS_YN();
			String email1 = memberVO.getEmail1();
			String email2 = memberVO.getEmail2();
			String emailsts = memberVO.getEmailsts_YN();
			String zipcode = memberVO.getZipcode();
			String jibun_addr = memberVO.getJibun_addr();
			String road_addr = memberVO.getRoad_addr();
			String rest_addr = memberVO.getRest_addr();
			String dept_No = memberVO.getDept_No();

			String sql = "insert into shopping_member";
			sql += " (member_id, member_pw, member_name, member_gender, member_birth_y, member_birth_m, member_birth_d, member_birth_gn, HP1, HP2, HP3, SMS_YN, email1, email2, emailsts_YN, zipcode, jibun_addr, road_addr, rest_addr, dept_No)";
			sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			System.out.println("prepareStatement : " + sql);

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, gender);
			pstmt.setString(5, birth_y);
			pstmt.setString(6, birth_m);
			pstmt.setString(7, birth_d);
			pstmt.setString(8, birth_SL);
			pstmt.setString(9, hp1);
			pstmt.setString(10, hp2);
			pstmt.setString(11, hp3);
			pstmt.setString(12, sms);
			pstmt.setString(13, email1);
			pstmt.setString(14, email2);
			pstmt.setString(15, emailsts);
			pstmt.setString(16, zipcode);
			pstmt.setString(17, jibun_addr);
			pstmt.setString(18, road_addr);
			pstmt.setString(19, rest_addr);
			pstmt.setString(20, dept_No);

			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login(String id, String pw) {
		try {
			/* connDB(); */
			con = dataFactory.getConnection();
			
			String sql = "SELECT * FROM shopping_member" + " WHERE member_id = '" + id + "' AND member_pw = '" + pw
					+ "'";
			System.out.println("prepareStatement : " + sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			if (rs.next()) {
				setLogin(true);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(String id) {
		try {
			/* connDB(); */
			con = dataFactory.getConnection();
			
			String sql = "delete from shopping_member" + " where member_id=?";
			System.out.println("prepareStatement : " + sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void connDB() { try { String dbURL =
	 * "jdbc:mysql://localhost:3306/shoppingmall?serverTimezone=UTC"; String dbID =
	 * "root"; String dbPassword = "!";
	 * 
	 * Class.forName("com.mysql.jdbc.Driver");
	 * 
	 * con = DriverManager.getConnection(dbURL, dbID, dbPassword); stmt =
	 * con.createStatement();
	 * 
	 * System.out.println("Mysql Connected"); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */
}
