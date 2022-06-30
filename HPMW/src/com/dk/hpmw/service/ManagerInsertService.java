package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.manager.ManagerDAO;

public class ManagerInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAO mdao = ManagerDAO.getInstance();
		int mno = Integer.parseInt(request.getParameter("mno"));
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");	
		int result = mdao.inSertManager(mno, mpw, mname);
		if(result == ManagerDAO.ManagerInsetSUCCESS) {
			request.setAttribute("managerInsertResultMSG", mname+" 사원 입력되었습니다.");
		}else {
			request.setAttribute("managerInsertResultMSG", mname+" 사원  입력에 실패하였습니다.");
		}
		
	}

}
