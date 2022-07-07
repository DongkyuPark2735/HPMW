package com.dk.hpmw.servicePT;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.parttimer.ParttimerDTO;
import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;
import com.dk.hpmw.service.Service;

public class PtMyEventsViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ParttimerDTO parttimer =  (ParttimerDTO)session.getAttribute("parttimer");
		ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
		ArrayList<ParttimerContractDTO> pcdtoarr = pcdao.detailIDParttimerContract(parttimer.getPtid());
		ParttimerContractDTO pcdto = null;
		if(!pcdtoarr.isEmpty()) {
			pcdto = pcdtoarr.get(0);
			
			EventsDAO evdao = EventsDAO.getInstance();
			
			request.setAttribute("EventsDetail", evdao.detailEvents(pcdto.getEvno()));// 할당된 행사 
			
		}
	}
}
