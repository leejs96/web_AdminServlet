<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	String user_id = request.getParameter("user_id");
	String check = request.getParameter("check");
%>
		
	<h1>아이디 중복체크</h1>
	<form method = get action = "member" name = "check_id">
		<input type = text name = "user_id" id = "user_id" value = "<%= user_id %>">
		<input type = submit value = "확인">
		<input type = button value = '사용' onclick = "useID();">
		<input type = hidden name = "command" value = "checkID">
	<%
		if(check != null) {
			if(check.equals("able")) {
	%>
				<p>사용 가능한 아이디입니다.</p>
				<input type = hidden name = dbcheck value = "able">
	<%
			} else if(check.equals("disable")) {
	%>
				<p>이미 사용중인 아이디입니다.</p>
				<input type = hidden name = dbcheck value = "disable">
	<%
			}
		}
	%>
	</form>
	
	
</body>
</html>

<script>
	function useID() {
		var checked = "<%=check%>";
	 	if (checked=="able") {
			opener.document.getElementById("user_id").value = document.getElementById("user_id").value;
			opener.document.getElementById("checked").value =  document.check_id.dbcheck.value;
			window.close();
		} else {
			alert("다른 아이디를 사용해주세요");
		}
	}
</script>