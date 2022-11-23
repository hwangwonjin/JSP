/**
 * 날짜 : 2022/10/21
 	이름 : 황원진
 	내용 : 사용자 정보
 	
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
	
	$(function(){
		
		
		//아이디 검사하기(select count)
		$('input[name=uid]').keydown(function(){
			isUidOk = false;
		});
		
		
		
		$('#btnIdCheck').click(function(){
			
			let uid =$('input[name=uid]').val();
			
			if(isUidOk){
				return;
			}
			
			if(!uid.match(reUid)){
				$('.uidResult').css('color','red').text('유효한 아이디가 아닙니다.');
				isUidOk = false;
				return;
			}
			
			
			let jsonData = {
				"uid": uid
			};
			
			$('.uidResult').css('color','black').text('....');
			
			setTimeout(function(){	//대기시간 부여
				$.ajax({
					url: '/Jboard2/user/checkUid.do',
					method: 'get',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						if(data.result == 0){
							
							$('.uidResult').css('color','green').text('사용 가능한 아이디 입니다.');
							isUidOk = true;
						}else{
							$('.uidResult').css('color','red').text('이미 사용중인 아이디 입니다.');
							isUidOk = false;
						}

					}

				});
			},1000);
			
		
			
		});
		

			
		
		//비밀번호 검사하기
		$('input[name=pass2]').focusout(function(){
			
			let pass1 = $('input[name=pass1]').val();
			let pass2 = $('input[name=pass2]').val();
			
			if(pass2.match(rePass)){
				
				if(pass1 == pass2){
					isPassOk = true;
					$('.passResult').css('color', 'green').text('사용할 수 있는 비밀번호입니다.');

				}else{
					isPassOk = false;
					$('.passResult').css('color', 'red').text('비밀번호가 일치하지 않습니다.');

				}
				
			}else{
				isPassOk = false;
				$('.passResult').css('color', 'red').text('비밀번호는 숫자, 영문, 특수문자 포함 5자리 이상 이어야 합니다.');
			}
			
		});
		
		
		
		//이름 검사하기
		$('input[name=name]').focusout(function(){
			
			let name = $(this).val();
			
			if(name.match(reName)){
				isNameOk = true;
				$('.nameResult').text('');
			}else{
				isNameOk = false;
				$('.nameResult').css('color', 'red').text('유효한 이름이 아닙니다.');
			}
			
		});
		
		//별명 검사하기
		
		$('input[name=nick]').keydown(function(){
			isNickOk = false;
		});
		
		
		
		$('#btnNickCheck').click(function(){
			
			let nick =$('input[name=nick]').val();
			
			if(isNickOk){
				return;
			}
			
			if(!nick.match(reNick)){
				$('.nickResult').css('color','red').text('유효한 별명이 아닙니다.');
				isNickOk = false;
				return;
			}
			
			
			let jsonData = {
				"nick": nick
			};
			
			$('.nickResult').css('color','black').text('....');
			
			setTimeout(function(){	//대기시간 부여
				$.ajax({
					url: '/Jboard2/user/checkNick.do',
					method: 'get',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						if(data.result == 0){
							$('.nickResult').css('color','green').text('사용 가능한 별명 입니다.');
							isNickOk = true;
						}else{
							$('.nickResult').css('color','red').text('이미 사용중인 별명 입니다.');
							isNickOk = false;
						}

					}

				});
			},1000);
			
		
			
		});
		//이메일 검사하기
		$('input[name=email]').focusout(function(){
			
			let email = $(this).val();
			
			if(email.match(reEmail)){
				isEmailOk = true;
				$('.emailResult').text('');
			}else{
				isEmailOk = false;
				$('.emailResult').css('color', 'red').text('유효하지 않는 이메일입니다.');
			}
		});
		
		
		// 이메일 인증검사
			
		
		$('#btnEmailAuth').click(function() {
			
			$(this).hide();
			let email = $('input[name=email]').val();
			console.log('here1 : '+email);
			
			if(email == ''){
				alert('이메일을 입력하세요');
				return;
			}
			
			if(isEmailAuthOk){
				console.log('here2');
				return;
			}
			
			isEmailAuthOk = true;
			
			$('.emailResult').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
			console.log('here3');
						
			setTimeout(function(){
				console.log('here4');
				$.ajax({
					url: '/Jboard2/user/emailAuth.do',
					method: 'get',
					data: {"email":email},
					dataType: 'json',
					success: function(data) {
						//console.log(data);
						if(data.status > 0){
							//메일발송 성공
							console.log('here5');
							isEmailAuthOk = true;
							$('.emailResult').text('인증코드를 전송 했습니다. 이메일을 확인하세요');
							$('.auth').show();
							receivedCode = data.code;
						}else{
							//메일발송 실패
							console.log('here6');
							isEmailAuthOk = false;
							alert('메일전송이 실패했습니다. \n 다시 시도 하시기 바랍니다.');
						}
					}
					
				});
				
			});
			
			
		});
		
		
		//이메일 인증코드 확인
		$('#btnEmailConfirm').click(function() {
			
			let code = $('input[name=auth]').val();
			
			if(code == ''){
				alert('이메일 확인 후 인증코드를 입력하세요');
				return;
			}
			
			if(code == receivedCode){
				isEmailAuthCodeOk = true;
				$('input[name=email]').attr('readonly', true);
				$('.emailResult').text('이메일이 인증되었습니다.');
				$('.auth').hide();
			}else{
				isEmailAuthCodeOk = false;
				alert('인증코드가 틀립니다. \n다시 확인 하십시오');
			}
			
		});
		
		
		
		//휴대폰 검사하기
		$('input[name=hp]').focusout(function(){
			
			let hp = $(this).val();
			
			if(hp.match(reHp)){
				isHpOk = true;
				$('.hpResult').text('');
			}else{
				isHpOk = false;
				$('.hpResult').css('color', 'red').text('유효하지 않는 휴대폰입니다.');
			}
		});
		
	});
	
	
	
		$(function(){
			
			//최종 폼 전송할 때 
			$('.register > form').submit(function(){
				
				//아이디검증
				if(!isUidOk){
					alert('아이디를 확인하십시오.');
					return false;
				}
				
				//비밀번호 검증
				if(!isPassOk){
					alert('비밀번호가 유효하지 않습니다.');
					return false;
				}
				
				//이름 검증
				if(!isNameOk ){
					alert('이름이 유효하지 않습니다.');
					return false;
				}
				
				//별명 검증
				if(!isNickOk){
					alert('별명이 유효하지 않습니다.');
					return false;
				}
				
				//이메일 검증
				if(!isEmailOk ){
					alert('이메일이 유효하지 않습니다.');
					return false;
				}
				
				//이메일 인증 검증
				if(!isEmailAuthOk ){
					alert('이메일을 인증하셔야 합니다.');
					return false;
				}
				
				//휴대폰 검증
				if(!isHpOk ){
					alert('휴대폰이 유효하지 않습니다.');
					return false;
				}
				
				return true;
				
			});
		});
	
	
	
 	
