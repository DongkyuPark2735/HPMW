package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.handler.PTContractDeleteHandler;
import com.dk.hpmw.parttimer.ParttimerDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;
import com.dk.hpmw.pastparttimercontractdata.PastParttimerContractDataDAO;

public class PtEmpConSubmitService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String ptconno = request.getParameter("ptconno");
		String tmppthourlywage = request.getParameter("pthourlywage").replace(",", "");
		int pthourlywage = Integer.parseInt(tmppthourlywage);
		int pttotalpay = Integer.parseInt(request.getParameter("pttotalpay").replace(",", ""));
		int ptworktime = Integer.parseInt(request.getParameter("ptworktime"));
		
		ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
		
		//파트타이머 시급 입력
		int parttimerHourlyWageresult = pcdao.insertParttimerHourlyWage(ptworktime, pthourlywage, pttotalpay, ptconno);
		if(parttimerHourlyWageresult == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
			// 시급 입력 성공시 마감상태 입력
			int parttimerSubmitResult = pcdao.intsertParttimerPtstatus(ptconno);
			if(parttimerSubmitResult == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
				//PTContractDeleteHandler ptdeletehandler = new PTContractDeleteHandler();
				//ptdeletehandler.PTContractDelete();
				
				ParttimerContractDTO ptcdto = pcdao.detailParttimerContract(ptconno);
				PastParttimerContractDataDAO ppcddao = PastParttimerContractDataDAO.getInstance();
				// 마감상태 입력 성공시 계약서 정보  데이터테이블에 저장
				int resultpastdate = ppcddao.insertPastParttimerContractData(ptcdto);
				if(resultpastdate == ParttimerContractDAO.ParttimerContractInsetSUCCESS) {
					// 기존 아이디에 계약서 작성 상태 초기화
					ParttimerDAO pdao = ParttimerDAO.getInstance();
					int resultdeleteconchek = pdao.deleteParttimerPtempconchek(ptcdto.getPtid());
					int resultdelete = pcdao.deleteParttimerContract(ptconno);
					// 데이터 테이블에 저장 성공시 기존 계약서 삭제 
					if(resultdelete == ParttimerDAO.ParttimerLoginSUCCESS && resultdeleteconchek == ParttimerDAO.ParttimerLoginSUCCESS) {
						request.setAttribute("parttimerSubmitResult", "해당 근로계약서 마감처리 되었습니다.");
					}else {
						System.out.println("기존 계약서 삭제 실패");
					}
				}else {
					System.out.println("데이터 저장 실패");
				}
			}else {
				request.setAttribute("parttimerSubmitResult", "해당 근로계약서 마감처리 실패하였습니다.");
			}
		}else {
			request.setAttribute("parttimerHourlyWageResult", "해당 근로계약서 급여입력에 실패하였습니다.");
		}
	}
}
