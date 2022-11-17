

<%@page import="kr.co.farmstory1.dao.UserDAO"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>

<%@page import="java.sql.Connection"%>
<%@ page  contentType="application/json;charset=UTF-8"  pageEncoding="UTF-8"%>
<%

	//데이터수신
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	
	//데이터베이스 처리
	int result = UserDAO.Instance.selectCountUid(uid);
	
	//JSON 출력
	JsonObject json = new JsonObject();
	json.addProperty("result", result);

	String jsonData = json.toString();
	out.print(jsonData);


%>