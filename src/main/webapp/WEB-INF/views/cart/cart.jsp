<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="com.sh.yespresso.member.model.dto.Member"%>
<%@page import="com.sh.yespresso.cart.model.dto.Cart"%>
<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
String loginMemberId = loginMember.getMemberId();
Cart cart = (Cart) request.getAttribute("cart");
Product product = (Product) request.getAttribute("product");
List<Cart> myCartList = (List<Cart>) request.getAttribute("myCartList");
%>

<section id=cart-container>

	<form name="cartFrm" method="post" action="<%=request.getContextPath()%>/myPage/myOrdersList">
		<h2>나의 카트</h2>

		<table id="tbl-cart">
			<thead>
				<tr>
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
				%>
				<tr>
					<td colspan="5">장바구니에 담긴 품목이 없습니다.</td>
				</tr>
				<%
				} else {
				for (Cart carts : myCartList) {
				%>
				<tr>
					<td><%=product.getProductName()%></td>
					<td><%=product.getProductPrice()%></td>
					<td><%=cart.getAmount()%></td>
					<td><%=cart.getAmount()%> * <%=product.getProductPrice()%></td>
					<td><input type="button" onclick="deleteCart();" value="삭제" /></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
		<input type="submit" value="결제하기" />
	</form>
</section>

<form action="<%=request.getContextPath()%>/myPage/deleteMyCartList" method="POST" name="deleteMyCartListFrm"></form>
<script>
const deleteCart = () => {
	if(confirm('정말 삭제하시겠습니까?')){
		document.deleteMyCartListFrm.submit();
	}
};


document.memberUpdateFrm.onsubmit = (e) => {
	const memberName = document.querySelector("#memberName");
	const phone = document.querySelector("#phone");

	// 이름 - 한글 2글자이상
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		memberName.select();
		return false;
	}
	
	// 전화번호는 숫자 01012345678 형식
	if(!/^010[0-9]{8}$/.test(phone.value)){
		alert("전화번호가 유효하지 않습니다.");
		phone.select();
		return false;
	}
	
};

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
