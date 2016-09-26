package ua.nure.rataichuk.SummaryTask4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.rataichuk.SummaryTask4.service.EntrantSE;
import ua.nure.rataichuk.SummaryTask4.service.FileAccess;
import ua.nure.rataichuk.SummaryTask4.service.Validation;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Message;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;



/**
 * Upload file command
 * 
 * @author Ivan Rataichuk
 *
 */
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Validation vld = new Validation();
		if (!vld.file(request)) {
			Message mes = new Message();
			mes.setError(true);
			mes.setCause("file");
			request.setAttribute("message", mes);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}*/
		
		String realPath = getServletContext().getRealPath("");
		FileAccess fa = new FileAccess();
		String file = fa.uploadFile(request, realPath);
		Visitor v = (Visitor) request.getSession().getAttribute("visitor");
		if (file != null) {
			EntrantSE ese = new EntrantSE();
			ese.updateEntrantFile(v, file);
		} else {
			Message mes = new Message();
			mes.setError(true);
			mes.setCause("file");
			request.setAttribute("message", mes);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("index.jsp");
	}

}
