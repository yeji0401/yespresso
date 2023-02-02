<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sh.yespresso.member.model.dto.Member"%>
<%@page import="com.sh.yespresso.cart.model.dto.Cart"%>
<%@page import="com.sh.yespresso.cart.model.dto.CartProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

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
%>
<section id=cart-container>

	<form name="cartFrm" method="get" action="<%=request.getContextPath()%>/payment/payment">

		<table id="tbl-cart">
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
					<td><%=myCartList.get(i).getProductPrice()%></td>
					<td><%=myCartList.get(i).getAmount()%></td>
					<td><%=totalPrice%></td>
				</tr>
				<%
				}
				}
				%>
				<tr>
					<th>총액</th>
					<th></th>
					<th><%=sumAmount%></th>
					<th><%=sumPrice%></th>
					<th></th>
				</tr>
			</tbody>
		</table>
		<button onclick="location.href='index.jsp'">결제하기</button>
	</form>



</section>


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
