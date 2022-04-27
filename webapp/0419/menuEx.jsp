<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>MenuEx.jsp</title>
</head>
<body>
logoName : ${logoName} / homeAddress : ${homeAddress}<br/>
<!-- 
<a href="main.jsp">홈으로</a>
<a href="guest.jsp">방명록</a>
<a href="board.jsp">게시판</a>
<a href="pds.jsp">자료실</a>
<a href="photo.jsp">포토갤러리</a>
 -->
<a href="main.jsp">홈으로</a> |
<a href="main.jsp?flag=guest">방명록</a> |
<a href="main.jsp?flag=board">게시판</a> |
<a href="main.jsp?flag=pds">자료실</a> |
<a href="main.jsp?flag=photo">포토갤러리</a><br/>
<a href="<%=request.getContextPath()%>/Study1ServletInit">서블릿에서초기값할당</a> |
<a href="<%=request.getContextPath()%>/Study1WebXmlInit">web.xml에서공통변수초기값할당</a> |
<a href="main.jsp?flag=test1">Test1호출</a>
</body>
</html>