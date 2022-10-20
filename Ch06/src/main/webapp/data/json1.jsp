<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	//Java JSON 데이터 생성
	String jsonData = "{\"uid\":\"a101\",\"name\":\"홍길동\",\"hp\":\"010-2258-5225\",\"age\":23}";

	//JSON 출력
	out.print(jsonData);
%>