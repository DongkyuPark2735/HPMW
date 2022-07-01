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
/* 글 상세보기 리스트 클릭시  */
	$(document).ready(function() {
		$('.pcContractDetail').click(function(){
			var pcno = Number($(this).siblings('.pcContractno').text());
			if(!isNaN(pcno)){
				location.href = '${conPath}/ptWriteEmpConDetail.do?pcno='+pcno+'&pageNum=${pageNum}';
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
				alert('${EventsInsertResult}');
			</script>
		</c:if>
		<c:if test='${EventsInsertResult eq "행사 입력 실패하였습니다."}'>
			<script>
				alert('${EventsInsertResult}');
				history.back();
			</script>
		</c:if>
	</c:if>

	<!-- 행사 수정 후 메시지 출력  -->
	<c:if test="${not empty EventsModifyResult }">
			<script>
				alert('${EventsModifyResult}');
			</script>
			<script>
				alert('${evtitle+" 행사 수정 성공"}');
			</script>
		<c:if test='${EventsModifyResult eq (evtitle+" 행사 수정 성공")}'>
			<script>
				alert('${EventsModifyResult}');
			</script>
		</c:if>
		<c:if test='${EventsModifyResult eq (evtitle+" 행사 수정 실패")}'>
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
	<div id="content_form">
		<table>
			<tr>
				<td>
<%-- 					<c:if test="${not empty manager }"> --%>
<%-- 						<a href="${conPath }/eventsBoardWriteView.do?pageNum=${pageNum }">행사 입력</a> --%>
<%-- 					</c:if> --%>
				</td>
			</tr>
		</table>
		<br>
		<h3>파트타이머 근로계약서 게시판</h3>
		<table>
			<tr>
				<th>근로계약서 번호</th>
				<th>파트타이머 ID</th>
				<th>파트타이머 이름</th>
				<th>파트타이머 전화번호</th>
				<th>행사 번호</th>
				<th>마감 여부</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="6">계약서를 작성한 파트타이머 없습니다</td>
				</tr>
			</c:if>
			<!-- 파트타이머 목록 출력 -->
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${pcContractList }" var="pcContractLists">
					<tr>
						<td class="pcContractno">${pcContractLists.ptconno }</td>
						<td>${pcContractLists.ptid }</td>
						<td class="pcContractDetail">${pcContractLists.ptname }</td>
						<td>${pcContractLists.pttel}</td>
						<td>${pcContractLists.evno}</td>
						<td>${pcContractLists.ptstatus}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<!-- 페이징 번호 출력 -->
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/ptEmpConList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
				[ <a href="${conPath }/ptEmpConList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/ptEmpConList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
		</div>
	</div>
</body>
</html>