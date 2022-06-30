package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.manager.ManagerDAO;
import com.dk.hpmw.manager.ManagerDTO;
import com.dk.hpmw.noticeboard.NoticeBoardDAO;
import com.dk.hpmw.noticeboard.NoticeBoardDTO;

public class NoticeBoardContentDetailService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		NoticeBoardDAO nbdao = NoticeBoardDAO.getInstance();
		int nbno = Integer.parseInt(request.getParameter("nbno"));
			
		NoticeBoardDTO nbdto = nbdao.detailNoticeBoard(nbno);
		
		request.setAttribute("noticBoardDetail", nbdto);
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		
	}
}
