package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Change language command
 * 
 * @author Ivan Rataichuk
 *
 */
public class ChangeLang extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lang = request.getParameter("lang");
		Locale loc = new Locale(lang);
		request.getSession().setAttribute("locale", loc);
		String page = (String) request.getSession().getAttribute("page");
		if (page != null && page.equals("showFaculty")) {
			request.getSession().setAttribute("page", "updateFaculty");
		} 
		
		response.sendRedirect("index.jsp");
	}

}
