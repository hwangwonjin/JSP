/**
 * 
 */
 function modify(u){
 
 
	 $(function(){
			
					let uid = u.eq(0).text();
					let name = u.eq(1).text();
					let hp = u.eq(2).text();
					let age = u.eq(3).text();
					
						
					
		
		
					$('section').empty();
					$('nav').empty().append("<h4>user2 수정</h4><a href='#' id='userList'>user2 목록</a>");
				
					let table = "<table border='1'>";
						table += "<tr>";
						table += "<td>아이디</td>";
						table += "<td><input type='text' name='uid' value='" +uid+ "'/></td>";
						table += "</tr>";
						table += "<tr>";
						table += "<td>이름</td>";
						table += "<td><input type='text' name='name' value='" +name+ "'/></td>";
						table += "</tr>";
						table += "<tr>";
						table += "<td>휴대폰</td>";
						table += "<td><input type='text' name='hp' value='" +hp+ "'/></td>";
						table += "</tr>";
						table += "<tr>";
						table += "<td>나이</td>";
						table += "<td><input type='text' name='age' value='" +age+ "'/></td>";
						table += "</tr>";
						table += "<tr>";
						table += "<td colspan='2' align='right'><input type='submit' id='btnModify' value='수정'/></td>";
						table += "</tr>";
						table += "</table>";
						
						$('section').append(table);
					
			
		
	});
}