<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<link rel="stylesheet" href = "./css/login.css">
</head>
<body>
	<div id = "page">
		<form name="loginform" action=member method=post onSubmit="return login();">
			<table>
				<tr>
					<td colspan = "2"><input type = "text" name = "user_id" class = "user" placeholder = "ID"></td>
				</tr>
				<tr>
					<td colspan = "2"><input type = "password" name = "user_pw" class = "user" placeholder = "PASSWORD"></td>
				</tr>
				<tr>
					<td><input type = "submit" value = "로그인" class = "submit"></td>
					<td><input type = "button" class = "submit" onclick = "location = 'join.jsp'" value = 회원가입 style = "background: #ACB1D6;"></td>
				</tr>
			</table>
			<input type = "hidden" name = "command" value = "login">
		</form>

	</div>
</body>
</html>

<script>
	function login() {
		var id = document.loginform.user_id.value;
		var pw = document.loginform.user_pw.value;
		
		if(id != "") {
			if(pw != "") {
				return true;
			} else {
				alert("비밀번호를 입력하세요.");
				return false;
			}
		} else {
			alert("아이디를 입력하세요.");
			return false;
		}
	}
</script>