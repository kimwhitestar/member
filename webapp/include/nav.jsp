<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int sLevel = session.getAttribute("sLevel")==null? 99: (int)session.getAttribute("sLevel");
	String sAdmin = session.getAttribute("sAdmin")==null? "": (String) session.getAttribute("sAdmin");
	
System.out.println("########### sLevel= "+ sLevel);
%>
<script>
	'use strict';
	function checkMemberDelete() {
		if (confirm('정말 탈퇴하겠습니까?')) {
			if (confirm('탈퇴 후 1개월간은 같은 아이디로 재가입할 수 없습니다.\n탈퇴하겠습니까?')) 
				location.href = '<%=request.getContextPath()%>+"/memberDeletePract.mbr"';
		}
	}
</script>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="<%=request.getContextPath()%>/">Home</a>
  <!-- <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button> -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
<%	if (99 != sLevel ) { //비회원 사용 가능 %>
      <li class="nav-item font-weight-bold">
        <a class="nav-link" href="<%=request.getContextPath()%>/guestList.gu">GUEST</a>
      </li>
<%	} %>
<%	if (0 == sLevel || (1 <= sLevel && 4 >= sLevel)) { //0관리자와 회원전용(1준회원,2정회원,3우수회원,4운영자) %>
      <li class="nav-item font-weight-bold">
        <a class="nav-link" href="<%=request.getContextPath()%>/boardList.bd">BOARD</a>
      </li>
<%	} %>
<%	if (0 == sLevel || (1 < sLevel && 4 >= sLevel)) { //0관리자와 회원전용(2정회원,3우수회원,4운영자) %>
      <li class="nav-item font-weight-bold">
        <a class="nav-link" href="#">PDS</a>
      </li>
<%	} %>
      <li class="nav-item font-weight-bold">
        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Study</a>
        <div class="dropdown">
		    <div class="dropdown-menu">
<%	if (99 != sLevel ) { //비회원 열람 가능 %>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/study1/urlMapping">URL(Controller) 디렉토리패턴</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/urlMapping.url">확장자(Controller) 디렉토리패턴</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/urlMapping.um">확장자(FrontController) 디렉토리패턴</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/el1.st">EL1표기 연습</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/el2.st">EL2표기 연습</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/jstl1.st">JSTL Core 연습</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/jstl2.st">JSTL Function 연습</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/jstl3.st">JSTL Format 연습</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/study2/shaPass.st">SHA암호화(단방향암호화:복호화안됨,문제성있다고함)</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/userList.st">Ajax1 연습-확장자패턴 화면깜박임 동작비교(dispathcer로 jsp설정없음)</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/ajaxIdSearch?mid=user1">ajax1연습-동적ajax는 URL패턴사용(dispathcer로 jsp설정없음)</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/ajaxIdSearch2?mid=user1">ajax2연습-동적ajax는 URL패턴사용(dispathcer로 jsp설정없음)</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/ajaxIdSearch3?mid=user1">ajax3연습-동적ajax는 URL패턴사용(dispathcer로 jsp설정없음)</a>
<%	} %>
		    </div>
		</div>
      </li>
      <li class="nav-item font-weight-bold">
<%	if (99 == sLevel || 0 != sLevel) { //회원전용(0관리자 접근 비허가), 로그인 레벨 확인안되면 모두 접근 %>
        <a class="nav-link" href="<%=request.getContextPath()%>/memberLogin.mbr">Login</a>
	<% 	if (1 <= sLevel && 4 >= sLevel) { //회원전용(0관리자 접근 비허가), 로그인 확인되면 접근 %>
	        <a class="nav-link" href="<%=request.getContextPath()%>/memberLogout.mbr">Logout</a>
	<%	} %>
<%	} %>
      </li>
      <li class="nav-item font-weight-bold">
<%	if (99 == sLevel ) { //비회원 접근 %>
        <a class="nav-link" href="<%=request.getContextPath()%>/memberJoin.mbr">Join</a>
<%	} %>
      </li>
<%	if (1 <= sLevel && 4 >= sLevel) { //회원전용(0관리자 접근 비허가), 로그인 확인되면 접근 %>
      <li class="nav-item font-weight-bold">
        <a href="<%=request.getContextPath()%>/memberMain.mbr" class="nav-link dropdown-toggle" data-toggle="dropdown">MyPage</a>
        <div class="dropdown">
		    <div class="dropdown-menu">
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/memberMain.mbr">회원방</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/">일정관리</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/">1:1문의</a>
		<%	if (4 == sLevel) { //운영자회원  %>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/memberList.mbr">공개회원목록</a>
		<%	} %>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/memberUpdate.mbr">회원정보변경</a>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/memberUpdatePwd.mbr">회원비밀번호변경</a>
		      <a class="dropdown-item" href="javascript:checkMemberDelete()">회원탈퇴</a>
		    </div>
		</div>
      </li>
<%	} %>
      <li class="nav-item font-weight-bold">
        <a href="<%=request.getContextPath()%>/adminLogin.adm" class="nav-link dropdown-toggle" data-toggle="dropdown">Admin Page</a>
        <div class="dropdown">
		    <div class="dropdown-menu">
	<%	if (0 == sLevel || sAdmin.equals("adminOk")) { //관리자 %>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/adminLogout.adm">관리자 로그아웃</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/adminMain.adm">관리자 메뉴</a>
	<%	} else { %>
		      <a class="dropdown-item" href="<%=request.getContextPath()%>/adminLogin.adm">관리자 로그인(인증)</a>
	<%	} %>
		    </div>
		</div>
      </li>
    </ul>
  </div>  
</nav>