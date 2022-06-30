package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.dk.hpmw.manager.ManagerDAO;
import com.dk.hpmw.manager.ManagerDTO;

public class ManagerLoginService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String mno = request.getParameter("mno");
		String mpw = request.getParameter("mpw");
		
		ManagerDAO mdao = ManagerDAO.getInstance();
		ManagerDTO mdto = null;
		
		mdto = mdao.loginManager(Integer.parseInt(mno), mpw);
		if(mdto != null ) {
			HttpSession session = request.getSession();
			session.setAttribute("manager", mdto);
		}else{
			request.setAttribute("managerLoginResultMSG", "아이디와 비밀번호가 맞지않습니다.");
		}
	}
}
