<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="com.sh.yespresso.member.model.dto.Gender"%>
<%@page import="com.sh.yespresso.member.model.dto.MemberRole"%>
<%@page import="com.sh.yespresso.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<section id=enroll-container>
	<h2>나의 회원 정보</h2>
	<form name="memberUpdateFrm" method="post" action="<%= request.getContextPath() %>/myPage/myAccountUpdate">
		<table>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" name="memberId" id="memberId" value="<%= loginMember.getMemberId() %>" readonly>
				</td>
			</tr>
			<tr>
				<th>회원권한<sup>*</sup></th>
				<td>
					<input type="text" name="memberRole" id="memberRole" value="<%= loginMember.getMemberRole() %>" readonly>
				</td>
			</tr>
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value="<%= loginMember.getMemberName() %>"  required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="<%= loginMember.getBirthday() %>"><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= loginMember.getEmail() != null ? loginMember.getEmail() : "" %>"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="<%= loginMember.getPhone() %>" required><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
			       		 <input type="radio" name="gender" id="gender0" value="M" <%= loginMember.getGender() == Gender.M ? "checked" : "" %>>
						 <label for="gender0">남</label>
						 <input type="radio" name="gender" id="gender1" value="F" <%= loginMember.getGender() == Gender.F ? "checked" : "" %>>
						 <label for="gender1">여</label>
				</td>
			</tr>
		</table>
        <input type="submit" value="정보수정"/>
        <input type="button" value="비밀번호변경" onclick="updatePassword();"/>
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
</section>

<form action="<%= request.getContextPath() %>/myPage/myAccountDelete" method="POST" name="memberDeleteFrm"></form>
<script>
const deleteMember = () => {
	if(confirm('정말 회원탈퇴하시겠습니까?')){
		document.memberDeleteFrm.submit();
	}
};

/**
 * 기존비밀번호입력
 * 새비밀번호/비밀번호 확인
 * 
 * 기존비밀번호가 일치하면, 새비밀번호 업데이트
 * 기존비밀번호가 일치하면, 새비밀번호 업데이트 취소
 * 
 * GET /mvc/member/updatePassword 비밀번호 변경폼 요청
 * POST /mvc/member/updatePassword db비밀번호 변경 요청
 * 
 */
const updatePassword = () => {
	location.href = "<%= request.getContextPath() %>/myPage/myPasswordUpdate";
};

document.memberUpdateFrm.onsubmit = (e) => {
	const memberName = document.querySelector("#memberName");
	const phone = document.querySelector("#phone");

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
