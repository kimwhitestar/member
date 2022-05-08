<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>memberJoin.jsp</title>
	<%@ include file="/include/bs4.jsp" %>
	<!-- daum웹사이트에서 제공하는 script open 예제소스 -->
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"/>
	<script src="${ctxPath}/js/post.js" />
	<style></style>
	<script>
	'use strict';
	let idChkFlg = false;
	let nickNameChkFlg = false;
	
	//아이디 중복 체크
	function idCheck() {
		let url = '${ctxPath}/memberIdCheck.mbr?mid=${mid}';
		window.open(url,"idCheckWin","width=580px,height=250px");
	}
	
	//닉네임 중복 체크
	function nickNameCheck() {
		let url = '${ctxPath}/memberNickNameCheck.mbr?nickName=${nickName}';
		window.open(url,"nickNameCheckWin","width=580px,height=250px");
	}
	
	//회원가입폼 체크후 서버로 전송하기
	function fCheck() {
		let mid = myForm.mid.value;
		let pwd = myForm.pwd.value;
		let nickName = myForm.nickName.value;
		let name = myForm.name.value;
/* 		let email1 = myForm.email1.value;
		let birthday = myForm.birthday.value;
		let postcode = myForm.postcode.value;
		let detailAddress = myForm.detailAddress.value;
 */		
		if (''==mid) {
			alert('아이디를 입력하세요');
			myForm.mid.focus();
		}
		if (''==pwd) {
			alert('비밀번호를 입력하세요');
			myForm.pwd.focus();
		}
		if (''==nickName) {
			alert('닉네임을 입력하세요');
			myForm.nickName.focus();
		}
		if (''==name) {
			alert('이름을 입력하세요');
			myForm.name.focus();
		}
		
		//이메일 편집
		if ('' != myForm.email1.value.trim()) 
			myForm.email.value = myForm.email1.value + '@' + myForm.email2.value;
		
		//전화번호 편집
		let tel1 = myForm.tel1.value;
		let tel2 = myForm.tel2.value;
		let tel3 = myForm.tel3.value;
		if (''!=tel2 && ''!=tel3) 
			myForm.telNo.value = tel1 + '-' + tel2 + '-' + tel3;
		
		//주소 편집
		let postcode =myForm.postcode.value;
		let roadAddress = myForm.roadAddress.value;
		let detailAddress = myForm.detailAddress.value;
		let extraAddress = myForm.extraAddress.value;
		myForm.address.value = postcode + '/' + roadAddress + '/' + detailAddress + '/' + extraAddress;
		
		//Homepage 편집
   		let homepage = document.getElementById("homepage").value;
   		let editHomepage = homepage;
		if (''!=homepage.trim()) {
			if (-1!=homepage.indexOf(' ')) {
				alert('홈페이지명에 공백을 포함할 수 없습니다.');
				document.getElementById("homepage").focus();
			} 
			else {
		   		if (7 <= homepage.length) {
		   			editHomepage = homepage.substring(0, homepage.indexOf(':'));
		   			if (editHomepage == 'https' || editHomepage == 'http' ) {
		   				editHomepage = homepage.substring(homepage.indexOf('://')+3);
		   				homepage = editHomepage;
		   			}
		   		}
				document.getElementById("homepage").value = homepage;
			}
		}
		
		//취미 
		let arrHobby = myForm.hobby;
		let hobby = '';
		let hobbyFlg = false;
		for (int i=0; i<hobby.length(); i++) {
			if (hobby[i].checked) {
				hobby += hobby[i].value + '/';
				hobbyFlg = true;
			}
		}
		if (!hobbyFlg) {
			document.getElementById("hobbyEtc").checked = true;
			hobby = document.getElementById("hobbyEtc").value;
		} else {
			hobby = hobby.substring(0, hobby.lastIndexOf("/"));
		}
		myForm.checkedHobbies.value = hobby;
		
		//사진upload 체크
		let fName = myForm.fName.value;
		let ext = fName.substring(fName.lastIndexOf(".")+1);//확장자
		let uExt = ext.toUpperCase();
		let maxSize = 1024 * 1024 * 2;//업로드 회원사진 최대용량 = 2MB
		
		//사진파일 체크
		if (''==fName.trim()) {
			myForm.photo.value = 'noimage';
		}
		else {
			let fileSize = document.getElementById("fName").files[0].size;
			if ('JPG'!=uExt && 'JPEG'!=uExt && 'GIF'!=uExt && 'PNG'!=uExt) {
				alert("업로드 가능한 파일은 'jpg/jpeg/gif/png'파일입니다.");
				document.getElementById("fName").focus();
			}
			if (-1 != fName.indexOf(' ')) {
				alert('업로드 파일명에 공백을 포함할 수 없습니다.');
				document.getElementById("fName").focus();
			}
			if (maxSize < fileSize) {
				alert('업로드 파일의 크기는 2MByte를 초과할 수 없습니다.');
				document.getElementById("fName").focus();
			}
		}
		
		if (!idChkFlg) {
    		document.getElementById("mid").innerText = '아이디 중복체크 버튼을 눌러주세요.';
    		document.getElementById("mid").focus();
		}
		if (!nickNameChkFlg) {
    		document.getElementById("nickName").innerText = '닉네임 중복체크 버튼을 눌러주세요.';
    		document.getElementById("nickName").focus();
		}
		myForm.submit();
	}
	
	//DB에 저장될 각각의 필드길이 체크

	
