<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	String prono = request.getParameter("prono");
	String orderCount = request.getParameter("orderCount");
	String orderId = request.getParameter("orderId");

	int result = 0;
	try{
		Connection conn = DBCP.getConnection();
		String sql = "insert into `order`(`orderProduct`,`orderCount`,`orderId`,`orderDate`) values(?,?,?,NOW())";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, prono);
		psmt.setString(2, orderCount);
		psmt.setString(3, orderId);
		
		result = psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
%>