<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <div class="header_block">
                <div class="header_logo_block">
                    <a href="<%= request.getContextPath() %>"><img class="header_logo" src="image/logo_basic.png" alt="yespresso_LOGO"></a>
                </div>
                <!-- 메인 메뉴 시작 -->
                <div class="main_block">
                    <ul class="main_nav">
                        <li class="coffee"><a href="<%= request.getContextPath() %>">COFFEE</a></li>
                        <li class="machine"><a href="<%= request.getContextPath() %>">MACHINE</a></li>
                        <li class="accessories"><a href="<%= request.getContextPath() %>">ACCESSORIES</a></li>
                        <li class="recipe"><a href="<%= request.getContextPath() %>">RECIPE</a></li>
                    </ul>
                    <ul class="main_login_block">
                        <li class="login"><a href="<%= request.getContextPath() %>">LOGIN</a></li>
                        <li class="cart"><a href="<%= request.getContextPath() %>">CART</a></li>
                    </ul>
                    <ul class="admin_block">
                        <li class="admin"><a href="<%= request.getContextPath() %>">ADMIN</a></li>
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