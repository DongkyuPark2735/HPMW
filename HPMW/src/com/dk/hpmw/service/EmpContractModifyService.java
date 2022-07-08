package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.employcontract.EmployContractDAO;
import com.dk.hpmw.employcontract.EmployContractDTO;

public class EmpContractModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String eccontent = request.getParameter("eccontent");
		String ectitle = request.getParameter("ectitle");
		String ecno = "2207010001";
		EmployContractDAO ecdao = EmployContractDAO.getInstance();
		EmployContractDTO ecdto = new EmployContractDTO();
		
		ecdto.setEcno(ecno);
		ecdto.setEctitle(ectitle);
		ecdto.setEccontent(eccontent);
		int result = ecdao.modifyEmployContract(ecdto);
		
		if(result == EmployContractDAO.EmployContractInsetSUCCESS) {
			request.setAttribute("empContract", ecdto);
			request.setAttribute("empContractResultMSG", "근로계약서 양식이 수정되었습니다.");
		}else {
			request.setAttribute("empContractResultMSG", "근로계약서 양식 수정에 실패하였습니다.");
			
		}
	}
}
