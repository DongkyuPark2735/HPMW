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
<link href="${conPath }/css/pcboardListPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
/* 글 상세보기 리스트 클릭시  */
	$(document).ready(function() {
		$('.pcContractDetail').click(function(){
			var ptconno = $(this).siblings('.pcContractno').text();
			if(!isNaN(ptconno)){
				location.href = '${conPath}/ptModifyEmpConView.do?ptconno='+ptconno+'&pageNum=${pageNum }';
			}
		});
	});
</script>
</head>
<body>
	<!-- 파트타이머 근로계약서 행사 입력 후 메시지 출력  -->
	<c:if test="${not empty partTimerInsertEventsResult }">
		<c:if test='${partTimerInsertEventsResult eq "행사가 할당되었습니다."}'>
			<script>
				alert('${partTimerInsertEventsResult}');
			</script>
		</c:if>
		<c:if test='${partTimerInsertEventsResult eq "행사 할당 실패하였습니다."}'>
			<script>
				alert('${partTimerInsertEventsResult}');
				history.back();
			</script>
		</c:if>
	</c:if>

	<!-- 파트타이머 마감처리후 메시지 출력-->
	<c:if test="${not empty parttimerSubmitResult }">
		<c:if test='${parttimerSubmitResult eq "해당 근로계약서 마감처리 되었습니다."}'>
			<script>
				alert('${parttimerSubmitResult}');
			</script>
		</c:if>
		<c:if test='${parttimerSubmitResult eq "해당 근로계약서 마감처리 실패하였습니다."}'>
			<script>
				alert('${parttimerSubmitResult}');
				history.back();
			</script>
		</c:if>
	</c:if>

	<!-- 파트타이머 근로계약서 급여입력 실패 메시지-->
	<c:if test="${not empty parttimerHourlyWageResult }">
		<script>
			alert('${parttimerHourlyWageResult}');
			history.back();
		</script>
	</c:if>
	
	<jsp:include page="../main/header.jsp" />
	<!-- 헤더  -->
	<div class="bdr">
		<div class="blHeader">
			<h2>파트타이머 근로계약서 목록</h2>
		</div>
		
		<table class="writeButton">
			<tr>
				<td>
					<input type="button" class="btn" value="마감된 근로계약서 보기" onclick="location.href='${conPath }/pastptEmpConList.do'">
				</td>
			</tr>
		</table>
			
			<table>
				<tr>
					<th>근로계약서 번호</th>
					<th>ID</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>행사 번호</th>
					<th>근로계약서<br>작성 날짜</th>
					<th>마감 여부</th>
				</tr>
				<c:if test="${totCnt==0 }">
					<tr>
						<td colspan="7">당일 계약서를 작성한 파트타이머가 없습니다</td>
					</tr>
				</c:if>
				<!-- 파트타이머 당일 목록 출력 -->
				<c:if test="${totCnt!=0 }">
					<c:forEach items="${pcContractList }" var="pcContractLists">
						<fmt:formatDate var="sysdateTemp" value="${sysdate }" pattern="yyyy-MM-dd"/>
						<c:if test="${pcContractLists.ptrdate eq sysdateTemp}">
							<tr>
								<td class="pcContractno">${pcContractLists.ptconno }</td>
								<td class="pcContractDetail">${pcContractLists.ptid }</td>
								<td class="pcContractDetail">${pcContractLists.ptname }</td>
								<td>${pcContractLists.pttel}</td>
								<td>${pcContractLists.evno}</td>
								<td>${pcContractLists.ptrdate}</td>
								<td>${pcContractLists.ptstatus}</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
			</table>
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