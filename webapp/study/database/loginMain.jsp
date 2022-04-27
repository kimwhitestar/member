<%--@ include file="/include/memberCheck.jsp" --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<%
	//int point = Integer.parseInt(request.getParameter("point"));
	//int vCount = Integer.parseInt(request.getParameter("vCount"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>loginMain.jsp</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style></style>
    <script>
    	'use strict';
    	function delCheck() {
    		let ans = confirm("정말 탈퇴하겠습니까?");
    		if (ans) {
    			location.href = "${ctxPath}/loginDelete";
    		}
    	}
    </script>
</head>
<body>
<p><br></p>
<div class="container text-center">
  <h2>회원 전용방</h2>
  <hr/>
  <p><font color="blue"><b>${sMid}(${sName})</b></font>님 로그인 중입니다.</p>
  <%--   <p><img src="<%=request.getContextPath()%>/images/nimo.jpg" width="400px"/></p>    <p><img src="<%=request.getContextPath()%>/images/nimo.jpg" width="400px"/></p> --%>
  <p><img src="${ctxPath}/images/nimo.jpg" width="400px"/></p>
  <hr/>
  <!-- 파라미터에 셋팅되어 온 값을 jstl el표기법 => ${param.vCount}
  그러나 주소이동방법이 옳지 않음.
  컨트롤러 서블릿을 사용한 서블릿매핑주소로 이동함.
  'get방식 location'으로 들어와서 'href=화일명.jsp'으로 이동하는 방법은 사용하면 안됨. 
  <p>방문횟수 : ${param.vCount} / 포인트 : ${param.point}</p> -->
  <p>방문횟수 : ${vo.vCount} / 포인트 : ${vo.point}</p>
  <div class="row">
    <div class="col"><a href="${ctxPath}/loginSearch" class="btn btn-success form-control m-1">개인정보 조회</a></div>
    <div class="col"><a href="${ctxPath}/loginUpdate" class="btn btn-success form-control m-1">개인정보 수정</a></div>
    <div class="col"><a href="javascript:delCheck();" class="btn btn-success form-control m-1">회원탈퇴</a></div>
  </div>
  <div class="row">
    <div class="col"><a href="${ctxPath}/loginList" class="btn btn-success form-control m-1">전체회원 조회</a></div>
    <div class="col"><a href="${ctxPath}/logout" class="btn btn-success form-control m-1">로그아웃</a></div>
  </div>
</div>
</body>
</html>