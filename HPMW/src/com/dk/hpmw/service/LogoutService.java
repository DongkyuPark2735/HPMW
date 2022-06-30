package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("manager")!=null) {
			request.setAttribute("LoginPage", "managerLoginPage");
		}else{
			request.setAttribute("LoginPage", "parttimerLoginPage");
		}
		session.invalidate();
		request.setAttribute("logoutMSG", "로그아웃 되었습니다.");
	}
}
