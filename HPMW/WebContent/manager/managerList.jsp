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
<link href="${conPath }/css/managerboardListPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<c:if test="${not empty ManagerDeleteMSG}">
		<script>
			alert("${ManagerDeleteMSG }");
		</script>
	</c:if>
	
	<!-- 해더 -->
	<jsp:include page="../main/header.jsp" />
	<!-- 매니저 관리자 리스트 -->
	<div class="bdr">
		<div class="blHeader">
			<h2>매니저 리스트</h2>
		</div>
		
		<table class="writeButton">
			<tr>
				<td>
					<input type="button" class="btn" value="매니저 관리자 입력" onclick="location.href='${conPath }/managerInsertView.do'">
				</td>
			</tr>
		</table>

	<table>
			<tr>
				<th>사 번</th>
				<th>사원 이름</th>
				<th>사원 등급</th>
				<th></th>
			</tr>
			<c:if test="${empty managerList}">
				<tr>
					<td>
						<p>등록된 관리자가 없습니다.</p>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty managerList}">
				<c:forEach items="${managerList }" var="managerLists">
					<tr>
						<td>
							${managerLists.mno } 
						</td>
						<td>
							${managerLists.mname }
						</td>
						<td>
							${managerLists.allevel }
						</td>
						<td>
							<input type="button" class="btn" value="매니저 삭제" onclick="location.href='${conPath }/managerDelete.do?mno=${managerLists.mno }'">
						</td>
					<tr>
				</c:forEach>		
			</c:if>
		</table>
	</div>
</body>
</html>




