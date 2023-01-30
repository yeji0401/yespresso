<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="coffee">
		<table>
			<tr>
				<td>취향에 맞게 먹는 커피</td>
			</tr>
			<tr>
				<td>Coffee of your Choice!</td>
			</tr>
			<tr>
				<td>하루를 위한 커피, 집에서 시작하세요.</td>
			</tr>
			<tr>
				<td><input type="button" value="시작하기" onclick="location.href = '<%= request.getContextPath() %>/coffee/coffeeChoice';"/></td>
			</tr>
		</table>
	</div>	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>