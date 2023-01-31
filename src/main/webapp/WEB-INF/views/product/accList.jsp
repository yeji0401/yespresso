<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="product-list">
		<table id="coffee-list">
			<tbody>
<%
	for(Product coffee : coffeeList){
%>	
				<tr>
					<td>
						<div class="prod-cont">
							<div class="thumbnail">
								<img src="<%= request.getContextPath() %>/upload/product/<%= coffee.getThumbnailFilename() %>" alt="" style="width: 120px;"/>
							</div>
							<div class="prod-name"><%= coffee.getProductName() %></div>
							<p class="price"><%= coffee.getProductPrice() %>원</p>
						</div>
					</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
				</tr>
				
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
				</tr>
				
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
				</tr>
<%
	}
%>
			</tbody>			
		</table>
		
	</div>


</body>
</html>