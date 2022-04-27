<%--@ include file="/include/memberCheck.jsp" --%>
<%@ page import="study.database.LoginVO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<%
	List<LoginVO> vos = (List<LoginVO>)request.getAttribute("vos");
%>
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
	<h2>전체 회원 목록</h2>
	<table>
			<tr>
				<th>번호</th><th>아이디</th><th>비밀번호</th><th>성명</th><th>포인트</th><th>최종방문일</th><th>방문횟수</th>
			</tr>
<%	for (LoginVO vo : vos) { %>
			<tr>
				<td><%=vo.getIdx()%></td>
				<td><%=vo.getMid()%></td>
				<td><%=vo.getPwd()%></td>
				<td><%=vo.getName()%></td>
				<td><%=vo.getPoint()%></td>
				<td><%=vo.getLastDate()%></td>
				<td><%=vo.getvCount()%></td>
			</tr>
<% } %>
	</table>
  <p><a href='${ctxPath}/loginMain' class="btn btn-success form-control">돌아가기</a></p>
</div>
</body>
</html>