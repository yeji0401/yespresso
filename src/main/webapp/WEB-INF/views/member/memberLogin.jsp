<%@page import="com.sh.yespresso.member.model.dto.MemberRole"%>
<%@page import="com.sh.yespresso.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.1.js"></script>
<%
	String msg = (String) session.getAttribute("msg");
	if (msg != null)
	session.removeAttribute("msg");

	Cookie[] cookies = request.getCookies();
	String saveId = null;
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			// System.out.println(name + "=" + value);
			if ("saveId".equals(name))
		saveId = value;
		}
	}

%>
<script>
window.addEventListener('load', () => {
	
	<% if(msg != null) { %>
		alert("<%= msg %>"); // alert(아이디가 존재하지 않거나 비밀번호가 틀립니다.)
	<% } %>
	
	<% if (loginMember == null) { %>
	document.loginFrm.addEventListener('submit', (e) => {
		const memberId = document.querySelector("#memberId");
		const password = document.querySelector("#password");
		
		if(!/^[A-Za-z0-9]{4,}$/.test(memberId.value)){
			alert("유효한 아이디를 입력하세요.");
			memberId.select();
			e.preventDefault(); // 폼제출방지
			return; // 조기리턴
		}
		
		if(!/^[A-Za-z0-9!@#$%]{4,}$/.test(password.value)){
			alert("유효한 비밀번호를 입력하세요");
			password.select();
			e.preventDefault();
			return;
		}
	});
	<% } %>
	
});

</script>
    <section id="login-container">
   
       
		
				<!-- 로그인폼 시작 -->
       
	        <form 
	        	class="wrap"
	            id="loginFrm"
	            name="loginFrm" 
	            action="<%= request.getContextPath() %>/member/memberLogin"
	            method="POST">
	            
	            <table>
	            	<tr>
	            		<td style="font-size: 25px; color: #3C6255;"><b>로그인</b> <br /> <br /></td>
	            	</tr>
	                <tr>
	                    <td><p style="font-size: 15px; padding-bottom:10px;">아이디</p><input type="text" name="memberId" id="memberId" placeholder="아이디" tabindex="1" style="padding-left:10px;" required></td>
	                </tr>
	                <tr>
	                    <td><p style="font-size: 15px; padding-top:10px; padding-bottom:10px;">비밀번호</p><input type="password" name="password" id="password" placeholder="비밀번호" tabindex="2" style="padding-left:10px;" required></td>
	                </tr>
	                <tr>
	                    <td>
	                        &nbsp; <input type="checkbox" name="saveId" id="saveId" <%= saveId != null ? "checked" : "" %>  /> 
	                        <label for="saveId" style="font-size: 12px; padding-left:2px;">아이디저장</label>
	                    </td>
	                </tr>
	                <tr>
	                    <td style="padding-top: 10px;"><input type="submit" value="로그인" tabindex="3"></td>
	                    
	                </tr>
	                
	                <tr>
	                    <td><p style="font-size: 12px; text-align: center;"> <br /> <u>예스프레소가 처음이신가요?</u></p> <br />
	                    <input type="button" value="회원가입" onclick="location.href = '<%= request.getContextPath() %>/member/memberEnroll';" tabindex="4"></td>
	                </tr>
	            </table>
	    
	    
	    </form>
   		<!-- 로그인폼 끝-->
   		
   		</div>
    
<%@ include file="/WEB-INF/views/common/footer.jsp" %>