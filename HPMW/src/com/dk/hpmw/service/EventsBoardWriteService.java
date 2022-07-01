package com.dk.hpmw.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.events.EventsDTO;
import com.dk.hpmw.manager.ManagerDTO;
import com.dk.hpmw.noticeboard.NoticeBoardDAO;

public class EventsBoardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ManagerDTO manager = (ManagerDTO)session.getAttribute("manager");
		request.setAttribute("pageNum", request.getParameter("pageNum")); 
		
		System.out.println("페이지 번호" +request.getParameter("pageNum"));
		if(manager!=null) {
			EventsDTO evdto = new EventsDTO();
			int mno = manager.getMno();
			String evtitle = request.getParameter("evtitle");
			String evdetail = request.getParameter("evdetail");;
			String tempevstartdate = request.getParameter("evstartdate");
			Date evstartdate = Date.valueOf(tempevstartdate); 
			String tempetno = request.getParameter("etno");
			int etno = Integer.parseInt(tempetno.toString().substring(0, 2));
			
			evdto.setEvdetail(evdetail);
			evdto.setEtno(etno);
			evdto.setEvstartdate(evstartdate);
			evdto.setEvtitle(evtitle);
			evdto.setMno(mno);
			
			EventsDAO evdao = EventsDAO.getInstance();
			int result = evdao.insertEvents(evdto);
			
			if(result == EventsDAO.EventsInsetSUCCESS) { 
				request.setAttribute("EventsInsertResult", "행사 입력 되었습니다.");
			}else {
				request.setAttribute("EventsInsertResult", "행사 입력 실패하였습니다.");
			}
		}
	}
}
