<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<meta charset="UTF-8">
<title>coffeeChoice</title>
<style>
	div.coffeewrap{position:relative; border-radius:30px; margin:10px auto; border:none; width:800px; height:1100px; background:#809A6F;}
	p{color:white;}
	p.question{font-weight:bold;}
	button{border:none; background:none; font-size: 50px; padding-top:300px;}
</style>
<script>
let index = 0;
let list = null;
const dataArr = [
	0, 0, 0, 0, 0
]
function startResearch(index){
	//console.log(5646);
	$qna = $('.qna');
	$content = $('.content');
	$question = $('.question');
	
	if(index == 0){
		$content.children('button').remove();
		
		$question.text('1. 어떤 커피를 알고 싶나요?');
		let ans1 = `<br /> <br /><a href="javascript:void(0)"  onclick="setData(0, 1);" style="text-decoration-line : none;">
		<img src="<%= request.getContextPath() %>/upload/product/p0001.png" style="width: 200px;"/><p style="color:white;">버츄오</p></a> <p>에스프레소에서 큰 머그잔까지 모든 커피 스타일과 우유 유무에 관계없이 좋아하는 경우</p>`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(0, 2);" style="text-decoration-line : none;">
		<img src="<%= request.getContextPath() %>/upload/product/p0002.png" style="width: 200px;"/> <p style="color:white;">오리지널</p></a> <p>주로 작은 컵이나 에스프레소 레시피(라떼, 플랫화이트, 카푸치노 등)를 마시는 경우</p>`;
		$question.append(ans1);
		$question.append(ans2);
		
	} else if(index == 1){
		$question.text('2. 어떤 종류의 향을 좋아하세요?');
		let ans1 = `<br /><a href="javascript:void(0)" onclick="setData(1, 1);" style="text-decoration-line : none;"><br />
		<img src="<%= request.getContextPath() %>/upload/coffee/chocolate.png" style="width: 200px;"/><p style="color:white;">초콜릿</p></a><p>약간의 쓴맛이 있고 강렬하고 지속적인 맛이 있습니다.</p><br />`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(1, 2);" style="text-decoration-line : none;"><br />
		<img src="<%= request.getContextPath() %>/upload/coffee/biscuit.png" style="width: 180px;"/><p style="color:white;">과자</p></a><p>달고 빵 같으며 종종 견과류가 들어 있습니다.</p> <br />`;
		let ans3 = `<a href="javascript:void(0)" onclick="setData(1, 3);" style="text-decoration-line : none;">
		<img src="<%= request.getContextPath() %>/upload/coffee/fruit.png" style="width: 180px;"/><p style="color:white;">과일</p></a><p>섬세한 과일향을 드러내는데 도움이 되는 약간 신맛이 납니다./</p>`;
		$question.append(ans1);
		$question.append(ans2);
		$question.append(ans3);
	} else if(index == 2){
		$question.text('3. 가벼운걸 좋아하세요?');
		let ans1 = `<br /><a href="javascript:void(0)" onclick="setData(2, 1); "style="text-decoration-line : none;"><br />
		<img src="<%= request.getContextPath() %>/upload/coffee/acidity1.png" style="width: 250px;"/><p style="color:white;">산미가 적어 바디감이 풍부</p></a> <br />`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(2, 2);" style="text-decoration-line : none;">
		<img src="<%= request.getContextPath() %>/upload/coffee/acidity2.png" style="width: 250px;"/><p style="color:white;">산미가 높아 상큼함</p></a>`;
		$question.append(ans1);
		$question.append(ans2);
	} else if(index == 3){
		$question.text('4. 어떤 종류의 커피의 맛을 좋아하세요?');
		let ans1 = `<br /> <br /><a href="javascript:void(0)" onclick="setData(3, 1);" style="text-decoration-line : none;"><br />
		<img src="<%= request.getContextPath() %>/upload/coffee/roasting1.png" style="width: 200px;"/><p style="color:white;">진한 커피</p></a><p>강렬한 커피는 입안을 가득 채우고 오래 지속되는 맛이 있습니다.</p> <br />`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(3, 2);" style="text-decoration-line : none;">
		<img src="<%= request.getContextPath() %>/upload/coffee/roasting2.png" style="width: 200px;"/><p style="color:white;">마일드 커피</p></a><p>마일드 커피는 부드럽고 가볍습니다.</p>`;
		$question.append(ans1);
		$question.append(ans2);
	} else if(index == 4){
		$question.text('5. 에스프레소가 풍부한 것을 원하시나요?');
		let ans1 = `<br /><a href="javascript:void(0)" onclick="setData(4, 1); "style="text-decoration-line : none;"><br />
		<img src="<%= request.getContextPath() %>/upload/coffee/small.png" style="width: 130px;"/><p style="color:white;">S</p></a><p>작은 컵. 리스트레토 또는 에스프레소</p>`;
		let ans2 = `<a href="javascript:void(0)" onclick="setData(4, 2);" style="text-decoration-line : none;"><br />
		<img src="<%= request.getContextPath() %>/upload/coffee/medium.png" style="width: 150px;"/><p style="color:white;">M</p></a><p>중간 컵. 룽고</p>`;
		let ans3 = `<a href="javascript:void(0)" onclick="setData(4, 3);" style="text-decoration-line : none;">
		<img src="<%= request.getContextPath() %>/upload/coffee/large.png" style="width: 180px;"/><p style="color:white;">L</p></a><p>큰 컵. 롱 블랙</p>`;
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
			dataType : "json",
			traditional: true,
			success: function(data){
				console.log(data);
				list = data;
			},
			error : function(err){
				console.log(err);
			}, 
			complete: function () {
				for(let i = 0; i < list.length; i++){
					let result = `<img src="<%= request.getContextPath() %>/upload/product/`+ list[i].thumbnailFilename + `" style="width: 180px;"/><p style="color:white;"></p></a><p>` + list[i].productName + `</p>`+`<p>` + list[i].productPrice + `</p>`;
					
					$content.append(result);
				}
				
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
	<div class="coffeewrap">
	<div class="qna" style="padding-top:20px;">
			<div class="content" style="text-align:center;">
				<p class="question" style="text-align:center; "></p>
				<button type="button" onclick="startResearch(0);">Do you want to know your taste?</button>
			</div>
		</div> 
	</div>


</body>
</html>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>