package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.parttimercontract.ParttimerContractDAO;

public class ParttimerContractModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String ptconno = request.getParameter("ptconno");
		ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
		int result = pcdao.modifyParttimerPtstatus(ptconno);
		if(result == ParttimerContractDAO.ParttimerContractModifySUCCESS) {
			request.setAttribute("pcModifyResult", "해당 근로계약서의 마감처리가 취소되었습니다. 급여 정보를 다시 입력해주세요");
		}
	}
}
