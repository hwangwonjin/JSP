<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%

	List<Stu>

	//데이터베이스 작업


	try{
		Connection conn = DB.get
		
	}catch(Exception e){
		e.printStackTrace();
	}
	

%>



<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>student::list</title>
	</head>
	<body>
			<h3>student 목록</h3>
		
		<a href="../1_JDBCTest.jsp">처음으로</a>
		<a href="./register.jsp">student 등록</a>
		
		
		<table border="1">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>학년</th>
				<th>주소</th>
				<th>관리</th>
			</tr>
			<% for(StudentBean sb : students){ %>
			<tr>
				<td><%= ub.get %></td>
				<td><%= ub.getName() %></td>
				<td><%= ub.getHp() %></td>
				<td><%= ub.getAge() %></td>
				<td>
					<a href="./modify.jsp?uid=<%= ub.getUid() %>">수정</a>
					<a href="./delete.jsp?uid=<%= ub.getUid()%>">삭제</a>
				</td>
				
			</tr>
			<% } %>
		</table>
	</body>
</html>