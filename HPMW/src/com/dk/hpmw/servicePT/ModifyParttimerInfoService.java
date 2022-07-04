package com.dk.hpmw.servicePT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dk.hpmw.parttimer.ParttimerDAO;
import com.dk.hpmw.parttimer.ParttimerDTO;
import com.dk.hpmw.service.Service;

public class ModifyParttimerInfoService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		ParttimerDTO parttimer = (ParttimerDTO)session.getAttribute("parttimer");
		ParttimerDAO ptdao = ParttimerDAO.getInstance();
		int result = ptdao.deleteParttimerPtempconchek(parttimer.getPtid());
		if(result == ParttimerDAO.ParttimerLoginSUCCESS) {
			request.setAttribute("deletePtempconchek", "수정후 다시 정보입력을 해주세요");
		}else {
			request.setAttribute("deletePtempconchek", "수정할 수 없습니다.");
		}
	}
}
