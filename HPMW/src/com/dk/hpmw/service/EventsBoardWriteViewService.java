package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.eventstype.EventsTypeDAO;

public class EventsBoardWriteViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EventsTypeDAO evtdao = EventsTypeDAO.getInstance();
		request.setAttribute("pageNum", request.getAttribute("pageNum"));
		request.setAttribute("eventsTypeList", evtdao.listEventsType()); 
		
	}
}
