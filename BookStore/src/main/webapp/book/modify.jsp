<%@page import="bean.BookBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	String bookId = request.getParameter("bookId");
	
	//데이터 작업
	BookBean bs = new BookBean();
	
	try{
		Connection conn = DBCP.getConnection();
		String sql = "SELECT * FROM `book` WHERE `bookId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, bookId);
		
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()){
			bs.setBookId(rs.getString(1));
			bs.setBookName(rs.getString(2));
			bs.setPublisher(rs.getString(3));
			bs.setPrice(rs.getString(4));
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>BookStore::modify</title>
	</head>
	<body>
		<h3>도서수정</h3>
		<a href="../DBCPTest.jsp">처음으로</a>
		<a href="./list.jsp">도서목록</a>
		
		<form action="./modifyProc.jsp" method="post">
		 <table border="1">
		 <tr>
		 	<td>도서번호</td>
		 	<td><input type="text" name="bookId" value="<%= bs.getBookId() %>"/> </td>
		 </tr>
		 <tr>
		 	<td>도서명</td>
		 	<td><input type="text" name="bookName" value="<%= bs.getBookName()%>"/> </td>
		 </tr>
		 <tr>
		 	<td>출판사</td>
		 	<td><input type="text" name="publisher" value="<%= bs.getPublisher()%>"/> </td>
		 </tr>
		 <tr>
		 	<td>가격</td>
		 	<td><input type="text" name="price" value="<%= bs.getPrice()%>"/> </td>
		 </tr>
		 <tr>
		 	<td colspan="2" align="right">
		 	<input type="submit" value="수정">
		 	</td>
		 </tr>
		 </table>	
		</form>
	</body>
</html>