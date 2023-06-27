<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function len_pw(pw) {
				if(pw.length <4) {
					alert("4자리 이상의 비밀번호를 설정해주세요.")
					return false;
				} else {
					return true;
				}
			}
		
			function validateForm() {
				console.log('확인');
				var new_pw = document.pwUpdate.new_pw.value;
				var pw_che = document.pwUpdate.pw_che.value;
				
				if(len_pw(new_pw)) {
					if(new_pw == pw_che) {
						return true;
					} else {
						alert("변경할 비밀번호가 일치하지 않습니다.");
						return false;
					}
				} else {
					return false;
				}
			}
		</script>
	</head>
	
	<body style = "margin : 20% auto;">
		<h1>비밀번호 변경</h1>
		<form name="pwUpdate" action=member method=post onSubmit="return validateForm();" style = "text-align:center;">
			<table>
				<tr>
					<td>
						<input type = "password" name = "origin_pw" id = "origin_pw" class = "pw" placeholder = "현재 비밀번호">
					</td>
				</tr>
				<tr>
					<td>
						<input type = "password" name = "new_pw" id = "new_pw" class = "pw" placeholder = "새 비밀번호">
					</td>
				</tr>
				<tr>
					<td>
						<input type = "password" name = "pw_che" id = "pw_che" class = "pw" placeholder = "새 비밀번호 확인">
						<input type = "hidden" name = "command" value = "pw_change">
					</td>
				</tr>
			</table>
			
			<input type = "submit" value = "변경" style = "width: 220px; height: 40px; margin: 7px auto; background: #9BABB8; border: 1px solid #B0A4A4;">
		</form>
		<div style = "text-align : center;">
			<button onclick = "back()" style = "width: 220px; height: 40px; border: 1px solid #B0A4A4; background: #EEEEEE;">취소</button>
		</div>
		
	</body>
</html>

<script>
	function back() {
		window.close();
	}
</script>

<style>
	body {
		width: 400px;
	    height: 300px;
	    margin: 4% auto;
	}
	
	h1 {
		text-align : center;
		font-size : 45px;
	}
	
	table {
    margin: 0 auto;
    font-size: 20px;
	}
	
	.pw {
		height: 40px;
		width: 220px;
	}
</style>