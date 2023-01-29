<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memberEnroll.css" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("address").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address").value = data.address; // 주소 넣기
                document.querySelector("input[name=detail]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>
<section id=enroll-container>
	<form 
		id="memberEnrollFrm"
		name="memberEnrollFrm" 
		method="POST" 
		action="<%= request.getContextPath() %>/member/memberEnroll">
		<table>
			<th>
				<th><p style="text-align = center;">회원가입</p></th>
			</th>
			
			<tr>
				<td>
					아이디<sup>*</sup>
					<input type="text" placeholder="4글자이상" name="memberId" id="_memberId" value="sinsa" required>
					<input type="button" value="아이디 중복검사" onclick="checkIdDuplicate();"/>
					<input type="hidden" id="idValid" name="idValid" value="0"/>
					<%-- 사용가능한 아이디인 경우 1, 이미 사용중인 아이디인 경우 0 --%>
				</td>
			</tr>
			<tr>
				<td>
					패스워드<sup>*</sup>
					<input type="password" name="password" id="_password" value="1234" required><br>
				</td>
			</tr>
			<tr>
				<td>
					패스워드확인<sup>*</sup>	
					<input type="password" id="passwordCheck" value="1234" required><br>
				</td>
			</tr>  
			<tr>
				<td>
					이름<sup>*</sup>	
					<input type="text"  name="memberName" id="memberName" value="신사임당" required><br>
				</td>
			</tr>
			<tr>
				<td>	
				생년월일
				<input type="date" name="birthday" id="birthday" value="1988-08-08"><br />
				</td>
			</tr> 
			<tr>
				<td>
					성별
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F" checked>
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<td>	
				    이메일
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="sinsa@naver.com"><br>
				</td>
			</tr>
			<tr>
				<td>	
				    휴대폰<sup>*</sup>
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="01012344321" required><br>
				</td>
			</tr>
			<tr>
				<td>	
					주소<sup>*</sup>
					<input type="text" placeholder="동까지 기입" name="address" id="address" readonly><br>
					<input type="text" placeholder="상세주소" name="detail" id="detail" value="1016호" required/> <br />
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="가입" ></td>
			</tr>
		
		</table>
	</form>
</section>
<form action="<%= request.getContextPath() %>/member/checkIdDuplicate" name="checkIdDuplicateFrm">
	<input type="hidden" name="memberId" />
</form>
<script>
/**
 * 중복검사이후 다시 아이디를 수정한 경우.
 */
document.querySelector("#_memberId").onfocus = (e) => {
	document.querySelector("#idValid").value = "0";
};

const checkIdDuplicate = () => {
	const memberId = document.querySelector("#_memberId");
	if(!/^[A-Za-z0-9]{4,}$/.test(memberId.value)){
		alert("아이디는 영문자/숫자 4글자이상이어야합니다.");
		memberId.select();
		return;
	};
	
	// frm의 action주소를 사용하기 때문에 open의 url은 비워둔다.
	const title = "checkIdDuplicatePopup"; 
	open("", title, "width=300px, height=200px, left=100px, top=100px");
	
	const frm = document.checkIdDuplicateFrm 
	frm.target = title; // 폼을 팝업에 제출
	frm.memberId.value = memberId.value;
	frm.submit();
	
};

document.memberEnrollFrm.onsubmit = (e) => {
	const memberId = document.querySelector("#_memberId");
	const idValid = document.querySelector("#idValid");
	const password = document.querySelector("#_password");
	const passwordCheck = document.querySelector("#passwordCheck");
	const memberName = document.querySelector("#memberName");
	const phone = document.querySelector("#phone");

	// 아이디 - 영문자/숫자 4글자이상
	if(!/^[A-Za-z0-9]{4,}$/.test(memberId.value)){
		alert("아이디는 영문자/숫자 4글자이상이어야합니다.");
		memberId.select();
		return false;
	}
	
	// 아이디중복검사 통과여부
	if(idValid.value !== '1'){
		alert("아이디 중복검사 해주세요.");
		memberId.nextElementSibling.focus(); // 중복검사 버튼
		return false;
	}
	
	// 비밀번호 - 영문자/숫자/특수문자 !@#$% 4글자이상
	if(!/^[A-Za-z0-9!@#$%]{4,}$/.test(password.value)){
		alert("비밀번호는 영문자/숫자/특수문자!@#$% 구성된 4글자이상이어야합니다.");
		password.select();
		return false;
	}
	
	// 비밀번호/비밀번호확인 동일
	if(password.value !== passwordCheck.value){
		alert("두 비밀번호가 일치하지 않습니다.");
		password.select();
		return false;
	}
	
	// 이름 - 한글 2글자이상
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		memberName.select();
		return false;
	}
	
	// 전화번호는 숫자 01012345678 형식
	if(!/^010[0-9]{8}$/.test(phone.value)){
		alert("전화번호가 유효하지 않습니다.");
		phone.select();
		return false;
	}
	
};
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>