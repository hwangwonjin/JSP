/**
 * 
 */
 	function list(){
	
	$(function(){
		
		$('setion').empty();
		$('nav').empty().append("<a href='#'>고객목록</a><a href='#'>주문목록</a><a href='#'>상품목록</a>")
		
			$.get('./data/list.jsp',function(data){
				
				let table = "<table border='1'>";
					table += "<tr>";
					table +="<th>상품번호</th>";
					table +="<th>상품명</th>";
					table +="<th>재고량</th>";
					table +="<th>가격</th>";
					table +="<th>제조사</th>";
					table +="<th>주문</th>";
					table +="</tr>";
					table +="</table>";
				
				$('section').append(table);
				
				for(let product of data){
					
					let tr = "<tr>";
						tr += "<td>"+product.Prono+"</td>";	
						tr += "<td>"+product.Prodname+"</td>";	
						tr += "<td>"+product.Stock+"</td>";	
						tr += "<td>"+product.Price+"</td>";	
						tr += "<td>"+product.Company+"</td>";	
						tr += "<td><form><input type='submit' value='주문'</td>";	
						tr += "</tr>";
					
						$('table').append(tr);
				}

			});
		
	});
	
}