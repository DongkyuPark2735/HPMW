package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.noticeboard.NoticeBoardDAO;
import com.dk.hpmw.noticeboard.NoticeBoardDTO;

public class NoticeBoardDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nbno = Integer.parseInt(request.getParameter("nbno"));
		int mno = Integer.parseInt(request.getParameter("mno"));
		NoticeBoardDAO nbdao = NoticeBoardDAO.getInstance();
		
		HttpSession session = request.getSession();
		session.getAttribute("manager");
		int result = nbdao.deleteNoticeBoard(mno, nbno);
		if(result==NoticeBoardDAO.NoticeBoardDeleteSUCCESS) {
			request.setAttribute("NoticeBoardDeleteResult", "해당 글이 삭제되었습니다.");
			request.setAttribute("pageNum", request.getAttribute("pageNum"));
		}else {
			request.setAttribute("NoticeBoardDeleteResult", "게시글 삭제 실패");
		}
	}
}
