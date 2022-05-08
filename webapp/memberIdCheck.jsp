<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <%@ include file="/include/bs4.jsp" %>
    <style></style>
    <script>
    	'use strict';
    	function sendCheck() {
    		//아이디중복체크flg =yn 후 close()필요
    		opener.window.document.myForm.mid.value = '${mid}';
    		opener.window.document.myForm.pwd.focus();
    		window.close();
    	}
    	//중복 아이디 다시 체크
    	function idCheck() {
    		let mid = childForm.mid.value;
    		if ('' == mid.trim()) {
    			alert('아이디를 입력하세요.')
    			childForm.mid.value = '';
    			childForm.mid.focus();
    		} else {
    			childForm.submit;
    		}
    	}
    </script>
</head>
<body>
<p><br></p>
<div class="container">
	<h3>아이디 체크</h3>
	<c:if test="${'N' == existIdYN}">
		<h4><font color="blue">${mid}</font>아이디는 사용가능합니다</h4>
		<p><input type="button" value="창닫기" onclick="sendCheck()"/></p>
	</c:if>
	<c:if test="${'Y' == existIdYN}">
		<h4><font color="blue">${mid}</font>입력한 아이디는 이미 사용중인 아이디입니다</h4>
		<form name="childForm" method="post" action="${ctxPath}/memberIdCheck.mbr?mid=${mid}"/>
			<p>
				<input type="text" id="mid" name="mid"/>
				<input type="button" value="아이디검색" onclick="idCheck()"/>
			</p>
		</form>
	</c:if>
</div>
</body>
</html>