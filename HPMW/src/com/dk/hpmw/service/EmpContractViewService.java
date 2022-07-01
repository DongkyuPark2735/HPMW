package com.dk.hpmw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.employcontract.EmployContractDAO;
import com.dk.hpmw.employcontract.EmployContractDTO;

public class EmpContractViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EmployContractDAO ecdao = EmployContractDAO.getInstance();
		EmployContractDTO ecdto;

		ecdto = ecdao.detailEmployContract();
		
		request.setAttribute("employContract", ecdto);
	}

}
