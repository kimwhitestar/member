<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>memberPwdFindout.jsp</title>
    <%@ include file="/include/bs4.jsp" %>
    <style></style>
    <script>
    	'use strict';
    	function fCheck() {
    		myForm.submit();
    	}
    </script>
</head>
<body>
    <%@ include file="/include/header_home.jsp" %>
    <%@ include file="/include/nav.jsp" %>
<p><br></p>
<div class="container">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="container p-3 border">
				<form name="myForm" method="post" action="${ctxPath}/memberPwdFindoutOk.mbr" class="was-validated">
					<h2>비 밀 번 호  찾 기</h2>
					<p>가입한 회원ID와 이메일을 입력해 주세요</p>
					<div class="form-group">
						<label for="mid">이메일 : </label>
						<input type="text" class="form-control" name="mid" placeholder="아이디를 입력하세요." required autofocus/>
						<div class="valid-feedback"></div>
						<div class="invalid-feedback">아이디는 필수 입력사항입니다.</div>
					</div>
					<div class="form-group">
						<label for="email">이메일 : </label>
						<input type="text" class="form-control" name="email" placeholder="이메일을 입력하세요." required />
						<div class="valid-feedback"></div>
						<div class="invalid-feedback">이메일은 필수 입력사항입니다.</div>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-secondary btn-sm" onclick="fCheck()">아이디 찾기</button> &nbsp;
						<button type="reset" class="btn btn-secondary btn-sm">다시작성</button> &nbsp;
						<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${ctxPath}/memberLogin.mbr';">돌아가기</button> &nbsp;
						<span class="col"><input type="checkbox" name="idSave" checked />아이디 저장</span>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
    <%@ include file="/include/footer.jsp" %>
</body>
</html>