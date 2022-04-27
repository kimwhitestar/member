<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <!-- <meta charset="UTF-8"> -->
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
	<h2>전송받은 내용을 출력합니다(Get/Post방식)</h2>
	<p>아이디 : <%=(String)request.getParameter("mid")%></p>
	<p>비밀번호 : <%=(String)request.getParameter("pwd")%></p>
	<br>
	<p><a href="TestDoPost.jsp">돌아가기</a></p>
</div>
</body>
</html>