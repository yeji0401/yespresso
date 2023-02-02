<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>coffeeChoice</title>
<style>
	
</style>
<script>
let index = 0;

const dataArr = [
	0, 0, 0, 0, 0
]
function startResearch(index){
	console.log(5646);
	$qna = $('.qna');
	$content = $('.content');
	$question = $('.question');
	
	if(index == 0){
		$content.children('button').remove();
		
		$question.text('1. 어떤 커피를 알고 싶나요?');
		let ans1 = `<a href="javascript:void(0)"  onclick="setData(0, 1);"> <img src="<%= request.getContextPath() %>/upload/product/p0001.png" style="width: 200px;"/><p>버츄오</p></a>`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(0, 2);"><img src="<%= request.getContextPath() %>/upload/product/p0002.png" style="width: 200px;"/> <p>오리지날</p></a>`;
		$question.append(ans1);
		$question.append(ans2);
		
	} else if(index == 1){
		$question.text('2. 어떤 종류의 향을 좋아하세요?');
		let ans1 = `<a href="javascript:void(0)" onclick="setData(1, 1);"><img src="<%= request.getContextPath() %>/upload/coffee/chocolate.png" style="width: 200px;"/><p>초콜릿</p></a>`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(1, 2);"><img src="<%= request.getContextPath() %>/upload/coffee/biscuit.png" style="width: 200px;"/><p>과자</p></a>`;
		let ans3 = `<a href="javascript:void(0)" onclick="setData(1, 3);"><img src="<%= request.getContextPath() %>/upload/coffee/fruit.png" style="width: 200px;"/><p>과일</p></a>`;
		$question.append(ans1);
		$question.append(ans2);
		$question.append(ans3);
	} else if(index == 2){
		$question.text('3. 가벼운걸 좋아하세요?');
		let ans1 = `<a href="javascript:void(0)" onclick="setData(2, 1);"><img src="<%= request.getContextPath() %>/upload/coffee/acidity1.png" style="width: 200px;"/><p>산미가 적어 바디감이 풍부</p></a>`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(2, 2);"><img src="<%= request.getContextPath() %>/upload/coffee/acidity2.png" style="width: 200px;"/><p>산미가 높아 상큼함</p></a>`;
		$question.append(ans1);
		$question.append(ans2);
	} else if(index == 3){
		$question.text('4. 어떤 종류의 커피의 맛을 좋아하세요?');
		let ans1 = `<a href="javascript:void(0)" onclick="setData(3, 1);"><img src="<%= request.getContextPath() %>/upload/coffee/roasting1.png" style="width: 200px;"/><p>진한 커피</p></a>`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(3, 2);"><img src="<%= request.getContextPath() %>/upload/coffee/roasting2.png" style="width: 200px;"/><p>마일드 커피</p></a>`;
		$question.append(ans1);
		$question.append(ans2);
	} else if(index == 4){
		$question.text('5. 에스프레소가 풍부한 것을 원하시나요?');
		let ans1 = `<a href="javascript:void(0)" onclick="setData(4, 1);"><img src="<%= request.getContextPath() %>/upload/coffee/small.png" style="width: 200px;"/><p>S</p></a>`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(4, 2);"><img src="<%= request.getContextPath() %>/upload/coffee/medium.png" style="width: 200px;"/><p>M</p></a>`;
		let ans3 = `<a href="javascript:void(0)" onclick="setData(4, 3);"><img src="<%= request.getContextPath() %>/upload/coffee/large.png" style="width: 200px;"/><p>L</p></a>`;
		$question.append(ans1);
		$question.append(ans2);
		$question.append(ans3);
	} else {
		$content.empty();
		let result = `<p>다음을 추천드려요.</p>`;
		$content.append(result);
		
		$.ajax({
			url : "<%= request.getContextPath() %>/coffee/coffeeResult",
			data : {dataArr : dataArr},
			datatype : "html",
			success: function(data){
				console.log(data);
			},
			erroe : function(err){
				console.log(err);
			} 
		});
		
	}
	
	
	
	
}

function setData(index, value){
	if(value != null || value != ''){
		dataArr[index] = value;
		startResearch(++index);
	}
}

</script>
</head>
<body>
	<div class="qna" style="padding-top:50px;">
			<div class="content">
				<p class="question" style="text-align:center;"></p>
				<button type="button" style="margin-left:720px;" onclick="startResearch(0);">시작하기</button>
			</div>
		</div> 
	</div>	

</body>
</html>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>