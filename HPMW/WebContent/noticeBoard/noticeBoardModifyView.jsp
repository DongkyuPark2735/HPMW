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
<link href="${conPath }/css/nbboardPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
			$(document).ready(function() {
				$('form').submit(function () {
					if($('textarea[name="nbcontent"]').val().length >= 4000){
							alert('본문 내용을 4000자 이내로 작성해주세요');
							return false;
					}else if($('input[name="nbtitle"]').val().length >= 150){
							alert('제목을 150자 이내로 작성해주세요');
							return false;
					};
				});
				$('textarea[name="nbcontent"]').keyup(function () {
					var contentlength = $('textarea[name="nbcontent"]').val().length;
					$('.lmitContentLength').text(contentlength+'/4000')
					if(contentlength >= 4000){
						$('.lmitContentLength').html('<b color="red">'+contentlength+'/4000</b>')
					};
				});
				/* 행사글 특수문자 입력 제한  */
				$("input, textarea").keyup(function() {
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
<!-- 헤더 -->
<jsp:include page="../main/header.jsp" />
	<div class="bdr">
		<form action="${conPath }/noticeBoardModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<input type="hidden" name="oldnbfilename" value="${NoticeBoardDetail.nbfilename}">
			<input type="hidden" name="nbno" value="${NoticeBoardDetail.nbno}">
		 <div class="nbheader">
			 <h2>${noticBoardDetail.nbno }번 공지사항 상세보기</h2>
		 </div>
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" name="nbtitle" required="required" size="30"
										 value="${NoticeBoardDetail.nbtitle}"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="mname" value="${manager.mname }(${manager.mno })" readonly="readonly"></td>
				</tr>
				<tr>
					<th colspan="2">본문</th>
				</tr>
				<tr>
					<td colspan="2">
					 <div class="txtwrap" style="white-space: pre-wrap;">
						<textarea name="nbcontent" rows="30" cols="80">${NoticeBoardDetail.nbcontent }</textarea>
					 </div>	
						<h5 class="textResult"></h5>
					 	<h5 class="lmitContentLength"></h5>
				 </td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="nbfilename" >
					</td>
				</tr>
				<tr>
					<td colspan="2" class="imgwrap">
						<img src="${conPath }/noticeBoardFile/${NoticeBoardDetail.nbfilename}" width="300" height="300">
					</td>
				</tr>
				<tr class="nbinput">
					<td colspan="2">
							<input type="submit" value="글수정" class="btn">
							<input type="button" value="취소" class="btn" onclick="location.href='${conPath}/noticeBoardList.do?pageNum=${pageNum}'">
							<input type="button" value="목록" class="btn"	 onclick="location.href='${conPath}/noticeBoardList.do?pageNum=${pageNum}'">
					</td>
				</tr>	
			</table>
		</form>
	</div>
</body>
</html>