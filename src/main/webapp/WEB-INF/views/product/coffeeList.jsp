<%@page import="com.sh.yespresso.product.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	int totalPage = (int) request.getAttribute("totalPage");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product/productList.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

<div class="productList-container">
	<!-- 상단 꾸미기 영역 -->
	<div class="addition">
		<div class="add-img">
			<img src="<%= request.getContextPath() %>/images/product/productList_coffee.jpg" style="width: 1200px;" />
		</div>
		<div class="add-text">
			<h2>COFFEE</h2>
		</div>
	</div>
	<div class="category-list">
		<ul>
			<li class="pad1">
			<span>필터</span>
			<span class="material-symbols-outlined">tune</span>
			</li>
			<li class="pad1"><span>버츄오</span></li>
			<li class="pad1"><span>오리지널</span></li>
			<li class="pad2"><span>낮은가격순</span></li>
			<li class="pad2"><span>높은강도순</span></li>
			<li class="pad1"><span>정렬</span></li>
		</ul>
	</div>


	<!-- 상품리스트 -->
	<div class="product-list">
		<table id="coffee-list">
			<tbody>
				<tr class="pdlist1">
				</tr>	
				<tr class="pdlist2">				
				</tr>
				<tr class="pdlist3">	
				</tr>
			</tbody>			
		</table>
	</div>

	<div id="pagebar">
		<div class="page-btn">1</div>
<%
		for(int i = 2; i < totalPage; i++){
%>
		<div class="page-btn"><%= i %></div>
<% 			
		}
%>
		<div class="page-btn"><%= totalPage %></div>
	</div>
</div>
<script>
window.addEventListener('load', () => {
	// 첫페이지 로드
	getPage(1);
});


document.querySelectorAll("#pagebar div").forEach((page) => {
	page.addEventListener('click', (e) => {
		const page = e.target;
		console.log(page.innerText);
		getPage(Number(page.innerText)); 
	});
});


const getPage = (page) => {
	$.ajax({
		url : "<%= request.getContextPath() %>/coffee/morePage",
		data : {page}, // 단축속성등록 {page: 1}
		dataType : "json",
		success(data){
			console.log(data);
			
			const pd1 = document.querySelector(".pdlist1");
			const pd2 = document.querySelector(".pdlist2");
			const pd3 = document.querySelector(".pdlist3");
			pd1.innerHTML = "";
			pd2.innerHTML = "";
			pd3.innerHTML = "";
			
			for(let i = 0; i < data.length; i++){
				
				const td = document.createElement("td");
				const div1 = document.createElement("div");
				const img = document.createElement("img");
				const div2 = document.createElement("div");
				const p = document.createElement("p");
				const a = document.createElement("a");

				
				if(i >= 0 && i < 4){
					div1.classList.add("prod-cont");
					a.setAttribute("href", `<%= request.getContextPath() %>/product/coffeeDetails?no=\${data[i].productNo}`);
					
					img.src = `<%= request.getContextPath() %>/upload/product/\${data[i].thumbnailFilename}`;
					a.append(img);
					
					div2.classList.add("prod-name");
					div2.append(data[i].productName);
					a.append(div2);

					p.classList.add("price");
					p.append(data[i].productPrice);
					a.append(p);
					
					div1.append(a);
					td.append(div1);
					
					pd1.append(td);
					
				}
				else if (i >= 4 && i < 8){
					div1.classList.add("prod-cont");
					a.setAttribute("href", `<%= request.getContextPath() %>/product/coffeeDetails?no=\${data[i].productNo}`);

					img.src = `<%= request.getContextPath() %>/upload/product/\${data[i].thumbnailFilename}`;
					a.append(img);
					
					div2.classList.add("prod-name");
					div2.append(data[i].productName);
					a.append(div2);

					p.classList.add("price");
					p.append(data[i].productPrice);
					a.append(p);
					
					div1.append(a);
					td.append(div1);
						
					pd2.append(td);
					
				}
				else {
					div1.classList.add("prod-cont");
					a.setAttribute("href", `<%= request.getContextPath() %>/product/coffeeDetails?no=\${data[i].productNo}`);
					
					img.src = `<%= request.getContextPath() %>/upload/product/\${data[i].thumbnailFilename}`;
					a.append(img);
					
					div2.classList.add("prod-name");
					div2.append(data[i].productName);
					a.append(div2);

					p.classList.add("price");
					p.append(data[i].productPrice);
					a.append(p);
					
					div1.append(a);
					td.append(div1);
					
					pd3.append(td);
					
				}
			}
			
		},
		error : console.log
	});
}
</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>