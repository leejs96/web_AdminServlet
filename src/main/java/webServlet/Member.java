package webServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberList
 */
@WebServlet("/member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		MemberDAO dao = new MemberDAO();
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		
		if (command != null && command.equals("MemList")) {
			List<MemberVO> list = dao.listMembers();
			out.print("<html><head><link rel=\"stylesheet\" href = \"./css/mem_list.css\">");
			out.println("<jsp:include page = \"nav_admin.jsp\"/></head>");
			out.println("<body><h1>회원관리</h1>");
			out.println("<div style = \"margin-top: 20px;\">\r\n"
					/* + "<button onclick = \"window.location.reload()\">전체 리스트보기</button>\r\n" */
								+ "</div>");
			out.println("<table border=1 id = 'list'>");
			out.println(
					"<tr style = \"background: #94AF9F\"><td>아이디</td><td>이름</td><td>부서명</td><td>성별</td><td>생년월일</td><td>HP</td><td>sms수신</td><td>이메일</td><td>메일수신</td><td>우편번호</td><td>지번주소</td><td>도로명주소</td><td>나머지주소</td><td>가입날짜</td><td>삭제</td></tr>"
					);
			
			for(int i=0; i < list.size(); i++) {
				MemberVO vo = (MemberVO) list.get(i);
				String id = vo.getMember_id();
				String name = vo.getMember_name();
				String dept = vo.getDept_Name();
				String gender = vo.getMember_gender();
				String birth = vo.getMember_birth_y() + "/" + vo.getMember_birth_m() + "/" + vo.getMember_birth_d() + "(" + vo.getMember_birth_SL() + ")";
				String hp = vo.getHP1() + "-" + vo.getHP2() + "-" + vo.getHP3();
				String sms = vo.getSMS_YN();
				String email = vo.getEmail1() + "@" + vo.getEmail2();
				String emailYN = vo.getEmailsts_YN();
				String zipcode = vo.getZipcode();
				String jibun_addr = vo.getJibun_addr();
				String road_addr = vo.getRoad_addr();
				String rest_addr = vo.getRest_addr();
				Date joinDate = vo.getJoindate();
				out.print("<tr><td>" + id + "</td><td>" + name + "</td><td>" + dept + "</td><td>" + gender + "</td><td>" + birth + "</td><td>" + hp + "</td><td>" + sms + "</td><td>" + email + "</td><td>" + emailYN + "</td><td>" + zipcode + "</td><td>" + jibun_addr + "</td><td>" + road_addr + "</td><td>" + rest_addr + "</td><td>" + joinDate + "</td><td><input type=button value = '삭제' onclick=location.href=\"member?command=delete&member_id="+ id + "\"></td></tr>");
				
			}
			out.print("</table></body></html>");

		} else if(command != null && command.equals("delete")) {
			String member_id = request.getParameter("member_id");
			dao.delMember(member_id);
			out.print("<html><body><script>alert('" + member_id + "님의 회원정보가 삭제 되었습니다.');window.location.href='member?command=MemList';</script></body></html>");
			
		} else if(command != null && command.equals("logout")) {
			request.getSession().invalidate();
			out.print("<html><body><script>alert('로그아웃 되었습니다.');window.location.href='./main.jsp';</script></body></html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		MemberDAO dao = new MemberDAO();
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		
		if (command != null && command.equals("addMember")) {
			String _id = request.getParameter("user_id");
			String _pwd = request.getParameter("user_pw");
			String _name = request.getParameter("user_name");
			String _dept_No = request.getParameter("dept_No");
			String _gender = request.getParameter("gender");
			String _user_birth_y = request.getParameter("user_birth_y");
			String _user_birth_m = request.getParameter("user_birth_m");
			String _user_birth_d = request.getParameter("user_birth_d");
			String _birth_SL = request.getParameter("birth_SL");
			String _user_hp1 = request.getParameter("user_hp1");
			String _user_hp2 = request.getParameter("user_hp2");
			String _user_hp3 = request.getParameter("user_hp3");
			String _sms = request.getParameter("sms");
			if (_sms != "Y") {
				_sms = "N";
			}
			String _user_email1 = request.getParameter("user_email1");
			String _user_email2 = request.getParameter("user_email2");
			String _emailsts = request.getParameter("emailsts");
			if (_emailsts != "Y") {
				_emailsts = "N";
			}
			String _zipcode = request.getParameter("zipcode");
			String _jibun_addr = request.getParameter("jibun_addr");
			String _road_addr = request.getParameter("road_addr");
			String _rest_addr = request.getParameter("rest_addr");

			MemberVO vo = new MemberVO();
			vo.setMember_id(_id);
			vo.setMember_pw(_pwd);
			vo.setMember_name(_name);
			vo.setDept_No(_dept_No);
			vo.setMember_gender(_gender);
			vo.setMember_birth_y(_user_birth_y);
			vo.setMember_birth_m(_user_birth_m);
			vo.setMember_birth_d(_user_birth_d);
			vo.setMember_birth_SL(_birth_SL);
			vo.setHP1(_user_hp1);
			vo.setHP2(_user_hp2);
			vo.setHP3(_user_hp3);
			vo.setSMS_YN(_sms);
			vo.setEmail1(_user_email1);
			vo.setEmail2(_user_email2);
			vo.setEmailsts_YN(_emailsts);
			vo.setZipcode(_zipcode);
			vo.setJibun_addr(_jibun_addr);
			vo.setRoad_addr(_road_addr);
			vo.setRest_addr(_rest_addr);

			dao.joinMember(vo);
			out.print("<html><body><script>alert('회원가입 되었습니다.');window.location.href='./login.jsp';</script></body></html>");
		
		} else if (command != null && command.equals("login")) {
			String _id = request.getParameter("user_id");
			String _pwd = request.getParameter("user_pw");

			if (_id.equals("admin") && _pwd.equals("0000")) {
				request.getSession().setAttribute("member_id", _id);
				out.print(
						"<html><body><script>alert('관리자이름으로 로그인합니다.');window.location.href='./main.jsp'</script></body></html>");
			} else {
				dao.login(_id, _pwd);
				if (dao.isLogin() == true) {
					request.getSession().setAttribute("member_id", _id);
					out.print("<html><body><script>alert('" + dao.isLogin()
							+ "');window.location.href='./main.jsp'</script>" + dao.isLogin() + "</body></html>");
				} else {
					out.print("<html><body><script>alert('" + dao.isLogin()
							+ " & 아이디와 비밀번호가 일치하지 않습니다.');history.go(-1);</script></body></html>");
				}
			}

		} 
	}
	
}
