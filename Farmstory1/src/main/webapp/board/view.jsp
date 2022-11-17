<%@page import="java.util.List"%>
<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%

	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");

	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	
	ArticleDAO dao = ArticleDAO.getinstance();
	
	//조회수 +1
	dao.updateArticleHit(no);
	
	//글 가져오기
	ArticleBean ab = dao.selectArticle(no);
	
	//댓글 가져오기
	List<ArticleBean> commments = dao.selectComments(no);
	
	
	pageContext.include("/board/_"+group+".jsp");
%>

<script>
	
			$(document).ready(function() {
				
				//글삭제
				$('.btnRemove').click(function(){
					let isDelete = confirm('정말 삭제하시겠습니까?');
					if(isDelete){
						return true;
					}else{
						return false;
					}
					
				});
				
				
				
				//댓글 삭제
				$(document).on('click', '.remove', function(e){
					e.preventDefault();
					
					let isDeleteOk = confirm('정말 삭제하시겠습니까?');
					
					if(isDeleteOk){
						
						let article = $(this).closest('article');
						let no = $(this).attr('data-no');
						let parent = $(this).attr('data-parent');
						
						
						let jsonData = {"no" : no, "parent" : parent};
						
						$.ajax({
							url: './proc/commentDeleteProc.jsp',
							typy: 'GET',
							data: jsonData,
							dataType: 'json',
							success: function(data){
								
								if(data.result == 1){
									alert('댓글이 삭제되었습니다.');
								}
							}
						});
						
					}
		
					
				});
				
				
				
				
				//댓글 수정
				$(document).on('click', '.modify', function(e){
					e.preventDefault();
					
					let txt = $(this).text();
					let p_tag = $(this).parent().prev();
					
					if(txt == '수정'){
						//수정모드
						$(this).text('수정완료');
						p_tag.attr('contentEditable', true);
						p_tag.focus();
					}else{
						//수정완료
						$(this).text('수정');
						
						let no = $(this).attr('data-no');
						let content = p_tag.text();
						
						let jsonData = {
								"no" : no,
								"content" : content
						};
						
						console.log(jsonData);
						
						$.ajax({
							url: './proc/commentModifyProc.jsp',
							type: 'POST',
							data: jsonData,
							dataType: 'json',
							success: function(data){
								
								if(data.result == 1){
									
									alert('댓글이 수정되었습니다.');
								
									p_tag.attr('contentEditable', false);
								}
								
							}
							
						});
						
					}
					
				});
				
				
				//댓글 작성
				$('.commentForm > form').submit(function(){

					let no			= $(this).children('input[name=no]').val();
					let uid 		= $(this).children('input[name=uid]').val();
					let textarea 	= $(this).children('textarea[name=content]');
					let content 	= textarea.val();
					
					if(content == ''){
						alert('댓글을 작성하세요');
						return false;
					}
					
					let jsonData={
							"no":no,
							"uid":uid,
							"content":content
							
					};
					
					
					
					$.ajax({
						url:'./proc/commentWriteProc.jsp',
						method:'POST',
						data:jsonData,
						dataType:'json',
						success: function(data){
							console.log(data);
							
							if(data.result > 0){
								
								let article = "<article>";
									article += "<span class='nick'>"+data.nick+"</span>";
									article += "<span class='date'>"+data.date+"</span>";
									article += "<p class='content'>"+data.content+"</p>";
									article += "<div>";
									article += "<a href='#' class='remove' data-no='"+data.no+"' data-parent='"+data.parent+"'>삭제</a>";
									article += "<a href='#' class='modify' data-no='"+data.no+"'>수정</a>";
									article += "</div>";
									article += "</article>";
									
								
								$('.commentList > .empty').hide();
								$('.commentList').append(article);
								textarea.val('');
							}
							
						}
						
					});
					
					return false;
					
				});
				
			});
	
	</script>

        <main id="board">
            <section class="view">
                
                   <table border="0">
                    <caption>글보기</caption>
                    <tr>
                        <td>제목</td>
                        <td>
                            <input type="text" name="title" value="<%= ab.getTitle()%>" readonly>
                        </td>
                    </tr>
                    <% if(ab.getFile() > 0){ %>
                    <tr>
                        <td>첨부파일</td>
                        <td>
                            <a href="./proc/dounload.jsp?group=<%= group %>&cate=<%= cate %>&parent=<%= ab.getNo()%>"><%= ab.getOriName() %></a>
                            &nbsp;<span><%= ab.getDownload() %></span>회 다운로드
                        </td>
                    </tr>
                    <% } %>
                    <tr>
                        <td>내용</td>
                        <td>
                           <textarea name="content" readonly><%= ab.getContent() %></textarea>
                        </td>
                    </tr>
                   </table>
                   <div>
                    <a href="#" class="btn btnRemove">삭제</a>
                    <a href="./modify.jsp?group=<%= group %>&cate=<%= cate %>" class="btn btnModify">수정</a>
                    <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>" class="btn btnList">목록</a>
                   </div>

                   <!--댓글목록-->
                   <section class="commentList">
                    <h3>댓글목록</h3>
                    <% for(ArticleBean comment : commments){ %>
                    <article border="1">
                        <span class="nick"><%= comment.getNick() %></span>
                        <span class="date"><%= comment.getRdate() %></span>
                        <p class="content"><%= comment.getContent() %></p>
                        
                        <div>
                            <a href="#" class="remove">삭제</a>
                            <a href="#" class="modify">수정</a>
                        </div>
                    </article>
					<% } %>
					
					<% if(commments.size() == 0){ %>
                    <p class="empty">등록된 댓글이 없습니다. </p>
					<% } %>
                   </section>

                   <!--댓글쓰기-->
                   <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form action="#" method="post">
                    	<input type="hidden" name="no" value="<%= no %>">
                    	<input type="hidden" name="uid" value="<%= ab.getUid() %>">
                        <textarea name="content">댓글내용 입력</textarea>
                        <div>
                            <a href="#" class="btn btnCancle">취소</a>
                            <input type="submit" class="btn btnComplete" value="작성완료">
                        </div>
                    </form>
                   </section>
            </section>
        </main>
          	</article>
	</section>
</div>
         <%@ include file="/_footer.jsp"%>