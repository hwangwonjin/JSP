<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page info = "copyright .com" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>5_지시자</title>
		<%--
		날짜 : 2022/10/04
		이름: 황원진
		내용: JSP 조건문 실습하기
		
		 --%>
	</head>
	<body>
		<h3>지시자</h3>
		
		
		<h4>page 지시자</h4>
		<p>
			page info : <%= getServletInfo() %>
			
		
		</p>
		
		<h4>include 지시자</h4>
		<%@ include file="/inc/_header.jsp" %>
		<%@ include file="/inc/_footer.jsp" %>
		
	</body>
</html>