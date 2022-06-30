package com.dk.hpmw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.service.AllBoardListService;
import com.dk.hpmw.service.EmpContractListService;
import com.dk.hpmw.service.LogoutService;
import com.dk.hpmw.service.ManagerDeleteService;
import com.dk.hpmw.service.ManagerIdConfirmDeService;
import com.dk.hpmw.service.ManagerInsertService;
import com.dk.hpmw.service.ManagerListService;
import com.dk.hpmw.service.ManagerLoginService;
import com.dk.hpmw.service.NoticeBoardContentDetailService;
import com.dk.hpmw.service.NoticeBoardDeleteService;
import com.dk.hpmw.service.NoticeBoardListService;
import com.dk.hpmw.service.NoticeBoardWriteService;
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

		if (com.equals("/parttimerLoginView.do")) { // 파트타이머 로그인 페이지 이동
			// viewPage = "main/loginParttimer.jsp";
		} else if (com.equals("/managerLoginView.do")) { // 관리자 로그인 페이지 이동
			viewPage = "main/managerLoginView.jsp";
		} else if (com.equals("/managerLogin.do")) { // 관리자 로그인
			service = new ManagerLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		} else if (com.equals("/main.do")) { // 메인화면으로
			service = new AllBoardListService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (com.equals("/empContractView.do")) { // 근로계약서 양식 리스트 페이지 이동
			service = new EmpContractListService();
			service.execute(request, response);
			viewPage = "manager/empContractView.jsp";
			///// 매니저 관리 부분 //////
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
				viewPage = "main/parttimerLoginView.jsp";
			}
			//////// 공지사항 게시판 관련 ////////
		} else if (com.equals("/noticeBoardList.do")) { // 공지사항 게시판 리스트
			service = new NoticeBoardListService();
			service.execute(request, response);
			viewPage = "noticeBoard/noticeBoardList.jsp";
		} else if (com.equals("/noticeBoardWriteView.do")) { // 공지사항 게시판 글 작성 폼
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
		}
		

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
