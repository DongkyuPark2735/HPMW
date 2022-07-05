package com.dk.hpmw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.service.AllBoardListService;
import com.dk.hpmw.service.EmpContractModifyService;
import com.dk.hpmw.service.EmpContractViewService;
import com.dk.hpmw.service.EventsBoardContentService;
import com.dk.hpmw.service.EventsBoardDeleteService;
import com.dk.hpmw.service.EventsBoardListService;
import com.dk.hpmw.service.EventsBoardModifyService;
import com.dk.hpmw.service.EventsBoardModifyViewService;
import com.dk.hpmw.service.EventsBoardWriteService;
import com.dk.hpmw.service.EventsBoardWriteViewService;
import com.dk.hpmw.service.LogoutService;
import com.dk.hpmw.service.ManagerDeleteService;
import com.dk.hpmw.service.ManagerIdConfirmDeService;
import com.dk.hpmw.service.ManagerInsertService;
import com.dk.hpmw.service.ManagerListService;
import com.dk.hpmw.service.ManagerLoginService;
import com.dk.hpmw.service.NoticeBoardContentDetailService;
import com.dk.hpmw.service.NoticeBoardDeleteService;
import com.dk.hpmw.service.NoticeBoardListService;
import com.dk.hpmw.service.NoticeBoardModifyService;
import com.dk.hpmw.service.NoticeBoardModifyViewService;
import com.dk.hpmw.service.NoticeBoardWriteService;
import com.dk.hpmw.service.ParttimerContractModifyService;
import com.dk.hpmw.service.PastPtEmpConListService;
import com.dk.hpmw.service.PtEmpConListService;
import com.dk.hpmw.service.PtEmpConSubmitService;
import com.dk.hpmw.service.PtInsertEventsService;
import com.dk.hpmw.service.PtModifyEmpConViewService;
import com.dk.hpmw.service.Service;

