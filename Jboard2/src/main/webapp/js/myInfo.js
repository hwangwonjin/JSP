/**
 * 2022/12/05
 * 수행평가
 */
 
 //데이터 검증에 사용하는 정규표현식
	let reUid 	= /^[a-z]+[a-z0-9]{5,19}$/g;
	let rePass 	= /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
	let reName 	= /^[가-힣]+$/
	let reNick 	= /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	let reHp = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
	
	//폼 데이터 검증 결과 상태변수
	let isUidOk = false;
	let isPassOk = false;
	let isNameOk = false;
	let isNickOk = false;
	let isEmailOk = false;
	let isHpOk = false;
	let isEmailAuthOk =false; 
	let isEmailAuthCodeOk =false;
	let receivedCode = 0;