package kr.co.Jboard2.dao;

import org.apache.catalina.User;
import org.apache.tomcat.jni.OS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.Vo.TermsVo;
import kr.co.Jboard2.Vo.UserVo;
import kr.co.Jboard2.db.DBHelper;
import kr.co.Jboard2.db.Sql;



public class UserDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	public void insertUser(UserVo vo) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getNick());
			psmt.setString(5, vo.getEmail());
			psmt.setString(6, vo.getHp());
			psmt.setString(7, vo.getZip());
			psmt.setString(8, vo.getAddr1());
			psmt.setString(9, vo.getAddr2());
			psmt.setString(10, vo.getRegip());
			
			psmt.executeUpdate();
			
			close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	public UserVo selectUser(String uid, String pass) {
		UserVo vo = null;
		try {
			logger.info("selectUser...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				vo.setUid(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNick(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setHp(rs.getString(6));
				vo.setGrade(rs.getInt(7));
				vo.setZip(rs.getString(8));
				vo.setAddr1(rs.getString(9));
				vo.setAddr2(rs.getString(10));
				vo.setRegip(rs.getString(11));
				vo.setRdate(rs.getString(12));
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public UserVo selectUserForFindId(String name, String email) {
		UserVo vo = null;
		try {
			logger.info("selectUserForFindId...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_FOR_FIND_ID);
			psmt.setString(1, name);
			psmt.setString(2, email);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new UserVo();
				vo.setUid(rs.getString(1));
				vo.setName(rs.getString(3));
				vo.setEmail(rs.getString(5));
				vo.setRdate(rs.getString(12));
			}
			close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public UserVo selectUserForFindPw(String uid, String email) {
		UserVo vo = null;
		
		try {
			logger.info("selectUserForFindPw...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_FOR_FIND_PW);
			psmt.setString(1, uid);
			psmt.setString(2, email);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new UserVo();
				vo.setUid(rs.getString(1));
				
			}
			close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public UserVo selectUserForFindPass(String uid, String pass) {
		UserVo vo = null;
		try {
			logger.info("selectUserForFindPass...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_FOR_FIND_PASS);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			
			logger.info("uid : " +uid);
			logger.info("pass : " +pass);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo = new UserVo();
				vo.setUid(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNick(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setHp(rs.getString(6));
				vo.setGrade(rs.getInt(7));
				vo.setZip(rs.getString(8));
				vo.setAddr1(rs.getString(9));
				vo.setAddr2(rs.getString(10));
				vo.setRegip(rs.getString(11));
				vo.setRdate(rs.getString(12));
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		
		return vo;
	}
	
	public UserVo selectUserBySessId(String sessId) {
		UserVo vo = null;
		try {
			logger.info("selectUserBySessId...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_BY_SESSID);
			psmt.setString(1, sessId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				vo.setUid(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNick(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setHp(rs.getString(6));
				vo.setGrade(rs.getInt(7));
				vo.setZip(rs.getString(8));
				vo.setAddr1(rs.getString(9));
				vo.setAddr2(rs.getString(10));
				vo.setRegip(rs.getString(11));
				vo.setRdate(rs.getString(12));
			}
			
			close();
			
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	
	public void selectUsers() {}
	public void deleteUser() {}
	public UserVo updateUser(UserVo vo) {
		try {
			logger.info("updateUser...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getNick());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getHp());
			psmt.setString(5, vo.getZip());
			psmt.setString(6, vo.getAddr1());
			psmt.setString(7, vo.getAddr2());
			psmt.setString(8, vo.getUid());
			
			psmt.executeUpdate();
			
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return vo;
	}
	public int updateUserPassword(String uid, String pass) {
		int result = 0;
		
		try {
			logger.info("updateUserPassword");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_PASSWORD);
			psmt.setString(1, pass);
			psmt.setString(2, uid);
			result = psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public void updateUserForSession(String uid, String sessId) {
		
		try {
			logger.info("updateUserForSession");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESSION);
			psmt.setString(1, sessId);
			psmt.setString(2, uid);
			psmt.executeUpdate();
			
			close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateUserForsessLimitDate(String sessId) {
		
		try {
			logger.info("updateUserForSessLimitDate");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESS_LIMIT_DATE);
			psmt.setString(1, sessId);
			psmt.executeUpdate();
			
			close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateUserForSessionOut(String uid) {
		
		try {
			logger.info("updateUserForSessionOut");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESSTION_OUT);
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public int updateUserWithdraw(String uid) {
		int result = 0;
		try {
			logger.info("updateUserWithdraw...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_WITHDRAW);
			psmt.setString(1, uid);
			result = psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public TermsVo selectTerms() {
			TermsVo vo = null;
		
		try {
			logger.info("selectTerms...");
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_TERMS);
			
			if(rs.next()) {
				vo = new TermsVo();
				vo.setTerms(rs.getString(1));
				vo.setPrivacy(rs.getString(2));
			}
			
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return vo;
		
	}

	public int selectCountUid(String uid) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}


	public int selectCountNick(String nick) {
		int result = 0;
		try {
			logger.info("selectcheckNick");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("result : " +result);
		
		return result;
	}

}
