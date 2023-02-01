<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
window.addEventListener('load', () => {
	$.ajax({
		url: "<%= request.getContextPath() %>/coffee/review",
		dataType : "json",
		success(data){
			console.log(data);
		},
		error : console.log
	})
});

</script>    
    
<style>
section#product-review-container {
    margin: 30px 0 0 0;
    background: #ffffff;
}
.product-review-row {
	display: table;
    width: 100%;
    margin-top: -1px;
    padding: 20px 0 20px 0;
    border-top: 1px solid #dbdbdb;
    border-bottom: 1px solid #dbdbdb;
}
.star_day_name {
display: table-cell;
width: 15%;
padding: 0 20px 0 40px;
}
.star_day_name strong {
display: block;
color: #ffcc00;
font-size: 20px;
}
.star_day_name span {
    display: block;
    padding: 5px 0 5px 0;
    color: #636363
}
.text_file_cont {
    display: table-cell;
    width: 85%;
    padding:10px 0 10px 10px;
    vertical-align: top;
    color: #100F0F;
    
}
.review-title {
	font-weight: bold;
}
.review-cont {
	padding: 10px 0;
}
</style>
<section id="product-review-container">
	<div class="product-review-row">
		<div class="star_day_name">
			<strong>★★★★</strong>
			<span>2023.02.01</span>
			<span>si*****</span>
		</div>
		<div class="text_file_cont">
			<div class="review-title">맛있어요</div>
			<div class="review-cont">붉은 베리와 잼류의 산뜻한 과일향이 달콤한 곡물향을 부드럽게 감쌉니다.</div>
			<div class="review-file"></div>
		</div>
	</div>

	<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
	</div>
</section>