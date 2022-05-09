<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="pageNo" value="${pageNo}" scope="request" />
<c:set var="totPage" value="${totPage}" scope="request" />
<c:set var="curScrStartNo" value="${curScrStartNo}" scope="request" />
<c:set var="blockSize" value="${blockSize}" scope="request" />
<c:set var="curBlock" value="${curBlock}" scope="request" />
<c:set var="lastBlock" value="${lastBlock}" scope="request" />
<%-- <c:set var="pageSize" value="${pageSize}" scope="request" /> --%>
<c:set var="LF" value="\n" scope="page" />
<c:set var="BR" value="<br>" scope="page" />
<c:set var="First" value="<<" scope="page" />
<c:set var="Last" value=">>" scope="page" />
<c:set var="Prev" value="◁" scope="page" />
<c:set var="Next" value="▷" scope="page" />

<c:set var="no" value="${vos.size()}" scope="page" />
<c:set var="attrMid" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>memberList.jsp</title>
    <%@ include file="/include/bs4.jsp" %>
    <style></style>
    <script>
    	'use strict';
    	function openWindowMemberDetail(mid) {
    		${attrMid} = mid;
    		let url = '${ctxPath}/memberDetail.mbr';
    		window.open(url, 'memberDetailWin', 'width=800px,height=600px');
    	}
    </script>
</head>
<body>
    <%@ include file="/include/header_home.jsp" %>
    <%@ include file="/include/nav.jsp" %>
<p><br></p>
<div class="container">
	<h2 class="text-center">전체 회원 리스트</h2>

	<!-- 페이징 처리 시작 -->
	<div class="col text-right">
<c:if test="${pageNo > 1}">
		<a href='memberList.adm?pageNo=1' title='first'>${First}</a>
			<a href='memberList.adm?pageNo=${pageNo - 1}' title='prev'>${Prev}</a>
</c:if>
			${pageNo}Page / ${totPage}Pages
<c:if test="${pageNo != totPage}">
			<a href='memberList.adm?pageNo=${pageNo + 1}' title='next'>${Next}</a>
</c:if>
		<a href='memberList.adm?pageNo=${totPage}' title='last'>${Last}</a>
	</div>
	<!-- 페이징 처리 끝 -->
			
	<table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>아이디</th>
			<th>별명</th>
			<th>성명</th>
			<th>성별</th>
		</tr>
<c:forEach var="vo" items="${vos}" varStatus="st">
		<tr>
			<td><c:out value="${curScrStartNo}"></c:out></td>
			<td><a href="javascript:openWindowMemberDetail(${vo.mid})"><c:out value="${vo.mid}"></c:out></a></td>
			<td><c:out value="${vo.nickName}"></c:out></td>
			<td><c:out value="${vo.name}"></c:out></td>
			<td><c:out value="${vo.gender}"></c:out></td>
		</tr>
		<c:set var="curScrStartNo" value="${curScrStartNo-1}"/>
</c:forEach>
		<tr><td colspan="5"></td></tr>
	</table>
	<!-- 블럭페이징 처리 시작 -->
	<div class="text-center">
<c:if test="${pageNo > 1}">
		[<a href='memberList.adm?pageNo=1' title='first'>첫페이지</a>]
</c:if>
<c:if test="${curBlock > 0}">
			[<a href='memberList.adm?pageNo=${(curBlock-1)*blockSize+1}' title='prevBlock'>이전블록</a>]
</c:if>
				<c:set var="isBreak" value="false"/>
			<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" step="1"> 
				<c:if test="${i>totPage}"><c:set var="isBreak" value="true"/></c:if>
				<c:if test="${isBreak == false}">
					<c:if test="${i==pageNo}">[<a href='memberList.adm?pageNo="${i}"'><font color='red'><b>${i}</b></font></a>]</c:if>
					<c:if test="${i!=pageNo}"></c:if>
				</c:if>
			</c:forEach>
<c:if test="${curBlock < lastBlock}">
			[<a href='memberList.adm?pageNo=${(curBlock+1)*blockSize+1}' title='nextBlock'>다음블록</a>]
</c:if>
<c:if test="${page != totPage}">
		[<a href='memberList.adm?pageNo=${totPage}' title='last'>마지막페이지</a>]
</c:if>
	</div>
	<!-- 블럭페이징 처리 끝 -->
	

</div>
    <%@ include file="/include/footer.jsp" %>
</body>
</html>