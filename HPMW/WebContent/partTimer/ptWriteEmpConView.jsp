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
<link href="${conPath }/css/ptWriteEmpConPage.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			// 근로계약서 정보 입력
			$('form').submit(function(){
				var ptemailselect = $('select[name="ptemailselect"]').val();
				var ptemail = $('input[name="ptemail"]').val();
				var selectbtno = $('select[name="btno"]').val();
				
				if(ptemail.length > 3 ){  
					if(ptemailselect == null){
						alert('메일 주소를 선택해주세요');
						$('select[name="ptemailselect"]').focus();
						return false;
					}
				}else if(selectbtno == null){
					alert('은행 명을 입력해주세요');
					$('select[name="btno"]').focus();
					return false;
				}
			});
			
			$('input[name="ptname"]').on("keyup", function(){  //이름 입력 제한
 				$(this).val($(this).val().replace(/[^가-힣]/g,''));
 			});
			$('input[name="ptaccountno"]').on("keyup", function(){ //계좌번호 입력제한
 				$(this).val($(this).val().replace(/[^0-9]/g,''));
 				$(this).val($(this).val().replace(/^(\d{4})(\d{4})(\d{4})(\d{2})$/, '$1-$2-$3-$4'));
 			});
			$('input[name="pttel"]').on("keyup", function(){     //전화번호  입력제한
 				$(this).val($(this).val().replace(/[^0-9]/g,''));
 				$(this).val($(this).val().replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, '$1-$2-$3'));
 			});
			$('input[name="ptemail"]').keyup(function(){ // 메일 입력 제한 
 				$(this).val($(this).val().replace(/[^a-zA-Z0-9()_-]/g,''));
			});
			$('input[name="ptaddress"]').keyup(function(){ // 주소 입력 제한 
 				$(this).val($(this).val().replace(/[^가-힣a-zA-Z0-9 ()_-]/g,''));
			});
			$('input[name="modifyParttimerInfo"]').click(function () {

			});
		});
	</script>
</head>
<!-- 근로계약서 작성 확인 메시지  -->

<c:if test="${not empty ParttimerContractInsetResult }">
	<script>
		$(document).ready(function() {
			console.log('${ParttimerContractInsetResult }');
			alert('${ParttimerContractInsetResult }');
		});
	</script>
</c:if>

<!-- 파트타이머 근로계약서 작성후 나의 근로계약서 보기 readonly로 수정-->
<c:if test="${parttimer.ptempconchek eq 1 }">
	<script>
		$(document).ready(function() {
			$('input[type="text"]').attr('readonly', true);
			$('select[name="ptemailselect"]').attr('disabled', 'disabled');
		});
	</script>
</c:if>
<!-- 파트타이머 근로계약서 수정 메시지-->
<c:if test="${not empty deletePtempconchek}">
	<script>
		$(document).ready(function() {
			alert('${deletePtempconchek}');
		});
	</script>
</c:if>

<!-- 파트타이머 근로계약서 수정 시 form 태그 값 변경 -->
<c:if test="${not empty parttimerContractdetail}">
	<script>
		$(document).ready(function() {
			$('.frm').attr('action', "${conPath }/modifyParttimerCon.ptdo");
		});
	</script>
</c:if>
<body>


<!-- 헤더 -->
<jsp:include page="../main/header.jsp"/>
	<div class="bdr">
		<div class="pcheader">
			<h2>계정(${parttimer.ptid }) 근로계약서 </h2>
			</div>
		<table>
			<tr>
				<th colspan="2">${EmployContractForm.ectitle }</th>
			</tr>
			<tr>
				<td colspan="2" class="EmployContract">
					<textarea rows="40" cols="100" readonly="readonly">${EmployContractForm.eccontent }</textarea>
				</td>
			</tr>
	</table>
	<hr>
	<form class="frm" action="${conPath }/ptWriteEmpCon.ptdo" method="post" >
	 <div class="inputpcinfo">
		<input type="hidden" name="ptconno" value="${parttimerContractdetail.ptconno }"> 
		<table>
	 	
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="ptname" value="${parttimerContractdetail.ptname }" maxlength="8" required="required"> 
				</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>
					<input type="text" name="pttel" value="${parttimerContractdetail.pttel }" maxlength="13" required="required" >
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" name="ptemail" value="${parttimerContractdetail.ptemail }" maxlength="30">
					<select name="ptemailselect">
						<option hidden="hidden" selected="selected" disabled="disabled" value="nonEmail">메일을 선택하세요</option>
							<option value="@daum.net">@daum.net</option>
							<option value="@naver.com">@naver.com</option>
							<option value="@google.com">@google.com</option>
					</select>
					</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="ptaddress" value="${parttimerContractdetail.ptaddress }"></td> <!-- 특수문자 금지  -->
			</tr>
			<tr>
				<th>계좌번호</th>
				<c:if test="${parttimer.ptempconchek eq 0 }">
					<td>
					<select name="btno">
							<option hidden="hidden" selected="selected" disabled="disabled">-- 은행 목록 --</option>
							<c:forEach var="BankTypes" items="${BankType }">
								<c:if test="${parttimerContractdetail.btno eq BankTypes.btno }">
									<option value="${BankTypes.btno }" selected="selected">
										${BankTypes.btname}
									</option>
								</c:if>
								<c:if test="${parttimerContractdetail.btno != BankTypes.btno }">
									<option value="${BankTypes.btno }">
										${BankTypes.btname}
									</option>
								</c:if>
							</c:forEach>
						</select>
					<input type="text" name="ptaccountno" value="${parttimerContractdetail.ptaccountno}" maxlength="17" required="required"> <!-- 숫자만 -->
					</td>
				</c:if>
				<c:if test="${parttimer.ptempconchek eq 1 }">
					<c:forEach var="BankTypes" items="${BankType }">
						<c:if test="${parttimerContractdetail.btno eq BankTypes.btno}">
					 		<td>${parttimerContractdetail.ptaccountno }(${BankTypes.btname})</td>
						</c:if>
					</c:forEach>
				</c:if>
			</tr>

			<tr>
				<c:if test="${parttimer.ptempconchek eq 0}"> 
				 <td colspan="2">
					<c:if test="${empty parttimerContractdetail}">  <!-- 첫 작성 일때 입력 버튼 -->
						<input type="submit" value="근로계약서  정보 입력" >
						<input type="reset" value="로그인 화면으로" onclick="location.href='${conPath}/ptLoginView.ptdo'">
					</c:if>
					<c:if test="${not empty parttimerContractdetail}">	<!-- 수정하기 일때 입력 버튼  -->
						<input type="submit" value="근로 계약서 정보 수정 ">
					</c:if>
				 </td>
				</c:if>
				
				<c:if test="${parttimer.ptempconchek eq 1}">	
					<td colspan="2">
						<c:if test="${parttimerContractdetail.ptstatus eq 0}"> <!-- 수정하기  -->
							<input type="button" value="수정하기" name="modifyParttimerInfo" onclick="location.href='${conPath}/modifyParttimerInfo.ptdo'" >
						</c:if>
						<input type="reset" value="돌아가기" onclick="location.href='${conPath}/main.do'">
					</td>
				</c:if>
			</tr>
		</table>
	 </div>
	</form>
	</div>
	<!-- 마감 처리 전 -->
</body>
</html>