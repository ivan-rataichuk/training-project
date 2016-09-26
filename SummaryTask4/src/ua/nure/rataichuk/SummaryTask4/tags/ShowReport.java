package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.rataichuk.SummaryTask4.entities.ReportEntry;
import ua.nure.rataichuk.SummaryTask4.service.ReportSE;

/**
 * Show all registration for all entrants
 * 
 * @author Ivan	Rataichuk
 *
 */
public class ShowReport extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Locale loc = (Locale) getJspContext().getAttribute("locale", PageContext.SESSION_SCOPE);
		ReportSE rse = new ReportSE();
		List<ReportEntry> re = rse.getReport(loc);
		getJspContext().setAttribute("report", re, PageContext.REQUEST_SCOPE);
	}

}
