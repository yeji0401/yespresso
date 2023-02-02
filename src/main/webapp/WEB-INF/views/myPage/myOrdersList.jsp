<%@page import="com.sh.yespresso.orders.model.dto.Orders"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page import="java.text.*"%>
<%
request.setCharacterEncoding("UTF-8");
String orderMemberId = (String) request.getAttribute("orderMemberId");
List<Orders> myOrdersList = (List<Orders>) request.getAttribute("myOrdersList");
Orders order = new Orders();
DecimalFormat df = new DecimalFormat("₩###,###");
NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.KOREA);
for (int i = 0; i < myOrdersList.size(); i++) {
	order = myOrdersList.get(i);
}
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myOrdersList.css" />
			<p style="font-size: 30px; font-weight: 200;text-align: center; color: #3C6255;">나의 주문 내역</p>
<section id="orders-container">
	<table id="tbl-orders-list">
		<tr id="col-name">
			<!-- ORDER_NO, ORDER_MEMBER_ID, ORDER_DATE, TOTALPRICE, ORDER_STATE -->
			<th>주문 일자</th>
			<th>주문 번호</th>
			<th>총금액</th>
			<th>주문 상태</th>
		</tr>
		<tbody>
			<%
			if (myOrdersList.isEmpty()) {
				myOrdersList = new ArrayList<Orders>();
			%>
			<tr>
				<td colspan="5">주문 내역이 없습니다.</td>
			</tr>
			<%
			} else {
			for (Orders orders : myOrdersList) {
			%>
			<tr>
				<td><%=orders.getOrderDate()%></td>
				<td><%=orders.getOrderNo()%></td>
				<td><%=df.format(orders.getTotalPrice())%></td>
				<td><%=orders.getOrderState()%></td>

			<%
			}
			}
			%>
			</tr>
		</tbody>
	</table>
<div id='pagebar'>
	<%=request.getAttribute("pagebar")%>
</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>



