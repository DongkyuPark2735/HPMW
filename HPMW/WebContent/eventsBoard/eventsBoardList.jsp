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
<link href="${conPath }/css/evboardListPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>/* 글 상세보기 리스트 클릭시  */
	$(document).ready(function() {
		$('.eventsEvdetail').click(function(){
			var evno = Number($(this).siblings('.eventsEvno').text());
			if(!isNaN(evno)){
				location.href = '${conPath}/eventsBoardContent.do?evno='+evno+'&pageNum=${pageNum}';
			}
		});
	});
</script>
</head>
<body>
	<!-- 행사 입력 후 메시지 출력  -->
	<c:if test="${not empty EventsInsertResult }">
		<c:if test='${EventsInsertResult eq "행사 입력 되었습니다."}'>
			<script>
				alert('${EventsInsertResult }');
			</script>
		</c:if>
		<c:if test='${EventsInsertResult eq "행사 입력 실패하였습니다."}'>
			<script>
				alert('${EventsInsertResult }');
				history.back();
			</script>
		</c:if>
	</c:if>
<!-- 행사 수정 후 메시지 출력  -->
	<c:if test="${not empty EventsModifyResult }">
		<c:if test='${EventsModifyResult eq "행사가 수정되었습니다."}'>
			<script>
				alert('${EventsModifyResult}');
			</script>
		</c:if>
		<c:if test='${EventsModifyResult eq "행사 수정 실패하였습니다."}'>
			<script>
				alert('${EventsModifyResult}');
				history.back();
			</script>
		</c:if>
	</c:if>
	
	
	<!-- 행사 삭제 후 메시지 출력  -->
	<c:if test="${not empty EventsDeleteResult }">
		<c:if test='${EventsDeleteResult eq "해당 행사가 삭제되었습니다."}'>
			<script>
				alert('${EventsDeleteResult}');
			</script>
		</c:if>
		<c:if test='${EventsDeleteResult eq "행사 삭제 실패"}'>
			<script>
				alert('${EventsDeleteResult}');
				history.back();
			</script>
		</c:if>
	</c:if>
	
	<jsp:include page="../main/header.jsp" />
	<!-- 헤더  -->
	<div class="bdr">
		<div class="blHeader">
			<h2>행사 정보 게시판</h2>
		</div>
		<table class="writeButton">
			<tr>
				<td>
					<c:if test="${not empty manager }">
						<a href="${conPath }/eventsBoardWriteView.do">행사 입력</a>
					</c:if>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<th>행사 번호</th>
				<th>행사 명</th>
				<th>행사 상세정보</th>
				<th>행사 일시</th>
				<th>행사 타입 </th>
				<th>작성자</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="6"><h2>등록된 행사가 없습니다</h2></td>
				</tr>
			</c:if>
			<!-- 행사 목록 출력 -->
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${EventsList }" var="Events">
					<tr>
						<td class="eventsEvno">${Events.evno }</td>
						<td>${Events.evtitle }</td>
						<td class="eventsEvdetail">${Events.evdetail }</td>
						<td>
							<fmt:formatDate value="${Events.evstartdate}" pattern="yy-MM-dd(E) HH:mm"/>
						</td>
						<td>${Events.etno}</td>
						<td>${Events.mno}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<!-- 페이징 번호 출력 -->
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/eventsBoardList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
				[ <a href="${conPath }/eventsBoardList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/eventsBoardList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
		</div>
	</div>
</body>
</html>