package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.Parser;
import ua.nure.rataichuk.SummaryTask4.service.SubjectSE;
import ua.nure.rataichuk.SummaryTask4.service.Validation;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;


/**
 * Create Subject command
 * 
 * @author Ivan Rataichuk
 *
 */
public class CreateSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Validation vld = new Validation();
		if (!vld.subject(request)) {
			Message mes = new Message();
			mes.setError(true);
			mes.setCause("val");
			request.setAttribute("message", mes);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		SubjectSE sse = new SubjectSE();
		Parser p = new Parser();
		sse.createSubject(p.getSubject(request));
		response.sendRedirect("index.jsp");
	}

}
