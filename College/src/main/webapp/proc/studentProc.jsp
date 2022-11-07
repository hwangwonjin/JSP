<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="config.DBCP"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	String stdNo = request.getParameter("stdNo");
	String stdName = request.getParameter("stdName");
	String stdHp = request.getParameter("stdHp");
	String stdYear = request.getParameter("stdYear");
	String stdAddress = request.getParameter("stdAddress");

	try{
		Connection conn = DBCP.getConnection();
		String sql = "insert into `student ";
				sql += "values(?,?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		
	}catch(Exception e){
		e.printStackTrace();
	}


%>