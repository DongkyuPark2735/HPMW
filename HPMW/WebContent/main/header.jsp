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
<style>
	* {
		margin: 0 auto;
	}
	
	.headerBarRight {
		height: 100px;
		background-image: #DCD7C9;
	}
	 
	.headerBarRight ul li {
		list-style: none;
		float: right;
		width: 100px;
	}
	a {
		text-decoration: none;
	}
	
</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<article>
		<header>
			<c:if test="${not empty manager }">
				<!-- 부서 관리자 일떄 헤더 -->
				<c:if test="${manager.allevel eq 2 }">
					<div class="headerBarRight">
						<ul>
							<li>
								<a href="${conPath }/main.do"> 
									<img alt="메인화면으로" src="${conPath }/img/homeicon.png" width="50" height="50">
								</a>
							</li>
						</ul>
						<ul>
							<li><a href=#>${departmenthead.mname } 님 </a></li>
							<li><a href=#>파트타이머 관리</a></li>
							<li><a href="${conPath }/managerList.do">매니저 관리</a></li>
							<li><a href="${conPath }/empContractView.do">근로계약서 서식 관리</a></li>
							<li><a href="${conPath }/logout.do">로그아웃</a></li>
						</ul>
					</div>
				</c:if>

				<c:if test="${manager.allevel eq 1 }">
				<!-- 매니저 관리자 일떄 헤더 -->
					<div class="headerBarRight">
						<ul>
							<li>
								<a href="${conPath }/main.do"> 
									<img alt="메인화면으로" src="${conPath }/img/homeicon.png" width="50" height="50">
								</a>
							</li>
						</ul>
						<ul>
							<li><a href=#>${manager.mname } 님 </a></li>
							<li><a href=#>파트타이머 관리 </a></li>
							<li><a href="${conPath }/logout.do">로그아웃</a></li>
						</ul>
					</div>
				</c:if>
			</c:if>
		
			<!-- 파트타이머 일떄 헤더 -->
			<c:if test="${not empty parttimer }">
				<div class="headerBarRight">
					<ul>
						<li>
							<a href="${conPath }/main.do"> 
								<img alt="메인화면으로" src="${conPath }/img/homeicon.png" width="50" height="50">
							</a>
						</li>
					</ul>
					<ul>
<%-- 						<li><a href=#>${parttimer.ptname } 님 </a></li> --%>
						<li><a href=#>내 정보</a></li>
						<li><a href="${conPath }/logout.do">로그아웃</a></li>
					</ul>
				</div>
			</c:if>

		</header>
	</article>
</body>
</html>


