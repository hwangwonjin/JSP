package kr.co.farmstory2.Controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.Vo.FileVo;
import kr.co.farmstory2.service.ArticleService;


@WebServlet("/board/download.do")
public class DownloadController extends HttpServlet{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.instance;

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parent = req.getParameter("parent");
		
		logger.info("download....");
		
		//파일 불러오기
		FileVo fo = service.selectFile(parent);
		logger.debug("parent : " +parent);
		//파일 다운로드 횟수 증가
		service.updateFileDownload(fo.getFno());
		logger.debug("fo : " +fo);
		
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fo.getOriName(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		
		//response 객체로 파일 스트림 작업
		ServletContext ctx = req.getServletContext();
		String savePath = ctx.getRealPath("/file");
		File file = new File(savePath+"/"+fo.getNewName());
		
		//츨력 스트림 초기화
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		while(true){
			
			int data = bis.read();
			
			if(data == -1){
				break;
			}
			bos.write(data);
		}
		bos.close();
		bis.close();
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
