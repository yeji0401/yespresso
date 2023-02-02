<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
	table{margin:auto; width:80%; padding-top:200px;}
	tr,td{text-align: center; padding-bottom:20px;}
	td{font-size:30px;}
	input[type=button]{border-radius: 30px; width:200px; 
    height:40px; background:#A6BB8D; color: white; border: none; font-size:16px;}
    div.mainwrap{position:relative; margin:20px auto; border:none; width: 100%; height:600px; background:#3C6255;}
</style>
<div class="mainwrap">
	<table>
			<tr>
				<td>취향에 맞게 먹는 커피</td>
			</tr>
			<tr>
				<td><b>Coffee of your Choice!</b></td>
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