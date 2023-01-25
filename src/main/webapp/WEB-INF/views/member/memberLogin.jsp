<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="login-container">
        
        <form 
            id="loginFrm"
            name="loginFrm" 
            action="<%= request.getContextPath() %>/member/login"
            method="POST">
            <table>
                <tr>
                    <td><input type="text" name="memberId" id="memberId" placeholder="아이디" tabindex="1" required></td>
                </tr>
                <tr>
                    <td><input type="password" name="password" id="password" placeholder="비밀번호" tabindex="2" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="saveId" id="saveId" />
                        <label for="saveId">아이디저장</label>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="로그인" tabindex="3"></td>
                    <span>예스프레소가 처음이신가요?</span>
                    <td><input type="button" value="회원가입" tabindex="4"></td>
                </tr>
            </table>
    
    
    	</form>
    </div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>