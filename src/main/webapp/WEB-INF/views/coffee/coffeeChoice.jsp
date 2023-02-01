<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>coffeeChoice</title>
</head>
<body>
	<section id="qna">
		<div class="qBox">
			<span>1. 어떤 커피를 알고 싶나요?</span>
		</div>
		<div class="answerBox">
			<table>
			<tr>
				<td><input type="button" value="버츄오"/></td>
				<td><input type="button" value="오리지날"/></td>
			</tr>
		</table>
		</div>
	</section>
	<section id="result">
		
	</section>

</body>
<script>
	const coffee = ['vertuo','original']
	const aroma = ['cocoa','biscuit','fruit']
	const sour = ['1-2','3-5']
	const strong = ['1-6','7-13']
	const cupsize = ['S','M','L']
	
	//버튼 추가
	function begin1() {
		for(let i=0;i<answer1.length;i++){
			addButton(answer1[i], i);
		}
	}	

	function begin2() {
		for(let i=0;i<answer2.length;i++){
			addButton(answer2[i], i);
		}
	}

	function begin3() {
		for(let i=0;i<answer3.length;i++){
			addButton(answer3[i], i);
		}
	}
	function begin4() {
		for(let i=0;i<answer4.length;i++){
			addButton(answer4[i], i);
		}
	}
	function begin5() {
		for(let i=0;i<answer5.length;i++){
			addButton(answer5[i], i);
		}
	}

	//버튼에 텍스트 , 클래스명 추가
	function addButton(answerText, idx) {
		const button = document.createElement('button');
		$("#abox").append(button);
		button.innerHTML = answerText;
		button.classList.add(idx);

	//버튼 클릭 시 다음페이지 이동
	button.addEventListener("click", function() {
		let link = window.location.href; // 현재 페이지의 url 값
		if(link == "http://localhost:8080/common/ch1"){
			choice = $(this).attr('class'); //클릭한 버튼의 클래스명 가져와서 저장
			let a = {"a1" : choice}; //localstorage 형태로 클래스명 value로, a1을 key로 저장
			localStorage.setItem('a1', JSON.stringify(a)); // a1라는 key에 a 변수를 저장(객체->문자열 변환)
			location.href="ch2";
		}else if(link == "http://localhost:8080/common/ch2"){
			choice = $(this).attr('class');
			let a = {"a2" : choice};
			localStorage.setItem('a2', JSON.stringify(a));
			location.href="ch3";
		}else if(link == "http://localhost:8080/common/ch2"){
			choice = $(this).attr('class');
			let a = {"a3" : choice};
			localStorage.setItem('a3', JSON.stringify(a));
			location.href="ch4";
		}else if(link == "http://localhost:8080/common/ch2"){
			choice = $(this).attr('class');
			let a = {"a4" : choice};
			localStorage.setItem('a4', JSON.stringify(a));
			location.href="ch5";
		}else {
			choice = $(this).attr('class');
			let a = {"a5" : choice};
			localStorage.setItem('a5', JSON.stringify(a));
			result();
		}
	})
}
	//결과 출력 후 해당 페이지로 이동
		function result() {
			const r1 = JSON.parse(localStorage.getItem('a1'));
			const r2= JSON.parse(localStorage.getItem('a2'));
			const r3= JSON.parse(localStorage.getItem('a3'));
			const r4= JSON.parse(localStorage.getItem('a4'));
			const r5= JSON.parse(localStorage.getItem('a5'));
			
			//선택된 결과
			const result = [r1.a1, r2.a2, r3.a3, r4.a4, r5.a5];
			
			//존재하는 결과들 17개
			const results = [
				["0", "0","0","0","0"],
				["0", "0","0","1","0"],
				["0", "0","0","1","1"],
				["0", "1","0","0","2"],
				["0", "1","0","1","1"],
				["0", "2","0","1","0"],
				["0", "2","1","0","1"],
				["0", "2","1","0","2"],
				["1", "0","0","0","0"],
				["1", "0","0","1","0"],
				["1", "0","1","1","0"],
				["1", "1","0","0","0"],
				["1", "1","0","1","0"],
				["1", "1","0","1","1"],
				["1", "1","1","1","0"],
				["1", "2","0","0","1"],
				["1", "2","1","0","0"]
				
			];
</script>
</html>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>