/* 	function regexCheck(target) {
        const regexMid = /^[a-zA-Z0-9_+-.]{3,20}$/g; //아이디 문자체크 3~20자리
        const regexPwd = /^[a-zA-Z0-9~!?@#$%^&*_+-.]{3,20}$/g; //비밀번호 문자체크 3~20자리
        const regexNickName = /^[가-힣a-zA-Z]{3,20}$/g; //닉네임 문자체크 3~20자리
        const regexName = /^[가-힣a-zA-Z]{3,20}$/g; //이름 문자체크 3~20자리
        const regexEmail = /^[a-zA-Z0-9_+-.]{3,15}+@[a-zA-Z0-9.]{2,10}+\.[a-zA-Z]{2,3}$/g;  //이메일체크(..은 입력불가)
        const regexTelNo = /d{3}+-d{3,4}+-d{4}/g; //전화번호 숫자체크
        const regexDetailAddress = /^[가-힣a-zA-Z0-9 .-#]{1,30}$/g; //상세주소 문자,숫자,특수문자(.-#)체크 1~30자리
        const regexContent = /^[가-힣a-zA-Z0-9~!?@#$%^&*()<>_+=-,.:;/ ]$/gm; //비고 숫자,문자,특수문자체크

        if ('telNo' == target) {
            if (! regexTarget.match(document.getElementById("telNo").value)) {
    			document.getElementById("tel2").value = '';
    			document.getElementById("tel3").value = '';
        		document.getElementById("telNoInValid").innerText = '전화번호 앞자리는 숫자 3~4자리, 뒷자리는 숫자 4자리를 입력하세요.';
            } else {
        		document.getElementById("telNo").value = 
        			document.getElementById("tel1").value + '-' +
        			document.getElementById("tel2").value + '-' +
        			document.getElementById("tel3").value;
        		document.getElementById("telNoValid").innerText = ''';
            }
        }
        if ('email1' == target) {
            if (! regexTarget.match(document.getElementById("email1").value)) {
            	document.getElementById("email1").value = '';
        		document.getElementById("emailInValid").innerText = '이메일은 영문자 또는 숫자 또는 특수문자(_+-.)를 입력하세요.';
            } else {
        		document.getElementById("email").value = 
        			document.getElementById("email").value + '@' + 
        			document.getElementById("emai2").value;
        		document.getElementById("emailValid").innerText = ''';
            }
        }
	}
 */	
	</script>
</head>
<body>
<%@ include file="/include/header_home.jsp" %>
<%@ include file="/include/nav.jsp" %>
<div class="container" style="padding:30px">
<%-- 화일 전송 enctype
  <form name="myform" method="post" action="${ctxPath}/memberJoinOk.mbr" class="was-validated" enctype="Multipart/form-data">
