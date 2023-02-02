<%@page import="java.util.Locale"%>
<%@page import="java.text.*"%>
<%@page import="com.sh.yespresso.orders.model.dto.OrderState"%>
<%@page import="com.sh.yespresso.orders.model.dto.Orders"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Orders> orders = (List<Orders>) request.getAttribute("orders");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	
	DecimalFormat df = new DecimalFormat("₩###,###");
	NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.KOREA);
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- 관리자용 admin.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/admin.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
<style>
div#search-orderNo	 	  {display: <%= searchType == null || "order_no".equals(searchType) ? "inline-block" : "none" %>;}
div#search-orderMemberId  {display: <%= "order_member_id".equals(searchType) ? "inline-block" : "none" %>;}
div#search-orderDate 	  {display: <%= "order_date".equals(searchType) ? "inline-block" : "none" %>;}
</style>
<script>
window.addEventListener('load', () => {
	document.querySelector("#searchType").addEventListener('change', (e) => {
		console.log(e.target.value); // member_id, member_name
		
		document.querySelectorAll(".search-type").forEach((div) => {
			div.style.display = "none";
		});
		
		let id; 
		switch(e.target.value){
		case "order_no" : id = "search-orderNo"; break; 
		case "order_member_id" : id = "search-orderMemberId"; break; 
		case "order_date" : id = "search-orderDate"; break;
		}
		
		document.querySelector("#" + id).style.display = "inline-block";
	});
});
</script>
	<section id="admin-container">
        <div id="admin-block">
            <ul class="admin-nav">
                <li class="member"><a href="<%= request.getContextPath() %>/admin/adminMemberList">회원 관리</a></li>
                <li class="product"><a href="<%= request.getContextPath() %>/admin/adminProductList"">제품 관리</a></li>
                <li class="orders" style="font-weight: bold;"><a href="">주문 관리</a></li>
            </ul>
        </div>
        <h2>주문 관리</h2>
        <div id="search-block">
            <select id="searchType">
                <option value="order_no">주문번호</option>
                <option value="order_member_id">주문아이디</option>
                <option value="order_date">주문일자</option>
            </select>
            <div id="search-orderNo" class="search-type">
                <form action="<%= request.getContextPath() %>/admin/adminOrdersFinder">
                    <input type="hidden" name="searchType" value="order_no">
                    <input type="text" name="searchKeyword" size="25" placeholder="검색할 주문번호를 입력하세요."
                        value="">
                    <button type="submit">검색</button>
                </form>
            </div>
            <div id="search-orderMemberId" class="search-type">
                <form action="<%= request.getContextPath() %>/admin/adminOrdersFinder">
                    <input type="hidden" name="searchType" value="order_member_id">
                    <input type="text" name="searchKeyword" size="25" placeholder="검색할 주문아이디를 입력하세요."
                        value="">
                    <button type="submit">검색</button>
                </form>
            </div>
            <div id="search-orderDate" class="search-type">
                <form action="<%= request.getContextPath() %>/admin/adminOrdersFinder">
                    <input type="hidden" name="searchType" value="order_date">
                    <input type="text" name="searchKeyword" size="25" placeholder="검색할 주문일자를 입력하세요."
                        value="">
                    <button type="submit">검색</button>
                </form>
            </div>
        </div>       
        <table id="tbl-orders" class="tbl">
            <thead>
                <tr>
                    <th>주문번호</th>
                    <th>주문아이디</th>
                    <th>주문일자</th>
                    <th>주문총액</th>
                    <th>주문상태</th>
                </tr>
            </thead>
            <tbody>
			<% if(orders.isEmpty()){ %>
				<tr>
					<td colspan="4">조회된 주문이 없습니다.</td>
				</tr>
			<% 
			   } else {
				  for(Orders order : orders){
			%>
					<tr>
						<td><%= order.getOrderNo() %></td>
						<td><%= order.getOrderMemberId() %></td>
						<td><%= order.getOrderDate() %></td>
						<td style="color: #820000;"><%=df.format(order.getTotalPrice())%></td>
						<td>
							<select class="order-state" data-order-no="<%= order.getOrderNo() %>">
								<option value="<%= OrderState.B %>" <%= order.getOrderState() == OrderState.B ? "selected" : "" %>>B (배송 전)</option>
								<option value="<%= OrderState.D%>" <%= order.getOrderState() == OrderState.D ? "selected" : "" %>>D (배송 중)</option>
								<option value="<%= OrderState.F %>" <%= order.getOrderState() == OrderState.F ? "selected" : "" %>>F (배송 완료)</option>
							</select>
						</td>
					</tr>
			<%
				  }			
				} 
			%>            
            </tbody>
        </table>
		<div id="pagebar">
			<%= request.getAttribute("pagebar") %>
		</div>
		</section>
<form action="<%= request.getContextPath() %>/admin/adminUpdateOrderState" name="orderStateUpdateFrm" method="POST">
	<input type="hidden" name="orderNo" />
	<input type="hidden" name="orderState" />
</form>
<script>
document.querySelectorAll(".order-state").forEach((select) => {
	select.addEventListener('change', (e) => {
		const orderNo = e.target.dataset.orderNo;
		const orderState = e.target.value;
		
		if(confirm(`[\${orderNo}]의 배송 상태를 \${orderState}로 변경하시겠습니까?`)){			
			const frm = document.orderStateUpdateFrm;
			frm.orderNo.value = orderNo;
			frm.orderState.value = orderState;
			frm.submit();
		}
		else {
			// e.target(select)하위의 selected 속성이 있는 option태그
			e.target.querySelector("option[selected]").selected = true;
		}
		
	});
});
</script> 
<%@ include file="/WEB-INF/views/common/footer.jsp" %>