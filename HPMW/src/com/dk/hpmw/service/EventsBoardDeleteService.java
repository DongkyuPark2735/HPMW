package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.noticeboard.NoticeBoardDAO;

public class EventsBoardDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String evno = request.getParameter("evno");
		int mno = Integer.parseInt(request.getParameter("mno"));
		EventsDAO evdao = EventsDAO.getInstance();
		
		int result = evdao.deleteEvents(evno, mno);
		if(result==EventsDAO.EventsDeleteSUCCESS) {
			request.setAttribute("EventsDeleteResult", "해당 행사가 삭제되었습니다.");
			request.setAttribute("pageNum", request.getAttribute("pageNum"));
		}else {
			request.setAttribute("EventsDeleteResult", "행사 삭제 실패");
		}
		
	}
}
