<%@page import="java.util.Locale"%>
<%@page import="java.text.*"%>
<%@page import="com.sh.yespresso.product.model.service.ProductService"%>
<%@page import="com.sh.yespresso.product.model.dto.CupSize"%>
<%@page import="com.sh.yespresso.product.model.dto.Aroma"%>
<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Product> products = (List<Product>) request.getAttribute("products");
	
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
</style>
<script>


</script>
    <section id="admin-container">
        <div id="admin-block">
            <ul class="admin-nav">
                <li class="member"><a href="<%= request.getContextPath() %>/admin/adminMemberList">회원 관리</a></li>
                <li class="product" style="font-weight: bold;"><a href="">제품 관리</a></li>
                <li class="orders"><a href="<%= request.getContextPath() %>/admin/adminOrdersList">주문 관리</a></li>
            </ul>
        </div>
        <h2>제품 관리</h2>
        <div id="product-search-block">
            <div id="search-product" class="search-type">
                <form action="<%= request.getContextPath() %>/admin/adminProductFinder">
                    <input type="hidden" name="searchType" value="product_name">
                    <input type="text" name="searchKeyword" size="25" placeholder="검색할 제품명을 입력하세요."
                        value="<%= "product_name".equals(searchType) ? searchKeyword : "" %>">
                    <button type="submit">검색</button>
                </form>
            </div>
            <div id="enroll-product">
                <form action="<%= request.getContextPath() %>/admin/adminProductEnroll">
                    <button type="submit">제품 등록</button>
                </form>
            </div>
        </div>    
        <table id="tbl-product" class="tbl">
            <thead>
                <tr>
                    <th>제품번호</th>
                    <th>제품사진</th>
                    <th>제품명</th>
                    <th>가격</th>
                    <th>재고</th>
                    <th>판매량</th>
                    <th>제품등록일</th>
                    <th>제품 타입</th>
                    <th>아로마</th>
                    <th>산미감</th>
                    <th>로스팅</th>
                    <th>컵사이즈</th>
                    <th>수정/삭제</th>
                </tr>
            </thead>
           <tbody>
			<% if(products.isEmpty()){ %>
				<tr>
					<td colspan="15">조회된 제품이 없습니다.</td>
				</tr>
			<% 
			   } else { 
				  for(Product product : products){
			%>
					<tr>
						<td><%= product.getProductNo() %></td>
						<td><img src="<%= request.getContextPath() %>/upload/product/<%= product.getThumbnailFilename() %>" alt="" style="width: 80px;"/> </td>
						<td><%= product.getProductName() %></td>
						<td style="color: #820000;"><%=df.format(product.getProductPrice())%></td>
						<td><%= product.getProductStock() %></td>
						<td><%= product.getProductSaleCnt() %></td>
						<td><%= product.getProductDate() %></td>
						<td><%= product.getType() %></td>
						<td><%= product.getAroma() != null ? (product.getAroma() == Aroma.none ? "" : product.getAroma()) : "" %></td>
						<td><%= product.getAcidity() != 0 ? product.getAcidity() : "" %></td>
						<td><%= product.getRoasting() != 0 ? product.getRoasting() : "" %></td>
						<td><%= product.getCupSize() != null ? (product.getCupSize() == CupSize.none ? "" : product.getCupSize()) : "" %></td>
						<td>
							<form action="<%= request.getContextPath() %>/admin/adminProductUpdate">
								<button type="submit" name ="productNo" value="<%= product.getProductNo() %>">수정</button>
							</form>
							<form action="<%= request.getContextPath() %>/admin/adminProductDelete" method="POST">
								<button type="submit" name ="productNo" value="<%= product.getProductNo() %>" onclick="confirmDeleteProuduct()">삭제</button>
							</form>
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
<script>
function confirmDeleteProuduct() {
    window.confirm("정말 이 제품을 삭제하시겠습니까?");
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>