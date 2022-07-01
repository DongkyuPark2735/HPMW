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
		text-align: center;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
						
			
		});
	</script>
</head>
<body>
	<!-- 관리자 로그인 오류 메시지 -->
	<c:if test="${not empty managerLoginResultMSG }">
		<script>
			alert('${managerLoginResultMSG}');
			history.back();
		</script>
	</c:if>
<!-- 헤더  -->
 <jsp:include page="../main/header.jsp"/>
 		<!-- 관리자 메인화면 -->
	<c:if test="${not empty manager}">
	 	<!-- 메인 화면 게시글 출력  -->
		<!-- 공지사항 게시글 출력 10개만  -->
		<p>관리자 메인페이지</p>
		<p>공지사항 게시글</p>
		<table>
			<tr>
				<th>글 번호</th>
				<th>작성자</th>
				<th>글 제목</th>
				<th>내 용</th>
				<th>조회수</th>
				<th>작성일시</th>
			</tr>
			<c:if test="${empty noticeBoardList}">
				<tr>
					<td>
						<p>공지사항 게시글이 없습니다.</p>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty noticeBoardList}">
				<c:forEach items="${noticeBoardList }" var="noticeBoardLists" >
					<tr>
						<td>${noticeBoardLists.nbno }</td>
						<td>${noticeBoardLists.mno }</td>
						<td>${noticeBoardLists.nbtitle }</td>
						<td>${noticeBoardLists.nbcontent}</td>
						<td>${noticeBoardLists.nbhit }</td>
						<td><fmt:formatDate value="${noticeBoardLists.nbrdate}" pattern="yy-MM-dd(E) HH:mm"/></td>
					</tr>
				</c:forEach>		
					<tr>
						<td colspan="6">
							<a href="${conPath }/noticeBoardList.do">공지사항 글 더보기 </a>
						</td>
					</tr>
			</c:if>
		</table>
		
	  <!-- 행사 출력 10개만  -->
		<p>행사</p>
		<table>
			<tr>
				<th>행사 번호</th>
				<th>행사 명</th>
				<th>행사 상세정보</th>
				<th>행사 시작일</th>
				<th>행사 유형</th>
				<th>작성자</th>
			</tr>
			<c:if test="${empty eventsList}">
				<tr>
					<td>
						<p>행사가 없습니다.</p>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty eventsList}">
				<c:forEach items="${eventsList }" var="eventsLists">
					<tr>
						<td>${eventsLists.evno }</td>
						<td>${eventsLists.evtitle }</td>
						<td>${eventsLists.evdetail }</td>
						<td><fmt:formatDate value="${eventsLists.evstartdate}" pattern="yy-MM-dd(E)"/></td>
						<td>${eventsLists.etno}</td>
						<td>${eventsLists.mno }</td>
					</tr>
				</c:forEach>		
					<tr>
						<td colspan="6">
							<a href="${conPath }/eventsBoardList.do">행사 더보기 </a>
						</td>
					</tr>
			</c:if>
		</table>
		
	  <!-- 건의사항게시판 출력 10개만  -->
		<p>건의사항게시판</p>
		<table>
			<tr>
				<th>글 번호</th>
				<th>작성자</th>
				<th>글 제목</th>
				<th>글 내용</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
			<c:if test="${empty suggestionBoardList}">
				<tr>
					<td>
						<p>건의게시글이 없습니다.</p>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty suggestionBoardList}">
				<c:forEach items="${suggestionBoardList }" var="suggestionBoardLists">
					<tr>
						<td>${suggestionBoardLists.sbno }</td>
						<td>${suggestionBoardLists.ptid }</td>
						<td class="left">
							<c:forEach var="i" begin="1" end="${suggestionBoardLists.sbindent }">
								<c:if test="${i==suggestionBoardLists.sbindent }">└─</c:if>
								<c:if test="${i!=suggestionBoardLists.sbindent }"> &nbsp; &nbsp; </c:if>
							</c:forEach>
							${suggestionBoardLists.sbtitle}
							<c:if test="${not empty suggestionBoardLists.sbfilename }">
								<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
							</c:if>
						</td>
						<td>${suggestionBoardLists.sbcontent }</td>
						<td>${suggestionBoardLists.sbhit }</td>
						<td><fmt:formatDate value="${suggestionBoardLists.sbrdate }" pattern="yy-MM-dd(E)"/></td>
					</tr>
				</c:forEach>		
			</c:if>
		</table>
	</c:if>
	
	
	<!-- 파트타이머  메인화면 -->
	<c:if test="${not empty parttimer}">
		<p>파트타이머 메인페이지</p>
		<p>공지사항 올 자리</p>
		<p>행사 정보 올자리 </p>
		<p>건의사항 게시판 목록 올자리 </p>
	</c:if>
</body>
</html>
