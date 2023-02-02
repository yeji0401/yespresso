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
            <div id="check-block">
                <div id="product-sort">
                    <p>정렬순</p>
                    <input type="radio" name="product-sort" value="no-A" checked>
                    <label for="no-A">제품번호 오름차순</label><br>
                    <input type="radio" name="product-sort" value="no-D">
                    <label for="no-D">제품번호 내림차순</label><br>
                    <input type="radio" name="product-sort" value="price-A">
                    <label for="price-A">가격 오름차순</label><br>
                    <input type="radio" name="product-sort" value="price-D">
                    <label for="price-D">가격 내림차순</label><br>
                    <input type="radio" name="product-sort" value="stock-A">
                    <label for="stock-A">재고 오름차순</label><br>
                    <input type="radio" name="product-sort" value="stock-D">
                    <label for="stock-D">재고 내림차순</label><br>
                    <input type="radio" name="product-sort" value="p-enroll-A">
                    <label for="p-enroll-A">제품등록일 오름차순</label><br>
                    <input type="radio" name="product-sort" value="p-enroll-D">
                    <label for="p-enroll-D">제품등록일 내림차순</label>
                </div>
                <div id="product-type">
                    <p>제품 타입</p>
                    <input type="radio" name="type" value="vertuo">
                    <label for="vertuo">버츄오</label><br>
                    <input type="radio" name="type" value="original">
                    <label for="original">오리지널</label>
                </div>
                <div id="aroma">
                    <p>아로마</p>
                    <input type="radio" name="aroma" value="cocoa">
                    <label for="cocoa">코코아</label><br>
                    <input type="radio" name="aroma" value="biscuity">
                    <label for="biscuity">비스킷</label><br>
                    <input type="radio" name="aroma" value="fruity">
                    <label for="fruity">과일</label>
                </div>
                <div id="acidity">
                    <p>산미감</p>
                    <input type="radio" name="acidity" value="aw-1">
                    <label for="aw-1">1(약)</label><br>
                    <input type="radio" name="acidity" value="aw-2">
                    <label for="aw-2">2(약)</label><br>
                    <input type="radio" name="acidity" value="as-3">
                    <label for="as-3">3(강)</label><br>
                    <input type="radio" name="acidity" value="as-4">
                    <label for="as-4">4(강)</label><br>
                    <input type="radio" name="acidity" value="as-5">
                    <label for="as-5">5(강)</label>
                </div>
                <div id="roasting">
                    <p>로스팅</p>
                    <div id="roasting-c">
	                    <div id="roasting-w">
	                        <input type="radio" name="roasting" value="rw-1">
	                        <label for="rw-1">1(약)</label><br>
	                        <input type="radio" name="roasting" value="rw-2">
	                        <label for="rw-2">2(약)</label><br>
	                        <input type="radio" name="roasting" value="rw-3">
	                        <label for="rw-3">3(약)</label><br>
	                        <input type="radio" name="roasting" value="rw-4">
	                        <label for="rw-4">4(약)</label><br>
	                        <input type="radio" name="roasting" value="rw-5">
	                        <label for="rw-5">5(약)</label><br>
	                        <input type="radio" name="roasting" value="rw-6">
	                        <label for="rw-6">6(약)</label>
	                    </div>
	                    <div id="roasting-s">
	                        <input type="radio" name="roasting" value="rs-7">
	                        <label for="rs-7">7(강)</label><br>
	                        <input type="radio" name="roasting" value="rs-8">
	                        <label for="rs-8">8(강)</label><br>
	                        <input type="radio" name="roasting" value="rs-9">
	                        <label for="rs-9">9(강)</label><br>
	                        <input type="radio" name="roasting" value="rs-10">
	                        <label for="rs-10">10(강)</label><br>
	                        <input type="radio" name="roasting" value="rs-11">
	                        <label for="rs-11">11(강)</label><br>
	                        <input type="radio" name="roasting" value="rs-12">
	                        <label for="rs-12">12(강)</label><br>
	                        <input type="radio" name="roasting" value="rs-13">
	                        <label for="rs-13">13(강)</label>
	                    </div>
                    </div>
                </div>
                <div id="cup-size">
                    <p>컵사이즈</p>
                    <input type="radio" name="cup-size" value="S">
                    <label for="S">S : 25 ~ 40ml</label><br>
                    <input type="radio" name="cup-size" value="M">
                    <label for="M">M : 80 ~ 110ml</label><br>
                    <input type="radio" name="cup-size" value="L">
                    <label for="L">L : 150 ~ 230ml</label>
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
                    <th>
                    	<form action="<%= request.getContextPath() %>/admin/adminProductUpdate">
                    		<button type="submit">수정</button>
                    	</form>
                    	<form action="">
                    		<button type="submit">삭제</button>
                    	</form>
                    </th>
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
						<td style="color: #820000;">&#8361;<%= product.getProductPrice() %></td>
						<td><%= product.getProductStock() %></td>
						<td><%= product.getProductSaleCnt() %></td>
						<td><%= product.getProductDate() %></td>
						<td><%= product.getType() %></td>
						<td><%= product.getAroma() != null ? (product.getAroma() == Aroma.none ? "" : product.getAroma()) : "" %></td>
						<td><%= product.getAcidity() != 0 ? product.getAcidity() : "" %></td>
						<td><%= product.getRoasting() != 0 ? product.getRoasting() : "" %></td>
						<td><%= product.getCupSize() != null ? (product.getCupSize() == CupSize.none ? "" : product.getCupSize()) : "" %></td>
						<td><input type="checkbox" name="productNo" value="<%= product.getProductNo() %>"></td>
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
$("input[type='checkbox']").on("change", function(){
    if($(this).is(":checked"))
    {
        $.ajax({
            url: "<%=request.getContextPath() %>/admin/adminProductUpdate",
            type: 'POST',
            data: $(this).val(),
            success(data){
				console.log(data);
            },
            error(data)) {
				console.log(data);
			}
        })

    }
})

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>