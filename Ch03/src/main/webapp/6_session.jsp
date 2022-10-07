<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>6_session</title>
		<!-- 
			날짜 : 2022/10/07
			이름 : 황원진
			내용 : JSP session 내장객체 실습하기
			
			session 내장객체
			- session은 최초의 요청부터 마지막 요청까지의 전체 시간 경과
			- 클라이언트가 서버로 요청할 때 서버에 기록되는 클라이언트 정보 객체(Session Table)
			- 서버는 각 클라이언트에 대한 고유식별번호(Session Id) 부여
			
		 -->
	</head>
	<body>
		<h3>session 내장객체</h3>
		
		<h4>세션 아이디 확인</h4>
		<%= session.getId() %>
		
		<h4>로그인 처리</h4>
		<form action="./user/loginproc.jsp" method = "post">
			<input type="text" name="id" placeholder="아이디 입력"/><br/>
			<input type="text" name="pw" placeholder="비밀번호 입력"/><br/>
			<input type="submit" value="로그인"/>
		</form>
		
	</body>
</html>