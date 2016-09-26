package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Select given faculty and redirect to main page
 * 
 * @author Ivan Rataichuk
 *
 */
public class SelectFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String facultyId = request.getParameter("facultyId");
		String lang = request.getParameter("lang");
		request.setAttribute("facultyId", facultyId);
		request.setAttribute("lang", lang);
		request.getSession().setAttribute("page", "showFaculty");
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
