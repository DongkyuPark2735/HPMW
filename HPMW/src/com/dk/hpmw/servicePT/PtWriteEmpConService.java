package com.dk.hpmw.servicePT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.parttimer.ParttimerDAO;
import com.dk.hpmw.parttimer.ParttimerDTO;
import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;
import com.dk.hpmw.service.Service;

public class PtWriteEmpConService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ParttimerDTO ptdto = (ParttimerDTO)session.getAttribute("parttimer");
		ParttimerContractDTO pcdto = new ParttimerContractDTO();
		if(ptdto!=null) {
			String ptid = ptdto.getPtid();
			String ptname = request.getParameter("ptname");
			String pttel = request.getParameter("pttel");
			String ptemail = request.getParameter("ptemail");
			String ptaddress = request.getParameter("ptaddress");
			int btno = Integer.parseInt(request.getParameter("btno"));
			String ptaccountno = request.getParameter("ptaccountno");
			
			pcdto.setPtid(ptid);
			pcdto.setPtname(ptname);
			pcdto.setPttel(pttel);
			pcdto.setPtemail(ptemail);
			pcdto.setPtaddress(ptaddress);
			pcdto.setBtno(btno);
			pcdto.setPtaccountno(ptaccountno.toString().replaceAll("-", ""));
			
			ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
			int result = pcdao.insertParttimerContract(pcdto);
			
			if(result == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
				ParttimerDAO ptdao = ParttimerDAO.getInstance();
				int PtempconchekResult = ptdao.insertParttimerPtempconchek(ptid); // 파트타이머 근로계약서 작성후 업데이트
				if(PtempconchekResult == ParttimerDAO.ParttimerLoginSUCCESS) {
					session.setAttribute("ptconno", pcdao.detailParttimerContract(ptid, ptname).getPtconno());
					request.setAttribute("ParttimerContractInsetResult", "근로계약서 작성 완료하였습니다.");
				}else {
					request.setAttribute("ParttimerContractInsetResult", "근로계약서 작성 실패 하였습니다. 아이디와 이름을 확인하세요");
				}
			}else {
				request.setAttribute("ParttimerContractInsetResult", "근로계약서 작성 실패 하였습니다.");
			}
		}
	}
}






