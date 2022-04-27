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
    	'use strict';/*Ecma6 사용*/
    	
    </script>
</head>
<body>
<p><br></p>
<div class="container">
	<h2>JSP 주석</h2>
	<!-- HTML주석 -->
	<%-- JSP주석 --%>
	<% 
	/*  <---- 퍼센트표시는 '스크립틀릿'이라고 하며 자바코드를 입력하는 곳이다 */
	
	out.println("스크립틀릿 안에 JSP출력함수 추가하기");
	out.println("<br><font color='aqua'>스크립틀릿 안에 JSP출력함수에 태그 추가하기</font>");
	%>
	<br>
	<%
	out.println("<font color='purple' size='5'>스크립틀릿에서 태그추가 연습</font>");
	%>
	<br>
	<%
	System.out.println("콘솔로그입니다");
	%>
	<br>
	<%
	out.println("이클립스 톰캣의 context 소스위치 (wtpwebapps) : "
			+ "본인드라이브/JavaGreenJsp/works/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps");
	out.println("이클립스 톰캣서버가 파싱한 jsp의 java파싱화일위치 (Catalina폴더 밑) : "
			+ "본인드라이브/JavaGreenJsp/works/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/work/Catalina/localhost/javagreenJ/org/apache/jsp");
	%>
	<h2>이곳은 jsp 본문 아래입니다</h2>
</div>
</body>
</html>