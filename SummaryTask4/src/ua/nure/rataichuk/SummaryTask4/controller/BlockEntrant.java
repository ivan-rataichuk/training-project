package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.EntrantSE;

/**
 * Block Entrant command
 * 
 * @author Ivan Rataichuk
 *
 */
public class BlockEntrant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int entrantId = Integer.parseInt(request.getParameter("entrantId"));
		EntrantSE entSe = new EntrantSE();
		entSe.blockEntrant(entrantId);
		response.sendRedirect("index.jsp");
	}

}
