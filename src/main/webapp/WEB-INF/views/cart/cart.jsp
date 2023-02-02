<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sh.yespresso.member.model.dto.Member"%>
<%@page import="com.sh.yespresso.cart.model.dto.Cart"%>
<%@ page import="java.text.*"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cart/cart.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>

<%
request.setCharacterEncoding("UTF-8");
String cartMemberId = (String) request.getAttribute("cartMemberId");
List<Cart> myCartList = (List<Cart>) request.getAttribute("myCartList");
Cart cartProduct = new Cart();
int sumPrice = 0;
int sumAmount = 0;
for (int i = 0; i < myCartList.size(); i++) {
	cartProduct = myCartList.get(i);
}
DecimalFormat df = new DecimalFormat("₩###,###");
NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.KOREA);
%>
<section id=cart-container>

	<form name="cartFrm" method="get" action="<%=request.getContextPath()%>/payment/payment">

		<table id="tbl-cart">
			<tr id="cart-col">
				<td>제품명</td>
				<td>개별 단가</td>
				<td>수량</td>
				<td>합계</td>
			</tr>
			</tdead>
			<tbody>
				<%
				if (myCartList.isEmpty()) {
					myCartList = new ArrayList<Cart>();
				%>
				<tr>
					<td colspan="5">장바구니에 담긴 품목이 없습니다.</td>
				</tr>
				<%
				} else {
					for (int i = 0; i < myCartList.size(); i++) {
						int totalPrice = myCartList.get(i).getProductPrice() * myCartList.get(i).getAmount();
						// 소계 = 가격 * 수량
						sumPrice = sumPrice + totalPrice;
						int totalAmount = myCartList.get(i).getAmount() + myCartList.get(i).getAmount();
						sumAmount = sumAmount + totalAmount;
				%>
				<tr>
					<td><%=myCartList.get(i).getProductName()%></td>
						<td><%=df.format(myCartList.get(i).getProductPrice())%></td>
						<td><%=myCartList.get(i).getAmount()%></td>
					<td><%=df.format(totalPrice)%></td>
				</tr>
				<%
				}

				}
				%>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">총액</td>
					<%-- <td><%=sumAmount%></td> --%>
					<td><%=df.format(sumPrice)%></td>
				</tr>
				<tr>
				</tr>
			</tfoot>
		</table>
		<input type="submit" value="결제하기" id="pay" tabindex="4">
		</td>
	</form>



</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>
