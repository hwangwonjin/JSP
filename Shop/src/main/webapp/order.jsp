<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.OrderBean"%>
<%@page import="java.util.List"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	List<OrderBean> orders = new ArrayList<>();

	try{
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select `orderno`,`name`,`prodname`,`orderCount`,`orderDate` from `order` as a "
					+ "join `customer` as b "
					+ "on a.orderId=b.custId "
					+ "join `product` as c "
					+ "on a.orderProduct=c.prodno";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			OrderBean ob = new OrderBean();
			ob.setOrderno(rs.getString(1));
			ob.setName(rs.getString(2));
			ob.setProdname(rs.getString(3));
			ob.setOrderCount(rs.getString(4));
			ob.setOrderDate(rs.getString(5));
			
			orders.add(ob);
			
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
		<title>shop::order</title>
	</head>
	<body>
		<h3>주문목록</h3>
	
		<a href="./customer.jsp">고객목록</a>
		<a href="./order.jsp">주문목록</a>
		<a href="./product.jsp">상품목록</a>
		
		<table border="1">
		<tr>
			<th>주문번호</th>
			<th>주문자</th>
			<th>주문상품</th>
			<th>주문수량</th>
			<th>주문일</th>
		</tr>
		<% for(OrderBean ob: orders){ %>
		<tr>
			<td><%= ob.getOrderno() %></td>
			<td><%= ob.getName() %></td>
			<td><%= ob.getProdname() %></td>
			<td><%= ob.getOrderCount() %></td>
			<td><%= ob.getOrderDate() %></td>
		</tr>
		<%} %>
		</table>
	</body>
</html>