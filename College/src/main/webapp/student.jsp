<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="config.DBCP"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
List<StudentBean> students = new ArrayList<>();

	try{
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from `student`");
		
		while(rs.next()){
	StudentBean sb = new StudentBean();
	sb.setStdNo(rs.getString(1));
	sb.setStdName(rs.getString(2));
	sb.setStdhp(rs.getString(3));
	sb.setStdYear(rs.getString(4));
	sb.setStdAddress(rs.getString(5));
	
	students.add(sb);
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
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(function(){
		
			$('.btnStudent').click(function(){
				
				alert('확인');
				let stdNo = $(this).val();
				$('section').show().find('input[name=stdNo]').val();
				
			});
			
			$('.btnStdClose').click(function(){
				$('section').hide();
			});
			
			$('input[type=submit]').click(function(){
				let stdNo = $('input[name=stdNo]').val();
				let stdName = $('input[name=stdName]').val();
				let stdHp = $('input[name=stdHp]').val();
				let stdYear = $('input[name=stdYear]').val();
				let stdAddress = $('input[name=stdAddress]').val();
				
				let jsonData = {
						"stdNo" : stdNo,
						"stdName" : stdName,
						"stdHp" : stdHp,
						"stdYear" : stdYear,
						"stdAddress" : stdAdress
				};
				
				$.post('./proc/studentProc.jsp', jsonData, function(data){
					
					if(data.result > 0){
						alert('등록완료');
						
						let tr = "<tr>";
							tr += "<td>"+data.stdNo+"</td>";
							tr += "<td>"+data.stdName+"</td>";
							tr += "<td>"+data.stdHp+"</td>";
							tr += "<td>"+data.stdYear+"</td>";
							tr += "<td>"+data.stdAdress+"</td>";
							tr += "</tr>"
							
							$('#lecList').append(tr);
					}else{
						alert('등록실패');
					}
				});
			});
		});
		</script>
	</head>
	<body>
		<h3>학생 관리</h3>
		
		<a href="#">강좌관리</a>
		<a href="#">수강관리</a>
		<a href="#">학생관리</a>
		
		<h4>학생목록</h4>
		
		<button class="btnStudent">등록</button>
		<table border="1">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>학년</th>
				<th>주소</th>
			</tr>
			<% for(StudentBean sb : students){ %>
			<tr>
				<td><%= sb.getStdNo() %></td>
				<td><%= sb.getStdName() %></td>
				<td><%= sb.getStdhp() %></td>
				<td><%= sb.getStdYear() %></td>
				<td><%= sb.getStdAddress() %></td>	
			</tr>
			<%} %>
		</table>
		
		<section style="display:none;">
			<h4>학생등록</h4>
			<button class="btnStdClose">닫기</button>
			<table border="1">
				<tr>
					<td>학번</td>
					<td><input type="text" name="stdNo"/></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="stdName"/></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="stdHp"/></td>
				</tr>
				<tr>
					<td>학년</td>
					<td><input type="text" name="stdYear"/></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="stdAddress"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
					<input type="submit" value="추가"/>
					</td>
				</tr>
			</table>
		</section>
	</body>
</html>