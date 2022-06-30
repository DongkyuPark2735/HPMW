<%@page import="com.dk.hpmw.parttimercontract.ParttimerContractDAO"%>
<%@page import="com.dk.hpmw.parttimercontract.ParttimerContractDTO"%>
<%@page import="com.dk.hpmw.suggestionboard.SuggestionBoardDAO"%>
<%@page import="com.dk.hpmw.suggestionboard.SuggestionBoardDTO"%>
<%@page import="com.dk.hpmw.parttimer.ParttimerDTO"%>
<%@page import="com.dk.hpmw.parttimer.ParttimerDAO"%>
<%@page import="com.dk.hpmw.noticeboard.NoticeBoardDAO"%>
<%@page import="com.dk.hpmw.noticeboard.NoticeBoardDTO"%>
<%@page import="com.dk.hpmw.manager.ManagerDAO"%>
<%@page import="com.dk.hpmw.manager.ManagerDTO"%>
<%@page import="java.sql.Date"%>
<%@page import="com.dk.hpmw.events.EventsDTO"%>
<%@page import="com.dk.hpmw.events.EventsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dk.hpmw.employcontract.EmployContractDTO"%>
<%@page import="com.dk.hpmw.employcontract.EmployContractDAO"%>
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
</head>
<body>
	<%-- <%
		EmployContractDAO ecdao = EmployContractDAO.getInstance();
		EmployContractDTO ecdto = new EmployContractDTO();	
/* 		ecdto.setEctitle("테스트 근로계약서 제목");
		ecdto.setEccontent("테스트 근로계약서 내용");
		int result = ecdao.insertEmployContract(ecdto);
		out.println("<b>"+result+""+ecdto+"</b>");
 */		
		/* ArrayList<EmployContractDTO> ecar = new ArrayList<EmployContractDTO>();
 		ecar.add(e) ecdao.listEmployContract()
		for(EmployContractDTO ec : ){
			out.println("<b>"+ec.getEcno()+"</b>");
			out.println("<b>"+ec.getEctitle()+"</b>");
			out.println("<b>"+ec.getEccontent()+"</b>");
		} */
		
		//out.println("<b>"+ecdao.detailEmployContract("2206270001")+"</b>");
		
		out.println("<b>"+ecdao.deleteEmployContract("2206280003")+"</b>");
	%> --%>
	<%
		//EventsDAO evdao = EventsDAO.getInstance();
		
		/* ArrayList<EventsDTO> evdtoArr = new ArrayList<EventsDTO>();
		
		evdtoArr = evdao.listEvents(1, 10);
		
		for(EventsDTO evarr : evdtoArr){
			out.println("<b>"+evarr.getEtno()+"</b>");
			out.println("<b>"+evarr.getEvdetail()+"</b>");
			out.println("<b>"+evarr.getEvno()+"</b>");
			out.println("<b>"+evarr.getEvtitle()+"</b>");
			out.println("<b>"+evarr.getEvstartdate()+"</b>");
			out.println("<b>"+evarr.getMno()+"</b>");
		} */
		
		
		/* out.println("<b>"+evdao.detailEvents("220627002")+"</b>");
		
		EventsDTO edto = new EventsDTO();
		Date date = new Date(2022,10,10);
		edto.setEvdetail("테스트 입력1");
		edto.setEvstartdate(date);
		edto.setEvtitle("테스트 내용1");
		edto.setEtno(10);
		edto.setMno(1212120);
		edto.setEvno("220628006");
		 */
		//int i = evdao.deleteEvents("220628006",1212120);
		//out.println("<b>"+i+"</b>");
		
/* 		ecdto.setEctitle("테스트 근로계약서 제목");
		ecdto.setEccontent("테스트 근로계약서 내용");
		int result = ecdao.insertEmployContract(ecdto);
		out.println("<b>"+result+""+ecdto+"</b>");
 */		
		/* ArrayList<EmployContractDTO> ecar = new ArrayList<EmployContractDTO>();
 		ecar.add(e) ecdao.listEmployContract()
		for(EmployContractDTO ec : ){
			out.println("<b>"+ec.getEcno()+"</b>");
			out.println("<b>"+ec.getEctitle()+"</b>");
			out.println("<b>"+ec.getEccontent()+"</b>");
		} */
		
		//out.println("<b>"+ecdao.detailEmployContract("2206270001")+"</b>");
		
		
		/* ManagerDAO mdao = ManagerDAO.getInstance();
		
// 		ManagerDTO mdto = new ManagerDTO();
// 		mdto.setMno(mno);
// 		mdto.setMpw(mpw);
// 		mdto.setMname(mname);
// 		mdto.setAllevel(allevel)
		
		
		ManagerDTO mdto = mdao.loginManager(1811009, "1811009");
		
		out.println("<b>"+mdto+"</b>");
		
// 	int i = mdao.inSertManager(1222222, "1222222", "유지원");
		
// 	out.println("<b>"+i+"</b>");
		
		int i = mdao.deleteManager(1811009);

  	out.println("<b>"+i+"</b>");
		 */
		 
// 		NoticeBoardDAO nbdao = NoticeBoardDAO.getInstance();
// 		NoticeBoardDTO nbdto = new NoticeBoardDTO();
// 		nbdto.setNbcontent("수정된 본문내용");
// 		nbdto.setNbtitle("수정된 본문제목");
// 		nbdto.setMno(1811009);
// 		nbdto.setNbno(4);
// //		nbdto = nbdao.detailNoticeBoard(1);
// 		int i = nbdao.modifyNoticeBoard(nbdto);
		
// 		int i = nbdao.deleteNoticeBoard(1811009, 4);
//   	out.println("<b>"+i+"</b>");
		 
//   		ParttimerDAO pdao = ParttimerDAO.getInstance();
// 			ParttimerDTO pdto = new ParttimerDTO();
// 			pdto = pdao.loginParttimer("2804", "2804");
//   		out.println("<b>"+pdto+"</b>");
	
// 			ParttimerDTO pdto2 = new ParttimerDTO();
// 			pdto2 = pdao.SearchPtidPtpw("5555", "한만운");
//   		out.println("<b>"+pdto2+"</b>");
		ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
 		out.println("<b>"+pcdao.detailParttimerContract("00000019")+"</b>");
		
		ParttimerContractDTO pcdto = new ParttimerContractDTO();
		/* pcdto.setPtid("8888");
		pcdto.setPtname("김유림");
		pcdto.setPttel("010-8888-8888");
		pcdto.setPtemail("gon@naver.com");
		pcdto.setPtaddress("수정된 주소 서울시 강남구");
		pcdto.setBtno(200);
		pcdto.setPtaccountno("77788899944");
		pcdto.setPtconno("00000027");
		 */
//  		out.println("<b>"+pcdao.insertParttimerContract(pcdto)+"</b>");
//  out.println("<b>"+pcdao.modifyParttimerContract(pcdto)+"</b>");
		
		
// 		out.println("<b>"+pcdao.insertEventsParttimer("220627001", "00000027")+"</b>");
//  		out.println("<b>"+pcdao.insertParttimerHourlyWage(11, 8000, 88000, "00000027")+"</b>");
 		out.println("<b>"+pcdao.intsertParttimerPtstatus("00000027")+"</b>");
		
		

	%>
</body>
</html>




















