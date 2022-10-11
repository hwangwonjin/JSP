<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"
buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>지시어 - buffer, autoFlush 속성</title>
		<!-- 
			날짜: 2022/10/11
			이름 : 황원진
			내용 : 버퍼와 플래시
			
			buffer 속성을 통해 버퍼의 크기나 사용여부를 지정할 수는 있으나
			크기를 줄이면 JSP의 기능을 온전히 사용할 수 없게 되므로 거의 사용하지 않음
		 -->
	</head>
	<body>
		<%
			for(int i = 1; i <= 100; i++){
				out.println("abcde12345");
			}
		
		
		%>
	</body>
</html>