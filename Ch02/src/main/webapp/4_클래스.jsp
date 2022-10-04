
<%@page import="sub1.Account"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>4_클래스</title>
		<%--
		날짜 : 2022/10/04
		이름: 황원진
		내용: JSP 조건문 실습하기
		
		 --%>
	</head>
	<body>
		<h3>클래스</h3>
		<%
		Account kb = new Account("우리은행", "0110-123-1344","홍길동",15000);
		Account hn = new Account("하나은행", "0110-123-1345","이순신",10000);
		
		kb.deposit(40000);
		kb.withdraw(5000);
		kb.show(out);
		%>
	</body>
</html>