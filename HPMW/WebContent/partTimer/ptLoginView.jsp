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
<style>
	*{
		margin : 0 auto;
		
	}
	#wrap{
		width: 1000px;
		height: 500px;
		text-align: center;		
		margin-top: 1000px;
	}
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('input[name="ptid"]').keyup(function(){
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
	<div class="wrap">
		<form action="${conPath }/ptLogin.ptdo" method="post" >
			<table>
			<caption><b>파트타이머 로그인 페이지</b></caption>
				<tr>
					<td>
						<input type="text" name="ptid" placeholder="전화번호 뒷 4자리를 입력하세요" required="required" maxlength="4" >
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="ptpw" placeholder="비밀번호를 입력하세요 최초 비밀번호는 전화번호 뒷  4자리 입니다." required="required">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="로그인 ">
					</td>
				</tr>
				<tr>
					<td>
						<p class="partTimerLoginResult"> </p>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>