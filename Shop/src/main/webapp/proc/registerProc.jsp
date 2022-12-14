<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	String prono = request.getParameter("prono");
	String orCount = request.getParameter("orCount");
	String orId = request.getParameter("orId");

	int result = 0;
	try{
		Connection conn = DBCP.getConnection();
		String sql = "insert into `order`(`orderProduct`,`orderCount`,`orderId`,`orderDate`) values(?,?,?,NOW())";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, prono);
		psmt.setString(2, orCount);
		psmt.setString(3, orId);
		
		result = psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	
	out.print(jsonData);
	
%>