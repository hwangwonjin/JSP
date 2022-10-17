<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DB"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		//데이터 수신
		request.setCharacterEncoding("UTF-8");
		String stdNo = request.getParameter("stdNo");
		String stdName = request.getParameter("stdName");
		String stdHp = request.getParameter("stdHp");
		String stdYear = request.getParameter("stdYear");
		String stdAddress = request.getParameter("stdAddress");
		
		//데이터 작업
		try{
			Connection conn = DB.getInstance().getConnection();
			String sql = "DELETE  FROM `student` WHERE `stdNo`=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, stdNo);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

		//목록이동
		response.sendRedirect("./list.jsp");

%>