<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" errorPage="1_67-2.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<!-- 
			날짜 : 2022/10/11
			이름 : 황원진
			내용 : 에러 페이지 isErrorPage 속성을 설정한 에러 페이지 작성
		 -->
	</head>
	<body>
		<h3>에러코드 발생</h3>
		<%
		int myAge = Integer.parseInt(request.getParameter("age")) + 10;//에러발생
		out.println("10년 후 당신의 나이는 " + myAge+"입니다.");	
	
		%>
	</body>
</html>