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
<link href="${conPath }/css/managerinsertPage.css" rel="stylesheet"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	$(document).ready(function(){
		$('input[name="mno"]').keyup(function(){
			var mno = $('input[name="mno"]').val();
			$.ajax({
				url : '${conPath}/managerIdConfirm.do',
				type : 'get',
				dataType : 'html',
				data : "mno="+mno,
				success : function(data){
					if(mno.length == 7){ 	// 요구 사번 길이 충족시 
						$('.managerIdChkResult').html(data);
					}else{
						$('.managerIdChkResult').html("");
					}
				} // 사번 중복체크 ajax
			});
		});
			
		$('form').submit(function(){
			var mno = $('input[name="mno"]').val();
			var idConfirmResult = $('.managerIdChkResult').text().trim();
			$('input[name="mpw"]').val(mno);
			if(idConfirmResult!='사용 가능한 사번입니다.'){
				alert('사용가능한 사번으로 입력하세요');
				return false;
			}// 사번 사용가능일시 입력한 사번으로 비밀번호 설정
		});
		$('input[name="mname"]').on("keyup", function(){  
			$(this).val($(this).val().replace(/[^가-힣a-zA-Z ]/g,''));
		});
		$('input[name="mno"]').on("keyup", function(){ 
			$(this).val($(this).val().replace(/[^0-9]/g,''));
		});
	});
	</script>
</head>
<body>
	<!-- 해더 -->
	<jsp:include page="../main/header.jsp" />
	<!-- 매니저 입력 -->
	<div class="bdr">
	 <div class="mheader">
		<h2>매니저 입력</h2>
	 </div>
		<form action="${conPath }/managerInsert.do" method="get">
			<table>
				<tr>
					<th>사번</th>
					<td>
					  <input type="text" name="mno" required="required" maxlength="7">
					</td>
				</tr>
				<tr>
				 	<th>비밀번호</th>		
					<td>
						<input type="password" name="mpw" required="required" 
												 placeholder="최초 비밀번호는 사번으로 자동지정됩니다." readonly="readonly" value="">
					</td>
				</tr>
				<tr>
					<th>사원 이름</th>		
					<td>
						<input type="text" name="mname" required="required">
					</td>		
				</tr>
				<tr>
					<td colspan="2">
						<h4 class="managerIdChkResult"></h4>
						<input type="submit" value="입력">
						<input type="button" value="취소" onclick="history.back();">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
