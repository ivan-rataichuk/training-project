package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.entities.User;
import ua.nure.rataichuk.SummaryTask4.service.AuthorizationSE;
import ua.nure.rataichuk.SummaryTask4.service.Validation;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;

/**
 * Create user command
 * 
 * @author Ivan Rataichuk
 *
 */
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Validation vld = new Validation();
		if (!vld.user(request)) {
			Message mes = new Message();
			mes.setError(true);
			mes.setCause("user");
			request.setAttribute("message", mes);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		Visitor v = (Visitor) request.getSession().getAttribute("visitor");
		User u = new User();
		u.setPassword(request.getParameter("password"));
		u.setLogin(request.getParameter("login"));
		u.setEmail(request.getParameter("email"));
		AuthorizationSE aut = new AuthorizationSE();
		aut.createUser(u, v);
		
		request.getSession().setAttribute("page", "facultyList");
		request.getRequestDispatcher("/Login").forward(request, response);
		
	}

}
