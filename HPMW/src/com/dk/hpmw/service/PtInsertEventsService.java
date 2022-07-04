package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.parttimercontract.ParttimerContractDAO;

public class PtInsertEventsService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String evno = request.getParameter("evno");
		String ptconno = request.getParameter("ptconno"); 
		ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
		
		int result = pcdao.insertEventsParttimer(evno, ptconno);
		if(result == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
			request.setAttribute("partTimerInsertEventsResult", "행사가 할당되었습니다.");
		}else if(result == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
			request.setAttribute("partTimerInsertEventsResult", "행사 할당 실패하였습니다.");
		}
	}
}
