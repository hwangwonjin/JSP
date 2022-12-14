<%@page import="config.DBCP"%>
<%@page import="java.sql.PreparedStatement"%>

<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//데이터수신
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");
	String gender = request.getParameter("gender");
	String age = request.getParameter("age");
	String addr = request.getParameter("addr");
	String hp = request.getParameter("hp");
	
	//데이터 작업
	
	try{
		Connection conn = DBCP.getConnection();
		String sql = "UPDATE `user5` SET `name`= ?, `brith` = ?, `gender`= ?, `age` = ?, `addr`=?, `hp` = ?,  WHERE `uid`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, name);
		psmt.setString(2, birth);
		psmt.setString(3, gender);
		psmt.setString(4, age);
		psmt.setString(5, addr);
		psmt.setString(6, hp);
		psmt.setString(7, uid);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

	//목록으로
	response.sendRedirect("./list.jsp");

%>