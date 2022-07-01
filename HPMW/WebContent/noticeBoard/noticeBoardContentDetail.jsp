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
		<!-- 해더 -->
	<jsp:include page="../main/header.jsp" />
			<table>
				<caption>${noticBoardDetail.nbno }번 공지사항 상세보기</caption>
				<tr>
					<td>제목</td>
					<td>${noticBoardDetail.nbtitle }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${noticBoardDetail.mno }</td>
				</tr>
				<tr>
					<td>본문</td>
					<td>${noticBoardDetail.nbcontent }</td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td>
						<img src="${conPath }/noticeBoardFile/${noticBoardDetail.nbfilename }" width="300" height="300"> 
					</td>
				</tr>
				<tr>
					<td>조회수</td>
					<td>${noticBoardDetail.nbhit }</td>
				</tr>
				<tr>
					<td>작성일</td>
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
							<input type="button" value="목록" class="btn"	 onclick="location.href='${conPath}/noticeBoardList.do?pageNum=${pageNum }'">
					</td>
				</tr>	
			</table>
</body>
</html>