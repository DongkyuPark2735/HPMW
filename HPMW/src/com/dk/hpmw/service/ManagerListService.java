package com.dk.hpmw.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dk.hpmw.manager.ManagerDAO;
import com.dk.hpmw.manager.ManagerDTO;

public class ManagerListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ManagerDAO mdao = ManagerDAO.getInstance();
		
		ArrayList<ManagerDTO> mdaoarr = new ArrayList<ManagerDTO>();

		mdaoarr = mdao.listManager();
		
		request.setAttribute("managerList", mdaoarr);
	}

}
