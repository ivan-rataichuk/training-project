package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.service.FacultySE;
import ua.nure.rataichuk.SummaryTask4.service.Sorter;

/**
 * Show all facultys localized information
 * 
 * @author Ivan Rataichuk
 *
 */
public class ShowFacultys extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		String sort = (String) getJspContext().getAttribute("sort", PageContext.SESSION_SCOPE);
		if (sort == null) {
			sort = "sort_alph_asc";
		}
		Locale loc = (Locale) getJspContext().getAttribute("locale", PageContext.SESSION_SCOPE);
		FacultySE fse = new FacultySE();
		List<Faculty> facultys = fse.getAllFacultys(loc);
		Sorter.sortFacultys(facultys, sort);
		getJspContext().setAttribute("facultys", facultys, PageContext.SESSION_SCOPE);
	}

}
