<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@page import="com.sh.yespresso.product.model.dto.Type"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Product product = (Product) request.getAttribute("product");
	String thumb = product.getDetails().get(0).getReProductFilename();
	String det = product.getDetails().get(1).getReProductFilename();
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product/productList.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<style>
section#product-container{
	padding-top : 200px;
}
.product-info-wrap {
	border: 1px solid black;
}
.product-info-wrap td {
	width: 600px;
		border: 1px solid black;
}
.product-info-img img{
	height: 350px;
	padding: 0 0 35px 35px;
}
#tbl-product-details {
	position: relative;
}
.product-detail-txt img {
	width: 1200px;
}
.info-box {
	position: absolute;
	width: 460px; height: 430px;
	background-color: white;
	z-index: 9;
	transform: translate(685px, -100px);
	box-shadow: 0px 1px 16px #2d2d2d5c;
	text-align: center;
}
.pd-name {
	font-size: 30px;
	font-weight: bold;
}
.info-box span {
	display: block;
}

</style>

<section id ="product-container">
	<table id="tbl-product-details">
		<tr class="product-info-wrap">
			<td class="product-info-img">
				<img src="<%= request.getContextPath() %>/upload/productDetails/<%= thumb %>" alt=""/>
			</td>
				<div class="info-box">
					<span class="pd-type">
					<%
						String pdType = "ORIGINAL";
						
						if((product.getType()).equals("vertuo")){
							pdType = "VERTUO";} 
					%>
					<%= pdType %>
					</span>
					<span class="pd-name"><%= product.getProductName() %></span>
						
				</div>
		</tr>
		<tr class="product-detail-wrap">
			<td>
				<div class="product-detail-txt">
					<img src="<%= request.getContextPath() %>/upload/productDetails/<%= det %>" alt="" />
				</div>
			</td>
		</tr>
		<tr class="product-review-wrap">
			<td id="review-wrap"></td>
		</tr>
	</table>
		<div class="product-question-wrap">
			<div id="question-wrap"></div>
		</div>
</section>
<script>
window.addEventListener('load', () => {
	getReview("<%= product.getProductNo() %>");
	getQuestion("<%= product.getProductNo() %>");

});

// 비동기로 리뷰 가져오기
const getReview = (pdNo) => {
	$.ajax({
		url: "<%= request.getContextPath() %>/coffee/review",
		data : {pdNo},
		dataType : "html",
		success(data){
			console.log(data);
			document.querySelector("#review-wrap").innerHTML = data;
		},
		error : console.log
	});
};
// 비동기로 문의 가져오기
const getQuestion = (pdNo) => { 
	$.ajax({
		url: "<%= request.getContextPath() %>/coffee/question",
		data : {pdNo},
		dataType : "html",
		success(data){
			console.log(data);
			document.querySelector("#question-wrap").innerHTML = data;
		},
		error : console.log
	});
};

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>