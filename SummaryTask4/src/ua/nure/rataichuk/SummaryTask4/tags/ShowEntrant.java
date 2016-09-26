package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.Grade;
import ua.nure.rataichuk.SummaryTask4.service.EntrantSE;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;

/**
 * Show localized entrant information with given id 
 * 
 * @author Ivan Rataichuk
 *
 */
public class ShowEntrant extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Locale loc = (Locale) getJspContext().getAttribute("locale", PageContext.SESSION_SCOPE);
		Visitor v = (Visitor) getJspContext().getAttribute("visitor", PageContext.SESSION_SCOPE);
		EntrantSE entSe = new EntrantSE();
		Entrant entrant = entSe.getEntrantInfo(v);
		List<Grade> grades = entSe.getGrades(v, loc);
		getJspContext().setAttribute("entrant", entrant, PageContext.REQUEST_SCOPE);
		getJspContext().setAttribute("grades", grades, PageContext.REQUEST_SCOPE);
	}

}
