package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.manager.ManagerDAO;

public class ManagerDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAO mdao = ManagerDAO.getInstance();
		int mno = Integer.parseInt(request.getParameter("mno"));
		int result = mdao.deleteManager(mno);
		
		if(result==ManagerDAO.ManagerDeleteSUCCESS) {
			request.setAttribute("ManagerDeleteMSG", "해당 매니저가 삭제되었습니다.");
		}else {
			request.setAttribute("ManagerDeleteMSG", "해당 매니저의 게시글이 있어 삭제 실패하였습니다.");
		}
	}
}
