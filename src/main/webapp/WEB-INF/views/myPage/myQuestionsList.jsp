<%@page import="com.sh.yespresso.question.model.dto.Question"%>
<%@page import="com.sh.yespresso.product.model.dto.Product"%>
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
Product product = new Product();

for (int i = 0; i < myQuestionsList.size(); i++) {
	question = myQuestionsList.get(i);
}
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myQuestionsList.css" />
<p style="font-size: 30px; font-weight: 200; text-align: center; color: #3C6255;">나의 질문 내역</p>
<section id="questions-container">
	<table id="tbl-questions-list">
		<tr id="col-name">
			<th>제품 이름</th>
			<th>질문 번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>첨부파일</th>
		</tr>
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
				<td><%=question.getProductName()%></td>
				<td><%=question.getQuestionNo()%></td>
<%-- 				<td><a href="<%=request.getContextPath()%>/myPage/myQuestionView?no=<%=question.getQuestionNo()%>"><%=question.getQuestionTitle()%></a></td> --%>
				<td><%=question.getQuestionTitle()%></td>
				<td><%=question.getQuestionDate()%></td>
				<td>
					<%
					if (question.getAttachCnt() > 0) {
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



