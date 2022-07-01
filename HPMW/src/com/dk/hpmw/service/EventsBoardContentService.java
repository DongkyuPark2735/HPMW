package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.events.EventsDTO;
import com.dk.hpmw.noticeboard.NoticeBoardDAO;
import com.dk.hpmw.noticeboard.NoticeBoardDTO;

public class EventsBoardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EventsDAO evdao = EventsDAO.getInstance();
		String evno = request.getParameter("evno");
			
		EventsDTO evdto = evdao.detailEvents(evno);
		
		request.setAttribute("EventsDetail", evdto);
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		
		
		
	}
}
