<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<%
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("cMid")) {
				pageContext.setAttribute("mid", cookie.getValue());
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login.jsp</title>
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
  <form name="myForm" method="post" action="${ctxPath}/login">
  	<table class="table table-bordered text-center">
  	  <tr>
  	    <td colspan="2"><h3>로 그 인</h3></td>
  	  </tr>
  	  <tr>
  	  	<th class="bg-secondary text-white">아이디</th>
  	  	<td><input type="text" name="mid" value="${mid}" autofocus class="form-control"/></td>
  	  </tr>
  	  <tr>
  	  	<th class="bg-secondary text-white">비밀번호</th>
  	  	<td><input type="password" name="pwd" value="1234" class="form-control"/></td>
  	  </tr>
  	  <tr>
  	    <td colspan="2">
  	      <button type="submit" class="btn btn-success">로그인</button> &nbsp;&nbsp;
  	      <button type="reset" class="btn btn-success">다시입력</button> &nbsp;&nbsp;
  	      <button type="button" class="btn btn-success" onclick="location.href='loginJoin.jsp'">회원가입</button> &nbsp;&nbsp;
  	      <input type="checkbox" name="idSave" id="idSave" checked/> 아이디 저장
  	    </td>
  	  </tr>
  	</table>
  </form>
</div>
</body>
</html>