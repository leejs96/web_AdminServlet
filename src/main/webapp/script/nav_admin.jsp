<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
	<%
		String user_id = (String)session.getAttribute("userID");
		String user_name = (String)session.getAttribute("userName");
	%>
		
		<div class = "navigation_bar">
			<ul class = "list">
				<li class = "menu" style = "float : left;"><a href = "main.jsp">Home</a></li>
				<%
					if(user_id == null) {
				%>
						<li class = "menu"><a href = "login.jsp">로그인</a></li>
				<%
					} else if(user_id.equals("admin")) {
				%>
						<li class = "menu"><a href = "member?command=logout">로그아웃</a></li>
						<li class = "menu" style = "padding-right: 10px;">
							<div class = "dropdown">관리자페이지</div>
							<div class = "dropdown_menu">
								<a href = "deptList">부서관리</a>
								<a href = "member?command=MemList">회원관리</a>
							</div>
						</li>
				<%
					} else {
				%>
						<li class = "menu"><a href = "member?command=logout">로그아웃</a></li>
						<li class = "menu" style = "padding-right: 10px;"><a href = "member?command=MyInfo">마이페이지</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</body>
</html>