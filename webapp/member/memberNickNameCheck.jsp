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
        	opener.window.document.myForm.nickName.value = '${nickName}';
    		opener.window.document.myForm.name.focus();
    		window.close();
    	}
    	//중복 닉네임 다시 체크
    	function nickNameCheck() {
    		let nickName = childForm.nickName.value;
    		if ('' == nickName.trim()) {
    			alert('닉네임을 입력하세요.')
    			childForm.nickName.focus();
    		} else {
    			childForm.submit();
    		}
    	}
    </script>
</head>
<body>
<p><br></p>
<div class="container">
	<h3>아이디 체크</h3>
	<c:if test="${'N' == existNickNameYN}">
		<h4><font color="blue">${nickName}</font>닉네임은 사용가능합니다</h4>
		<p><input type="button" value="창닫기" onclick="sendCheck()"/></p>
	</c:if>
	<c:if test="${'Y' == existNickNameYN}">
		<h4><font color="blue">${nickName}</font>입력한 닉네임은 이미 사용중인 아이디입니다</h4>
		<form name="childForm" method="post" action="${ctxPath}/memberNickNameCheck.mbr?nickName=${nickName}"/>
			<p>
				<input type="text" id="nickName" name="nickName"/>
				<input type="button" value="닉네임검색" onclick="nickNameCheck()"/>
			</p>
		</form>
	</c:if>
</div>
</body>
</html>