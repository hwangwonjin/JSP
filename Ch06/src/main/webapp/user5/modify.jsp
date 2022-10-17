
<%@page import="config.DBCP"%>
<%@page import="bean.User5Bean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>

<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	String uid =request.getParameter("uid");
	
	//데이터 작업
	User5Bean us = new User5Bean();
	
	try{
		Connection conn = DBCP.getConnection();
		String sql = "SELECT * FROM `user5` WHERE `uid` =?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1,uid);
		
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()){
			us.setUid(rs.getString(1));
			us.setName(rs.getString(2));
			us.setBirth(rs.getString(3));
			us.setGender(rs.getInt(4));
			us.setAge(rs.getInt(5));
			us.setAddr(rs.getString(6));
			us.setHp(rs.getString(7));
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	//목록으로

%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user5::modify</title>
	</head>
	<body>
	<h3>user5 수정</h3>
		
		<a href="../2_DBCPTest.jsp">처음으로</a>
		<a href="./list.jsp">user5 목록</a>
		
		<form action="./modifyProc.jsp" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid" readonly value="<%= us.getUid() %>" /></td>
			</tr>
			
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="<%= us.getName() %>"/></td>
			</tr>
			
			<tr>
				<td>생년월일</td>
				<td><input type="date" name="birth" value="<%= us.getBirth() %>"/></td>
			</tr>
			
			<tr>
			<td>성별</td>
				<td>
					<label><input type="radio" name="gender" value="1" />남성</label>
					<label><input type="radio" name="gender" value="2" />여성</label>
				</td>
			</tr>
			
			<tr>
			<td>나이</td>
			<td><input type="number" name="age" value="<%= us.getAge() %>"/></td>
			
			</tr>
			<tr>
			<td>주소</td>
			<td><input type="text" name="addr" value="<%= us.getAddr() %>"/></td>
			</tr>
			
			<tr>
			<td>휴대폰</td>
			<td><input type="text" name="hp" value="<%= us.getHp() %>"/></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
				<input type="submit" value="수정하기">
				</td>
			</tr>
		</table>
		
		</form>
	</body>
</html>