<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="config.DBCP"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.registerBean"%>
<%@page import="java.util.List"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	List<registerBean> registers = new ArrayList<>();
	
	try{
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT `regStdNo`, `stdName`, `lecName`, `regLecNo`, `regMidScore`, `regFinalScore`, `regTotalScore`, `regGrade`  FROM `register` AS a "
					+ "JOIN `lecture` AS b "
					+ "ON a.regLecNo=b.lecNo "
					+ "JOIN `student` AS c "
					+ "ON a.regStdNo=c.stdNo";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			registerBean rgs = new registerBean();
			rgs.setRegStdNo(rs.getString(1));
			rgs.setStdName(rs.getString(2));
			rgs.setLecName(rs.getString(3));
			rgs.setRegLecNo(rs.getString(4));
			rgs.setRegMidScore(rs.getInt(5));
			rgs.setRegFinalScore(rs.getInt(6));
			rgs.setRegTotalScore(rs.getInt(7));
			rgs.setRegGrade(rs.getString(8));
			
			registers.add(rgs);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}


%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h3>수강관리</h3>
		
		<a href="#">강좌관리</a>
		<a href="#">수강관리</a>
		<a href="#">학생관리</a>
		
		<h4>수강현황</h4>
		
		<input type="text">
		<button class="btnSearch">검색</button>
		<button class="btnRegister">수강신청</button>
		<table border="1">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>강좌명</th>
				<th>강좌코드</th>
				<th>중간시험</th>
				<th>기말시험</th>
				<th>총점</th>
				<th>등급</th>
			</tr>
			<% for(registerBean rgs : registers){ %>
			<tr>
				<td><%= rgs.getRegStdNo() %></td>
				<td><%= rgs.getStdName() %></td>
				<td><%= rgs.getLecName() %></td>
				<td><%= rgs.getRegLecNo() %></td>
				<td><%= rgs.getRegMidScore() %></td>
				<td><%= rgs.getRegFinalScore() %></td>
				<td><%= rgs.getRegTotalScore() %></td>
				<td><%= rgs.getRegGrade() %></td>
			</tr>
			<%} %>
		</table>
	</body>
</html>