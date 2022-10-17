<%@page import="bean.CustomerBean"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	
	String name =request.getParameter("name");
	
	
	//데이터 작업
	
	
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>BookStore::modify</title>
	</head>
	<body>
		<h3>고객수정</h3>
		<a href="../index.jsp">처음으로</a>
		<a href="./list.jsp">고객목록</a>
		
		
		 <table border="1">
		 <tr>
		 	<td>고객번호</td>
		 	<td><input type="text" name="custuid" value=""> </td>
		 </tr>
		 <tr>
		 	<td>고객명</td>
		 	<td><input type="text" name="custname"> </td>
		 </tr>
		 <tr>
		 	<td>주소</td>
		 	<td><input type="text" name="addr"> </td>
		 </tr>
		 <tr>
		 	<td>휴대폰</td>
		 	<td><input type="text" name="hp"> </td>
		 </tr>
		 <tr>
		 	<td colspan="2" align="right">
		 	<input type="submit" value="수정">
		 	</td>
		 </tr>
		 </table>	
	
	</body>
</html>