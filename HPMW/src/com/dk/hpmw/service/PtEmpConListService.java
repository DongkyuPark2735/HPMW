package com.dk.hpmw.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.events.EventsDAO;
import com.dk.hpmw.events.EventsDTO;
import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;

public class PtEmpConListService implements Service {

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
		ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance(); 
		ArrayList<ParttimerContractDTO> pclist = pcdao.listParttimerContract(startRow, endRow);
		
		request.setAttribute("pcContractList", pclist);
		int totCnt = pcdao.parttimerContractTotcnt();// 글갯수
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//페이지갯수
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt는 없으면 size()
		request.setAttribute("pageNum", currentPage);
	}

}
