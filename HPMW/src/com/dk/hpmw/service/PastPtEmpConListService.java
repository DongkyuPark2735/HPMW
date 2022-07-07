package com.dk.hpmw.service;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;
import com.dk.hpmw.pastparttimercontractdata.PastParttimerContractDataDAO;
import com.dk.hpmw.pastparttimercontractdata.PastParttimerContractDataDTO;

public class PastPtEmpConListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			if(request.getAttribute("pageNum")==null) { 
				pageNum = "1";
			}else {
				pageNum = (String)request.getAttribute("pageNum");
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=10, BLOCKSIZE=10;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		
		PastParttimerContractDataDAO ppcddao = PastParttimerContractDataDAO.getInstance(); 
		ArrayList<PastParttimerContractDataDTO> pclist = ppcddao.listPastParttimerContractData(startRow, endRow);
		
		Date sysdate = new Date();
		request.setAttribute("ppContractDataList", pclist);
		request.setAttribute("sysdate", sysdate);
		int totCnt = ppcddao.PastParttimerContractDataTotcnt();
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); 
		request.setAttribute("pageNum", currentPage);
	}


}
