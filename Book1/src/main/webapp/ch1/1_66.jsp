<%@page import="java.util.Date"%>

<%@page import="java.text.SimpleDateFormat"%>

<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>1_66</title>
		<!-- 
			날짜 : 2022/10/11
			이름 : 황원진
			내용 : import  속성으로 외부 클래스 불러오기
		 -->
	</head>
	<body>
	<%
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		String todayStr = dateFormat.format(today);
		out.print("오늘날짜 : " + todayStr);
	
	%>
	</body>
</html>