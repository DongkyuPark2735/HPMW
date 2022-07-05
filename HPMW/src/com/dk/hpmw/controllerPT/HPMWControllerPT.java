package com.dk.hpmw.controllerPT;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.service.Service;
import com.dk.hpmw.servicePT.ModifyParttimerConService;
import com.dk.hpmw.servicePT.ModifyParttimerInfoService;
import com.dk.hpmw.servicePT.PtLoginService;
import com.dk.hpmw.servicePT.PtMyEventsViewService;
import com.dk.hpmw.servicePT.PtWriteEmpConService;
import com.dk.hpmw.servicePT.PtWriteEmpConViewService;

@WebServlet("*.ptdo")
public class HPMWControllerPT extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HPMWControllerPT() {
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

		if (com.equals("/ptLoginView.ptdo")) { // 파트타이머 로그인 페이지 이동
			viewPage = "partTimer/ptLoginView.jsp";
		}else if (com.equals("/ptLogin.ptdo")) { // 파트타이머 로그인 후 메인화면으로
			service = new PtLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		} else if (com.equals("/ptWriteEmpConView.ptdo")) { //근로계약서 작성화면으로
			service = new PtWriteEmpConViewService();
			service.execute(request, response);
			viewPage = "partTimer/ptWriteEmpConView.jsp";
		} else if (com.equals("/ptWriteEmpCon.ptdo")) { //파트타이미 근로계약서 입력
			service = new PtWriteEmpConService();
			service.execute(request, response);
			viewPage = "ptWriteEmpConView.ptdo";
		} else if (com.equals("/modifyParttimerInfo.ptdo")) { //파트타이미 근로계약서 수정을 위한 값 삭제 
			service = new ModifyParttimerInfoService(); // 수정하러 갈떄
			service.execute(request, response);
			viewPage = "ptWriteEmpConView.ptdo";
		} else if (com.equals("/modifyParttimerCon.ptdo")) { //파트타이미 근로계약서 수정
			service = new ModifyParttimerConService(); //수정하고 난 후
			service.execute(request, response);
			viewPage = "ptWriteEmpConView.ptdo";
		} else if (com.equals("/ptMyEventsView.ptdo")) { //파트타이미 나의 행사 보기
			service = new PtMyEventsViewService();
			service.execute(request, response);
			viewPage = "eventsBoard/eventsBoardContent.jsp";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
	
}