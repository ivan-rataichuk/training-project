package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.RegistrationSE;


/**
 * Confirm current registration and block others for given entrant
 * 
 * @author Ivan Rataichuk
 *
 */
public class CheckRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int regId = Integer.parseInt(request.getParameter("regId"));
		int entrantId = Integer.parseInt(request.getParameter("entrantId"));
		String posType = request.getParameter("posType");
		RegistrationSE rse = new RegistrationSE();
		rse.checkRegistration(regId, entrantId, posType);
		response.sendRedirect("index.jsp");
	}

}
