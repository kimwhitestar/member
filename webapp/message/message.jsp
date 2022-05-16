<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//  String msg = (String) request.getAttribute("msg");
//  String url = (String) request.getAttribute("url");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>message.jsp</title>
    <script>
    'use strict';
    //JavaScript 변수는 server실행일 경우 el표기법으로 사용가능하나, client실행일 경우는 순차가 안맞아서 사용안됨
    let msg = '${msg}';
    let mid = '${sMid}';
    
    <!--/study/로그인 관련 연습-->
    if (msg == 'loginDeleteOk') msg = "회원 탈퇴됬습니다.";
    else if (msg == 'loginDeleteNo') msg = "회원 탈퇴 실패~~";
    else if (msg == 'loginOk') msg = "${sNickName}님 로그인됬습니다";
    else if (msg == 'loginNo') msg = "로그인 실패~~";
    else if (msg == 'loginIdCheckNo') msg = "회원ID 중복입니다. 새로운 회원ID를 입력하세요";
    else if (msg == 'loginJoinOk') msg = "회원에 가입했습니다.";
    else if (msg == 'loginJoinNo') msg = "회원 가입 실패~~";
    else if (msg == 'loginUpdateOk') msg = "회원정보 수정됬습니다.";
    else if (msg == 'loginUpdateNo') msg = "회원정보 수정 실패~~";

    <!--/guest/방명록관리 - 방명록목록조회,방명록등록,방명록수정,방명록삭제-->
    else if (msg == 'guestInputOk') msg = "방명록에 등록했습니다.";
    else if (msg == 'guestInputNo') msg = "방명록 등록 실패~~";
    else if (msg == 'guestDeleteOk') msg = "방명록에서 삭제했습니다.";
    else if (msg == 'guestDeleteNo') msg = "방명록 삭제 실패~~";
    
    <!--/member/회원관리 - 회원가입,회원탈퇴신청(30일임시삭제),회원탈퇴처리(회원DB삭제),회원로그인,회원로그아웃,개별회원조회,운영자회원용 전체회원목록조회-->
    else if (msg == 'memberLoginNo') msg = "로그인 실패~~";
    else if (msg == 'memberLoginOk') msg = "${sNickName}님 로그인됬습니다";
    else if (msg == 'memberIdFindoutNo') msg = "아이디 찾기 실패~~";
    else if (msg == 'memberIdFindoutOk') msg = "회원님의 아이디를 찾았습니다";
    else if (msg == 'memberPwdChangeNo') msg = "비밀번호 수정 실패~~";
    else if (msg == 'memberPwdChangeOk') msg = "회원님의 비밀번호를 수정했습니다";
    else if (msg == 'memberLogoutOk') msg = "${nickName}님 로그아웃됬습니다";
    else if (msg == 'memberJoinOk') msg = "회원으로 가입됬습니다.";
    else if (msg == 'memberJoinNo') msg = "회원 가입 실패~~";
    else if (msg == 'memberUpdateOk') msg = "회원정보가 수정됬습니다.";
    else if (msg == 'memberUpdateNo') msg = "회원정보 수정 실패~~";
    else if (msg == 'memberLevelUpdateOk') msg = "회원레벨이 수정됬습니다";
    else if (msg == 'memberLevelUpdateNo') msg = "회원레벨 수정 실패~~";
    else if (msg == 'memberDeletePractOk') msg = "회원에서 탈퇴됬습니다";
    else if (msg == 'memberDeletePractNo') msg = "회원 탈퇴 실패~~";
    else if (msg == 'memberDeleteOk') msg = "회원DB에서 회원을 삭제했습니다";
    else if (msg == 'memberDeleteNo') msg = "회원DB에서 회원 삭제 실패~~";
    
    <!--/board/게시판 - 게시판목록,글쓰기-->
    else if (msg == 'boardInputOk') msg = "게시글이 등록됬습니다.";
    else if (msg == 'boardInputNo') msg = "게시글 등록 실패~~";
    else if (msg == 'boardUpdateOk') msg = "게시글이 수정됬습니다.";
    else if (msg == 'boardUpdateNo') msg = "게시글 수정 실패~~";
    else if (msg == 'boardDeleteOk') msg = "게시글이 삭제됬습니다.";
    else if (msg == 'boardDeleteNo') msg = "게시글 삭제 실패~~";
    else if (msg == 'boardDeleteRestrict') msg = "댓글이 존재하여 게시글을 삭제할 수 없습니다";
    
    
    <!--/admin/관리자 - 관리자로그인(인증), 관리자로그아웃 -->
    else if (msg == 'adminOk') msg = "관리자 인증됬습니다";
    else if (msg == 'adminNo') msg = "관리자 인증 실패~~";
    else if (msg == 'adminLogoutOk') msg = "관리자 로그아웃됬습니다";
        
    alert(msg);
    if ('${url}' != '') location.href = '${url}';
    </script>
</head>
<body>
</body>
</html>