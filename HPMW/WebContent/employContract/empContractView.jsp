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
<link href="${conPath }/css/empContPage.css" rel="stylesheet"> 
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		/* 제목 입력 제한 */
		$('form').submit(function() {
			if ($('input[name="nbtitle"]').val().length >= 150) {
				alert('제목을 150자 이내로 작성해주세요');
				return false;
			}
		});
		/* 본문 특수문자 입력 제한  */
		$("input, textarea").keyup(function() {
			$(this).val($(this).val().replace(/><''/g,''));
 			var value = $(this).val();
 			var arrchar = new Array();
 			arrchar.push("'");
 			arrchar.push("<");
 			arrchar.push(">");
 			$('.textResult').text("");
 			for (var i = 0; i < arrchar.length; i++) {
 				if (value.indexOf(arrchar[i]) != -1) {
 					$('.textResult').text("<, >, ' 특수문자는 사용하실 수 없습니다.");
 					value = value.substr(0, value.indexOf(arrchar[i]));
 					$(this).val(value);
 				}
 			}
		});
	});
	
</script>
</head>
<body>
	<!-- 근로계약서 수정 결과   -->
	<c:if test="${not empty empContractResultMSG }">
		<c:if test='${empContractResultMSG eq "근로계약서 양식이 수정되었습니다."}'>
			<script>
				alert("${empContractResultMSG }");
			</script>
		</c:if>
		<c:if test='${empContractResultMSG eq "근로계약서 양식 수정에 실패하였습니다."}'>
			<script>
				alert("${empContractResultMSG }");
			</script>
		</c:if>
	</c:if>

	<!-- 헤더  -->
	<jsp:include page="../main/header.jsp" />

	

	<!-- 부서 관리자 근로계약서 서식 페이지  -->
	
	<div class="bdr">
	 <div class="echeader">
	 		<h2>근로계약서 서식 수정</h2>
	 </div>
		<form action="${conPath }/empContractModify.do" method="post">
		 <table>
		 	<tr>
		 		<th>근로계약서 제목</th>
		 		<td>
		 			<input type="text" name="ectitle" value="${employContract.ectitle}">
		 		</td>
		 	</tr>
		 	<tr>
		 		<td colspan="2">
					<div style="white-space: pre-wrap;">
		 				<textarea name="eccontent" rows="40" cols="100">${employContract.eccontent}</textarea>
					</div>
					<h5 class="textResult"></h5>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td colspan="2">
					<input type="submit" value="수정"> 
					<input type="button" value="취소" onclick="history.back();">
		 		</td>
		 	</tr>
		 </table>	
		</form>
	</div>	
</body>
</html>
