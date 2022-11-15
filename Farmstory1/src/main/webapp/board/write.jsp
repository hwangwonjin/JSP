<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	pageContext.include("/board/_"+group+".jsp");
%>
        <main id="board">
            <section class="write">
               
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
                            <textarea name="conten"></textarea>
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
                    <a href="./list.jsp?group<%= group %>&<%= cate %>" class="btn btnCancle">취소</a>
                    <input type="submit" class="btn btnWrite" value="작성완료">
                </div>
                
            </section>
        </main>
       <%@ include file="/_footer.jsp"%>