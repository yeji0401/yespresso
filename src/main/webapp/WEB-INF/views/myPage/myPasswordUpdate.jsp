<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myPasswordUpdate.css" />
<section id=enroll-container>

	<form name="passwordUpdateFrm" id="passwordUpdateFrm" method="post" class="accviewwrap">
		<table>
			<tr>
				<th><p style="font-size: 30px; text-align: center; color: #3C6255;">비밀번호 변경</p></th>
			</tr>
			<tr>
				<td>
					<p style="font-size: 17px;">현재 비밀번호</p> <input type="password" name="oldPassword" id="oldPassword" required>
				</td>
			</tr>
			<tr>

				<td>
					<p style="font-size: 17px;">새로운 비밀번호</p> <input type="password" name="newPassword" id="newPassword" required>
				</td>
			</tr>
			<tr>
				<td>
					<p style="font-size: 17px;">비밀번호 확인</p> <input type="password" id="newPasswordCheck" required><br>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input type="submit" value="변경" /></td>
			</tr>
		</table>
	</form>
</section>
<script>
	const oldPassword = document.querySelector("#oldPassword");
	const newPassword = document.querySelector("#newPassword");
	const newPasswordCheck = document.querySelector("#newPasswordCheck");
	/**
	 * 폼제출 유효성검사
	 */
	document.passwordUpdateFrm.onsubmit = function() {

		if (!passwordValidate()) {
			return false;
		}

		if (oldPassword.value == newPassword.value) {
			alert("기존비밀번호와 신규비밀번호는 같을 수 없습니다.");
			oldPassword.select();
			return false;
		}
	};

	document.querySelector("#newPasswordCheck").onblur(passwordValidate);

	/**
	 * 신규비밀번호 일치 검사 
	 */
	function passwordValidate() {
		if (newPassword.value !== newPasswordCheck.value) {
			alert("입력한 비밀번호가 일치하지 않습니다.");
			newPassword.select();
			return false;
		}
		return true;
	}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
