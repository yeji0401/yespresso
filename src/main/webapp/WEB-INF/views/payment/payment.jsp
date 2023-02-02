<%@page import="com.sh.yespresso.orders.model.dto.Orders"%>
<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Product> products = (List<Product>) request.getAttribute("products");
	List<Orders> orders = (List<Orders>) request.getAttribute("orders");
%> 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- 결제용 payment.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/payment/payment.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
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
						<td colspan="2" style="font-weight: bold">[<%= loginMember.getMemberName() %> 님]</td>
					</tr>
					<tr>
						<td colspan="2"><%= loginMember.getAddress() %></td>
					</tr>
					<tr>
						<td><img src="<%= request.getContextPath() %>/upload/payment/delivery.png" width="80px" /></td>
						<td><span style="font-weight: bold">일반 택배 배송</span></td>	
		
					</tr>
					<tr>
						<th colspan="2">청구 정보</th>
					</tr>
					<tr>
						<td colspan="2"><%= loginMember.getAddress() %></td>
					</tr>
					<tr>
						<td><img src="<%= request.getContextPath() %>/upload/payment/credit-card.png" width="60px" /></td>
						<td><span style="font-weight: bold">신용카드 결제</span></td>		
					</tr>
				</table>
			</div>
			<div id="cart">
				<table id="tbl-cart">
					<tr>
						<th colspan="2">오리지널 커피 ()</th>
						<th>개별 단가</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<th colspan="2">버츄오 커피 ()</th>
						<th>개별 단가</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="5">합계 : 오리지널 커피() + 버츄오 커피()</td>
					</tr>
					<tr>
						<td>소계</td>
						<td colspan="4"></td>
					</tr>
					<tr>
						<td>부가세</td>
						<td colspan="4"></td>
					</tr>
					<tr>
						<td>전체 합계</td>
						<td colspan="4"></td>
					</tr>
					<tr>
						<td colspan="5"><input type="submit" value="결제 진행하기" onclick="complete()"/></td>
					</tr>
				</table>
			</div>
		</div>
	</section>
<script>
function complete() {
	
	if(window.confirm("결제 진행하시겠습니까?")) {
		alert('주문해 주셔서 감사합니다.');
	    location.href = "<%= request.getContextPath() %>";
	} else {
		location.href = "<%= request.getContextPath() %>/payment/payment";
	}
    

 }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>