package kr.co.farmstory2.service;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.Vo.TermsVo;
import kr.co.farmstory2.Vo.UserVo;
import kr.co.farmstory2.dao.UserDAO;

public enum UserService {

	instance;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UserDAO dao;
	
	private UserService() {
		dao = new UserDAO();
	}
	
	public TermsVo selectTerms() {
		return dao.selectTerms();
	}
	
	public void insertUser(UserVo vo) {
		dao.insertUser(vo);
	}
	
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public UserVo selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	
	public void updateUserForSession(String uid, String sessId) {
		dao.updateUserForSession(uid, sessId);
	}
	
	public UserVo selectUserBySessId(String sessId) {
		return dao.selectUserBySessId(sessId);
	}
	
	public void updateUserForsessLimitDate(String sessId) {
		dao.updateUserForsessLimitDate(sessId);
	}
	
	public void updateUserForSessionOut(String uid) {
		dao.updateUserForSessionOut(uid);
	}
	
	public int[] sendEmailCode(String receiver) {
		
		//인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
	
	
	
			//기본정보
			String sender = "hdigimon27@gmail.com";
			String password = "gsylqxvwpawfzlmk";
			
			
			String title = "Farmstory2 인증코드 입니다.";
			String content = "<h1>인증코드는 "+code+" 입니다.</h1>";
			
			
			//Gmail SMTP 서버설정
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(sender, password);
				}
			});
			
			//메일발송
		
			Message message = new MimeMessage(session);
			int status = 0;
			
			try {
				logger.debug("메일전송시작...");
				message.setFrom(new InternetAddress(sender, "관리자", "UTF-8"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
				message.setSubject(title);
				message.setContent(content, "text/html;charset=utf-8");
				Transport.send(message);
				
				status = 1;
				
			}catch(Exception e) {
				e.printStackTrace();
				status = 0;
				System.out.println("메일 전송 실패...");
			}
			
			System.out.println("메일 전송 성공...");
			
			int result[] = {status, code};
				
			return result;
		}
}
