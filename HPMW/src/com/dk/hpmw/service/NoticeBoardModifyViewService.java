package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.noticeboard.NoticeBoardDAO;

public class NoticeBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		NoticeBoardDAO nbdao = NoticeBoardDAO.getInstance();
		int nbno = Integer.parseInt(request.getParameter("nbno"));
		request.setAttribute("NoticeBoardDetail", nbdao.detailNoticeBoard(nbno)); 
		request.setAttribute("pageNum", request.getParameter("pageNum"));	
	
	}
}
