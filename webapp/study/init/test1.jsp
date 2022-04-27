<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>test1.jsp(/0419/menuEx.jsp에서 호출됨)</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>특정 서블릿으로부터 초기값을 할당받는다.</h2>
  <h3>servlet에서 받은 초기값</h3>
  <p>
    아이디 : ${mid}<br/>
    비밀번호 : ${pwd}<br/>
    부서명 : ${className}<br/>
  </p>
  <hr/>
  <p>
    회사명 : ${logoName}
    홈페이지주소 : ${homeAddress}
  </p>
  <div>
    <input type="button" value="서블릿으로부터초기값할당받기" onclick="location.href='<%=request.getContextPath()%>/Study1ServletInit';" class="btn btn-info"/>
    <input type="button" value="돌아가기" onclick="history.back();" class="btn btn-info"/>
  </div>
</div>
<p><br/></p>
</body>
</html>