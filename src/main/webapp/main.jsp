<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<%if(user_id != null) { %>
		<p><%=user_id %>님 안녕하세요!</p>
	<%}%>
	
</body>
</html>
