<%@page import="bean.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<% 
	List<BookBean> books = new ArrayList<>();
	try{
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery("SELECT * FROM `book`");
		
		while(rs.next()){
			BookBean bs = new BookBean();
			bs.setBookId(rs.getString(1));
			bs.setBookName(rs.getString(2));
			bs.setPublisher(rs.getString(3));
			bs.setPrice(rs.getString(4));
			
			books.add(bs);
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
		<h3>도서목록</h3>
		
		<a href="../index.jsp">처음으로</a>
		<a href="./register.jsp">도서등록</a>
		
		<table border="1">
		<tr>
			<th>도서번호</th>
			<th>도서명</th>
			<th>출판사</th>
			<th>가격</th>
			<th>관리</th>
		</tr>
		<% for(BookBean bs : books){ %>
		<tr>
			<td><%= bs.getBookId() %></td>
			<td><%= bs.getBookName() %></td>
			<td><%= bs.getPublisher() %></td>
			<td><%= bs.getPrice() %></td>
			<td>
				<a href="./modify.jsp?bookId=<%= bs.getBookId()%>">수정</a>
				<a href="./delete.jsp?bookId=<%= bs.getBookId()%>">삭제</a>
			
			</td>
		
		
		</tr>
		<%} %>
		</table>
	</body>
</html>