<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	
	//데이터 작업 
	String host = "jdbc:mysql://127.0.0.1:3306/java2db";
	String user = "root";
	String pass = "1234";
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DELETE FROM `member` WHERE `uid`='"+uid+"'");
		//String sql = "DELETE FROM `member` WHERE `uid`=?";
		//PreparedStatement psmt = conn.prepareStatement(sql);
		//psmt.setString(1, uid);
		//psmt.executeUpdate();
		
		//psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	//리스트 이동
	response.sendRedirect("./list.jsp");


%>