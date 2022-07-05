<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style></style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			
			console.log('에러타입  : ' + <%= exception.getClass().getName() %>);
			console.log('에러메세지 : ' + <%=exception.getMessage() %>);
		});
	</script>
</head>
<body>
	<h2>500에러 페이지 입니다.</h2>
	<h2>죄송합니다. 요청 처리과정에서 문제가 발생하였습니다.</h2>
	
	<h2>오류 보고 <a>aaaa@naver.com</a> 010-0000-0000</h2>

</body>
</html>