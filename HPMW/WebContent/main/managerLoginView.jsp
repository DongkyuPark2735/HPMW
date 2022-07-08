<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/loginPage.css" rel="stylesheet"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('input[name="mno"]').keyup(function(){
			$(this).val($(this).val().replace(/[^0-9]/g,''));
		});

	});
</script>
</head>
<body>

<c:if test="${not empty logoutMSG }">
	<script>
		alert("${logoutMSG }");
	</script>
</c:if>
	<div class="warpLogo">
		<div class="logo">
			<h2>Hotel Parttimer Management System</h2>
		</div>
	</div>
	<div class="wrap">
		<form action="${conPath }/managerLogin.do" method="post" >
			<table>
			<caption><b>관리자 로그인 페이지</b></caption>
				<tr>
					<td>
						<label for="mnoClass">아이디</label>
						<input type="text" id="mnoClass" name="mno" placeholder="아이디를 입력하세요 " required="required" maxlength="7" >
					</td>
				</tr>
				<tr>
					<td>
						<label for="mpwClass">비밀번호</label>
						<input type="password" id="mpwClass" name="mpw" placeholder="비밀번호를 입력하세요" required="required">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="로그인 ">
					</td>
				</tr>
				<tr>
					<td>
						<p class="managerLoginResult"> </p>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>