--%>
  <form name="myform" method="post" action="${ctxPath}/memberJoinOk.mbr" class="was-validated" >
    <h2>회 원 가 입</h2>
    <br/>
    <div class="form-group">
		<label for="mid">아이디 : &nbsp; &nbsp;<input type="button" value="아이디 중복체크" class="btn btn-secondary" onclick="idCheck()"/></label>
		<input type="text" class="form-control" id="mid" name="mid" placeholder="아이디를 입력하세요." required autofocus/>
		<div id="midValid" class="valid-feedback"></div>
		<div id="midInValid" class="invalid-feedback">아이디는 필수 입력사항입니다.</div>
    </div>
    <div class="form-group">
		<label for="pwd">비밀번호 :</label>
		<input type="password" class="form-control" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요." required />
		<div id="pwdValid" class="valid-feedback"></div>
		<div id="pwdInvalid" class="invalid-feedback">비밀번호는 필수 입력사항입니다.</div>
    </div>
    <div class="form-group">
		<label for="nickName">닉네임 : &nbsp; &nbsp;<input type="button" value="닉네임 중복체크" class="btn btn-secondary" onclick="nickNameCheck()"/></label>
		<input type="text" class="form-control" id="nickName" name="nickName" placeholder="별명을 입력하세요." required />
		<div id="nickNameValid" class="valid-feedback"></div>
		<div id="nickNameInvalid" class="invalid-feedback">닉네임은 필수 입력사항입니다.</div>
    </div>
    <div class="form-group">
		<label for="name">성명 :</label>
		<input type="text" class="form-control" id="name" name="name" placeholder="성명을 입력하세요." required />
		<div id="nameValid" class="valid-feedback"></div>
		<div id="nameInvalid" class="invalid-feedback">성명은 필수 입력사항입니다.</div>
    </div>
    <div class="form-group">
		<label for="email">Email address :</label>
		<div class="input-group mb-3">
			<input type="text" class="form-control" id="email1" name="email1" placeholder="Email을 입력하세요." required />
			<div class="input-group-append" >
				<select name="email2" class="custom-select">
					<option value="naver.com" selected>naver.com</option>
					<option value="hanmail.net">hanmail.net</option>
					<option value="hotmail.com">hotmail.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="nate.com">nate.com</option>
					<option value="yahoo.com">yahoo.com</option>
				</select>
			</div>
			<input type="hidden" class="form-control" id="email" name="email" />
			<div id="email1Valid" class="valid-feedback"></div>
			<div id="email1Invalid" class="invalid-feedback">이메일은 필수 입력사항입니다.</div>
		</div>
	</div>
    <div class="form-group">
		<div class="form-check-inline">
			<span class="input-group-text">성별 :</span> &nbsp; &nbsp;
			<label class="form-check-label">
				<input type="radio" class="form-check-input" name="gender" value="남자" checked>남자
			</label>
		</div>
		<div class="form-check-inline">
			<label class="form-check-label">
				<input type="radio" class="form-check-input" name="gender" value="여자">여자
			</label>
		</div>
    </div>
    <div class="form-group">
    	<label for="birthday">생일</label>
<%--
	Date now = new Date();
    String strNow = new SimpleDateFormat("yyyy-MM-dd").format(now);
    pageContext.setAttribute("birthday", strNow);
