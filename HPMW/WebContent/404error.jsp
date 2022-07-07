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
<link href="${conPath }/css/errorPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="wrap">
 <div class="erheader">
	<h2>404에러 페이지 입니다.</h2>
 </div>
 <div class="erWrap">
	<h2>죄송합니다. 요청하신 페이지를 찾을수 없습니다.</h2>
	<br>
	<h2><a href="${conPath }/main.do">메인으로</a></h2>
 </div>
</div>
</body>
</html>