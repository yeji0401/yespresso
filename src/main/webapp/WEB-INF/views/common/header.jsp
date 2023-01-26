<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yespresso</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<div class="header_block">
				<div class="header_logo_block" onclick="location.href='<%=request.getContextPath()%>'"></div>
				<!-- 메인 메뉴 시작 -->
				<div class="main_block">
					<ul class="main_nav">
						<li class="coffee"><a href="<%=request.getContextPath()%>/product/productList">COFFEE</a></li>
						<li class="machine"><a href="<%=request.getContextPath()%>/product/productList">MACHINE</a></li>
						<li class="accessories"><a href="<%=request.getContextPath()%>/product/productList">ACCESSORIES</a></li>
						<li class="recipe"><a href="<%=request.getContextPath()%>">RECIPE</a></li>
					</ul>
					<!-- awon start -->
					<%
					if (loginMember == null) {
					%>

					<!-- awon end -->
					<ul class="main_login_block">
						<li class="login"><a href="<%=request.getContextPath()%>/member/memberLogin">LOGIN</a></li>
						<li class="cart"><a href="<%=request.getContextPath()%>">CART</a></li>
					</ul>
					<!-- awon start -->
					<%
					} else {
					%>
					<!-- 로그인 후 마이페이지 시작 -->
					<ul class="main_already_login_block">
						<li class="already_login"><a href="<%=request.getContextPath()%>">MYPAGE</a></li>
						<li class="already_cart"><a href="<%=request.getContextPath()%>">CART</a></li>
					</ul>


					<%
					}
					%>
					<!-- awon end -->
					<ul class="admin_block">
						<li class="admin"><a href="<%=request.getContextPath()%>/admin/memberList">ADMIN</a></li>
					</ul>
					</nav>
					<!-- 메인 메뉴 끝 -->
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