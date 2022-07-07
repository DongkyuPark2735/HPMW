package com.dk.hpmw.handler;

import java.util.Timer;
import java.util.TimerTask;

import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;


public class PTContractDeleteHandler{
	public boolean PTContractDelete(String ptconno, String ptid) {
		boolean result = false;
		Timer m = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("timer실행");
				System.out.println("1111");
//				ParttimerContractDAO pcdao = ParttimerContractDAO.getInstance();
//				ParttimerContractDTO pcdto = pcdao.detailParttimerContract(ptconno);
//				pcdto.setPtid(ptid+"9");//임의로 "9" 추가 해서 입력
			}
		};
		m.schedule(task, 5000);
		m.cancel();
		return result;
	}
}
