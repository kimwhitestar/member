<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>loginJoin.jsp</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style></style>
    <script>
    	'use strict';
    	
    </script>
</head>
<body>
<p><br></p>
<div class="container text-center">
  <form name="myForm" method="post" action="${ctxPath}/loginJoin">
  	<table class="table table-bordered text-center">
  	  <tr>
  	    <td colspan="2"><h3>회 원 가 입</h3></td>
  	  </tr>
  	  <tr>
  	  	<th class="bg-secondary text-white">아이디</th>
  	  	<td><input type="text" name="mid" autofocus class="form-control"/></td>
  	  </tr>
  	  <tr>
  	  	<th class="bg-secondary text-white">비밀번호</th>
  	  	<td><input type="password" name="pwd" value="1234" class="form-control"/></td>
  	  </tr>
  	  <tr>
  	  	<th class="bg-secondary text-white">이름</th>
  	  	<td><input type="text" name="name" value="김말숙" class="form-control"/></td>
  	  </tr>
  	  <tr>
  	    <td colspan="2">
  	      <button type="reset" class="btn btn-success">다시입력</button> &nbsp;&nbsp;
  	      <button type="submit" class="btn btn-success">신청</button> &nbsp;&nbsp;
  	      <button type="button" class="btn btn-success" onclick="location.href='${ctxPath}/loginMain'">돌아가기</button> &nbsp;&nbsp;
  	    </td>
  	  </tr>
  	</table>
  </form>
</div>
</body>
</html>