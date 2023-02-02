<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@100;400&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@100;400&family=Nanum+Myeongjo:wght@800&family=Ubuntu&display=swap" rel="stylesheet">
<style>
	table{margin:auto; width:80%; padding-top:150px;}
	tr,td{text-align: center; padding-bottom:20px;}
	td{font-size:30px;}
	input[type=button]{border-radius: 30px; width:200px; 
    height:40px; background:#A6BB8D; color: white; border: none; font-size:16px;}
    div.mainwrap{font-family: 'Gothic A1', sans-serif;position:relative; margin:40px auto; border:none; width: 100%; height:600px; background:#3C6255;}

</style>
<div class="mainwrap">
	<table>
			<tr>
				<td>취향에 맞게 먹는 커피</td>
			</tr>
			<tr>
				<td><b style=" font-family: 'Ubuntu', sans-serif; font-size:70px;">Coffee of your Choice!</b></td>
			</tr>
			<tr>
				<td style="padding-bottom:10px;">하루를 위한 커피, 집에서 시작하세요.</td>
			</tr>
			<tr>
				<td><input  type="button" value="시작하기" onclick="location.href = '<%= request.getContextPath() %>/coffee/coffeeChoice';"/></td>
			</tr>
		</table> 
	<img src="<%= request.getContextPath() %>/upload/coffee/coffeebin.png" style="width: 100px;" />
	<img src="<%= request.getContextPath() %>/upload/coffee/coffeebin.png" style="width: 100px;" />
	<img src="<%= request.getContextPath() %>/upload/coffee/coffeebin.png" style="width: 100px;" />
</div>	

<%@ include file="/WEB-INF/views/common/footer.jsp" %>