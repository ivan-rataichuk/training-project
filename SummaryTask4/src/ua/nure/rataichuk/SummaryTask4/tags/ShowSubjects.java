package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.rataichuk.SummaryTask4.entities.Subject;
import ua.nure.rataichuk.SummaryTask4.service.Sorter;
import ua.nure.rataichuk.SummaryTask4.service.SubjectSE;

/**
 * Show all subjects
 * 
 * @author Ivan	Rataichuk
 *
 */
public class ShowSubjects extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Locale loc = (Locale) getJspContext().getAttribute("locale", PageContext.SESSION_SCOPE);
		SubjectSE sse = new SubjectSE();
		List<Subject> subjects = sse.getSubjects(loc);
		Sorter.sortSubjects(subjects);
		getJspContext().setAttribute("subjects", subjects, PageContext.REQUEST_SCOPE);
	}

}