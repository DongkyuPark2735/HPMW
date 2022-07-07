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
<link href="${conPath }/css/evboardContentPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			var parttimer = "<c:out value='${parttimer}'/>";
			if(parttimer!=""){
				console.log(parttimer);
				$(".listbtn").attr("value", '메인으로');
				$(".listbtn").attr("onclick", 'location.href="${conPath}/main.do"');
			}
		});
	</script>
</head>
<body>
		<!-- 해더 -->
	<jsp:include page="../main/header.jsp" />
		<div class="bdr">
		<c:if test="${not empty EventsDetail }">
			<div class="evheader">
				<h2>행사 상세 정보 </h2>
			</div>
			<table>
				<tr>
					<th>행사번호</th>
					<td>${EventsDetail.evno }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${EventsDetail.mno }</td>
				</tr>
				<tr>
					<th>행사명</th>
					<td>${EventsDetail.evtitle }</td>
				</tr>
				<tr>
					<th colspan="2">행사 상세정보</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="40" cols="100" readonly="readonly">${EventsDetail.evdetail }</textarea>
					</td>
				</tr>
				<tr>
					<th>행사 일시</th>
					<td><fmt:formatDate value="${EventsDetail.evstartdate}" pattern="yy-MM-dd(E)"/></td>
				</tr>
				<tr>
					<th>행사 유형</th>
					<td>${EventsDetail.etno}</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:if test="${manager.mno eq EventsDetail.mno }">
							<input type="button" value="행사 수정" class="btn"
										 onclick="location.href='${conPath}/eventsBoardModifyView.do?evno=${EventsDetail.evno }&pageNum=${pageNum }&mno=${manager.mno }'" >
							<input type="button" value="행사 삭제" 
										 onclick="location.href='${conPath}/eventsBoardDelete.do?evno=${EventsDetail.evno }&pageNum=${pageNum }&mno=${manager.mno }'">
						</c:if>
							<input type="button" value="목록" class="listbtn"	 onclick="location.href='${conPath}/eventsBoardList.do?pageNum=${pageNum}'">
					</td>
				</tr>	
			</table>
		</c:if>
		<c:if test="${empty EventsDetail }">
			<div class="evheader">
				<h2>나에게 등록된 <br>행사가 없습니다.</h2>
			</div>
			<table>
				<tr>
					<td id="nonline">
						<input type="button" value="메인페이지로" class="btn"	 onclick="location.href='${conPath}/main.do'">
					</td>
				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>