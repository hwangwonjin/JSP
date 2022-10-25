<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="config.DBCP"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.ProductBean"%>
<%@page import="java.util.List"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	List<ProductBean> products = new ArrayList<>();

	try{
		Connection conn =DBCP.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from `product`");
		
		while(rs.next()){
			ProductBean pb = new ProductBean();
			pb.setProdno(rs.getInt(1));
			pb.setProdname(rs.getString(2));
			pb.setStock(rs.getString(3));
			pb.setPrice(rs.getString(4));
			pb.setCompany(rs.getString(5));
			
			products.add(pb);
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
		<title>shop::product</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(function(){
		
			$(document).on('click', '#btnOrder', function(e){
				e.preventDefault();
				
				let prono = $(this).val();
				
				$('section').empty();
				$('nav').empty().append("<h4>주문하기</h4>");
			
				let table = "<table border='1'>";
					table += "<tr>";
					table += "<td>상품번호</td>";
					table += "<td><input type='text' name='prono' value="+prono+" /></td>";
					table += "</tr>";
					table += "<tr>";
					table += "<td>수량</td>";
					table += "<td><input type='text' name='orderCount'/></td>";
					table += "</tr>";
					table += "<tr>";
					table += "<td>주문자</td>";
					table += "<td><input type='text' name='orderId'/></td>";
					table += "</tr>";
					table += "<tr>";
					table += "<td colspan='2' align='right'><input type='submit' id='submit' value='등록'/></td>";
					table += "</tr>";
					table += "</table>";
					
					$('section').append(table);
				
				});
			
				$(document).on('click', '#submit', function(e){
					e.preventDefault();
					
					//데이터 가져오기
					let prono = $('input[name=prono]').val();
					let orderCount = $('input[name=orderCount]').val();
					let orderId = $('input[name=orderId]').val();
					
					//JSON 생성
					let jsonData={
							"prono":prono,
							"orderCounnt":orderCount,
							"orderId":orderId
					};
					console.log(jsonData);
				});
			});	
		</script>
		
	</head>
	<body>
		<h3>상품목록</h3>
		
		<a href="./customer.jsp">고객목록</a>
		<a href="./order.jsp">주문목록</a>
		<a href="./product.jsp">상품목록</a>
		
		<table border="1">
		<tr>
			<th>상품번호</th>
			<th>상품명</th>
			<th>재고량</th>
			<th>가격</th>
			<th>제조사</th>
			<th>주문</th>
		</tr>
		<% for(ProductBean pb : products){ %>
		<tr>
			<td><%= pb.getProdno() %></td>
			<td><%= pb.getProdname() %></td>
			<td><%= pb.getStock() %></td>
			<td><%= pb.getPrice() %></td>
			<td><%= pb.getCompany() %></td>
			<td>
				<button id="btnOrder">주문</button>
			</td>
		</tr>
		<%} %>
		</table>
		
		<nav></nav>
		<section></section>
	</body>
</html>