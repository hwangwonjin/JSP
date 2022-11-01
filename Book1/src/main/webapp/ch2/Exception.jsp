<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>내장객체 - exception</title>
	</head>
	<body>
	<%
		int status = response.getStatus(); //response  내장 객체로부터 에러 코드 확인
		
		//에러 코드에 따라 적절한 메시지 출력
		if(status == 404){
			out.print("404에러가 발생하였습니다.");
			out.print("<br/>파일 경로를 확인해 주세요");
			
		}else if(status == 405){
			out.print("405에러가 발생하였습니다.");
			out.print("<br/> 요청방식을 확인해주세요");
			
		}else if(status == 500){
			out.print("500에러 코드가 발생했습니다.");
			out.print("<br/> 소스 코드에 오류가 있는지 확인해 주세요");
		}
	
	
	%>
	</body>
</html>