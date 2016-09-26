package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.RegistrationSE;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;


/**
 * Create registration for given entrant
 * 
 * @author Ivan Rataichuk
 *
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int facultyId = Integer.parseInt(request.getParameter("facultyId")); 
		Visitor v = (Visitor) request.getSession().getAttribute("visitor");
		RegistrationSE rse = new RegistrationSE();
		if (!rse.createRegistration(v, facultyId)) {
			Message mes = new Message();
			mes.setError(true);
			mes.setCause("reg");
			request.setAttribute("message", mes);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("index.jsp");
	}

}
