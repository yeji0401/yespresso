<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <section id="login-container">
        
       
	        <form 
	        	class="wrap"
	            id="loginFrm"
	            name="loginFrm" 
	            action="<%= request.getContextPath() %>/member/memberlogin"
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
	                        &nbsp; <input type="checkbox" name="saveId" id="saveId" /> 
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
   
    </section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>