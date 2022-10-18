<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	String custId = request.getParameter("custId");
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String phone = request.getParameter("phone");

	//데이터 작업
	try{
		Connection conn = DBCP.getConnection();
		String sql = "DELETE FROM `customer` WHERE `custId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, custId);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

	//목록이동
	response.sendRedirect("./list.jsp");
%>