<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
  
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainHome</title>
<%@ include file="../script/nav_admin.jsp" %>
		
<link rel="stylesheet" href = "./css/nav.css">
</head>

<body>
	
	<h1>메인페이지</h1>
	<%if(user_name != null) {
		%>
			<p>이름 : <%=user_name %></p>
			<p>아이디 : <%=user_id %></p>
			
		<p><%=user_name %>(<%=user_id %>)님 안녕하세요!</p>
	<%}%>
	
	<table border=1>
		<tr><td>아이디</td><td><input type = text></td><td rowspan=5><input type = text value = "Hello"></td></tr>
		<tr><td>비밀번호</td><td><input type = text></td></tr>
		<tr><td>비밀번호확인</td><td><input type = text></td></tr>
		<tr><td>이름</td><td><input type = text></td></tr>
		<tr><td>부서</td><td><input type = text></td></tr>
	</table>
	
</body>
</html>
