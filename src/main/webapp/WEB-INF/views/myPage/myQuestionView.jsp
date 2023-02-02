<%@page import="com.sh.yespresso.question.model.dto.Question"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sh.yespresso.question.model.dto.QuestionAttachment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Question question = (Question) request.getAttribute("question");
List<Question> answersList = (List<Question>) request.getAttribute("answersList");
String questionMemberId = (String) request.getAttribute("questionMemberId");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myQuestionView.css" />
<section id="question-container">
	<h2>나의 질문</h2>
	<table id="tbl-question-view">
		<tr>
			<th>글번호</th>
			<td><%=question.getQuestionNo()%></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%=question.getQuestionTitle()%></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=question.getQuestionMemberId()%></td>
		</tr>
		<%
		if (!question.getQuestionAttachments().isEmpty()) {
			for (QuestionAttachment questionAttach : question.getQuestionAttachments()) {
		%>
		<tr>
			<th>첨부파일</th>
			<td><img alt="첨부파일" src="<%=request.getContextPath()%>/images/file.png" width=16px> <a href="<%=request.getContextPath()%>/myPage/fileDownload?no=<%=questionAttach.getQuestionNo()%>"><%=questionAttach.getQuestionFilename()%></a></td>
		</tr>

		<%
		}
		}
		%>
		<tr>
			<th>내 용</th>
			<td><%=question.getQuestionContent()%></td>
		</tr>
	</table>

	<hr style="margin-top: 30px;" />

	<%
	if (!answersList.isEmpty()) {
	%>

	<%
	}
	%>
	</div>


</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
