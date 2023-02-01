<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sh.yespresso.member.model.dto.Member"%>
<%@page import="com.sh.yespresso.cart.model.dto.Cart"%>
<%@page import="com.sh.yespresso.cart.model.dto.CartProduct"%>
<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
String cartMemberId = (String) request.getAttribute("cartMemberId");
List<CartProduct> myCartList = (List<CartProduct>) request.getAttribute("myCartList");
CartProduct cartProduct = new CartProduct();
int sum = 0;
for (int i = 0; i < myCartList.size(); i++) {
	cartProduct = myCartList.get(i);

}
%>
<section id=cart-container>

	<form name="cartFrm" method="post" action="<%=request.getContextPath()%>/myPage/myCartList">

		<table id="tbl-cart">
			<thead>
				<tr>
					<h2>나의 카트</h2>
					<th>제품명</th>
					<th>개별 단가</th>
					<th>수량</th>
					<th>합계</th>
					<th>삭제</th>
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
				for (CartProduct carts : myCartList) {
					int total = carts.getProductPrice() * carts.getAmount();
					// 소계 = 가격 * 수량
					sum = sum + total;
				%>
				<tr>
					<td><%=carts.getProductName()%></td>
					<td><%=carts.getProductPrice()%></td>
					<td><%=carts.getAmount()%></td>
					<td><%=total%></td>
					<td><input type="button" onclick="deleteCart();" value="삭제" /></td>
				</tr>

				<%
				}
				}
				%>
				<tr>
					<th>총액</th>
					<th></th>
					<th></th>
					<th><%=sum%></th>
					<th></th>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="결제하기" />
	</form>
</section>



<form action="<%=request.getContextPath()%>/cart/deleteMyCartList" method="POST" name="deleteMyCartListFrm"></form>
<script>
const deleteCart = () => {
	if(confirm('정말 삭제하시겠습니까?')){
		document.deleteMyCartListFrm.submit();
	}
};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
