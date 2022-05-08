<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>memberLogin.jsp</title>
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
<div class="container text-center">
	<div class="modal-content">
		<div class="modal-dialog">
			<div class="container p-3">
				<form name="myForm" method="post" action="${ctxPath}/memberLoginOk.mbr" class="was-validated">
					<h2>Member Login</h2>
					<p>회원 아이디와 비밀번호를 입력해 주세요</p>
					<div class="form-group">
						<label for="mid">아이디 : </label>
						<input type="text" class="form-control" name="mid" id="mid" value="${mid}" placeholder="아이디를 입력하세요." required autofocus/>
						<div class="valid-feedback"></div>
						<div class="invalid-feedback">아이디는 필수 입력사항입니다.</div>
					</div>
					<div class="form-group">
						<label for="pwd">비밀번호 : </label>
						<input type="text" class="form-control" name="pwd" id="pwd" value="1234" placeholder="비밀번호를 입력하세요." required />
						<div class="valid-feedback"></div>
						<div class="invalid-feedback">비밀번호는 필수 입력사항입니다.</div>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-secondary btn-sm" onclick="fCheck()">로그인</button> &nbsp;
						<button type="reset" class="btn btn-secondary btn-sm">다시작성</button> &nbsp;
						<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${ctxPath}/';">돌아가기</button> &nbsp;
						<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='${ctxPath}/memberJoin.mbr';">회원가입</button>
					</div>
					<div class="row" style="font-size:12px">
						<span class="col"><input type="checkbox" name="idSave" checked />아이디 저장</span>
						<span class="col"><a href="#">아이디 찾기</a> / <a href="#">비밀번호 찾기</a></span>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/include/footer.jsp" %>
</body>
</html>