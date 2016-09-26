package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.rataichuk.SummaryTask4.service.ReportSE;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.FacultyReport;

/**
 * Show report information combined by facultys 
 * 
 * @author Ivan Rataichuk
 *
 */
public class ShowFacultysReport extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Locale loc = (Locale) getJspContext().getAttribute("locale", PageContext.SESSION_SCOPE);
		ReportSE rse = new ReportSE();
		List<FacultyReport> frl = rse.getFacultyReport(loc);
		getJspContext().setAttribute("fReport", frl, PageContext.REQUEST_SCOPE);
	}

}
