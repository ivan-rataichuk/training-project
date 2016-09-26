package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.ReportSE;


/**
 * Confirm final report and send emails to all entrants
 * 
 * @author Ivan Rataichuk
 *
 */
public class ConfirmFinalRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ReportSE rse = new ReportSE();
		rse.confirmCheckedRegistrations();
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
