package com.dk.hpmw.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.events.EventsDTO;
import com.dk.hpmw.manager.ManagerDTO;
import com.dk.hpmw.noticeboard.NoticeBoardDAO;

public class EventsBoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ManagerDTO manager = (ManagerDTO)session.getAttribute("manager");
		
		if(manager!=null) {
			int mno = manager.getMno();
			String evno = request.getParameter("evno");
			String evtitle = request.getParameter("evtitle");
			String evdetail = request.getParameter("evdetail");
			String tempevstartdate = request.getParameter("evstartdate");
			Date evstartdate = Date.valueOf(tempevstartdate); 
			String tempetno = request.getParameter("etno");
			int etno = Integer.parseInt(tempetno.toString().substring(0, 2));
			
			EventsDAO evdao = EventsDAO.getInstance();
			int result = evdao.modifyEvents(new EventsDTO(evno, evtitle, evdetail, evstartdate, etno, mno));
			
			if(result == NoticeBoardDAO.NoticeBoardModifySUCCESS) { 
				request.setAttribute("evtitle", evtitle);
				request.setAttribute("EventsModifyResult", "행사가 수정되었습니다.");
			}else {
				request.setAttribute("evtitle", evtitle);
				request.setAttribute("EventsModifyResult", "행사 수정 실패하였습니다.");
			}
		}
	}
}
