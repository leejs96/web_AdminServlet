package webServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
			request.setAttribute("memberList", list);
			RequestDispatcher dispatch = request.getRequestDispatcher("memberlist.jsp");
			dispatch.forward(request, response);

		} else if(command != null && command.equals("delete")) {
			String member_id = request.getParameter("member_id");
			dao.delMember(member_id);
			out.print("<html><body>");
			out.print("<script>"
					+ "alert('\" + member_id + \"님의 회원정보가 삭제 되었습니다.');"
					+ "window.location.href='member?command=MemList';"
					+ "</script>");
			out.print("</body></html>");
			
		} else if(command != null && command.equals("logout")) {
			request.getSession().invalidate();
			out.print("<html><body>");
			out.print("<script>alert('로그아웃 되었습니다.');"
					+ "window.location.href='./main.jsp';</script>");
			out.print("</body></html>");
			
		} else if(command != null && command.equals("search")) {
			String opt=request.getParameter("opt");
			String search_word=request.getParameter("search_word") != null ? request.getParameter("search_word") : "";
			String male=request.getParameter("male") != null ? request.getParameter("male") : "";
			String female=request.getParameter("female") != null ? request.getParameter("female") : "";
			String SMSY=request.getParameter("SMSY") != null ? request.getParameter("SMSY") : "";
			String SMSN=request.getParameter("SMSN") != null ? request.getParameter("SMSN") : "";
			String emailY=request.getParameter("emailY") != null ? request.getParameter("emailY") : "";
			String emailN=request.getParameter("emailN") != null ? request.getParameter("emailN") : "";
			String joindate1 =  request.getParameter("joindate1") != null ? request.getParameter("joindate1") : null;
			String joindate2 = request.getParameter("joindate2") != null ? request.getParameter("joindate2") : null;
			
			List<MemberVO> list = dao.listMembers(opt, search_word, male, female, SMSY, SMSN, emailY, emailN, joindate1, joindate2);
			request.setAttribute("memberList", list);
			RequestDispatcher dispatch = request.getRequestDispatcher("memberlist.jsp");
			dispatch.forward(request, response);
		
		} else if(command != null && command.equals("checkID")) {
			String user_id = request.getParameter("user_id");
			System.out.println(user_id);
			dao.checkID(user_id);
			System.out.println(dao.isCheck());
			if(dao.isCheck()) {
				out.print("<html><body>");
				out.print("<script>"
						+ "window.location.href='./check_id.jsp?user_id=" + user_id + "&check=able" + "';</script>");
				out.print("</body></html>");
			} else {
				out.print("<html><body>");
				out.print("<script>"
						+ "window.location.href='./check_id.jsp?user_id=" + user_id + "&check=disable" + "';</script>");
				out.print("</body></html>");
			}
		} else if (command != null && command.equals("MyInfo")) {
			String user_id = (String)request.getSession().getAttribute("userID");
			List<MemberVO> list = dao.info(user_id);
			request.setAttribute("memberList", list);
			DeptDAO deptDAO = new DeptDAO();
			List<DeptVO> deptList = deptDAO.listdept();
			request.setAttribute("deptList", deptList);
			RequestDispatcher dispatch = request.getRequestDispatcher("my_info.jsp");
			dispatch.forward(request, response);
			
		} else if (command != null && command.equals("Info")) {
			String user_id = request.getParameter("user_id");
			List<MemberVO> list = dao.info(user_id);
			request.setAttribute("memberList", list);
			DeptDAO deptDAO = new DeptDAO();
			List<DeptVO> deptList = deptDAO.listdept();
			request.setAttribute("deptList", deptList);
			RequestDispatcher dispatch = request.getRequestDispatcher("my_info.jsp");
			dispatch.forward(request, response);
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
//			Map<String, String> photoMap = upload(request, response);
//			String imgFileName = photoMap.get("imageFileName");
			
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			String user_name = request.getParameter("user_name");
			String dept_No = request.getParameter("dept_No");
			String gender = request.getParameter("gender");
			String user_birth_y = request.getParameter("user_birth_y");
			String user_birth_m = request.getParameter("user_birth_m");
			String user_birth_d = request.getParameter("user_birth_d");
			String birth_SL = request.getParameter("birth_SL");
			String user_hp1 = request.getParameter("user_hp1");
			String user_hp2 = request.getParameter("user_hp2");
			String user_hp3 = request.getParameter("user_hp3");
			String sms = request.getParameter("sms");
			if (sms != "Y") {
				sms = "N";
			}
			
			String user_email1 = request.getParameter("user_email1");
			String user_email2 = request.getParameter("user_email2");
			String emailsts = request.getParameter("emailsts");
			if (emailsts != "Y") {
				emailsts = "N";
			}
			String zipcode = request.getParameter("zipcode");
			String jibun_addr = request.getParameter("jibun_addr");
			String road_addr = request.getParameter("road_addr");
			String rest_addr = request.getParameter("rest_addr");

			MemberVO vo = new MemberVO();
			vo.setMember_id(user_id);
			vo.setMember_pw(user_pw);
			vo.setMember_name(user_name);
			vo.setDept_No(dept_No);
			vo.setMember_gender(gender);
			vo.setMember_birth_y(user_birth_y);
			vo.setMember_birth_m(user_birth_m);
			vo.setMember_birth_d(user_birth_d);
			vo.setMember_birth_SL(birth_SL);
			vo.setHP1(user_hp1);
			vo.setHP2(user_hp2);
			vo.setHP3(user_hp3);
			vo.setSMS_YN(sms);
			vo.setEmail1(user_email1);
			vo.setEmail2(user_email2);
			vo.setEmailsts_YN(emailsts);
			vo.setZipcode(zipcode);
			vo.setJibun_addr(jibun_addr);
			vo.setRoad_addr(road_addr);
			vo.setRest_addr(rest_addr);
//			vo.setImgFileName(imgFileName);

			dao.joinMember(vo);
			out.print("<html><body>");
			out.print("<script>alert('회원가입 되었습니다.');"
					+ "window.location.href='./login.jsp';"
					+ "</script>");
			out.print("</body></html>");
		
		} else if (command != null && command.equals("login")) {
			HttpSession session = request.getSession();
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");

			if(!session.isNew()) {
				if (user_id.equals("admin") && user_pw.equals("0000")) {
					session.setAttribute("userID", user_id);
					session.setAttribute("userName", "관리자");
					out.print("<html><body>");
					out.print("<script>alert('관리자이름으로 로그인합니다.');"
							+ "window.location.href='./main.jsp'</script>");
					out.print("</body></html>");
				} else {
					dao.login(user_id, user_pw);
					if (dao.isLogin()) {
						String user_name = dao.getUser_name();
						session.setAttribute("userID", user_id);
						session.setAttribute("userName", user_name);
//						LoginImpl loginUser = new LoginImpl(user_id, user_pw);
						/*
						 * if(session.isNew()) { session.setAttribute("loginUser", loginUser); } int
						 * total = LoginImpl.total_user; // p.379 request.setAttribute("totalUser",
						 * total);
						 */
						out.print("<html><body>");
						out.print("<script>"
								+ "window.location.href='./main.jsp'</script>"
								+ dao.isLogin());
						out.print("</body></html>");
					} else {
						out.print("<html><body>");
						out.print("<script>alert('아이디와 비밀번호가 일치하지 않습니다.');"
								+ "history.go(-1);</script>");
						out.print("</body></html>");
					}
				}
			}

		} else if (command != null && command.equals("update")) {
			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("userID");
			
			String user_name = request.getParameter("user_name");
			String dept_No = request.getParameter("dept_No");
			String user_birth_y = request.getParameter("user_birth_y");
			String user_birth_m = request.getParameter("user_birth_m");
			String user_birth_d = request.getParameter("user_birth_d");
			String birth_SL = request.getParameter("birth_SL");
			String user_hp1 = request.getParameter("user_hp1");
			String user_hp2 = request.getParameter("user_hp2");
			String user_hp3 = request.getParameter("user_hp3");
			String sms = request.getParameter("sms");
			if (sms != "Y") {
				sms = "N";
			}
			
			String user_email1 = request.getParameter("user_email1");
			String user_email2 = request.getParameter("user_email2");
			String emailsts = request.getParameter("emailsts");
			if (emailsts != "Y") {
				emailsts = "N";
			}
			String zipcode = request.getParameter("zipcode");
			String jibun_addr = request.getParameter("jibun_addr");
			String road_addr = request.getParameter("road_addr");
			String rest_addr = request.getParameter("rest_addr");

			
			MemberVO vo = new MemberVO();
			vo.setMember_id(user_id);
			vo.setMember_name(user_name);
			vo.setDept_No(dept_No);
			vo.setMember_birth_y(user_birth_y);
			vo.setMember_birth_m(user_birth_m);
			vo.setMember_birth_d(user_birth_d);
			vo.setMember_birth_SL(birth_SL);
			vo.setHP1(user_hp1);
			vo.setHP2(user_hp2);
			vo.setHP3(user_hp3);
			vo.setSMS_YN(sms);
			vo.setEmail1(user_email1);
			vo.setEmail2(user_email2);
			vo.setEmailsts_YN(emailsts);
			vo.setZipcode(zipcode);
			vo.setJibun_addr(jibun_addr);
			vo.setRoad_addr(road_addr);
			vo.setRest_addr(rest_addr);
			
			dao.update(user_id, vo);
			out.print("<html><body>");
			out.print("<script>alert('정보가 수정되었습니다.');"
					+ "window.location.href='member?command=MyInfo';"
					+ "</script>");
			out.print("</body></html>");
			
		} else if (command != null && command.equals("pw_change")) {
			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("userID");
			String orgin_pw = request.getParameter("origin_pw");
			String new_pw = request.getParameter("new_pw");
			
			dao.pwupdate(user_id, orgin_pw, new_pw);
			if(dao.isPw()) {
				out.print("<html><body>");
				out.print("<script>"
						+ "alert('비밀번호가 변경되었습니다.'); window.close(); opener.location.reload();" + "</script>");
				out.print("</body></html>");
			} else {
				out.print("<html><body>");
				out.print("<script>"
						+ "alert('기존 비밀번호가 일치하지 않습니다.');"
						+ "history.go(-1);"
						+ "</script>");
				out.print("</body></html>");
			}
		}
	}
	
//	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String, String> photoMap = new HashMap<String, String>();
//		String encoding = "utf-8";
//		File currentDirPath = new File("C:\\ThisIsJava\\workspace\\fileupload\\src\\main\\webapp\\resource\\img");
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setRepository(currentDirPath);
//		factory.setSizeThreshold(1024 * 1024);
//		
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		try {
//			List items = upload.parseRequest(request);
//			for (int i = 0; i < items.size(); i++) {
//				FileItem fileItem = (FileItem) items.get(i);
//
//				if (fileItem.isFormField()) {
//					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
//				} else {
//					System.out.println("파라미터명:" + fileItem.getFieldName());
//					System.out.println("파일명:" + fileItem.getName());
//					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
//
//					if (fileItem.getSize() > 0) {
//						int idx = fileItem.getName().lastIndexOf("\\");
//						if (idx == -1) {
//							idx = fileItem.getName().lastIndexOf("/");
//						}
//						String fileName = fileItem.getName().substring(idx + 1);
//						File uploadFile = new File(currentDirPath + "\\" + fileName);
//						fileItem.write(uploadFile);
//					} // end if
//				} // end if
//			} // end for
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return photoMap;
//	}
}
