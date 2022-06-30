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
<style></style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			
			
		});
	</script>
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
	<p>매니저 리스트</p>
	<table>
			<tr>
				<th>사 번</th>
				<th>사원 이름</th>
				<th>사원 등급</th>
				<td><input type="button" value="매니저 관리자 입력" onclick="location.href='${conPath }/managerInsertView.do'"></td>
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
							<input type="button" value="매니저 삭제" onclick="location.href='${conPath }/managerDelete.do?mno=${managerLists.mno }'">
						</td>
					<tr>
				</c:forEach>		
			</c:if>
		</table>
</body>
</html>




