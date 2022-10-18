<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	String custId = request.getParameter("custId");
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String phone = request.getParameter("phone");
	
	//데이터 작업
	try{
		Context initCtx = new InitialContext();
		Context Ctx =(Context) initCtx.lookup("java:comp/env");
		
		//2단계
		DataSource ds = (DataSource) Ctx.lookup("dbcp_java2_bookstore");//커넥션 풀 열기
		Connection conn = ds.getConnection();
		
		String sql = "INSERT INTO `customer` VALUES(?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, custId);
		psmt.setString(2, name);
		psmt.setString(3, address);
		psmt.setString(4, phone);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

	//목록으로
	response.sendRedirect("./list.jsp");
%>