<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>test1.jsp(/filter/test1.jsp)</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>회 원 정 보</h2>
	<form name="myForm" method="post" action="<%=request.getContextPath()%>/filter/test1">
	  <table class="table">
	    <tr>
	      <th>성명</th>
	      <td><input type="text" name="name" class="form-control" autofocus /></td>
	    </tr>
	    <tr>
	      <th>성별</th>
	      <td>
	        <input type="radio" name="gender" value="남"/>남 &nbsp;&nbsp;
		      <input type="radio" name="gender" value="여">여
		    </td>
		  </tr>
		  <tr>
		    <th>나이</th>
		    <td><input type="number" name="age" class="form-control"/></td>
		  </tr>
			<tr>
			  <td colspan="2">
			  	<input type="submit" value="전송" class="btn btn-success"/>
			    <input type="reset" value="취소" class="btn btn-success"/>
			  </td>
			</tr>
	  </table>
	</form>
</div>
<p><br/></p>
</body>
</html>