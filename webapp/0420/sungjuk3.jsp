<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>sungjuk2.jsp</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
  <script>
  	'use strict';
    function fCheck() {
    	let name = myForm.name.value;
    	let gender = myForm.gender.value;
    	let no = myForm.no.value;
    	let hakyun = myForm.hakyun.value;
    	let hakyunOption = myForm.hakyunOption.value;
    	let kor = myForm.kor.value;
    	let eng = myForm.eng.value;
    	let mat = myForm.mat.value;
    	
    	if(name == "") {
    		alert("성명을 입력하세요!");
    		myForm.name.focus();
    	}
    	else if(no == "") {
    		alert("학생 번호를 입력하세요!");
    		myForm.no.focus();
    	}
    	else if(hakyun == "" && hakyunOption == "") {
    		alert("학년을 입력하세요!");
    		myForm.hakyun.focus();
    	}
    	else if(hakyun == "" && (5 > hakyunOption || 6 < hakyunOption)) {
    		alert("학년은 6학년까지 입력가능합니다");
    		myForm.hakyunOption.value = '';
    		myForm.hakyunOption.focus();
    	}
    	else if(kor == "") {
    		alert("국어 점수를 입력하세요!");
    		myForm.no.focus();
    	}
    	else if(eng == "") {
    		alert("영어 점수를 입력하세요!");
    		myForm.no.focus();
    	}
    	else if(mat == "") {
    		alert("수학 점수를 입력하세요!");
    		myForm.no.focus();
    	}
    	else {
    		myForm.submit();
    	}
    }
    
    function changeHakyun() {
    	let options = myForm.hakyun.options;
    	//sports가 '기타' 선택이면, 기타스포츠 항목으로 포커스 설정
    	if(options[0].selected == true && myForm.hakyunOption.value == '' ) {
    		myForm.hakyunOption.focus();
    	} else {
    		options[0].selected = false;
    		myForm.hakyunOption.value = '';
    	}
    }
    
    function changeHakyunOption() {
    	let options = myForm.hakyun.options;
    	for (let i=0; i<options.length; i++) {
    		options[i].selected = false;
    	}
    	myForm.hakyun.options[0].selected = true; 
    }
    
  </script>
</head>
<body>
<p><br/></p>
<div  class="container" align="left">
문제4) 성적평가표2 -> 스포츠,language항목 삭제 후 성적평가표3 만들기<br>
입력사항 : 성명(name : text), 성별(gender : radio), 학생번호(no : number), 학년(hakyun:콤보상자), 국어(kor:number), 영어(eng:number), 수학(mat:number), 전송버튼(button), 취소(reset)<br>
자바스크립트를 이용한 유효성체크(공백)<br>
전송받을 주소? /sungjukOk2 (컨트롤러 어노테이션 이용)<br>
출력사항 : 입력사항 모두 + 총점/평균/학점<br>
</div>
<br/>
<hr/>
<div class="container" align="center">
	<form name="myForm" method="get" action='<%=request.getContextPath()%>/sungjukOk3' >
	  <caption><h2>성 적 입 력</h2></caption>
	  <table class="table">
	    <tr>
	      <th>성명</th>
	      <td><input type="text" name="name" value="테스터" class="form-control" autofocus /></td>
	    </tr>
	    <tr>
	      <th>성별</th>
	      <td>
	        <input type="radio" name="gender" value="남"/>남 &nbsp;&nbsp;
		      <input type="radio" name="gender" value="여" checked>여
		    </td>
		  </tr>
		  <tr>
		    <th>학생번호</th>
		    <td><input type="number" name="no" value="220231" class="form-control"/></td>
		  </tr>
		  <tr>
		    <th>학년</th>
		    <td>
		      <div class="row">
		        <div class="col">
			      <select name="hakyun" class="form-control" onchange="changeHakyun()">
					<option value="">학년을 선택하세요</option> 		
					<option value="1">1학년</option> 		
					<option value="2">2학년</option> 		
					<option value="3">3학년</option> 		
					<option value="4" selected>4학년</option> 		
				  </select> &nbsp;&nbsp;
				</div>
				<div class="col">
				  기타 : <input type="number" name="hakyunOption" min="5" max="6" onclick="changeHakyunOption()"/>
				</div>
			  </div>
			</td>
		</tr>
		<tr>
		  <th>국어</th>
		  <td><input type="number" name="kor" value="98" min="0" max="100" class="form-control"/></td>
		</tr>
		<tr>
		  <th>영어</th>
		  <td><input type="number" name="eng" value="89" min="0" max="100" class="form-control"/></td>
		</tr>
		<tr>
		  <th>수학</th>
		  <td><input type="number" name="mat" value="78" min="0" max="100" class="form-control"/></td>
		</tr>
		<tr>
		  <td colspan="2" align="center">
		  	<input type="submit" value="전송" class="btn btn-success m-2"/>
		    <input type="reset" value="취소" class="btn btn-success m-2"/>
		  </td>
		</tr>
	  </table>
	</form>
</div>
<p><br/></p>
</body>
</html>