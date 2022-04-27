<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>loginSearch.jsp</title>
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
  <h2>개별 자료 조회</h2>
  <hr/>
  <p>아이디 : ${vo.mid}</p>
  <p>성명 : ${vo.name}</p>
  <p>포인트 : ${vo.point}</p>
  <p>방문횟수 : ${vo.vCount}</p>
  <p>최종방문일수 : ${vo.lastDate}</p>
  <hr/>
  <p><a href='${ctxPath}/loginMain' class="btn btn-success form-control">돌아가기</a></p>
</div>
</body>
</html>