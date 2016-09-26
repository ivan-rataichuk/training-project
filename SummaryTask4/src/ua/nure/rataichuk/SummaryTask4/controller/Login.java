package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.AuthorizationSE;
import ua.nure.rataichuk.SummaryTask4.service.Response;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;


/**
 * Check if user with given login and password exists,
 * check its credentials.   
 * 
 * @author Ivan Rataichuk
 *
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Locale loc = (Locale) request.getSession().getAttribute("locale");
		
		AuthorizationSE aut = new AuthorizationSE();
		Visitor v = aut.getCredentials(login, password, loc);
		Response res = new Response();
		Message mes = res.checkAuthorization(v);
		request.setAttribute("message", mes);
		
		if (mes.isError()) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		request.getSession().setAttribute("page", "facultyList");
		request.getSession().setAttribute("visitor", v);
		response.sendRedirect("index.jsp");
	}

}
