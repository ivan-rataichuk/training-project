package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.service.EntrantSE;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;

/**
 * Show all registered entrants localized information
 * 
 * @author Ivan	Rataichuk
 *
 */
public class ShowEntrants extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Visitor v = (Visitor) getJspContext().getAttribute("visitor", PageContext.SESSION_SCOPE);
		Locale loc = (Locale) getJspContext().getAttribute("locale", PageContext.SESSION_SCOPE);
		EntrantSE entSe = new EntrantSE();
		List<Entrant> entrants = entSe.getAllEntrants(v, loc);
		getJspContext().setAttribute("entrants", entrants, PageContext.REQUEST_SCOPE);
	}

}
