<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//데이터 수신
	request.setCharacterEncoding("UTF-8");
	String stdNo = request.getParameter("stdNo");
	
	//데이터 작성
	
	
		try{
			
			
		}catch(Exception e){
			e.printStackTrace();
		}


%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>student::modify</title>
	</head>
	<body>
		<h3>student 수정</h3>
		
		<a href="../1_JDBCTest.jsp">처음으로</a>
		<a href="./list.jsp">student 목록</a>
		
		
			<form action="./modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>학번</td>
					<td><input type="text" name="stdNo" maxlength="8" required></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="stdName"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="stdHp"></td>
				</tr>
				<tr>
					<td>학년</td>
					<td>
					<select name="stdYear">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
					</select>
					
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="stdAddress" placeholder="주소 입력"></td>
				</tr>
				<tr>
				<td colspan="2" align="right">
					<input type="submit" value="수정"/>
				</td>
				
				</tr>
			
			</table>
		
		
		</form>
	</body>
</html>