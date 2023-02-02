<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page Index</title>
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" /> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myPageIndex.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@100;400;600;700;900&display=swap" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
</head>
<body>
	<!-- 마이페이지 메뉴 영역 시작 -->
	<div class="my-page-menu-block">
		<div class="my-account-link-block">
			<a href="<%=request.getContextPath()%>/myPage/myAccountView">나의 회원 정보</a>
		</div>
		<div class="my-orders-link-block">
			<a href="<%=request.getContextPath()%>/myPage/myOrdersList">나의 주문 내역</a>
		</div>
		<div class="my-reviews-link-block">
			<a href="<%=request.getContextPath()%>/myPage/myQuestionsList">나의 질문 내역</a>
		</div>
		<div class="my-questions-link-block">
			<a href="<%=request.getContextPath()%>/myPage/myReviewsList">나의 리뷰 내역</a>
		</div>
	</div>

</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>