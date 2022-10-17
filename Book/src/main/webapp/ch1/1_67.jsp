<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>1_67</title>
		<!-- 
			날짜 : 2022/10/11
			이름 : 황원진
			내용 : 에러 페이지 발생
			
			try-catch문으로 작성
		 -->
	</head>
	<body>
		<h3>에러페이지 발생</h3>
		<%
			try{
			int myAge = Integer.parseInt(request.getParameter("age")) + 10;//에러발생
			out.println("10년 후 당신의 나이는 " + myAge+"입니다.");		
			}catch(Exception e){
				out.println("예외 발생 : 매개변수 Age가 null입니다.");
			}
		%>
	</body>
</html>