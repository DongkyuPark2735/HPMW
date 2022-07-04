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
<style></style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			/* 시급입력시 단위 표시 및 문자 입력 제한  */
			$('input[name="pthourlywage"]').on("keyup", function(){    
 				$(this).val($(this).val().replace(/[^0-9]/g,''));
 				$(this).val($(this).val().replace(/,/g,''));
 				$(this).val($(this).val().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
 			});
			// 시급계산  //// 미완성///////////
	  	$('input[name="calculatePay"]').click(function (){
	  		var starttime = $('input[name="startTime"]').val().substring(0,2);
	  		var endtime = $('input[name="endTime"]').val().substring(0,2);
	  		var minutecal = Math.round(($('input[name="startTime"]').val().substring(3,5) - $('input[name="endTime"]').val().substring(3,5)));
	  		var worktime = (endtime - starttime);
	  		
	  		console.log(minutecal);
	  		console.log(starttime);
	  		console.log(endtime);
	  		var pthourlywage = Number($('input[name="pthourlywage"]').val().replace(/,/g,''));
	  		console.log(pthourlywage);
	  		
	  		$('input[name="pttotalpay"]').val(worktime * pthourlywage);
	  		$('input[name="ptworktime"]').val(worktime);
			});
			//행사 입력 
			$('input[name="insertEvents"]').click(function(){
			  if($('select[name="evno"]').val() == null){
					alert('행사를 입력해주세요');
					$('select[name="evno"]').focus();
				}else{
					var insertEvents = $('select[name="evno"]').val();
					console.log(insertEvents);
					location.href='${conPath}/PtInsertEvents.do?ptconno=${parttimerContract.ptconno }&evno='+insertEvents;
				}
			});
			// 마감처리 
			$('form').submit(function(){
				var pthourlywage = $('input[name="pthourlywage"]').val();
				var pttotalpay = $('input[name="pttotalpay"]').val();
				var eventsChek = "<c:out value='${parttimerContract.evno }'/>";
				if(!pthourlywage){
					alert('시급을 입력해주세요');
					$('input[name="pthourlywage"]').focus();
					return false;
				}else if(!pttotalpay || pttotalpay == 0 || pttotalpay == null ){
					alert('예상 총 금액을 입력해주세요');
					$('input[name="pttotalpay"]').focus();
					return false;
				}else if($('select[name="evno"]').val() == null){
					alert('행사를 입력해주세요');
					$('select[name="evno"]').focus();
					return false;
				}else if(!eventsChek){
					alert('마감전에 행사를 입력해 주세요 ');
					$('select[name="evno"]').focus();
					return false;
				}
			});
	  });
	</script>
</head>
<body>
	<!-- 헤더 -->
 <jsp:include page="../main/header.jsp"/>
	<c:if test="${not empty pcModifyResult }">
		<script>
			alert('${pcModifyResult }');
		</script>
	</c:if>
	<table>
		<caption>${parttimerContract.ptconno }(${parttimerContract.ptname })님 근로계약서 상세보기</caption>
			<tr>
				<td>파트타이머 근로계약서 번호</td>
				<td>${parttimerContract.ptconno }</td>
			</tr>
			<tr>
				<td>파트타이머 이름(아이디)</td>
				<td>${parttimerContract.ptname }(${parttimerContract.ptid })</td>
			</tr>
			<tr>
				<td>연락처</td>
				<td>${parttimerContract.pttel }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${parttimerContract.ptemail }</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${parttimerContract.ptaddress }</td>
			</tr>
			<tr>
				<td>계좌번호</td>
				<td>${bankNo }(${bankName })</td>
			</tr>
			<tr>
			<tr>
				<td>근로계약서 작성 날짜</td>
				<td>${parttimerContract.ptrdate }</td>
			</tr>
			<tr>
				<td colspan="2"><hr></td>
			</tr>
	</table>
	<!-- 마감 처리 전 -->
	<c:if test="${parttimerContract.ptstatus != 1}">
		<form action="${conPath }/ptEmpConSubmit.do" method="post" >
			<input type="hidden" name="ptconno" value="${parttimerContract.ptconno }"> 
			<input type="hidden" name="ptworktime" value=""> 
			<table>
				<tr>
					<td>행사 입력 </td>
					<td>
						<select name="evno">
							<option hidden="hidden" selected="selected" disabled="disabled" value="nonEvents">행사를 입력하세요</option>
							<c:forEach var="evnetsDails" items="${evnetsDail }">
								<c:if test="${parttimerContract.evno eq evnetsDails.evno }">
									<option value="${evnetsDails.evno }" selected="selected">
										${evnetsDails.evno }(${evnetsDails.evtitle })
									</option>
								</c:if>
								<c:if test="${parttimerContract.evno != evnetsDails.evno }">
									<option value="${evnetsDails.evno }" class="selectEvents">
										${evnetsDails.evno }(${evnetsDails.evtitle })
									</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>근로 시간 입력</td>
					<td>
						시작 : <input type="time" name="startTime" value="" min="05:00" >
	 					종료 : <input type="time" name="endTime" value=""> 
					</td>
				</tr>
				<tr>
					<td>시급 입력</td>
					<td>
						<input type="text" name="pthourlywage" inputmode="numeric">
						<input type="button" name="calculatePay" value="예상 총 급여 입력">
					</td>
				</tr>
				<tr>
					<td>예상 총 급여</td>
					<td>
						<input type="text" name="pttotalpay" inputmode="numeric"
									 readonly="readonly" value="">
					</td>
				</tr>
			</table>
			<input type="submit" value="근로계약서 마감" >
			<input type="button" name="insertEvents" value="행사 입력">
			<input type="reset" value="취소" onclick="history.back();">
		</form>
	</c:if>
	
	<!-- 마감 처리 후 -->
	<script>
	$(document).ready(function() {
		$('input[name="pcModifyStatus"]').click(function () {
			var result = confirm("근로계약서 수정시 마감처리가 취소되며 시급정보를 다시 입력해야합니다.수정하시겠습니까?");
			if(result == false){
			}else{
				location.href='${conPath }/parttimerContractModify.do?ptconno=${parttimerContract.ptconno }';
			}
		});
	});
	</script>
	<c:if test="${parttimerContract.ptstatus == 1}">
			<table>
				<tr>
					<td>입력된 행사</td>
					<td>
						<c:forEach var="evnetsDails" items="${evnetsDail }">
							<c:if test="${parttimerContract.evno eq evnetsDails.evno }">
									${evnetsDails.evno }(${evnetsDails.evtitle })
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td>입력된 근로시간</td>
					<td>${parttimerContract.ptworktime }</td>
				</tr>
				<tr>
					<td>입력된 시급</td>
					<td>${parttimerContract.pthourlywage }</td>
				</tr>
				<tr>
					<td>입력된 예상 총 급여</td>
					<td>${parttimerContract.pttotalpay }</td>
				</tr>
			</table>
		<fmt:formatDate var="sysdateTemp" value="${sysdate }" pattern="yyyy-MM-dd"/>
		<c:if test="${parttimerContract.ptrdate == sysdateTemp}">
			<input type="button" name="pcModifyStatus" value="수정하기">
		</c:if>
			<input type="button" value="취소" onclick="history.back();">
	</c:if>
</body>
</html>