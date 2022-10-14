<%@page import="java.sql.Statement"%>
<%@page import="config.DB"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
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
			Statement stmt = conn.createStatement();
			
			String sql = "UPDATE `student` SET";
					sql += "`stdName` = '"+stdName+"',";
					sql += "`stdHp` = '"+stdHp+"',";
					sql += "`stdYear` = '"+stdYear+"',";
					sql += "`stdAddress` = '"+stdAddress+"'";
					sql += "WHERE `stdNo` = '"+stdNo+"'";			
					
					stmt.executeUpdate(sql);
					conn.close();
					
		}catch(Exception e){
			e.printStackTrace();
		}

	//목록이동
	response.sendRedirect("./list.jsp");


%>