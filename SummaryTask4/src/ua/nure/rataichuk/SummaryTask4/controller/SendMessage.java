package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.Email;


/**
 * Send message to admins email
 * 
 * @author Ivan Rataichuk
 *
 */
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mailFrom = request.getParameter("mailFrom");
		String subject = request.getParameter("subject");
		String text = request.getParameter("message");
		Email em = new Email();
		em.sendMessageToAdmins(mailFrom, subject, text);
		response.sendRedirect("index.jsp");
	}

}
