package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.AuthorizationSE;


/**
 * Send email with credentials if user exists in db
 * 
 * @author Ivan Rataichuk
 *
 */
public class RemindPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		AuthorizationSE aut = new AuthorizationSE();
		aut.remindPassword(email);
		response.sendRedirect("index.jsp");
	}

}
