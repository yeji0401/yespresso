<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart View</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myPage/myOrderDetail.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@100;400;600;700;900&display=swap" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>

</head>
<body>
<section id="orderInfo-container">
<table id = "orderInfo-tbl">
<tr>
	<td>주문번호</td>
</tr>
<tr>
	<td>20083939</td>
</tr>
<tr>
	<td>주문자아이디</td>
</tr>
<tr>
	<td>honggd</td>
</tr>
<tr>
	<td>주문일자</td>
</tr>
<tr>
	<td>20203030</td>
</tr>
<tr>
	<td>배송상세정보</td>
</tr>
<tr>
	<td>ㅇㅇㅇ님<br />ㅇㅇㅇ도 ㅇㅇ시 ㅇㅇ 1010-2020</td>
</tr>
<tr>
	<td>주문상태</td>
</tr>
<tr>
	<td>orderState</td>
</tr>
</table>
</section>
<section id="ordered-product-container">
	<table id="ordered-product-tbl">
<colgroup>
<col style="width: 89px">
<col style="width: 121px">
<col style="width: 71px">
<col style="width: 25px">
<col style="width: 58px">
<col style="width: 82px">
<col style="width: 72px">
</colgroup>
<tbody>
  <tr>
    <td colspan="7">주문완료</td>
  </tr>
  <tr>
    <td colspan="2">productCategory(total amount)</td>
    <td>개별 단가</td>
    <td></td>
    <td>수량</td>
    <td>합계</td>
    <td>리뷰작성</td>
  </tr>
  <tr>
    <td>img</td>
    <td>카자르</td>
    <td>₩699</td>
    <td>X</td>
    <td>amount</td>
    <td>₩6,990</td>
    <td>button</td>
  </tr>
  <tr>
    <td>img</td>
    <td>카자르</td>
    <td>₩699</td>
    <td>X</td>
    <td>amount</td>
    <td>₩6,990</td>
    <td>button</td>
  </tr>
  <tr>
    <td colspan="2">productCategory(total amount)</td>
    <td>개별 단가</td>
    <td></td>
    <td>수량</td>
    <td>합계</td>
    <td>리뷰작성</td>
  </tr>
  <tr>
    <td>img</td>
    <td>카자르</td>
    <td>₩699</td>
    <td>X</td>
    <td>amount</td>
    <td>₩6,990</td>
    <td>button</td>
  </tr>
  <tr>
    <td>전체 합계</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>₩(6990*3)</td>
    <td></td>
  </tr>
</tbody>
	</table>

	<hr style="margin-top: 30px; width:300px;"/>


</section>


</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>