/**
 * 
 */
 let preventDoubleClick = false;
 let isEmailAuthOk = false;
 
 $(function(){
	
		// 이메일 인증검사
		$('#btnEmailAuth').click(function() {
			
			$(this).hide();
			let email = $('input[name=email]').val();
			console.log('here1 : '+email);
			
			if(email == ''){
				alert('이메일을 입력하세요');
				return;
			}
			
			//여러번 클릭 방지
			if(preventDoubleClick){
				console.log('here2');
				return;
			}
			
			preventDoubleClick = true;
			
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
							
							$('.emailResult').text('인증코드를 전송 했습니다. 이메일을 확인하세요');
							$('.auth').show();
							receivedCode = data.code;
						}else{
							//메일발송 실패
							console.log('here6');
							
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
				isEmailAuthOk = true;
				$('input[name=email]').attr('readonly', true);
				$('.emailResult').text('이메일이 인증되었습니다.');
				$('.auth').hide();
			}else{
				isEmailAuthOk = false;
				alert('인증코드가 틀립니다. \n다시 확인 하십시오');
			}
			
		});
});