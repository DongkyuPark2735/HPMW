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
<link href="${conPath }/css/evboardPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			/* 행사글 입력 글자수 제한 */
			$('form').submit(function () {
				if($('textarea[name="evdetail"]').val().length >= 4000){
						alert('본문 내용을 4000자 이내로 작성해주세요');
						return false;
				}else if($('input[name="evtitle"]').val().length >= 150){
						alert('제목을 150자 이내로 작성해주세요');
						return false;
				};
			});
			$('textarea[name="evdetail"]').keyup(function () {
				var contentlength = $('textarea[name="evdetail"]').val().length;
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
<jsp:include page="../main/header.jsp" />
	<div class="bdr">
		<div class="evheader">
			<h2>행사 입력</h2>
		</div>
		<form action="${conPath}/eventsBoardWrite.do" method="post">
			<table>
				<tr>
					<th>행사명</th>
					<td>
						<input type="text" name="evtitle" required="required"></td>
				</tr>
				<tr>
					<th colspan="2">행사 상세정보</th>
				</tr>
				<tr>
					<td colspan="2">
						<div class="txtwrap" style="white-space: pre-wrap;">
							<textarea rows="40" cols="100" name="evdetail"></textarea>
						</div>
						<h5 class="textResult"></h5>
						<h5 class="lmitContentLength"></h5>
					</td>
				</tr>
			</table>
			
			<table class="evbottom">
				<tr>
					<th>행사 일시</th>
					<td><input type="text" name="evstartdate" id="evstartdate"></td>
				</tr>
				<tr>
					<th>행사 유형</th>
					<td>
						<select name="etno">
							<c:forEach var="evtype" items="${eventsTypeList }">
									<option>${evtype.etno } (${evtype.etname })</option>	
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" value="${manager.mno }" name="mno" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td colspan="2">
							<input type="submit" value="행사입력" class="btn">
							<input type="button" value="취소" 
										 onclick="history.back();">
							<input type="button" value="목록" class="btn"	 onclick="location.href='${conPath}/eventsBoardList.do?pageNum=${pageNum }'">
					</td>
				</tr>	
			</table>
		</form>
	</div>
</body>
 <!-- datepicker -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  <script>
	  $( function() {
	    $("#evstartdate" ).datepicker({
	    	dateFormat : 'yy-mm-dd',
	    	changeMonth : true, 
	    	monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    	showMonthAfterYear : true,
	    	yearSuffix : '년',
	    	showOtherMonths : true,
	    	dayNamesMin:['일','월','화','수','목','금','토'],
				changeYear : true, 
				minDate : 0, 
				yearRange : 'c-10:c+10',
	    });
	  });
  </script>
</html>