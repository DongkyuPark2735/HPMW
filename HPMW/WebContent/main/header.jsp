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
<link href="${conPath }/css/header.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 <style>
	#lnbFixed {
		position: fixed;
    top: 0;
    right: 0;
    left: 0;
  }
 </style>
 <script>
	$(document).ready(function() {
		var jbOffset = $('#lnb').offset();
		$(window).scroll(function() {
			if ($(document).scrollTop() > jbOffset.top) {
					$('#lnb').attr('id','lnbFixed');
			}else {
					$('#lnb').removeAttr('id');
      }
    });
  });
 </script>
</head>
<body>
	<!-- 파트타이머 로그인 오류 메시지 -->
	<c:if test="${not empty parttimerLoginMSG }">
		<script>
			alert('${parttimerLoginMSG}');
			history.back();
		</script>
	</c:if>

	<header class="lnb" id="lnb">
		<c:if test="${not empty manager }">
			<!-- 부서 관리자 일떄 헤더 -->
			<c:if test="${manager.allevel eq 2 }">
				<div id="headerBar">
					<div id="headerBarleft">
						<ul>
							<li>
								<a href="${conPath }/main.do"> 
								<img alt="메인화면으로"	src="${conPath }/img/homeicon.png" width="40" height="35">
								</a>
							</li>
						</ul>
					</div>
					<div id="headerBarRight">
						<ul>
							<li><a href=#>${manager.mname } 님 </a></li>
							<li><a href="${conPath }/ptEmpConList.do">파트타이머 관리 </a></li>
							<li><a href="${conPath }/managerList.do">매니저 관리</a></li>
							<li><a href="${conPath }/empContractView.do">근로계약서 관리</a></li>
							<li><a href="${conPath }/logout.do">로그아웃</a></li>
						</ul>
					</div>
				</div>
			</c:if>
			<c:if test="${manager.allevel eq 1 }">
				<!-- 매니저 관리자 일떄 헤더 -->
				<div id="headerBar">
					<div id="headerBarleft">
						<ul>
							<li><a href="${conPath }/main.do"> <img alt="메인화면으로"
									src="${conPath }/img/homeicon.png" width="40" height="35">
							</a></li>
						</ul>
					</div>
					<div id="headerBarRight">
						<ul>
							<li><a href=#>${manager.mname } 님 </a></li>
							<li><a href="${conPath }/ptEmpConList.do">파트타이머 관리</a></li>
							<li><a href="${conPath }/logout.do">로그아웃</a></li>
						</ul>
					</div>
				</div>
			</c:if>
		</c:if>

		<!-- 파트타이머 일떄 헤더 -->
		<c:if test="${not empty parttimer }">
			<c:if test="${parttimer.ptempconchek eq 0 }">
				<script>
					$(document).ready(function() {
						$("a").removeAttr("href");
					});
				</script>
			</c:if>
				<div id="headerBar">
				<div id="headerBarleft">
					<ul>
						<li><a href="${conPath }/main.do"> <img alt="메인화면으로"
								src="${conPath }/img/homeicon.png" width="40" height="35">
						</a></li>
					</ul>
				</div>
				<div id="headerBarRight">
					<ul>
						<li><a href=#>${parttimer.ptid } 님 </a></li>
						<li><a href="${conPath }/ptMyEventsView.ptdo">나의 행사 보기</a></li>
						<li><a href="${conPath }/ptWriteEmpConView.ptdo">나의 근로계약서</a></li>
						<li><a href="${conPath }/logout.do">로그아웃</a></li>
					</ul>
				</div>
			</div>
		</c:if>
	</header>
</body>
</html>



