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
<link href="${conPath }/css/pastpcboardListPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
/* 글 상세보기 리스트 클릭시  */
//   $(document).ready(function() {
// 		$('tr').click(function(){
// 			var ptconno = $(this).siblings('.pcContractno').text();
// 			if(!isNaN(ptconno)){
// 				location.href = '${conPath}/ptModifyEmpConView.do?ptconno='+ptconno+'&pageNum=${pageNum}';
// 			}
// 		});
// 	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<!-- 헤더  -->
	<div class="bdr">
		<div class="blHeader">
			<h2> 지난 근로계약서 목록 </h2>
		</div>
		<table class="writeButton">
			<tr>
				<td>
					<input type="button" value="당일 근로계약서 보기" class="btn" onclick="location.href='${conPath }/ptEmpConList.do'">
				</td>
			</tr>
		</table>
		
		<table>
			<tr>
				<th>구분번호</th>
				<th>근로계약서 번호</th>
				<th>ID</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>주 소</th>
				<th>행사 번호</th>
				<th>마감<br>여부 </th>
				<th>근무<br>시간</th>
				<th>시급</th>
				<th>예상 지급액</th>
				<th>근로 날짜</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="6">지난 근로계약서가 없습니다.</td>
				</tr>
			</c:if>
			<!-- 파트타이머 지난 목록 출력 -->
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${ppContractDataList }" var="ppContractDataLists">
						<tr>
							<td>${ppContractDataLists.pcdatano }</td>
							<td class="pcContractno">${ppContractDataLists.ptconno }</td>
							<td class="pcContractDetail">${ppContractDataLists.ptid }</td>
							<td class="pcContractDetail">${ppContractDataLists.ptname }</td>
							<td>${ppContractDataLists.pttel}</td>
							<td>${ppContractDataLists.ptemail}</td>
							<td>${ppContractDataLists.ptaddress}</td>
							<td>${ppContractDataLists.evno}</td>
							<td>${ppContractDataLists.ptstatus}</td>
							<td>${ppContractDataLists.ptworktime}</td>
							<td>${ppContractDataLists.pthourlywage}</td>
							<td>${ppContractDataLists.pttotalpay}</td>
							<td>${ppContractDataLists.ptrdate}</td>
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