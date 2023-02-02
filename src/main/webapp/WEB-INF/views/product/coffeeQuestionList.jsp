<%@page import="com.sh.yespresso.question.model.dto.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product/productReview.css" />
<%
	List<Question> questionList = (List<Question>) request.getAttribute("questionList");

%>
<style>
#product-question-container {
	position: relative;
	background-color: white;
}
#qna-tbl {
	width: 100%;
    border: 0;
    border-spacing: 0;
    border-collapse: collapse;
    border-top: 1px solid #999999;
    text-align: center;
}
#qna-tbl th {
    padding: 10px 10px 10px 14px;
    color: #555555;
}
#qna-tbl tr {
    display: table-row;
    vertical-align: inherit;
    border-color: inherit;
}
#qna-tbl td {
	padding: 13px 10px 15px 18px;
    border-bottom: 1px solid #dbdbdb;
}
#qna-tbl tbody {
    display: table-row-group;
    vertical-align: middle;
    border-color: inherit;
}
.board_tit {
    text-align: left;
    vertical-align: middle;
}
colgroup {
    display: table-column-group;
}
.qna-title {
	font-weight: bold;
	display: block;
}
tr.level2 {
	background-color: gray;
}
</style>

<section id="product-question-container">
	<h3>제품문의</h3>
	<table id="qna-tbl">
		<colgroup>
			<col width="5%">
			<col>
			<col width="13%">
			<col width="13%">
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
		<% if(!questionList.isEmpty()){
			int qNo = 0;
			for(int i = 0; i < questionList.size(); i++){
				if(questionList.get(i).getQuestionLevel() == 1){
					String filterId = questionList.get(i).getQuestionMemberId().substring(0,3) + "*****";
					qNo++;
		%>
					<tr class="level1">
						<td><%= qNo %></td>
						<td class="board_tit">
							<span class="qna-title"><%= questionList.get(i).getQuestionTitle() %></span>
							<span class="qna-cont"><%= questionList.get(i).getQuestionContent() %></span>
						</td>
						<td><%= filterId %></td>
						<td><%= questionList.get(i).getQuestionDate() %></td>
					</tr>
<%          
			} else { 
%>
					<tr class="level2">
						<td></td>
						<td class="board_tit">
							<span class="qna-cont"><%= questionList.get(i).getQuestionContent() %></span>
						</td>
						<td>관리자</td>
						<td><%= questionList.get(i).getQuestionDate() %></td>
					</tr>
<% 
			}
		} // for문 끝
	} // if문 끝
%>	
		</tbody>
	</table>	

	<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
	</div>
</section>