<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="ub" class="bean.AddUser"></jsp:useBean>
	<jsp:setProperty property="id" name="ub"/>
	<jsp:setProperty property="pw" name="ub"/>
	<jsp:setProperty property="name" name="ub"/>
	<jsp:setProperty property="brith" name="ub"/>
	<jsp:setProperty property="gender" name="ub"/>
	<jsp:setProperty property="addr" name="ub"/>
	<jsp:setProperty property="hobby" name="ub"/>



<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addUser</title>
	</head>
	<body>
		<h3>회원가입 사용자 출력</h3>
		<p>
			아이디: <%= ub.getId() %><br/>
			비밀번호: <%= ub.getPw() %><br/>
			이름: <%= ub.getName() %><br/>
			생년월일: <%= ub.getBrith() %><br/>
			성별: <%= ub.getGender() == 1 ? "남자":"여자" %><br/>
			주소: <%= ub.getAddr() %><br/>
			취미: <%=String.join(",", ub.getHobby()) %><br/>
		
		
		</p>
	</body>
</html>