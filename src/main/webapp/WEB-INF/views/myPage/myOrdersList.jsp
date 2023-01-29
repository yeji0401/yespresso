<%@page import="com.sh.yespresso.orders.model.dto.Orders"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
List<Orders> myOrdersList = (List<Orders>) request.getAttribute("myOrdersList");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myOrdersList.css" />
<section id="orders-container">
	<h2>게시판</h2>

	<table id="tbl-orders">
		<thead>
			<tr>
				<th>번호</th>
				<th>주문자아이디</th>
				<th>주문일자</th>
				<th>총가격</th>
				<th>주문상태</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (myOrdersList.isEmpty()) {
			%>
			<tr>
				<td colspan="5">조회된 주문내역이 없습니다.</td>
			</tr>
			<%
			} else {
			for (Orders orders : myOrdersList) {
			%>
			<tr>
				<td><a href="<%=request.getContextPath()%>/myPage/myOrdersDetail?no=<%=orders.getOrderNo()%>"><%=orders.getOrderNo()%></a></td>
				<td><%=orders.getOrderMemberId()%></td>
				<td><%=orders.getOrderDate()%></td>
				<td><%=orders.getTotalPrice()%></td>
				<td><%=orders.getOrderState()%></td>

			</tr>
			<%
			}
			}
			%>
		</tbody>
	</table>

	<div id='pagebar'>
		<%=request.getAttribute("pagebar")%>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
