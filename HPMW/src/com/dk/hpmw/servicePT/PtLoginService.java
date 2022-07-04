package com.dk.hpmw.servicePT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.parttimer.ParttimerDAO;
import com.dk.hpmw.parttimer.ParttimerDTO;
import com.dk.hpmw.service.Service;

public class PtLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String ptid = request.getParameter("ptid");
		String ptpw = request.getParameter("ptpw");

		ParttimerDAO ptdao = ParttimerDAO.getInstance();
		ParttimerDTO parttimer = null;
		
		parttimer = ptdao.loginParttimer(ptid, ptpw);
		
		HttpSession session = request.getSession();
		if(parttimer !=null) {
			session.setAttribute("parttimer", parttimer);
		}else {
			request.setAttribute("parttimerLoginMSG", "로그인 실패하였습니다.");
		}
	}
}
