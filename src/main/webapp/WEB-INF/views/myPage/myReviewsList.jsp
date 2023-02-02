<%@page import="com.sh.yespresso.review.model.dto.Review"%>
<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
List<Review> myReviewsList = (List<Review>) request.getAttribute("myReviewsList");
request.setCharacterEncoding("UTF-8");
String reviewMemberId = (String) request.getAttribute("reviewMemberId");
String productName = (String) request.getAttribute("productName");
Review review = new Review();
Product product = new Product();

for (int i = 0; i < myReviewsList.size(); i++) {
	review = myReviewsList.get(i);
}
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myReviewsList.css" />
<p style="font-size: 30px; font-weight: 200; text-align: center; color: #3C6255;">나의 리뷰 내역</p>
<section id="reviews-container">
	<table id="tbl-reviews-list">
		<tr id="col-name">
			<th>제품 이름</th>
			<th>리뷰 번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>첨부파일</th>
		</tr>
		<tbody>
			<%
			if (myReviewsList.isEmpty()) {
				myReviewsList = new ArrayList<Review>();
			%>
			<tr>
				<td colspan="5">조회된 리뷰가 없습니다.</td>
			</tr>
			<%
			} else {
			for (Review reviews : myReviewsList) {
			%>
			<tr>
				<td><%=review.getProductName()%></td>
				<td><%=review.getReviewNo()%></td>
				<td><%=review.getReviewTitle()%></td>
				<td><%=review.getReviewDate()%></td>
				<td>
					<%
					if (review.getAttachCnt() > 0) {
					%> <img src="<%=request.getContextPath()%>/images/file.png" style="width: 16px;"> <%
 }
 %>
				</td>
				<%
				}
				}
				%>
			</tr>
		</tbody>
	</table>
	<div id='pagebar'>
		<%=request.getAttribute("pagebar")%>
	</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>



