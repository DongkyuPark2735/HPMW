package com.dk.hpmw.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.eventstype.EventsTypeDAO;
import com.dk.hpmw.eventstype.EventsTypeDTO;
import com.dk.hpmw.noticeboard.NoticeBoardDAO;

public class EventsBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EventsDAO evdao = EventsDAO.getInstance();
		String evno = request.getParameter("evno");
		int mno = Integer.parseInt(request.getParameter("mno"));
		request.setAttribute("EventsDetail", evdao.detailEvents(evno)); 
		request.setAttribute("pageNum", request.getParameter("pageNum"));	
		
		EventsTypeDAO evtydao = EventsTypeDAO.getInstance();
		ArrayList<EventsTypeDTO> evtypelist = new ArrayList<EventsTypeDTO>();
		request.setAttribute("evtypelist", evtypelist = evtydao.listEventsType());
		
	}
}
