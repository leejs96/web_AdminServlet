package webServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeptList
 */
@WebServlet("/deptList")
public class DeptList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		DeptDAO dept_dao = new DeptDAO();
		PrintWriter out = response.getWriter();
		/* String command = request.getParameter("command"); */
		
		List<DeptVO> list = dept_dao.listdept();
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='#EEEEEE'>");
		out.print("<tr><td>부서번호</td><td>부서명</td></tr>");
		for(int i=0; i < list.size(); i++) {
			DeptVO dept = (DeptVO) list.get(i);
			String dept_No = dept.getDept_No();
			String dept_Name = dept.getDept_Name();
			out.print("<tr><td>" + dept_No + "</td><td>" + dept_Name + "</td></tr>");
		}
		out.print("</table></body></html>");
		
	}
}
