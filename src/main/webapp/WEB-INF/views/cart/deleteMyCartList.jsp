<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sh.yespresso.member.model.dto.Member"%>
<%@page import="com.sh.yespresso.cart.model.dto.Cart"%>
<%@page import="com.sh.yespresso.cart.model.dto.CartProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String cartMemberId = (String) request.getAttribute("cartMemberId");
List<CartProduct> myCartList = (List<CartProduct>) request.getAttribute("myCartList");
int cartListNo = Integer.parseInt(request.getParameter("cartListNo"));

%>
</body>
</html>