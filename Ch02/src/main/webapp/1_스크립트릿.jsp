<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>1_스크립트릿</title>
		<!-- 
		날짜: 2022/10/04
		이름 : 황원진
		내용 : Jsp 스크립트릿 실습하기		
		
		스크립트릿
		-정적문서(HTML)에 프로그래밍 코드를 삽입하기 위한 코드영역
		동적문성(JSP) 크트립트릿을 실행해서 정적문서로 변환
		
		표현식 
		-변수의 값을 출력을 위한 그크립트
		간단한 값, 식 결과를 출력
		 -->
	</head>
	<body>
		<h3>스크립트 요소</h3>
		
		<h4>스크립트릿(Scriptlet)</h4>
		
		<%
			//스크림트립(프로그래밍 코드 영역)
			int var1 =1;
			boolean var2 = true;
			double var3 = 3.14;
			String var4 = "Hello";
			
			//JSP 출력객체로 HTML 출력
			out.println("<p>var1 : "+var1+"</p>");
			out.println("<p>var2 : "+var2+"</p>");
		
		%>
		
		<h4>표현식(Expression)</h4>
		<p>var3 : <%= var3 %> </p>
		<p>var4 : <%= var4 %> </p>
		
	</body>
</html>