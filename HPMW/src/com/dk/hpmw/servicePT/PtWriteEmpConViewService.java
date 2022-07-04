package com.dk.hpmw.servicePT;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.banktype.BankTypeDAO;
import com.dk.hpmw.banktype.BankTypeDTO;
import com.dk.hpmw.employcontract.EmployContractDAO;
import com.dk.hpmw.employcontract.EmployContractDTO;
import com.dk.hpmw.parttimer.ParttimerDAO;
import com.dk.hpmw.parttimer.ParttimerDTO;
import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;
import com.dk.hpmw.service.Service;

public class PtWriteEmpConViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployContractDAO ecdao = EmployContractDAO.getInstance();
		EmployContractDTO ecdto = null;
		ecdto = ecdao.detailEmployContract(); 
		request.setAttribute("EmployContractForm", ecdto); // 당일 근로계약서만 가져오기 
		
		System.out.println(request.getAttribute("ParttimerContractInsetResult"));
		HttpSession session = request.getSession();
		
		ParttimerDTO ptdto = (ParttimerDTO)session.getAttribute("parttimer");
		ParttimerDAO ptdao = ParttimerDAO.getInstance();
		session.setAttribute("parttimer", ptdao.loginParttimer(ptdto.getPtid(), ptdto.getPtpw()));// 파트타이머 정보 세션에 재할당
		
		BankTypeDAO btdao = BankTypeDAO.getInstance();
		ArrayList<BankTypeDTO> btdtoarr = btdao.listBankType();
		request.setAttribute("BankType", btdtoarr); // 은행정보 
		
		
		request.setAttribute("ParttimerContractInsetResult", request.getAttribute("ParttimerContractInsetResult"));
		// 정보 입력 결과 재할당 
		
		ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
		
		if(session.getAttribute("ptconno") != null) {
			String ptconno = (String)session.getAttribute("ptconno");
			request.setAttribute("parttimerContractdetail", pcdao.detailParttimerContract(ptconno));
		}else {
			ArrayList<ParttimerContractDTO> pcdtoarr = pcdao.detailIDParttimerContract(ptdto.getPtid());
			ParttimerContractDTO pcdto = null;
			if(!pcdtoarr.isEmpty()) {
				pcdto = pcdtoarr.get(0);
				System.out.println(pcdto);
				request.setAttribute("parttimerContractdetail", pcdto);
			}
		}
	}
}
