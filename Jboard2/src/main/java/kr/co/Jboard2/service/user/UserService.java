package kr.co.Jboard2.service.user;

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

import kr.co.Jboard2.Vo.TermsVo;
import kr.co.Jboard2.Vo.UserVo;
import kr.co.Jboard2.dao.UserDAO;

public enum UserService {

	instance;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UserDAO dao;
	
	private UserService() {
		dao = new UserDAO();
	}
	
	public void insertUser(UserVo vo) {
		 dao.insertUser(vo);
	};
	public UserVo selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	};
	public TermsVo selectTerms() {
		return dao.selectTerms();
	};
	
	public UserVo selectUserForFindId(String name, String email) {
		return dao.selectUserForFindId(name, email);
	};
	
	public UserVo selectUserForFindPw(String uid, String email) {
		return dao.selectUserForFindPw(uid, email);
	};
	public UserVo selectUserForFindPass(String uid, String pass) {
		return dao.selectUserForFindPass(uid, pass);
	}
	
	public UserVo selectUserBySessId(String sessId) {
		return dao.selectUserBySessId(sessId);
	}
	
	public void updateUserForSession(String uid, String sessId) {
		dao.updateUserForSession(uid, sessId);
	}
	
	public void updateUserForsessLimitDate(String sessId) {
		dao.updateUserForsessLimitDate(sessId);
	}
	
	public void updateUserForSessionOut(String uid) {
		dao.updateUserForSessionOut(uid);
	}
	
	public int updateUserWithdraw(String uid) {
		return dao.updateUserWithdraw(uid);
	}
	
	public UserVo updateUser(UserVo vo) {
		return dao.updateUser(vo);
	};
	public int updateUserPassword(String uid, String pass) {
		return dao.updateUserPassword(uid, pass);
	};
	public void deleteUser() {};
	
	public int[] sendEmailCode(String receiver) {
		
			//인증코드 생성
			int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		
		
		
				//기본정보
				String sender = "hdigimon27@gmail.com";
				String password = "fmwmevdipmcihlhh";
				
				
				String title = "Jboard2 인증코드 입니다.";
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
	
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	};
		
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
}