--%>
		<c:set var="now" value="<%=new java.util.Date() %>"/>
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>
		<input type="date" id="birthday" name="birthday" value="${birthday}" class="form-control"/>
    </div>
    <div class="form-group">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">전화번호 :</span> &nbsp;&nbsp;
				<select name="tel1" class="custom-select">
					<option value="010" selected>010</option>
					<option value="02">서울</option>
					<option value="031">경기</option>
					<option value="032">인천</option>
					<option value="041">충남</option>
					<option value="042">대전</option>
					<option value="043">충북</option>
					<option value="051">부산</option>
					<option value="052">울산</option>
					<option value="061">전북</option>
					<option value="062">광주</option>
				</select>-
			</div>
			<input type="text" name="tel2" size=4 maxlength=4 class="form-control"/>-
			<input type="text" name="tel3" size=4 maxlength=4 class="form-control"/>
			<br>
			<input type="hidden" id="telNo" name="telNo" minlength=12 maxlength=13 class="form-control">
			<div id="telNoValid" class="valid-feedback"></div>
			<div id="telNoInValid" class="invalid-feedback"></div>
		</div> 
    </div>
    <div class="form-group">
		<label for="address">주소</label>
		<input type="text" id="sample6_postcode" name="postcode" size="10" placeholder="우편번호">
		<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text" id="sample6_address" name="roadAddress" size="52" placeholder="주소"><br>
		<input type="text" id="sample6_detailAddress" name="detailAddress" size="30" placeholder="상세주소">
		<input type="text" id="sample6_extraAddress" name="extraAddress" size="20" placeholder="참고항목">	
		<input type="hidden" id="address" name="address">
		<div id="detailAddressValid" class="valid-feedback"></div>
		<div id="detailAddressInValid" class="invalid-feedback"></div>
    </div>
    <div class="form-group">
	    <label for="homepage">Homepage address:</label>
	    <input type="text" class="form-control" name="homepage" placeholder="'http://'없이 홈페이지를 입력하세요." id="homepage"/>
		<div id="homepageValid" class="valid-feedback"></div>
		<div id="homepageInValid" class="invalid-feedback"></div>
	</div>
    <div class="form-group">
		<label for="name">직업</label>
		<select class="form-control" id="job" name="job">
			<option value="학생">학생</option>
			<option value="회사원">회사원</option>
			<option value="공무원">공무원</option>
			<option value="군인">군인</option>
			<option value="의사">의사</option>
			<option value="법조인">법조인</option>
			<option value="세무인">세무인</option>
			<option value="자영업">자영업</option>
			<option value="기타">기타</option>
		</select>
    </div>
    <div class="form-group">
        <span class="input-group-text">취미</span> &nbsp; &nbsp;
		<div class="form-check-inline">
		  <label class="form-check-label">
		    <input type="checkbox" class="form-check-input" value="수영" name="hobby"/>수영
		  </label>
		</div>
		<div class="form-check-inline">
		  <label class="form-check-label">
		    <input type="checkbox" class="form-check-input" value="독서" name="hobby"/>독서
		  </label>
		</div>
		<div class="form-check-inline">
		  <label class="form-check-label">
		    <input type="checkbox" class="form-check-input" value="영화감상" name="hobby"/>영화감상
		  </label>
		</div>
		<div class="form-check-inline">
		  <label class="form-check-label">
		    <input type="checkbox" class="form-check-input" value="축구" name="hobby"/>축구
		  </label>
		</div>
		<div class="form-check-inline">
		  <label class="form-check-label">
		    <input type="checkbox" class="form-check-input" value="기타" name="hobby" id="hobbyEtc" checked/>기타
		  </label>
		</div>
		<input type="hidden" name="checkedHobbies" />
    </div>
    <div class="form-group">
		<label for="content">자기소개</label>
		<textarea rows="5" class="form-control" id="content" name="content" placeholder="자기소개를 입력하세요"></textarea>
		<div id="contentValid" class="valid-feedback"></div>
		<div id="contentInValid" class="invalid-feedback"></div>
    </div>
    <div class="form-group">
		<div class="form-check-inline">
		  <span class="input-group-text">정보공개</span>  &nbsp; &nbsp; 
		  <label class="form-check-label">
		    <input type="radio" class="form-check-input" name="userInfo" value="공개" checked/>공개
		  </label>
		</div>
		<div class="form-check-inline">
		  <label class="form-check-label">
		    <input type="radio" class="form-check-input" name="userInfo" value="비공개"/>비공개
		  </label>
		</div>
    </div>
    <div  class="form-group">
      회원 사진(파일용량:2MByte이내) :
		<input type="file" id="fName" name="fName" class="form-control-file border"/>
		<div id="fileValid" class="valid-feedback"></div>
		<div id="fileInValid" class="invalid-feedback"></div>
    </div>
    <button type="button" class="btn btn-secondary" onclick="fCheck()">회원가입</button>
    <button type="reset" class="btn btn-secondary">다시작성</button>
    <button type="button" class="btn btn-secondary" onclick="location.href='${ctp}/memLogin.mem';">돌아가기</button>
	<input type="hidden" name="photo" />
  </form>
  <p><br/></p>
</div>
<br/>
<%@ include file="/include/footer.jsp" %>
</body>
</html>