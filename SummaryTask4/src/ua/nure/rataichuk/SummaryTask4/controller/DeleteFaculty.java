package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.FacultySE;

/**
 * Delete faculty command
 * 
 * @author Ivan Rataichuk
 *
 */
public class DeleteFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FacultySE fse = new FacultySE();
		int facultyId = Integer.parseInt(request.getParameter("facultyId"));
		fse.deleteFaculty(facultyId);
		response.sendRedirect("index.jsp");

	}

}
