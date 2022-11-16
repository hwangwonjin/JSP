<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	//multipart 폼 데이터 수신
	String savePath = application.getRealPath("/file");
	int maxSize = 1024 * 1024 *10;
	MultipartRequest mr = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());

	String group = mr.getParameter("group");
	String cate = mr.getParameter("cate");
	String uid = mr.getParameter("uid");
	String title = mr.getParameter("title");
	String content = mr.getParameter("content");
	String fname = mr.getFilesystemName("fname");
	String regip = request.getRemoteAddr();
	
	ArticleBean ab = new ArticleBean();
	ab.setCate(cate);
	ab.setTitle(title);
	ab.setContent(content);
	ab.setFile(0);
	ab.setUid(uid);
	ab.setRegip(regip);
	
	ArticleDAO dao = ArticleDAO.getinstance();
	
	int parent = dao.insertArticle(ab);
	
	// 파일을 첨부했으면
		if(fname != null){
			
			//파일명 수정 
			int i = fname.lastIndexOf(".");
			String ext = fname.substring(i);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_");
			String now = sdf.format(new Date());
			String newName = now+uid+ext; //20221026160417_j101.txt
			
			File f1 = new File(savePath+"/"+fname);
			File f2 = new File(savePath+"/"+newName);
			
			f1.renameTo(f2);
			
			//파일 테이블 insert
			
			dao.insertFile(parent, newName, fname);
		}
	
	response.sendRedirect("/Farmstory1/board/list.jsp?group="+group+"&cate="+cate);
	
%>