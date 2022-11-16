<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	if(sessUser == null){
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101");
		return;
	}


	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	pageContext.include("/board/_"+group+".jsp");
%>
        <main id="board">
            <section class="write">
               
               <form action="./proc/writeProc.jsp" method="post" enctype="multipart/form-data">
               <input type="hidden" name="group" value="<%= group %>">
               <input type="hidden" name="cate" value="<%= cate %>">
               <input type="hidden" name="uid" value="<%= sessUser.getUid() %>">
                <table border="1">
                    <caption>글쓰기</caption>
                    <tr>
                        <td>제목</td>
                        <td>
                            <input type="text" name="title" placeholder="제목을 입력하세요">
                        </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>첨부</td>
                        <td>
                            <input type="file" name="fname">
                        </td>
                    </tr>
                </table>
                <div>
                    <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>" class="btn btnCancle">취소</a>
                    <input type="submit" class="btn btnWrite" value="작성완료">
                </div>
                </form>
            </section>
        </main>
          	</article>
	</section>
</div>
    
       <%@ include file="/_footer.jsp"%>