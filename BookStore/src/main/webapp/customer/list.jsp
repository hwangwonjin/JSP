<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.CustomerBean"%>
<%@page import="java.util.List"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<% 
	List<CustomerBean> customers = new ArrayList<>();
	try{
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `customer`");
		
		while(rs.next()){
			CustomerBean cs = new CustomerBean();
			cs.setCustId(rs.getString(1));
			cs.setName(rs.getString(2));
			cs.setAddress(rs.getString(3));
			cs.setPhone(rs.getString(4));
			
			customers.add(cs);
			
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
		<title>BookStore::list</title>
	</head>
	<body>
		<h3>고객 목록</h3>
		
		<a href="../index.jsp">처음으로</a>
		<a href="./register.jsp">고객등록</a>
		
		<table border="1">
		<tr>
			<th>고객번호</th>
			<th>고객명</th>
			<th>주소</th>
			<th>휴대폰</th>
			<th>관리</th>
		</tr>
		<%for(CustomerBean cs : customers) {%>
		<tr>
			<td><%= cs.getCustId()%></td>
			<td><%= cs.getName() %></td>
			<td><%= cs.getAddress() %></td>
			<td><%= cs.getPhone() %></td>
			<td>
				<a href="./modify.jsp?custId=<%= cs.getCustId()%>">수정</a>
				<a href="./delete.jsp?custId=<%= cs.getCustId()%>">삭제</a>
			
			</td>
		
		
		</tr>
		<%} %>
	
		</table>
	</body>
</html>