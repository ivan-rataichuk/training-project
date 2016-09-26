package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.service.EntrantSE;
import ua.nure.rataichuk.SummaryTask4.service.Parser;
import ua.nure.rataichuk.SummaryTask4.service.Validation;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;



/**
 * Update entrant command
 * 
 * @author Ivan Rataichuk
 *
 */
public class UpdateEntrant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Validation vld = new Validation();
		if (!vld.entrant(request)) {
			Message mes = new Message();
			mes.setError(true);
			mes.setCause("val");
			request.setAttribute("message", mes);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		EntrantSE ese = new EntrantSE();
		Parser p = new Parser();
		Entrant e = p.getEntrant(request);
		ese.updateEntrant(e);
		response.sendRedirect("index.jsp");
	}

}
