<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	int price = Integer.parseInt(request.getParameter("price"));
%>
<p>성명 : <%=name%></p>
<p>가격 : <%=price%></p>


<%--
<form action="#">
<input type="hidden" name="name" value="<%=name%>" />
<input type="hidden" name="price" value="<%=price%>" />
</form>
--%>
<%
	//jsp:forward시 불필요
	//response.setContentType("text/html; charset=utf-8");
	//response.sendRedirect("test1Result.jsp?name=김말숙&price=1500");//get방식
%>


<!-- request객체를 유지하면서 response로 전송(doPost) -->
<% if (name.equals("admin")) { %>
		<jsp:forward page="test1Admin.jsp"></jsp:forward>
<% } else { %>
		<jsp:forward page="test1Result.jsp"></jsp:forward>
<% } %>
