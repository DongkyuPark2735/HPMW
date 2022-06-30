package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.manager.ManagerDAO;

public class ManagerIdConfirmDeService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAO mdao = ManagerDAO.getInstance();
		int mno = Integer.parseInt(request.getParameter("mno"));
		int result = mdao.managerIdConfirm(mno);
		if(result == ManagerDAO.ManagerEXISTENT) {
			request.setAttribute("managerIdConfirmResultMSG", "이미 존재하는 사번입니다.");
		}else{
			request.setAttribute("managerIdConfirmResultMSG", "사용 가능한 사번입니다.");
		}
	}
}
