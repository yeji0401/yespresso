<%@page import="com.sh.yespresso.orders.model.dto.Orders"%>
<%@page import="com.sh.yespresso.orders.model.dto.OrderDetail"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page import="java.text.*" %>
<%
request.setCharacterEncoding("UTF-8");
String orderNo = (String) request.getAttribute("orderNo");
String orderMemberId = (String) request.getAttribute("orderMemberId");
List<OrderDetail> myOrdersDetail = (List<OrderDetail>) request.getAttribute("myOrdersDetail");
OrderDetail od = new OrderDetail();
int sumPrice = 0;
int sumAmount = 0;

	DecimalFormat df = new DecimalFormat("₩###,###");
	NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.KOREA);
for (int i = 0; i < myOrdersDetail.size(); i++) {
	od = myOrdersDetail.get(i);
}
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myOrdersList.css" />
	<section id="orderInfo-container">
		<table id="orderInfo-tbl">
		
			<tr>
				<td>주문번호</td>
			</tr>
			<tr>
				<td>20083939</td>
			</tr>
			<tr>
				<td>주문자아이디</td>
			</tr>
			<tr>
				<td>honggd</td>
			</tr>
			<tr>
				<td>주문일자</td>
			</tr>
			<tr>
				<td>20203030</td>
			</tr>
			<tr>
				<td>배송상세정보</td>
			</tr>
			<tr>
				<td>ㅇㅇㅇ님<br />ㅇㅇㅇ도 ㅇㅇ시 ㅇㅇ 1010-2020
				</td>
			</tr>
			<tr>
				<td>주문상태</td>
			</tr>
			<tr>
				<td>orderState</td>
			</tr>
		</table>
	</section>
	<section id="ordered-product-container">
		<table id="ordered-product-tbl">
			<colgroup>
				<col style="width: 89px">
				<col style="width: 121px">
				<col style="width: 71px">
				<col style="width: 25px">
				<col style="width: 58px">
				<col style="width: 82px">
				<col style="width: 72px">
			</colgroup>
			<tbody>
				<tr>
					<td colspan="5">주문완료</td>
				</tr>
				<tr>
					<td></td>
					<td>개별 단가</td>
					<td>수량</td>
					<td>합계</td>
					<td>리뷰작성</td>
				</tr>
				<%
			if (myOrdersDetail.isEmpty()) {
				myOrdersDetail = new ArrayList<OrderDetail>();
			%>
			<%
			} else {
			for (int i = 0; i < myOrdersDetail.size(); i++) {
				int totalPrice = myOrdersDetail.get(i).getProductPrice() * myOrdersDetail.get(i).getOrderDetailAmount();
				sumPrice = sumPrice + totalPrice;
				int totalAmount = myOrdersDetail.get(i).getOrderDetailAmount() + myOrdersDetail.get(i).getOrderDetailAmount();
				sumAmount = sumAmount + totalAmount;
			%>
				<tr>
					<td><%=myOrdersDetail.get(i).getProductName()%></td>
					<td><%=df.format(myOrdersDetail.get(i).getProductPrice())%></td>
					<td>X</td>
					<td><%=myOrdersDetail.get(i).getOrderDetailAmount()%></td>
					<td><%=df.format(totalPrice)%></td>
					<td>button "/myPage/myReviewEnroll" 삽입하기.</td>
				</tr>
			<%}
			} %>
				
				<tr>
					<td>전체 합계</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><%=df.format(sumPrice)%></td>
					<td></td>
				</tr>
			</tbody>
		</table>

		<hr style="margin-top: 30px; width: 300px;" />


	</section>


</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>