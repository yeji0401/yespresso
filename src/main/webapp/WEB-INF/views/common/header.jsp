<%@page import="com.sh.yespresso.member.model.dto.MemberRole"%>
<%@page import="com.sh.yespresso.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yespresso</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.1.js"></script>
</head>
<body>
    <div id="container">
        <header>
            <div class="header-block">
                <div class="header-logo-block" onclick="location.href='<%= request.getContextPath() %>'"></div>
                <!-- 메인 메뉴 시작 -->
                <div class="main-block">
                    <ul class="main_nav">
                        <li class="coffee"><a href="<%= request.getContextPath() %>/product/coffeeList">COFFEE</a></li>
                        <li class="machine"><a href="<%= request.getContextPath() %>/product/productList">MACHINE</a></li>
                        <li class="accessories"><a href="<%= request.getContextPath() %>/product/productList">ACCESSORIES</a></li>
                        <li class="recipe"><a href="<%= request.getContextPath() %>">RECIPE</a></li>
                    </ul>
                    <ul class="main-block_2">
                        <li class="login"><a href="<%= request.getContextPath() %>/member/memberLogin">LOGIN</a></li>
                        <li class="cart"><a href="<%= request.getContextPath() %>">CART</a></li>
                    </ul>
                    
                    <ul class="main-block_3">
                    <% if(loginMember != null && loginMember.getMemberRole() == MemberRole.A) { %>
                        <li class="myadmin"><a href="<%= request.getContextPath() %>/admin/adminMemberList">ADMIN</a></li>
                    <% } else if (loginMember != null && (loginMember.getMemberRole() == MemberRole.C || loginMember.getMemberRole() == MemberRole.V)){ %>
                        <li class="myadmin"><a href="<%= request.getContextPath() %>/myPage/myPage">MYPAGE</a></li>
                    <% } else { %>
                    	<li class="myadmin">☕</li>
                   	<% } %>
                    </ul>                    
                <!-- 메인 메뉴 끝 -->
            	</div>
            </div>
        </header>

        <section id="content">
<!-- hj start -->
<!-- hj end -->

<!-- yeji start -->
<!-- yeji end -->

<!-- awon start -->
<!-- awon end -->

<!-- jooh start -->
<!-- jooh end -->
</body>
</html>