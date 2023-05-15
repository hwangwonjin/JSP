package kr.co.farmstory2.db;

public class Sql {

	//user
	
	//회원가입
	public static final String INSERT_USER = "insert into `board_user` set"
											+"`uid`=?,"
											+ "`pw`=SHA2(?,256),"
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`rdate`= NOW()";
	
	//약관
	public static final String SELECT_TERMS = "select * from `board_terms`";
	//로그인(아이디 비밀번호 체크)
	public static final String SELECT_USER = "select * from `board_user` where `uid`=? and `pw`=SHA2(?, 256)";
	//자동 로그인 여부
	public static final String SELECT_USER_BY_SESSID = "SELECT * FROM `board_user` WHERE `sessId`= ? AND `sessLimitDate` > NOW()";
	//회원가입 할때 해당 아이디 찾기
	public static final String SELECT_COUNT_UID = "select count('uid') from `board_user` where `uid`=?";
	//회원가입 할때 해당 별명 조회
	public static final String SELECT_COUNT_NICK = "select count('nick') from `board_user` where `nick`=?";
	//아이디 찾기
	public static final String SELECT_USER_FOR_FIND_ID = "select * from `board_user` where `name`=? and `email`=?";
	//패스워드 찾기
	public static final String SELECT_USER_FOR_FIND_PW = "select * from `board_user` where `uid`=? and `email`=?";
	//패스워드 교체
	public static final String UPDATE_USER_PASSWORD = "update `board_user` set `pass`=SHA2(?, 256) where `uid`=?";
	
	//자동 로그인 저장
	public static final String UPDATE_USER_FOR_SESSION = "UPDATE `board_user` SET `sessId`=?, `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `uid`=?";
	//자동 로그인 세션 자동 연장
	public static final String UPDATE_USER_FOR_SESS_LIMIT_DATE = "UPDATE `board_user` SET `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `sessId`=?";
	//로그아웃 세션 말소
	public static final String UPDATE_USER_FOR_SESSTION_OUT = "UPDATE `board_user` SET `sessId`=NULL, `sessLimitDate`=NULL WHERE `uid`=?";
	
	
	//board
	
	//글 입력
	public static final String INSERT_ARTICLE = "insert into `board_article` set "
												+"`cate`=?,"
												+"`title`=?,"
												+"`content`=?,"
												+"`file`=?,"
												+"`uid`=?,"
												+"`regip`=?,"
												+"`rdate`=NOW()";
	
	//파일첨부
	public static final String INSERT_FILE = "insert into `board_file` set"
											+"`parent`=?,"
											+"`newName`=?,"
											+"`oriName`=?";
	
	//댓글달기
	public static final String INSERT_COMMENT = "insert into `board_article` set"
												+"`parent`=?, "
												+"`content`=?, "
												+"`uid`=?, "
												+"`regip`=?, "
												+"`rdate`=NOW()";
	
	//게시글의 최대넘버									
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `board_article`";
	
	//게시글의 총갯수
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(`no`) FROM `board_article` WHERE `parent`=0 AND `cate`=?";
	//검색된 게시글의 총갯수
	public static final String SELECT_COUNT_TOTAL_FOR_SEARCH = "SELECT COUNT(`no`) FROM `board_article` AS a "
															+"JOIN `board_user` AS b ON a.uid = b.uid "
															+"WHERE `cate`=? and `parent`=0 and (`title` LIKE ? OR `nick` LIKE ?)";
	//게시글 목록보기
	public static final String SELECT_ARTICLES = "SELECT a. *, b.nick FROM `board_article` AS a "
												+"JOIN `board_user` AS b "
												+"ON a.uid = b.uid "
												+"WHERE `parent` = 0 AND `cate`=? "
												+"ORDER BY `no` DESC "
												+"LIMIT ?, 10";
	//게시글 검색하기
	public static final String SELECT_ARTICLES_BY_KEYWORD = "SELECT * FROM `board_article` AS a "
														+ "JOIN `board_user` AS b ON a.uid = b.uid "
														+ "WHERE "
														+ "`cate`=? and "
														+ "`parent`=0 and (`title` LIKE ? OR `nick` LIKE ?) "
														+ "order by `no` desc "
														+ "limit ?, 10";
	//단일 글 가져오기(View)		
	public static final String SELECT_ARTICLE = "SELECT a. *, b.fno, b.parent AS pno, b.newName, b.oriName, b.download "
												+ "FROM `board_article` AS a "
												+ "LEFT JOIN `board_file` AS b "
												+ "ON a.`no` = b. `parent` "
												+ "WHERE `no`=?";
	//파일 가져오기
	public static final String SELECT_FILE = "select * from `board_file` where `parent`=?";
	//댓글 목록 가져오기
	public static final String SELECT_COMMENTS = "SELECT a.*, b.`nick` FROM `board_article` AS a "
													+ "JOIN `board_user` AS b "
													+ "ON a.uid = b.uid "
													+ "WHERE `parent` =? ORDER BY `no` ASC";
	//댓글 작성 시 최신 댓글 조회							
	public static final String SELECT_COMMENT_LATEST = "SELECT a.*, b.nick FROM `board_article` AS a "
														+ "JOIN `board_user` AS b USING(`uid`) "
														+ "WHERE `parent`!=0 ORDER BY `no` DESC LIMIT 1";
	//공지사항 최신글 조회
	public static final String SELECT_LATESTS = "(SELECT `no`, `cate`, `title`, `rdate` FROM `board_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5) "
												+ "UNION "
												+ "(SELECT `no`, `cate`, `title`, `rdate` FROM `board_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5) "
												+ "UNION "
												+ "(SELECT `no`, `cate`, `title`, `rdate` FROM `board_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5)";
	//공지사항 최신글 3개 조회
	public static final String SELECT_LATEST = "SELECT `no`, `cate`, `title`, `rdate` FROM `board_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 3";
	//게시글 수정
	public static final String UPDATE_ARTICLE = "update `board_article` set `title`=?, `content`=?, `rdate`=NOW() where `no`=?";
	
	// 게시글 조회수 증가
	public static final String UPDATE_ARTICLE_HIT = "UPDATE `board_article` SET `hit` = `hit` + 1 WHERE `no`=?";
	//파일 다운로드
	public static final String UPDATE_FILE_DOWNLOAD = "update `board_file` set `download` = `download` + 1 where `fno`=?";
	//댓글 갯수 증가
	public static final String UPDATE_ARTICLE_COMMENT_COUNT_PLUSE = "update `board_article` set `comment` = `comment` + 1 where `no`=?";
	//댓글 갯수 감소
	public static final String UPDATE_ARTICLE_COMMENT_COUNT_MINUS = "update `board_article` set `comment` = `comment` - 1 where `no`=?";
	//댓글 작성
	public static final String UPDATE_COMMENT = "update `board_article` set "
													+ "`content`=?, "
													+ "`rdate`=NOW() "
													+ "where `no`=?";
	//댓글 삭제
	public static final String DELETE_COMMENT = "delete from `board_article` where `no`=?";
	//게시글 삭제
	public static final String DELETE_ARTICLE = "delete from `board_article` where `no`=? or `parent`=?";
	//파일 삭제
	public static final String DELETE_FILE = "delete from `board_file` where `parent`=?";
}
