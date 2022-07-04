package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;

public class PtEmpConSubmitService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String ptconno = request.getParameter("ptconno");
		String tmppthourlywage = request.getParameter("pthourlywage").replace(",", "");
		int pthourlywage = Integer.parseInt(tmppthourlywage);
		int pttotalpay = Integer.parseInt(request.getParameter("pttotalpay"));
		int ptworktime = Integer.parseInt(request.getParameter("ptworktime"));
		
		ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
		
		int parttimerHourlyWageresult = pcdao.insertParttimerHourlyWage(ptworktime, pthourlywage, pttotalpay, ptconno);
		if(parttimerHourlyWageresult == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
			int parttimerSubmitResult = pcdao.intsertParttimerPtstatus(ptconno);
			if(parttimerSubmitResult == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
				request.setAttribute("parttimerSubmitResult", "해당 근로계약서 마감처리 되었습니다.");
			}else {
				request.setAttribute("parttimerSubmitResult", "해당 근로계약서 마감처리 실패하였습니다.");
			}
		}else {
			request.setAttribute("parttimerHourlyWageResult", "해당 근로계약서 급여입력에 실패하였습니다.");
		}
	}
}
