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
    	function fCheck() {
    		let product1 = $("#product1").val();
    		
    		if (product1 == "") {
    			alert("첫번째 상품은 등록하셔야 합니다");
    			$("#product1").focus();
    			return false;
    		}
    		alert("상품을 등록합니다");
    		form.submit();
    	}
    </script>
</head>
<body>
<p><br></p>
<div class="container">
<h2>상품등록</h2>
<form name="form" method="post" action="<%=request.getContextPath()%>/T2ok">
	<p>상품1 : <input type="text" name="product" id="product1" value="" autofocus /></p>
	<p>상품2 : <input type="text" name="product" id="product2" value="냉장고"/></p>
	<p>상품3 : <input type="text" name="product" id="product3" value=""/></p>
	<p>상품4 : <input type="text" name="product" id="product4" value=""/></p>
	<p>상품5 : <input type="text" name="product" id="product5" value=""/></p>
	<p>상품6 : <input type="text" name="product" id="product6" value=""/></p>
	<p>상품7 : <input type="text" name="product" id="product7" value=""/></p>
	<p>
		<input type="button" value="전송" onclick="fCheck()"/>
		<input type="reset" value="취소"/>
	</p>
</form>
</div>
</body>
</html>