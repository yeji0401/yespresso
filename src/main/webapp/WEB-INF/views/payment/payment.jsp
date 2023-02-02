<%@page import="java.text.*"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sh.yespresso.cart.model.dto.CartProduct"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cartMemberId = (String) request.getAttribute("cartMemberId");
	List<CartProduct> myCartList = (List<CartProduct>) request.getAttribute("myCartList");
	CartProduct cartProduct = new CartProduct();
	int sumPrice = 0;
	int sumAmount = 0;
	for (int i = 0; i < myCartList.size(); i++) {
		cartProduct = myCartList.get(i);
	}
	int tax = 0;
	int total = 0;
	DecimalFormat df = new DecimalFormat("₩###,###");
	NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.KOREA);
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!-- 결제용 payment.css link -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/payment/payment.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap"
	rel="stylesheet">
<style>
</style>
<section id="payment-container">
	<h2>주문 내역 확인</h2>
	<div id="payment">
		<div id="member-info">
			<table id="tbl-member-info">
				<tr>
					<th colspan="2">배송 상세 정보</th>
				</tr>
				<tr>
					<td colspan="2" style="font-weight: bold">[<%=loginMember.getMemberName()%>
						님]
					</td>
				</tr>
				<tr>
					<td colspan="2"><%=loginMember.getAddress() == null ? " " : loginMember.getAddress() %></td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/upload/payment/delivery.png"
						width="80px" /></td>
					<td><span style="font-weight: bold">일반 택배 배송</span></td>

				</tr>
				<tr>
					<th colspan="2">청구 정보</th>
				</tr>
				<tr>
					<td colspan="2"><%=loginMember.getAddress() == null ? " " : loginMember.getAddress() %></td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/upload/payment/credit-card.png"
						width="60px" /></td>
					<td><span style="font-weight: bold">신용카드 결제</span></td>
				</tr>
			</table>
		</div>
		<div id="cart">
			<table id="tbl-cart">
				<thead>
					<tr>
						<th>제품명</th>
						<th>개별 단가</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (myCartList.isEmpty()) {
						myCartList = new ArrayList<CartProduct>();
					%>
					<tr>
						<td colspan="4"></td>
					</tr>
				<%
				} else {
				for (int i = 0; i < myCartList.size(); i++) {
					int totalPrice = myCartList.get(i).getProductPrice() * myCartList.get(i).getAmount();
					// 소계 = 가격 * 수량
					sumPrice = sumPrice + totalPrice;
					int totalAmount = myCartList.get(i).getAmount() + myCartList.get(i).getAmount();
					sumAmount = sumAmount + totalAmount;
					tax = (int) (sumPrice * 0.1);
					total = sumPrice + tax;
					
				%>
					<tr>
						<td><%=myCartList.get(i).getProductName()%></td>
						<td><%=df.format(myCartList.get(i).getProductPrice())%></td>
						<td><%=myCartList.get(i).getAmount()%></td>
						<td style="padding: 0px 10px 0px 0px; text-align: right;"><%=df.format(totalPrice)%></td>
					</tr>
					<%
					}
					}
					%>
					<tr style="height: 50px;"></tr>
					<tr>
						<td>소계</td>
						<td colspan="3" style="padding: 0px 10px 0px 0px; text-align: right;"><%=df.format(sumPrice)%></td>
					</tr>
					<tr>
						<td>부가세</td>
						<td colspan="3" style="padding: 0px 10px 0px 0px; text-align: right;"><%=df.format(tax)%></td>
					</tr>
					<tr>
						<td style="font-weight: bold;">전체 합계</td>
						<td colspan="3" id="total" style="padding: 0px 10px 0px 0px; text-align: right;"><%=df.format(total)%>​​</td>
					</tr>
					<tr>
						<td colspan="4"><button type="submit" onclick="complete()">결제 진행하기</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</section>
<script>
function complete() {
	if(window.confirm("결제 진행하시겠습니까?")) {
		alert('주문해 주셔서 감사합니다.');
	    location.href = "<%=request.getContextPath()%>";
	} else {
		location.href = "<%=request.getContextPath()%>/payment/payment";
		}

	}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>