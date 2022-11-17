
<%@page import="kr.co.farmstory1.bean.FileBean"%>
<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.awt.image.DataBufferDouble"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>

<%@page import="java.sql.ResultSet"%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>

<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	//view에서 파일번호 받아오기
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String parent = request.getParameter("parent");

	//파일정보 가져오기	
	ArticleDAO dao = ArticleDAO.getinstance();
	
	FileBean fb = dao.selectFile(parent);
	dao.updateFlieDownload(fb.getFno());
	
	

	//다운로드 로직
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode("파일명.txt", "utf-8"));
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");
	
	
	//response 객체로 파일 스트림 작업
	String savePath = application.getRealPath("/file");
	File file = new File(savePath+"/"+fb.getNewName());
	
	//츨력 스트림 초기화
	out.clear();
	
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	while(true){
		
		int data = bis.read();
		
		if(data == -1){
			break;
		}
		bos.write(data);
	}
	bos.close();
	bis.close();

%>