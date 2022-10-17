<%@page import="config.DBCP"%>
<%@page import="bean.User5Bean"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>

<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	List<User5Bean> users = new ArrayList<>(); 
	try{
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user5`");
		
		while(rs.next()){
			User5Bean us = new User5Bean();
			us.setUid(rs.getString(1));
			us.setName(rs.getString(2));
			us.setBirth(rs.getString(3));
			us.setGender(rs.getInt(4));
			us.setAge(rs.getInt(5));
			us.setAddr(rs.getString(6));
			us.setHp(rs.getString(7));
			
			users.add(us);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}



%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user5::list</title>
	</head>
	<body>
		<h3>user5 목록</h3>
		
		<a href="../2_DBCPTest.jsp">처음으로</a>
		<a href="./register.jsp">user5 등록</a>
		
		<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>성별</th>
			<th>나이</th>
			<th>주소</th>
			<th>전화번호</th>
		</tr>
		<% for(User5Bean us : users){ %>
		<tr>
			<td><%= us.getUid() %></td>
			<td><%= us.getName() %></td>
			<td><%= us.getBirth() %></td>
			<td><%= us.getGender() == 1? "남":"여" %></td>
			<td><%= us.getAge() %></td>
			<td><%= us.getAddr() %></td>
			<td><%= us.getHp() %></td>	
			<td>
				<a href="./modify.jsp?uid=<%= us.getUid()%>">수정</a>
				<a href="./delete.jsp?uid=<%= us.getUid()%>">삭제</a>
			</td>	
		</tr>
		<%} %>
		</table>
		
	</body>
</html>