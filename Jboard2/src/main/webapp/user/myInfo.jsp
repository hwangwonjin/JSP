<%@ page  contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:include page="./_header.jsp"/>
<script src="/Jboard2/js/validation.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/Jboard2/js/zipcode.js"></script>
<script>
	$(function() {
			
			//비밀번호 검사하기
			$('.updatePass').focusout(function(){
				e.preventDefault();
				
				console.log('click updatePass....');
				
				let pass1 = $('input[name=pass1]').val();
				let pass2 = $('input[name=pass2]').val();
				let uid = $('#uid').text();
				
				if(pass2.match(rePass)){
					
					if(pass1 == pass2){
						isPassOk = true;
						$('.passResult').css('color', 'green').text('사용할 수 있는 비밀번호입니다.');

						let jsonDate = 
						
					}else{
						isPassOk = false;
						$('.passResult').css('color', 'red').text('비밀번호가 일치하지 않습니다.');

					}
					
				}else{
					isPassOk = false;
					$('.passResult').css('color', 'red').text('비밀번호는 숫자, 영문, 특수문자 포함 5자리 이상 이어야 합니다.');
				}
				
			});
			
	
		
		
		
		$('.withdraw').click(function(e) {
			e.preventDefault();
			
			console.log('click with...');
			
			let nick = $('input[name=uid]').val();
			
			let jsonData = {
						"uid" : uid	
			};
			
			console.log('click uid...');

			$.ajax({
				url : '/Jboard2/user/withdraw.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data) {
					
					if(data.result > 0){
						location.href = "/Jboard2/user/login.do"
					}else{
						alert('탈퇴가 실패하였습니다.')
					}
				}
			});
		});
	});
</script>	     
        <main id="user">
            <section class="register">
                <form action="/Jboard2/user/modify.do" method="post">
                <input type="hidden" name="uid" value="${sessUser.uid}">
                    <table border="1">
                        <caption> 회원정보 수정</caption>
                        <tr>
                            <td>아이디</td>
                            <td id="uid">${sessUserForPass.uid}</td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td>
                            <input type="password" name="pass1" placeholder="비밀번호 입력"/>
                            <span class="resultPass"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호 확인</td>
                            <td>
                            <input type="password" name="pass2" placeholder="비밀번호 입력 확인"/>
                           	<button type="button" class="updatePass">비밀번호 수정</button>
                            </td>
                        </tr>
                        <tr>
                        	<td>회원가입일</td>
                        	<td>${sessUserForPass.rdate}</td>
                        </tr>
                    </table>

                    <table border="1">
                        <caption>개인정보 입력</caption>
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" name="name" placeholder="이름 입력" value="${sessUserForPass.uid}"/>                        
                            	<span class="resultName"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>별명</td>
                            <td>
                                <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                                <input type="text" name="nick" placeholder="별명 입력" value="${sessUserForPass.nick}"/>
                                <button type="button" id="btnNickCheck" ><img src="../img/chk_id.gif" alt="중복확인"/></button>
                                <span class="nickResult"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td>
                                
                                <input type="email" name="email" placeholder="이메일 입력" value="${sessUserForPass.email}"/>
                                <span class="emailResult"></span>
                                <button type="button" id="btnEmailAuth"><img src="../img/chk_auth.gif" alt="인증번호 받기"/></button>
                                <div class="auth">
                                    <input type="text" name="auth" placeholder="인증번호 입력"/>
                                    <button type="button" id="btnEmailConfirm"><img src="../img/chk_confirm.gif" alt="확인"/></button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td><input type="text" name="hp" placeholder="휴대폰 입력" value="${sessUserForPass.hp}"/></td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <input type="text" name="zip" id="zip" placeholder="우편번호" value="${sessUserForPass.zip}"/>
                                <button type="button" onclick="zipcode()"><img src="../img/chk_post.gif" alt="우편번호찾기"/></button>
                                <input type="text" name="addr1" id="addr1" placeholder="주소 검색" value="${sessUserForPass.addr1}"/>
                                <input type="text" name="addr2" id="addr2" placeholder="상세주소 입력" value="${sessUserForPass.addr2}"/>
                            </td>
                        </tr>
                        <tr>
                        	<td>회원탈퇴</td>
                        	<td>
                        		<button type="button" style="padding: 6px; background: #ed2f2f; color: #fff;" class="withdraw">탈퇴하기</button>
                       		</td>
                        </tr>
                        
                    </table>

                    <div>
                        <a href="/Jboard2/user/login.do" class="btn btnCancel">취소</a>
                        <input type="submit" value="회원수정" class="btn btnRegister"/>
                    </div>

                </form>

            </section>
        </main>
<jsp:include page="./_footer.jsp"/>