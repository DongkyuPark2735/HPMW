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
<link href="${conPath }/css/nbboardContentPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
		<!-- 해더 -->
	<jsp:include page="../main/header.jsp" />
		<div class="bdr">
			<div class="nbheader">
				<h2>${noticBoardDetail.nbno }번 공지사항 상세보기</h2>
			</div>
			<table>
				<tr>
					<th>제목</th>
					<td>${noticBoardDetail.nbtitle }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${noticBoardDetail.mno }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${noticBoardDetail.nbhit }</td>
				</tr>
				<tr>
					<th colspan="2">본문</th>
				</tr>
				<tr>
					<td colspan="2" class="nbcontent">${noticBoardDetail.nbcontent }</td>
				</tr>
				
				<tr>
					<th>첨부파일</th>
					<td>
						<img src="${conPath }/noticeBoardFile/${noticBoardDetail.nbfilename }" width="300" height="300"> 
					</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${noticBoardDetail.nbrdate }</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:if test="${manager.mno eq noticBoardDetail.mno }">
							<input type="button" value="글수정" class="btn"
										 onclick="location.href='${conPath}/noticeBoardModifyView.do?nbno=${noticBoardDetail.nbno }&pageNum=${pageNum }&mno=${manager.mno }'" >
							<input type="button" value="글삭제" 
										 onclick="location.href='${conPath}/noticeBoardDelete.do?nbno=${noticBoardDetail.nbno }&pageNum=${pageNum }&mno=${manager.mno }'">
						</c:if>
							<input type="button" value="목록" class="btn"	 onclick='location.href="${conPath}/noticeBoardList.do?pageNum=${pageNum}"'>
					</td>
				</tr>	
			</table>
		</div>
</body>
</html>