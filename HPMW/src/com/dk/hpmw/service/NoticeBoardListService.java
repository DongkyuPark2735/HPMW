package com.dk.hpmw.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.noticeboard.NoticeBoardDAO;
import com.dk.hpmw.noticeboard.NoticeBoardDTO;

public class NoticeBoardListService implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("pageNum"));
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum == "") {
			System.out.println(request.getParameter(pageNum));
			if(request.getAttribute("pageNum")==null) {
				System.out.println(request.getParameter(pageNum));
				pageNum = "1";
			}else {
				pageNum = (String)request.getAttribute("pageNum");
				System.out.println(request.getParameter(pageNum));
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=10, BLOCKSIZE=10;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		NoticeBoardDAO nbdao = NoticeBoardDAO.getInstance();
		ArrayList<NoticeBoardDTO> nblist = nbdao.listNoticeBoard(startRow, endRow);
		
		request.setAttribute("NoticeBoardList", nblist);
		int totCnt = nbdao.getnoticeBoardTotCnt();// 글갯수
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
		request.setAttribute("totCnt", totCnt); // totCnt는 없으면 size()대용
		request.setAttribute("pageNum", currentPage);
	}
}
