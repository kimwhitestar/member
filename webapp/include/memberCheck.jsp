<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	if (null == session.getAttribute("sMid")) { %>
		<script>
			alert('로그인 후 사용하세요!');
			location.href='<%=request.getContextPath()%>/WEB-INF/member/memberLogin.jsp';
		</script>
<%		return;
	} 
%>