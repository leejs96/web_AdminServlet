<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import = "java.util.Date, webServlet.*, java.util.List"    
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원리스트</title>
		<%@ include file="./script/nav_admin.jsp" %>
		
		<link rel="stylesheet" href = "./css/nav.css">
		<link rel="stylesheet" href = "./css/mem_list.css">
		
		<script>
			function validateForm() { // 검색어는 입력하고 항목은 선택 안한 경우 항목을 선택하게하는 함수 다시 만들기!!
				var opt = document.SearchForm.opt.value;
				var search_word = document.SearchForm.search_word.value;
				
				if(opt.equals("1") && search_word.length > 0) {
					return false;
				} else {return true;}
			}
		</script>
	</head>
	
	<body>
	
		<div class = "wrap">
			<h1>회원관리</h1>
			<form name="SearchForm" action=member method=get style = "margin : 5px; border : 1px solid;" onSubmit="return validateForm();">
					<h2 style = "margin-left: 15px; margin-bottom: 10px;">검색조건</h2>
					<div style = "margin-left: 28px;">
						<select name = "opt" id = "opt">
							<option value = "1">항목</option>
							<option value = "member_id">ID</option>
							<option value = "member_name">이름</option>
							<option value = "dept_Name">부서명</option>
							<option value = "zipcode">우편번호</option>
							<option value = "jibun_addr">지번주소</option>
							<option value = "road_addr">도로명주소</option>
						</select>
						<input type = "search" name = "search_word" style = "height : 25px;">
						<input type = "hidden" name = "command" value = "search">
						<input type = "submit" id = "submit" value = "검색">
					</div>
				<table id = "search_con">
					<tr>
						<td class="cond">성별</td>
						<td>
							<input type = "checkbox" name = "male" value = "male">남자
							<input type = "checkbox" name = "female" value = "female">여자
						</td>
					</tr>
					<tr>
						<td class="cond">SMS수신</td>
						<td>
							<input type = "checkbox" name = "SMSY" value = "Y">허용
							<input type = "checkbox" name = "SMSN" value = "N">미허용
						</td>
					</tr>
					<tr>
						<td class="cond">email수신</td>
						<td>
							<input type = "checkbox" name = "emailY" value = "Y">허용
							<input type = "checkbox" name = "emailN" value = "N">미허용
						</td>
					</tr>
					<tr>
						<td class="cond">가입날짜</td>
						<td>
							<input type = "date" name = "joindate1" min = "2020-01-01"> ~
							<input type = "date" name = "joindate2" min = "2020-01-01">
						</td>
					</tr>
				</table>
			</form>
			<div style = "margin-top: 20px;">
				<button onclick = "location = 'member?command=MemList'">전체 리스트보기</button>
			</div>
			<table border = "1" id = "list">
				<tr style = "background: #94AF9F">
					<td>ID</td>
					<td>이름</td>
					<td>부서명</td>
					<td>성별</td>
					<td>생년월일</td>
					<td>HP</td>
					<td>sms수신</td>
					<td>이메일</td>
					<td>메일수신</td>
					<td>우편번호</td>
					<td>지번주소</td>
					<td>도로명주소</td>
					<td>나머지주소</td>
					<td>가입날짜</td>
					<td>정보수정</td>
					<td>회원삭제</td>
				</tr>
				
				<% 
					List<MemberVO> list = (List<MemberVO>) request.getAttribute("memberList");
					for(int i = 0; i<list.size(); i++) {
						MemberVO vo = (MemberVO) list.get(i);
						String member_id = vo.getMember_id();
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
				%>
				<tr>
					<td><%=member_id%></td>
					<td><%=name %></td>
					<td><%=dept %></td>
					<td><%=gender %></td>
					<td><%=birth%></td>
					<td><%=hp%></td>
					<td><%=sms%></td>
					<td><%=email%></td>
					<td><%=emailYN%></td>
					<td><%=zipcode%></td>
					<td><%=jibun_addr%></td>
					<td><%=road_addr%></td>
					<td><%=rest_addr%></td>
					<td><%=joinDate%></td>
					<td><button id = <%=member_id%> onclick = "mem_update(this.id);">수정</button></td>
					<td><button id = <%=member_id%> onclick = "mem_delete(this.id);">삭제</button></td>
				</tr>
				
			<%
				}
			%>
			</table>
		</div>
	</body>
</html>

<script>
	function mem_delete(id) {
		if (confirm(id + "님의 계정을 삭제하시겠습니까?") == true) {
			var manage_num = prompt("관리자 비밀번호를 입력해주세요.");
	        if(manage_num == "0000") {
				location.href="member?command=delete&id=" + id;
	        } else if(manage_num == null){
				return false;
	        } else {
	        	alert("관리자비밀번호가 일치하지 않습니다.");
				return false;
			}
		} else {
			return false;
		}
	}
	
	function mem_update(id) {
		window.open("http://localhost:8060/web_AdminServlet/member?command=MyInfo&user_id=" + id, "name(about:blank)", "width=700, height=900")
	}
</script>