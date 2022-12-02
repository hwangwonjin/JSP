package kr.co.farmstory2.Controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.Vo.ArticleVo;
import kr.co.farmstory2.service.ArticleService;



@WebServlet("/board/write.do")
public class WriteController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;
	Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public void init() throws ServletException {
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//파일업로드
			ServletContext ctx = req.getServletContext();
			String path = ctx.getRealPath("/file");
			
			logger.info("writeController...");
			MultipartRequest mr  = service.uploadFile(req, path);
			
			//데이터 수신
			
			String group = mr.getParameter("group");
			String cate = mr.getParameter("cate");
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");
			String uid = mr.getParameter("uid");
			String fname = mr.getFilesystemName("fname");
			String regip = req.getRemoteAddr();
			
			logger.debug("fname : " +fname);
			
			
			ArticleVo article = new ArticleVo();
			article.setCate(cate);
			article.setTitle(title);
			article.setContent(content);
			article.setUid(uid);
			article.setFname(fname);
			article.setRegip(regip);
			
			logger.debug("article1 : " +article);
			
			//글 등록
			int parent = service.insertArticle(article);
			
			//파일 첨부 시
			if(fname != null) {
				logger.debug("1");
				//파일 명 수정
				String newName = service.renameFile(fname, uid, path);
				
				//파일 테이블 추가
				service.insertFile(parent, newName, fname);
				
				logger.debug("newname : " +newName);
			}
			logger.debug("article2 : " +article);
			//리다이렉트
			resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);
	}

	
}
