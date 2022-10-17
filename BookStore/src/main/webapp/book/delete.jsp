<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//데이터수신 	
	request.setCharacterEncoding("UTF-8");
	String bookId =request.getParameter("bookId");
	String bookname =request.getParameter("bookName");
	String publisher =request.getParameter("publisher");
	String price =request.getParameter("price");
	
	//데이터 작업
	try{
		Connection conn = DBCP.getConnection();
		String sql = "DELETE FROM `book` WHERE `bookId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, bookId);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	//목록으로
	response.sendRedirect("./list.jsp");
%>



