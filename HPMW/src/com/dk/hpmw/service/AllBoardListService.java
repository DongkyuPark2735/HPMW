package com.dk.hpmw.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.events.EventsDTO;
import com.dk.hpmw.noticeboard.NoticeBoardDAO;
import com.dk.hpmw.noticeboard.NoticeBoardDTO;
import com.dk.hpmw.suggestionboard.SuggestionBoardDAO;
import com.dk.hpmw.suggestionboard.SuggestionBoardDTO;

public class AllBoardListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//공지사항
		NoticeBoardDAO nbdao = NoticeBoardDAO.getInstance();
		ArrayList<NoticeBoardDTO> nbs = nbdao.listNoticeBoard(1, 10);
		//행사 
		EventsDAO edao = EventsDAO.getInstance();
		ArrayList<EventsDTO> events = edao.listEvents(1, 10);
		//건의사항
		SuggestionBoardDAO sbdao = SuggestionBoardDAO.getInstance();
		ArrayList<SuggestionBoardDTO> sbdtos = sbdao.listSuggestionBoard(1, 10);
		
		request.setAttribute("noticeBoardList", nbs);
		request.setAttribute("eventsList", events);
		request.setAttribute("suggestionBoardList", sbdtos);
	}

}
