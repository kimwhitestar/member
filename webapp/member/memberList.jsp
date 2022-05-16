<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="LF" value="\n" scope="page" />
<c:set var="BR" value="<br>" scope="page" />
<c:set var="First" value="<<" scope="page" />
<c:set var="Last" value=">>" scope="page" />
<c:set var="Prev" value="◁" scope="page" />
<c:set var="Next" value="▷" scope="page" />
<c:set var="mid" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>memberList.jsp</title>
    <%@ include file="/include/bs4.jsp" %>
    <style></style>
    <script>
    	'use strict';
    	function openWindowMemberDetail(idx, mid) {
    		let url = '${ctxPath}/memberDetail.mbr';
    		let param = {	idx : idx, 
							mid : mid 	};
    		$.ajax({
    			type:		"post",
    			url:		"${ctxPath}/memberDetail.mbr",
    			data:		param,
    			success:	function(res) {
    				window.open(url, 'memberDetailWin', 'width=800px,height=600px,toolbar=0,status=0,menubar=0,scrollbars=yes');
/*     				if("1"==res) location.reload();
    				else alert('회원상세정보를 찾을 수 없습니다');
 */    			},
    			error:		function() {
    				alert('요청 오류~~');
    			}
    		});
    		//ajaxSubmit();
    	}
    	function changePaging() {
			$("#pageNo").val(1);
    		boardForm.action = "${ctxPath}/memberList.adm";//Post요청
    		boardForm.submit();
    	}
    	function changePage(pageNo) {
    		$("#pageNo").val(pageNo);
    		boardForm.action = "${ctxPath}/memberList.adm";//Post요청
    		boardForm.submit();
    	}
    </script>
</head>
<body>
    <%@ include file="/include/header_home.jsp" %>
    <%@ include file="/include/nav.jsp" %>
<p><br></p>
<div class="container">
	<h2 class="text-center">전체 회원 리스트</h2>
	<br>
	<form name="boardForm" method="post" action="${ctxPath}/boardList.bd">
		<input type="hidden" id="pageNo" name="pageNo"/>
		<div class="row m-2">
	
			<!-- 페이징 처리 시작 -->
			<div class="col text-right">
			<c:if test="${pageNo > 1}">
				<a href="javascript:changePage('1')" title='first'>${First}</a>
					<a href="javascript:changePage('${pageNo - 1}')" title='prev'>${Prev}</a>
			</c:if>
					${pageNo}Page / ${totPage}Pages
			<c:if test="${pageNo != totPage}">
					<a href="javascript:changePage('${pageNo + 1}')" title='next'>${Next}</a>
			</c:if>
			<c:if test="${pageNo != totPage}">
				<a href="javascript:changePage('${totPage}')" title='last'>${Last}</a>
			</c:if>
			</div>
			<!-- 페이징 처리 끝 -->
	
			<div class="text-right p-0">
				<select name="pageSize" id="pageSize" onchange="changePaging()">
					<option value="5"  ${5==pageSize  ? 'selected' : ''} >5건</option>
					<option value="10" ${10==pageSize ? 'selected' : ''} >10건</option>
					<option value="15" ${15==pageSize ? 'selected' : ''} >15건</option>
					<option value="20" ${20==pageSize ? 'selected' : ''} >20건</option>
				</select>
			</div>	
		</div>
			
	<table class="table table-hover text-center">
		<tr class="table-dark text-dark">
			<th>번호</th>
			<th>아이디</th>
			<th>별명</th>
			<th>성명</th>
			<th>성별</th>
		</tr>
<c:forEach var="vo" items="${vos}" >
		<tr>
			<td><c:out value="${curScrStartNo}"></c:out></td>
			<td><a href="javascript:openWindowMemberDetail(${vo.idx},'${vo.mid}')"><c:out value="${vo.mid}"></c:out></a></td>
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
				<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}">
			      <c:if test="${i <= totPage && i == pageNo}">
			        [<a href="memberList.adm?pageNo=${i}"><font color='red'><b>${i}</b></font></a>]
			      </c:if>
			      <c:if test="${i <= totPage && i != pageNo}">
			        [<a href="memberList.adm?pageNo=${i}">${i}</a>]
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
	
	</form>
</div>
    <%@ include file="/include/footer.jsp" %>
</body>
</html>