package com.dk.hpmw.servicePT;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.parttimer.ParttimerDAO;
import com.dk.hpmw.parttimer.ParttimerDTO;
import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;
import com.dk.hpmw.service.Service;

public class ModifyParttimerConService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		ServletContext application = request.getServletContext();
		application.setAttribute("blockParttimerContractinsert", 1);
		
		ParttimerDTO ptdto = (ParttimerDTO)session.getAttribute("parttimer");
		ParttimerContractDTO pcdto = new ParttimerContractDTO();
		if(ptdto!=null) {
			String ptconno = request.getParameter("ptconno");
			String ptid = ptdto.getPtid();
			String ptname = request.getParameter("ptname");
			String pttel = request.getParameter("pttel");
			String ptemail = request.getParameter("ptemail");
			String ptemailselect = request.getParameter("ptemailselect");
			String ptaddress = request.getParameter("ptaddress");
			int btno = Integer.parseInt(request.getParameter("btno"));
			String ptaccountno = request.getParameter("ptaccountno");
			
			if(ptconno != "" || ptconno != null) {
				pcdto.setPtconno(ptconno);
			}
			pcdto.setPtid(ptid);
			pcdto.setPtname(ptname);
			pcdto.setPttel(pttel);
			pcdto.setPtemail(ptemail.trim() + ptemailselect.trim());
			pcdto.setPtaddress(ptaddress);
			pcdto.setBtno(btno);
			pcdto.setPtaccountno(ptaccountno.toString().replaceAll("-", ""));
			
			ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
			int result = pcdao.modifyParttimerContract(pcdto);
			
			if(result == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
				ParttimerDAO ptdao = ParttimerDAO.getInstance();
				int PtempconchekResult = ptdao.insertParttimerPtempconchek(ptid); // ??????????????? ??????????????? ????????? ????????????
				if(PtempconchekResult == ParttimerDAO.ParttimerLoginSUCCESS) {
					session.setAttribute("ptconno", pcdao.detailParttimerContract(ptid, ptname).getPtconno());
					request.setAttribute("ParttimerContractInsetResult", "??????????????? ?????? ?????????????????????.");
					System.out.println(application.getAttribute("blockParttimerContractInsert")+"??????.");
					application.removeAttribute("blockParttimerContractInsert");
					System.out.println(application.getAttribute("blockParttimerContractInsert")+"??????");
				}else {
					request.setAttribute("ParttimerContractInsetResult", "??????????????? ?????? ?????? ???????????????. ???????????? ????????? ???????????????");
				}
			}else {
				request.setAttribute("ParttimerContractInsetResult", "??????????????? ?????? ?????? ???????????????.");
			}
		}
	}

}
