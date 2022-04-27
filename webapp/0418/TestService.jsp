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
	<h2>Servlet Service(doPost,doGet불필요) 연습</h2>
	<form name="form" method="post" action="../TestService">
		<p>아이디 : <input type="text" name="mid" value="한글1" autofocus /></p>
		<p>비밀번호 : <input type="password" name="pwd" value="12345" /></p>
		<p>이름 : <input type="text" name="name"/></p>
		<p>숫자 : <input type="number" name="num" value="1234"/></p>
		<p>
		  현재접속중인 client IP : <%=request.getRemoteAddr()%>
		</p>
		<p>
			<input type="submit" value="전송"/>
			<input type="reset" value="다시입력"/>
		</p>
		<p>
		  <input type="text" name="hostIP" value="<%=request.getRemoteAddr()%>"/>
		  <input type="hidden" name="hostIP3" value="<%=request.getRemoteAddr()%>"/>
		</p>
	</form>

</div>
</body>
</html>