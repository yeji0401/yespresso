<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Member> members = (List<Member>) request.getAttribute("members");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>   
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!-- 관리자용 admin.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin/admin.css" />
<style>
div#search-memberId	 {display: <%= searchType == null || "member_id".equals(searchType) ? "inline-block" : "none" %>;}
div#search-memberName{display: <%= "member_name".equals(searchType) ? "inline-block" : "none" %>;}
</style>
<script>
window.addEventListener('load', () => {
	document.querySelector("#searchType").addEventListener('change', (e) => {
		console.log(e.target.value); // member_id, member_name
		
		document.querySelectorAll(".search-type").forEach((div) => {
			div.style.display = "none";
		});
		
		let id; 
		switch(e.target.value){
		case "member_id" : id = "search-memberId"; break; 
		case "member_name" : id = "search-memberName"; break; 
		}
		
		document.querySelector("#" + id).style.display = "inline-block";
	});
});
</script>
    <section id="admin-container">
        <div id="admin-block">
            <ul class="admin-nav">
                <li class="member"><a href="">회원 관리</a></li>
                <li class="product"><a href="<%= request.getContextPath() %>/admin/adminProductList">제품 관리</a></li>
                <li class="orders"><a href="<%= request.getContextPath() %>/admin/adminOrdersList">주문 관리</a></li>
            </ul>
        </div>
        <h2>회원 관리</h2>
        <div id="search-block">
            <select id="searchType">
                <option value="member_id">아이디</option>
                <option value="member_name">이름</option>
            </select>
            <div id="search-memberId" class="search-type">
                <form action="#">
                    <input type="hidden" name="searchType" value="member_id">
                    <input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요."
                        value="">
                    <button type="submit">검색</button>
                </form>
            </div>
            <div id="search-memberName" class="search-type">
                <form action="#">
                    <input type="hidden" name="searchType" value="member_name">
                    <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."
                        value="">
                    <button type="submit">검색</button>
                </form>
            </div>
        </div>
            <div id="check-block">
                <div id="member-sort" class="sorting">
                    <p>정렬순</p>
                    <input type="checkbox" name="enroll-A" id="enroll-A">
                    <label for="enroll-A">가입일자 오름차순</label><br>
                    <input type="checkbox" name="enroll-D" id="enroll-D">
                    <label for="enroll-D">가입일자 내림차순</label><br>
                    <input type="checkbox" name="id-A" id="id-A">
                    <label for="id-A">아이디 오름차순</label><br>
                    <input type="checkbox" name="id-D" id="id-D">
                    <label for="id-D">아이디 내림차순</label><br>
                    <input type="checkbox" name="name-A" id="name-A">
                    <label for="name-A">이름 오름차순</label><br>
                    <input type="checkbox" name="name-D" id="name-D">
                    <label for="name-D">이름 내림차순</label>
                </div>
                <div id="member-role" class="sorting">
                    <p>회원 권한</p>
                    <input type="checkbox" name="ADMIN" id="ADMIN">
                    <label for="ADMIN">ADMIN</label><br>
                    <input type="checkbox" name="COMMON" id="COMMON">
                    <label for="COMMON">COMMON</label><br>
                    <input type="checkbox" name="VIP" id="VIP">
                    <label for="VIP">VIP</label>
                </div>
            </div>        
        <table id="tbl-member" class="tbl">
            <thead>
                <tr>
                    <th>No</th>
                    <th>아이디</th>
                    <th>권한</th>
                    <th>이름</th>
                    <th>생년월일</th>
                    <th>성별</th>
                    <th>휴대폰 번호</th>
                    <th>이메일</th>
                    <th>주소</th>
                    <th>가입일자</th>
                    <th><button type="submit">삭제</button></th>
                </tr>
            </thead>
            <tbody>
			<% if(members.isEmpty()){ %>
				<tr>
					<td colspan="11">조회된 회원이 없습니다.</td>
				</tr>
			<% 
			   } else { 
				  for(Member member : members){
			%>
					<tr>
						<td>1</td>
						<td><%= member.getMemberId() %></td>
						<td>
							<select class="member-role" data-member-id="<%= member.getMemberId() %>" data-member-birthday="<%= member.getBirthday() %>">
								<option value="<%= MemberRole.C %>" <%= member.getMemberRole() == MemberRole.C ? "selected" : "" %>>COMMON</option>
								<option value="<%= MemberRole.V %>" <%= member.getMemberRole() == MemberRole.V ? "selected" : "" %>>VIP</option>
								<option value="<%= MemberRole.A %>" <%= member.getMemberRole() == MemberRole.A ? "selected" : "" %>>ADMIN</option>
							</select>
						</td>
						<td><%= member.getMemberName() %></td>
						<td><%= member.getBirthday() != null ? member.getBirthday() : "" %></td>
						<td><%= member.getGender() != null ? member.getGender() : "" %></td>
						<td><%= member.getPhone() %></td>
						<td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
						<td><%= member.getAddress() %></td>
						<td><%= member.getEnrollDate() %></td>
						<td><input type="checkbox" name="" id=""></td>
					</tr>
			<%
				  }			
				} 
			%>            
            </tbody>
        </table>
		<div id="pagebar">
			<%= request.getAttribute("pagebar") %>
		</div>        
<%@ include file="/WEB-INF/views/common/footer.jsp" %>