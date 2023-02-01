<%@page import="com.sh.yespresso.question.model.dto.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
List<Question> myQuestionsList = (List<Question>) request.getAttribute("myQuestionsList");

String questionMemberId = loginMember.getMemberId();
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myQuestionsList.css" />
<section id="question-container">
	<h2>게시판</h2>

	<table id="tbl-question-list">
		<thead>
			<tr>
				<!-- QUESTION_NO, QUESTION_PRODUCT_NO, QUESTION_TITLE, QUESTION_DATE -->
				<th>질문 제품</th>
				<th>질문 번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>첨부파일</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (myQuestionsList.isEmpty()) {
			%>
			<tr>
				<td colspan="5">조회된 질문이 없습니다.</td>
			</tr>
			<%
			} else {
			for (Question question : myQuestionsList) {
			%>
			<tr>
				<td><%=question.getQuestionNo()%></td>
				<td><%=question.getQuestionProductNo()%></td>
				<td><a href="<%=request.getContextPath()%>/myPage/myQuestionView?no=<%=question.getQuestionNo()%>"><%=question.getQuestionTitle()%></a></td>
				<td><%=question.getQuestionDate()%></td>
				<td>
					<%
					if (question.getAttachCnt() > 0) {
					%> <img src="<%=request.getContextPath()%>/images/file.png" style="width: 16px;"> <%
 }
 %>
				</td>
			</tr>
			<%
			}
			}
			%>
		</tbody>
	</table>

	<div id='pagebar'>
		<%=request.getAttribute("pagebar")%>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
