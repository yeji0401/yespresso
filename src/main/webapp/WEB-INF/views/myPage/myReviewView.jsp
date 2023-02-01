<%@page import="com.sh.yespresso.review.model.dto.Review"%>
<%@page import="java.util.List"%>
<%@page import="com.sh.yespresso.review.model.dto.ReviewAttachment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Review review = (Review) request.getAttribute("review");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myReviewView.css" />
<section id="review-container">
	<h2>나의 질문</h2>
	<table id="tbl-review-view">
		<tr>
			<th>글번호</th>
			<td><%=review.getReviewNo()%></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%=review.getReviewTitle()%></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=review.getReviewMemberId()%></td>
		</tr>
		<%
		if (!review.getReviewAttachment().isEmpty()) {
			for (ReviewAttachment reviewAttach : review.getReviewAttachment()) {
		%>
		<tr>
			<th>첨부파일</th>
			<td><img alt="첨부파일" src="<%=request.getContextPath()%>/images/file.png" width=16px> <a href="<%=request.getContextPath()%>/myPage/fileDownload?no=<%=reviewAttach.getReviewNo()%>"><%=reviewAttach.getReviewFileName()%></a></td>
		</tr>

		<%
		}
		}
		%>
		<tr>
			<th>내 용</th>
			<td><%=review.getReviewContent()%></td>
		</tr>
	</table>

	<hr style="margin-top: 30px;" />

	</div>


</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
