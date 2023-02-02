<%@page import="com.sh.yespresso.review.model.dto.Review"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product/productReview.css" />
<%
	List<Review> reviewList = (List<Review>) request.getAttribute("reviewList");
	int totalRate = 0; // 별점총합
	double rating = 0; // 리뷰없을시 평점 0
	
	if(!reviewList.isEmpty()){
		for(Review review : reviewList){
			totalRate += review.getReviewRating();
		}
		rating = (Math.round(totalRate / reviewList.size() * 10) / 10.0);
	}
%>
<section id="product-review-container">
	<div class="product-review-rating">
		평점 : <%= rating %>
	</div>

<%
	if(!reviewList.isEmpty()){
		for(Review review : reviewList){
%>
	<div class="product-review-row">
		<div class="star_day_name">
			<strong><%= review.getReviewRating() %></strong>
			<span><%= review.getReviewDate() %></span>
			<span><%= review.getReviewMemberId() %></span>
		</div>
		<div class="text_file_cont">
			<div class="review-title"><%= review.getReviewTitle() %></div>
			<div class="review-cont"><%= review.getReviewContent() %></div>
			<div class="review-file"></div>
		</div>
	</div>

	<div id='pagebar'>
	</div>
<%
		} // for문 끝
	} // if문 끝
%>	
</section>