@WebServlet("*.do")
public class HPMWController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HPMWController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;

		if (com.equals("/managerLoginView.do")) { // 관리자 로그인 페이지 이동
			viewPage = "main/managerLoginView.jsp";
		} else if (com.equals("/managerLogin.do")) { // 관리자 로그인
			service = new ManagerLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		} else if (com.equals("/main.do")) { // 메인화면으로
			service = new AllBoardListService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			///// 부서 관리자 부분 //////
			///// 근로계약서 양식//////
		} else if (com.equals("/empContractView.do")) { // 근로계약서 양식 리스트 페이지 이동
			service = new EmpContractViewService();
			service.execute(request, response);
			viewPage = "employContract/empContractView.jsp";
		} else if (com.equals("/empContractModify.do")) { // 근로계약서 양식 수정
			service = new EmpContractModifyService();
			service.execute(request, response);
			viewPage = "empContractView.do";
			///// 매니저 관리//////
		} else if (com.equals("/managerList.do")) { // 매니저 관리 페이지 이동
			service = new ManagerListService();
			service.execute(request, response);
			viewPage = "manager/managerList.jsp";
		} else if (com.equals("/managerInsertView.do")) { // 매니저 입력 페이지 이동
			viewPage = "manager/managerInsertView.jsp";
		} else if (com.equals("/managerIdConfirm.do")) { // 매니저 중복확인
			service = new ManagerIdConfirmDeService();
			service.execute(request, response);
			viewPage = "confirm/managerIdConfirm.jsp";
		} else if (com.equals("/managerInsert.do")) { // 매니저 입력
			service = new ManagerInsertService();
			service.execute(request, response);
			viewPage = "managerList.do";
		} else if (com.equals("/managerDelete.do")) { // 매니저 삭제
			service = new ManagerDeleteService();
			service.execute(request, response);
			viewPage = "managerList.do";
		} else if (com.equals("/logout.do")) { // 로그아웃
			service = new LogoutService();
			service.execute(request, response);
			if (request.getAttribute("LoginPage") == "managerLoginPage") {
				viewPage = "main/managerLoginView.jsp";
			} else {
				viewPage = "partTimer/ptLoginView.jsp";
			}
			//////// 공지사항 게시판 관련 ////////
		} else if (com.equals("/noticeBoardList.do")) { // 공지사항 게시판 리스트
			service = new NoticeBoardListService();
			service.execute(request, response);
			viewPage = "noticeBoard/noticeBoardList.jsp";
		} else if (com.equals("/noticeBoardWriteView.do")) { // 공지사항 게시판 글 작성 뷰
			viewPage = "noticeBoard/noticeBoardWriteView.jsp";
		} else if (com.equals("/noticeBoardWrite.do")) { // 공지사항 게시판 글 등록
			service = new NoticeBoardWriteService();
			service.execute(request, response);
			viewPage = "noticeBoardList.do";
		} else if (com.equals("/noticeBoardContentDetail.do")) { // 공지사항 게시판 글 상세보기
			service = new NoticeBoardContentDetailService();
			service.execute(request, response);
			viewPage = "noticeBoard/noticeBoardContentDetail.jsp";
		} else if (com.equals("/noticeBoardDelete.do")) { // 공지사항 게시판 글 삭제
			service = new NoticeBoardDeleteService();
			service.execute(request, response);
			viewPage = "noticeBoardList.do";
		} else if (com.equals("/noticeBoardModifyView.do")) { // 공지사항 게시판 글 수정 뷰
			service = new NoticeBoardModifyViewService();
			service.execute(request, response);
			viewPage = "noticeBoard/noticeBoardModifyView.jsp";
		} else if (com.equals("/noticeBoardModify.do")) { // 공지사항 게시판 글 수정
			service = new NoticeBoardModifyService();
			service.execute(request, response);
			viewPage = "noticeBoardList.do";
			//////// 행사 게시판 관련 ////////
		} else if (com.equals("/eventsBoardList.do")) { // 행사 게시판 리스트
			service = new EventsBoardListService();
			service.execute(request, response);
			viewPage = "eventsBoard/eventsBoardList.jsp";
		} else if (com.equals("/eventsBoardContent.do")) { // 행사 디테일
			service = new EventsBoardContentService();
			service.execute(request, response);
			viewPage = "eventsBoard/eventsBoardContent.jsp";
		} else if (com.equals("/eventsBoardDelete.do")) { // 행사 삭제
			service = new EventsBoardDeleteService();
			service.execute(request, response);
			viewPage = "eventsBoardList.do";
		} else if (com.equals("/eventsBoardModifyView.do")) { // 행사 수정 뷰
			service = new EventsBoardModifyViewService();
			service.execute(request, response);
			viewPage = "eventsBoard/eventsBoardModifyView.jsp";
		} else if (com.equals("/eventsBoardModify.do")) { // 행사 수정
			service = new EventsBoardModifyService();
			service.execute(request, response);
			viewPage = "eventsBoardList.do";
		} else if (com.equals("/eventsBoardWriteView.do")) { // 행사 입력 뷰
			service = new EventsBoardWriteViewService();
			service.execute(request, response);
			viewPage = "eventsBoard/eventsBoardWriteView.jsp";
		} else if (com.equals("/eventsBoardWrite.do")) { // 행사 입력
			service = new EventsBoardWriteService(); ///////// 페이지 번호 에러
			service.execute(request, response);
			viewPage = "eventsBoardList.do";
			/////// 파트타이머 관리 관련 ///////
		} else if (com.equals("/ptEmpConList.do")) { // 파트타이머 당일 근로계약서 리스트
			service = new PtEmpConListService();
			service.execute(request, response);
			viewPage = "parttimerContract/ptEmpConList.jsp";
		} else if (com.equals("/pastptEmpConList.do")) { // 파트타이머 지난 근로계약서 리스트
			service = new PastPtEmpConListService();
			service.execute(request, response);
			viewPage = "parttimerContract/pastptEmpConList.jsp";
		} else if (com.equals("/ptModifyEmpConView.do")) { // 파트타이머 근로계약서 상세보기
			service = new PtModifyEmpConViewService();
			service.execute(request, response);
			viewPage = "parttimerContract/ptModifyEmpConView.jsp";
		} else if (com.equals("/PtInsertEvents.do")) { // 파트타이머 행사 입력
			service = new PtInsertEventsService();
			service.execute(request, response);
			viewPage = "ptEmpConList.do";
		} else if (com.equals("/ptEmpConSubmit.do")) { // 파트타이머 마감처리
			service = new PtEmpConSubmitService();
			service.execute(request, response);
			viewPage = "ptEmpConList.do";
		} else if (com.equals("/parttimerContractModify.do")) { // 근로계약서 수정 위한 마감 수정
			service = new ParttimerContractModifyService();
			service.execute(request, response);
			viewPage = "ptModifyEmpConView.do";
		}
			////건의사항 게시판 관련 ////
	

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
