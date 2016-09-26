package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.rataichuk.SummaryTask4.entities.Registration;
import ua.nure.rataichuk.SummaryTask4.service.RegistrationSE;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;

/**
 * Show all registrations for given entrant
 * 
 * @author Ivan Rataichuk
 *
 */
public class ShowRegistrations extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Locale loc = (Locale) getJspContext().getAttribute("locale", PageContext.SESSION_SCOPE);
		Visitor v = (Visitor) getJspContext().getAttribute("visitor", PageContext.SESSION_SCOPE);
		RegistrationSE rse = new RegistrationSE();
		List<Registration> regl = rse.getRegistrations(v, loc);
		getJspContext().setAttribute("registrations", regl, PageContext.REQUEST_SCOPE);
	}

}