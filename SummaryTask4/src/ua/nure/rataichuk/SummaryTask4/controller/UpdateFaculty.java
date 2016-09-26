package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.service.FacultySE;
import ua.nure.rataichuk.SummaryTask4.service.Parser;
import ua.nure.rataichuk.SummaryTask4.service.Validation;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;

/**
 * Update faculty command
 * 
 * @author Ivan Rataichuk
 *
 */
public class UpdateFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Validation vld = new Validation();
		if (!vld.faculty(request)) {
			Message mes = new Message();
			mes.setError(true);
			mes.setCause("val");
			request.setAttribute("message", mes);
			String facultyId = request.getParameter("facultyId");
			request.setAttribute("facultyId", facultyId);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		FacultySE fse = new FacultySE();
		Parser p = new Parser();
		Faculty f = p.getFaculty(request);
		fse.updateFaculty(f);
		request.getSession().setAttribute("page", "updateFaculty");
		response.sendRedirect("index.jsp");
	}

}
