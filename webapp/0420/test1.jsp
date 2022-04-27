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
<form name="form" method="post" action="test1ok_redirectVSforward.jsp">
	<p>성명 : <input type="text" name="name" value="admin"/></p>
	<p>가격 : <input type="number" name="price" value="10000"/></p>
	<p><input type="submit" value="전송"/></p>
	<p><input type="button" value="전송2" onclick="location.href='test1ok_redirectVSforward.jsp?price=10000&name=admin';"/><!--get방식--></p>
</form>
</div>
</body>
</html>