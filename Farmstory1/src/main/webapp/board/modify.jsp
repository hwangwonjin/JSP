<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");

	ArticleBean article =ArticleDAO.getinstance().selectArticle(no);
	
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	pageContext.include("/board/_"+group+".jsp");
%>
        <main id="board">
            <section class="modify">
                <form action="./proc/modifyProc.jsp" method="post">
                	<input type="hidden" name="group" value="<%= group %>">
                	<input type="hidden" name="cate" value="<%= cate %>">
                	<input type="hidden" name="no" value="<%= no %>">
                	<input type="hidden" name="pg" value="<%= pg %>">
                	<table border="1">
                    	<caption>글수정</caption>
                    	<tr>
                       	 <td>제목</td>
                        <td>
                            <input type="text" name="title" placeholder="제목을 입력하세요" value="<%= article.getTitle()%>">
                        </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content"><%= article.getContent() %></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>첨부</td>
                        <td>
                            <input type="file" name="file">
                        </td>
                    </tr>
                </table>
                <div>
                    <a href="./view.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= no %>&pg=<%= pg %>" class="btn btnCancle">취소</a>
                    <input type="submit" class="btn btnComplete" value="수정완료">
                </div>
                </form>
            </section>
        </main>
        </article>
	</section>
</div>
        <%@ include file="/_footer.jsp"%>