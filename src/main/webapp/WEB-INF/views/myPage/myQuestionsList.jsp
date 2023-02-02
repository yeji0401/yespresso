<%@page import="com.sh.yespresso.question.model.dto.Question"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
List<Question> myQuestionsList = (List<Question>) request.getAttribute("myQuestionsList");
request.setCharacterEncoding("UTF-8");
String questionMemberId = (String) request.getAttribute("questionMemberId");
Question question = new Question();
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myQuestionsList.css" />
<section id="question-container">
	<h2>게시판</h2>
<section id="question-container">
	<table id="tbl-question-list">
		<thead>
			<tr>

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
				myQuestionsList = new ArrayList<Question>();
			%>
			<tr>
				<td colspan="5">조회된 질문이 없습니다.</td>
			</tr>
			<%
			} else {
			for (Question questions : myQuestionsList) {
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
	</section>

	<div id='pagebar'>
		<%=request.getAttribute("pagebar")%>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

