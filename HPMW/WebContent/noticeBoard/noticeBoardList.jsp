<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/nboardListPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>/* 글 상세보기 리스트 클릭시  */
	$(document).ready(function() {
		$('.noticeBoarddetail').click(function(){
			var nbno = Number($(this).siblings('.noticeBoardNbno').text());
			if(!isNaN(nbno)){
				location.href = '${conPath}/noticeBoardContentDetail.do?nbno='+nbno+'&pageNum=${pageNum}';
			}
		});
// 		$('.noticeBoarddetail').mouseover(function(){
// 			$('.bdr table tr td~td').css("background-color","#003057");
// 		});
		
	});
</script>
</head>
<body>
	<!-- 글쓰기 후 메시지 출력  -->
	<c:if test="${not empty noticeBoardResult }">
		<c:if test='${noticeBoardResult eq "글쓰기 성공"}'>
			<script>
				alert('${noticeBoardResult}');
			</script>
		</c:if>
		<c:if test='${noticeBoardResult eq "글쓰기 실패"}'>
			<script>
				alert('${noticeBoardResult}');
				history.back();
			</script>
		</c:if>
	</c:if>

	<!-- 글수정 후 메시지 출력  -->
	<c:if test="${not empty noticeBoardResult }">
		<c:if test='${noticeBoardResult eq "글수정 성공"}'>
			<script>
				alert('${noticeBoardResult}');
			</script>
		</c:if>
		<c:if test='${noticeBoardResult eq "글수정 실패"}'>
			<script>
				alert('${noticeBoardResult}');
				history.back();
			</script>
		</c:if>
	</c:if>
	
	<!-- 글삭제 후 메시지 출력  -->
	<c:if test="${not empty NoticeBoardDeleteResult }">
		<c:if test='${NoticeBoardDeleteResult eq "해당 글이 삭제되었습니다."}'>
			<script>
				alert('${NoticeBoardDeleteResult}');
			</script>
		</c:if>
		<c:if test='${NoticeBoardDeleteResult eq "게시글 삭제 실패"}'>
			<script>
				alert('${NoticeBoardDeleteResult}');
				history.back();
			</script>
		</c:if>
	</c:if>
	
	<jsp:include page="../main/header.jsp" />
	<!-- 헤더  -->
	<div class="bdr">
		<div class="blHeader">
			<h2>공지사항 게시판</h2>
		</div>

	<c:if test="${not empty manager }">
		<table class="writeButton">
			<tr>
				<td>
						<a href="${conPath }/noticeBoardWriteView.do">글쓰기</a>
				</td>
			</tr>
		</table>
	</c:if>

		<table>
			<tr>
				<th>글 번호</th>
				<th>작성자</th>
				<th>글 제목</th>
				<th>내 용</th>
				<th>조회수</th>
				<th>작성일시</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="6">등록된 글이 없습니다</td>
				</tr>
			</c:if>
			<!-- 글 목록 출력 -->
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${NoticeBoardList }" var="NoticeBoard">
					<tr>
						<td class="noticeBoardNbno">${NoticeBoard.nbno }</td>
						<td>${NoticeBoard.mno }</td>
						<td>${NoticeBoard.nbtitle }</td>
						<td class="noticeBoarddetail">${NoticeBoard.nbcontent }</td>
						<td>${NoticeBoard.nbhit }</td>
							<c:if test="${not empty board.fFileName }">
								<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
							</c:if>
						<td>
							<fmt:formatDate value="${NoticeBoard.nbrdate}" pattern="yy-MM-dd(E) HH:mm"/>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<!-- 페이징 번호 출력 -->
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/noticeBoardList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
				[ <a href="${conPath }/noticeBoardList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/noticeBoardList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
		</div>
	</div>
</body>
</html>