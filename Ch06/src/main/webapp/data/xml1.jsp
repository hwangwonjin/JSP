<%@ page  contentType="text/xml;charset=UTF-8"  pageEncoding="UTF-8"%>
<%

	//xml 생성
	String xml = "<user>";
			xml += "<uid>a101</uid>"; 
			xml += "<name>홍길동</name>"; 
			xml += "<hp>010-2258-6624</hp>"; 
			xml += "<age>23</age>"; 
			xml += "</user>";
			
		// xml 출력
		out.print(xml);
%>