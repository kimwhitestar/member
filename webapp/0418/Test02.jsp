<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style></style>
    <script>
    	'use strict';
    	
    </script>
</head>
<body>
<p><br></p>
<div class="container">
	<h2>선언문학습(구구단...)</h2>
	<p>1.이곳은 jsp 선언문입니다.</p>
	<%! //JSP선언문은 !를 기입합니다//servlet을 사용하므로 이곳은 비사용
	int tot = 0;
	String res;//순차대로 컴파일되므로, 함수설정 후 변수사용해야 출력됨(JSP->JAVA SERVLET으로 자동파싱된 후에 변수사용가능)
	public String strLowser(String str) {
		res = str.toLowerCase();
		return res;
	}
	%>
	<%=res %><br>
	<%=strLowser("ㅌㅅㅌ") %><br>
	<%=res %><br>
	<%
	int sw = 0;
	for(int i=0; i<10; i++) {
	}
	%>
	<p>2.이곳은 Java를 이용한 구구단 출력</p>
	<%
	int tot = 0;
	for(int dan=2; dan<10; dan++) {
		for(int i=1; i<10; i++) {
			tot = dan * i;
			out.println(dan + " * " + i + " = " + tot +"<br>");
		}
	}
	%>
	<p>3.이곳은 본문의 마지막 입니다</p>
</div>
</body>
</html>