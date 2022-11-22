package kr.co.Jboard2.dao;

import org.apache.tomcat.jni.OS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.db.DBHelper;
import kr.co.Jboard2.db.Sql;
import kr.co.Jboard2Vo.TermsVo;



public class UserDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	public void insertUser() {}
	public void selectUser() {}
	public void selectUsers() {}
	public void deleteUser() {}
	public void updateUser() {}
	
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
