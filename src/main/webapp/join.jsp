<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import = "webServlet.*"        
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<%@ include file="./script/dbconn.jsp" %>
		<%@ include file="./script/nav_admin.jsp" %>
		
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <!-- 우편번호검색 -->
		<script src="./script/daumPostcode.js"></script>
		
		<link rel="stylesheet" href = "./css/nav.css">
		<link rel="stylesheet" href = "./css/member.css">
		
		<script>
			function id_doublecheck(){ // 아이디 중복확인 만들기부터 시작
				var user_id = document.Registform.user_id.value;
				var openWin = window.open("http://localhost:8060/web_AdminServlet/check_id.jsp?user_id=" + user_id + "", "name(about:blank)", "width=300, height=300");
			}
		</script>
		
		<script>
			function check_id(user_id) { // 아이디 체크
				var id_len = user_id.length;
				
				if(id_len>=5 && id_len <=8) {
					var regex = /^[A-Za-z0-9+]*$/;
					if (regex.test(user_id)) 
					{ 
						return true;
					} else 
					{
						alert('영어와 숫자가 혼용된 아이디를 설정해주세요.');
						document.Registform.user_id.focus();
						return false;
					}
				}
				else
				{
					alert('5~8자리 아이디를 설정해주세요.');
					document.Registform.user_id.focus();
					return false;
				}
			}
		
			function check_pw(pw1, pw2) { //비밀번호체크
				if(pw1.length >= 4)
				{
					if(pw1 == pw2) {
						return true;
					} else {
						alert('비밀번호가 일치하지않습니다.');
						return false;
					}
				} else
				{
					alert('4자리 이상의 비밀번호를 입력해주세요.');
					document.Registform.user_pw.focus();
					return false;
				}
			}
			
			function check_name(user_name) { // 이름 체크
				if(user_name.length > 0) {

					var regex = /^[ㄱ-ㅎㅏ-ㅣ가-힣]*$/;
					if (regex.test(user_name)) 
					{ 
						return true;
					} else 
					{
						alert('이름을 한글로 적어주세요.');
						document.Registform.user_name.focus();
						return false;
					}
				}
				else
				{
					alert('이름을 입력해주세요.');
					document.Registform.user_name.focus();
					return false;
				}
			}
			
			function check_dept(dept) {
				if(dept == "1")
				{
					alert('부서를 선택해주세요.');
					return false;
				} else
				{
					return true;
				}
			}
			
			function check_gender(gender) { //성별체크
				if(gender == "male" || gender == "female")
				{
					return true;
				} else
				{
					alert('성별을 선택해주세요.');
					return false;
				}
			}
			
			function check_birth(Y, M, D, sl) { // 생년월일체크
				if(Y.length == 4 && M.length == 2 && D.length == 2)
				{
					if (sl == "양" || sl == "음")
						{
							return true;
						} else {
							alert('양/음력을 체크해주세요.');
							return false;
						}
				} else
				{
					alert('생년월일을 입력해주세요.');
					return false;
				}
			}
			
			
			function check_hp(hp1, hp2, hp3) { //핸드폰번호 체크
				if(hp1.length == 3 && hp2.length == 4 && hp3.length == 4)
				{
					return true;
				} else
				{
					alert('핸드폰 번호를 입력해주세요');
					document.Registform.user_hp1.focus();
					return false;
				}
			}
			
			function check_email(email1, email2) { // 이메일체크
				var regex = /^[a-zA-z]+\.[a-zA-z]+$/;
					if(email1.length > 0) {
						if (regex.test(email2)) {
								return true;
							} else {
								alert('이메일 입력양식과 일치하지않습니다.');
								document.Registform.user_email2.focus();
								return false;
							}
					} else {
						alert('이메일을 입력해주세요.');
						document.Registform.user_email1.focus();
						return false;
					}
			}
			
			function check_address(addr1, addr2, addr3) {
				if (addr1.length > 0 && (addr2.length > 0 || addr3.length > 0)) {
					return true;
				} else {
					alert('주소를 입력해주세요.');
					return false;
				}
			}

			function dbcheck_id(dbcheck) {
				if(dbcheck=="able") {
					return true;
				} else if (dbcheck=="disable"){
					alert("아이디 중복확인을 해주세요.");
					return false;
				}
				
			}
			
			function validateForm() {
				console.log('확인');
		    	
		    	var dbcheck = document.Registform.checked.value;
		    	var user_id = document.Registform.user_id.value;
		    	var pw1 = document.Registform.user_pw.value;
		    	var pw2 = document.Registform.user_pw_ch.value;
		    	var user_name = document.Registform.user_name.value;
		    	var dept = document.Registform.dept_No.value;
		    	var gender = document.Registform.gender.value;
		    	var Y = document.Registform.user_birth_y.value;
		    	var M = document.Registform.user_birth_m.value;
		    	var D = document.Registform.user_birth_d.value;
		    	var sl = document.Registform.birth_SL.value;
		    	var hp1 = document.Registform.user_hp1.value;
		    	var hp2 = document.Registform.user_hp2.value;
		    	var hp3 = document.Registform.user_hp3.value;
		    	var email1 = document.Registform.user_email1.value;
		    	var email2 = document.Registform.user_email2.value;
		    	var addr1 = document.Registform.zipcode.value;
		    	var addr2 = document.Registform.jibun_addr.value;
		    	var addr3 = document.Registform.road_addr.value;
		    	
		    	if (check_id(user_id)) 
		    	{
			    	if (dbcheck_id(dbcheck))
			    	{
			    		if(check_pw(pw1, pw2))
			    		{
			    			if (check_name(user_name)) 
			    			{
			    				if (check_dept(dept)) 
				    				{
					    				if(check_gender(gender))
					    				{
					    					if(check_birth(Y, M, D, sl)) 
				    						{
							    				if(check_hp(hp1, hp2, hp3))
							    				{
							    					if(check_email(email1, email2))
							    					{
								    					if(check_address(addr1, addr2, addr3)) {
							    							return true;
								    					} else {
								    						return false;
								    					}
						    						} else 
						    						{
									    				return false;
						    						}
						    					} else 
							    				{
									    			return false;
							    				}
			    							} else 
				    						{
								    			return false;
				    						}
				    					} else 
					    				{
							    			return false;
					    				}
				    				} else 
					    			{
						    			return false;
					    			}
					    		} else 
					    		{
					    			return false;
					    		}
				    		} else 
				    		{
				    			return false;
				    		}
				    	} else
				    	{
				    		return false;
				    	}
		    	}else
		    	{
		    		return false;
		    	}
    		}
			
    	</script>
	</head>
	
	<body>
		<script>
			<% 
				String checked_id = request.getParameter("user_id");
				if(checked_id == null) {
					checked_id = "";
				}
				String checked_use = request.getParameter("check");
			%>
		</script>
		<div class = "wrap" style = "width: 670px;">
			<h1>회원가입</h1>
			<form name="Registform" action="member" method=post onSubmit="return validateForm();" enctype="multipart/form-data">
				<table id = "joinT" border = 1>
					<tr>
						<td class = "title" >아이디</td>
						<td colspan = "3">
							<input type = "text" name=user_id id=user_id>
							<input type = "button" onclick = "id_doublecheck();" value = "중복확인">
							<input type = hidden name = "checked" id = "checked" value = "disable">
						</td>
						<td rowspan=5>
							<input type = file name = "picture">
						</td>
					</tr>
					<tr>
						<td class = "title">비밀번호</td> 
						<td colspan = "3"><input type = "password" name=user_pw id=user_pw value = "1111"></td>
					</tr>
					<tr>
						<td class = "title">비밀번호 확인</td>
						<td colspan = "3"><input type = "password" name=user_pw_ch id=user_pw_ch value = "1111"></td>
					</tr>
					<tr>
						<td class = "title">이름</td>
						<td colspan = "3"><input type = "text" name=user_name id=user_name></td>
					</tr>
					<tr>
						<td class = "title">부서명</td>
						<td colspan = "3">
							<select name = "dept_No">
								<option value = "1">선택</option>
								<option value = "10">인사팀</option>
								<%
									PreparedStatement pstmt = null;
									ResultSet rs = null;
									
									String sql = "SELECT * FROM dept ORDER BY dept_No ASC";
									pstmt = conn.prepareStatement(sql);
								
									// 4) 실행
									rs = pstmt.executeQuery();
								
									// 5) 결과를 테이블에 출력
									while (rs.next()) {
										String dept_No = rs.getString("dept_No");
										String dept_Name = rs.getString("dept_Name");
								%>	
									<option value = "<%=dept_No%>"><%=dept_Name%></option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td class = "title">성별</td>
						<td colspan = "4">
							<input type = "radio" name = "gender" value = "male">남자
							<input type = "radio" name = "gender" value = "female">여자
						</td>
					</tr>
					<tr>
						<td class = "title">법정생년월일</td>
						<td colspan = "4">
							<input type = "text" name=user_birth_y id=user_birth_y class = "birth" value = "1996">/<input type = "text" name=user_birth_m id=user_birth_m class = "birth" value = "01">/<input type = "text" name=user_birth_d id=user_birth_d class = "birth" value = "01">
							<input type = "radio" name = "birth_SL" value = "양" checked>양력
							<input type = "radio" name = "birth_SL" value = "음">음력
						</td>
					</tr>
					<tr>
						<td rowspan = "2" class = "title">휴대폰번호</td> 
						<td colspan = "4" class = "hp" style = "border: 0; padding-bottom: 0;">
							<input type = "text" name=user_hp1 class = "hp" id=user_hp1 value = "010">-<input type = "text" name=user_hp2  class = "hp" id=user_hp2 value = "1111">-<input type = "text" name=user_hp3  class = "hp" id=user_hp3 value = "1111">
						</td>
					</tr>
					<tr>
						<td colspan = "4">
							<input type = "checkbox" name = "sms" value = "Y" id = "sms">
							쇼핑몰에서 발송하는 SMS 소식을 수신합니다.
						</td>
					</tr>
					<tr>
						<td rowspan = "2" class = "title">이메일(e-mail)</td>
						<td colspan = "4" style = "border: 0; padding-bottom: 0;">
							<input type = "text" name=user_email1 id=user_email1 value = "aaa">@<input type = "text" name=user_email2 id=user_email2 value="naver.com">
							<select name = "select_email" onchange="selectEmail();">
								<option value = "1">직접입력 </option>
								<option value = "naver.com">naver.com</option>
								<option value = "gmail.com">gmail.com</option>
								<option value = "daum.net">daum.net</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan = "4">
							<input type = "checkbox" name = "emailsts" value = "Y" id = "emailsts">
							쇼핑몰에서 발송하는 e-mail을 수신합니다.
						</td>
					</tr>
					<tr>
						<td rowspan = "8" class = "title">주소</td>
						<td class = "addrTitle">우편번호</td>
						<td class = "addr" colspan=3>
							<input type = "text" name=zipcode id = zipcode value = "13536">
							<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
						</td>
					</tr>
					<tr>
						<td class = "addrTitle">지번주소</td>
						<td class = "addr" colspan=3>
							<input type = "text" name=jibun_addr id = jibun_addr style = "width: 85%;">
						</td>
					</tr>
					<tr>
						<td class = "addrTitle">도로명주소</td>
						<td class = "addr" colspan=3>
							<input type = "text" name=road_addr id = road_addr value = "경기 성남시 분당구 판교역로10번길 3" style = "width: 85%;">
						</td>
					</tr>
					<tr>
						<td class = "addrTitle">나머지주소</td>
						<td class = "addr" colspan=3>
							<input type = "text" name=rest_addr style = "width: 85%;">
						</td>
					</tr>
				</table>
				
				<input type=submit value='회원 가입' class = "submit">
				<input type = "hidden" name = "command" value = "addMember">
			</form>
		</div>
		
	</body>
</html>

<script>
	function selectEmail() {
		var select_email = document.Registform.select_email.value;
		
		if (select_email == "1") {
			document.Registform.user_email2.value = "";
			document.Registform.user_email2.focus();
		} else {
			document.Registform.user_email2.value = document.Registform.select_email.value;
		}
	}
	
	function check_reset() {
		document.Registform.dbcheck.value = false;
	}
</script>
