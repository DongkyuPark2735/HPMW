package com.dk.hpmw.service;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.banktype.BankTypeDAO;
import com.dk.hpmw.banktype.BankTypeDTO;
import com.dk.hpmw.employcontract.EmployContractDAO;
import com.dk.hpmw.employcontract.EmployContractDTO;
import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.events.EventsDTO;
import com.dk.hpmw.eventstype.EventsTypeDAO;
import com.dk.hpmw.eventstype.EventsTypeDTO;
import com.dk.hpmw.parttimer.ParttimerDAO;
import com.dk.hpmw.parttimer.ParttimerDTO;
import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;

public class PtModifyEmpConViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String ptconno = request.getParameter("ptconno");
		ParttimerContractDAO ptcdao = ParttimerContractDAO.getInstance();
		EventsTypeDAO etdao = EventsTypeDAO.getInstance();
		EmployContractDAO empcdao = EmployContractDAO.getInstance();
		BankTypeDAO btdao = BankTypeDAO.getInstance();
		EventsDAO evdao = EventsDAO.getInstance();
		
		ParttimerContractDTO ptcdto = ptcdao.detailParttimerContract(ptconno);
		ArrayList<EventsTypeDTO> etarr = etdao.listEventsType();
		
		EmployContractDTO empdto = empcdao.detailEmployContract();
		ArrayList<EventsDTO> evlist = evdao.listDailyEvents();
		
		Date sysdate = new Date();
		ArrayList<BankTypeDTO> btarr = btdao.listBankType();
		for(BankTypeDTO btdto : btarr) {
			if(btdto.getBtno()==ptcdto.getBtno()) {
				request.setAttribute("bankName", btdto.getBtname());
				request.setAttribute("bankNo", ptcdto.getBtno());
			}
		}

		request.setAttribute("sysdate", sysdate);
		request.setAttribute("evnetsDail", evlist);
		request.setAttribute("employContract", empdto);
		request.setAttribute("eventsType", etarr);
		request.setAttribute("parttimerContract", ptcdto);
	}
}
