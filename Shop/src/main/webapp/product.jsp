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
				
				$('.btnOrder').click(function() {
					
					alert('dfdf');
					let prodNo = $(this).val();
					$('section').show().find('input[name=prodNo]').val(prodNo);
					
				});
				
				$('.btnClose').click(function(){
					$('section').hide();
				});
				
				$('input[type=submit]').click(function(){
					let prodNo		 = $('input[name=prodNo]').val();
					let prodCount	 = $('input[name=prodCount]').val();
					let prodOrderer	 = $('input[name=prodOrderer]').val();
					
					let jsonData = {
							"prodNo": prodNo,
							"prodCount": prodCount,
							"prodOrderer": prodOrderer
					};
					
					$.post('./registerProc.jsp', jsonData, function(data){
						
						if(data.result > 0){
							alert('주문완료');
						}else{
							alert('주문실패');
						}
					});
					
					
				});
		/*
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
					table += "<td><input type='text' name='orCount'/></td>";
					table += "</tr>";
					table += "<tr>";
					table += "<td>주문자</td>";
					table += "<td><input type='text' name='orId'/></td>";
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
					let orCount = $('input[name=orCount]').val();
					let orId = $('input[name=orId]').val();
					
					//JSON 생성
					let jsonData={
							"prono":prono,
							"orCount":orCount,
							"orId":orId
					};
					console.log(jsonData);
					
					$.ajax({
						url:'./proc/registerProc.jsp',
						method:'post',
						data:jsonData,
						dataType:'json',
						success: function(data){
							
							if(data.result == 1){
								alert('주문완료');
							}else{
								alert('주문실패');
							}
						}
					});
				});
				*/
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
				<button value="<%= pb.getProdno() %>" class="btnOrder">주문</button>
			</td>
		</tr>
		<%} %>
		</table>
		
		
		<section style="display:none;">
			<h4>주문하기</h4>
			<table border="1">
				<tr>
					<td>상품번호</td>
					<td><input type="text" name="prodNo" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>수량</td>
					<td><input type="text" name="prodCount"/></td>
				</tr>
				<tr>
					<td>주문자</td>
					<td><input type="text" name="prodOrderer"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
					<input type="submit" value="주문하기">
					</td>
				</tr>
			</table>
			<button class="btnClose">닫기</button>
		</section>
	</body>
</html>