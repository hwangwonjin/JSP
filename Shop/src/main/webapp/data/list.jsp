<%@page import="com.google.gson.Gson"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.ProductBean"%>
<%@page import="java.util.List"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
		List<ProductBean> products = new ArrayList<>();
		
		try{
			Connection conn =DBCP.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from `product`");
			
			while(rs.next()){
				ProductBean pb = new ProductBean();
				pb.setProdno(rs.getString(1));
				pb.setProdname(rs.getString(2));
				pb.setStock(rs.getString(3));
				pb.setPrice(rs.getString(4));
				pb.setCompany(rs.getString(5));
				
				products.add(pb);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	Gson gson = new Gson();
	String jsonData = gson.toJson(products);
	
	out.print(jsonData);

%>