<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<String> lists = new ArrayList<String>();
    lists.add("리스트");
    lists.add("컬랙션");
    session.setAttribute("lists", lists);
    %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Session 영역</title>
			<!-- 페이지 : 129 -->
		</head>
		<body>
			<h2>페이지 이동 후 Session 영역의 속성 읽기</h2>
			<a href="SessionLocation.jsp">SessionLocation.jsp 바로가기</a>
		</body>
	</html>