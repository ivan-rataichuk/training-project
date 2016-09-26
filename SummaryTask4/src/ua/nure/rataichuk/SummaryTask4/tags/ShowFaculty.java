package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.service.FacultySE;

/**
 * Show faculty information with given id
 * 
 * @author Ivan Rataichuk
 *
 */
public class ShowFaculty extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		int facultyId = Integer.parseInt((String) getJspContext().getAttribute("facultyId", PageContext.REQUEST_SCOPE));
		FacultySE fse = new FacultySE();
		Faculty faculty = fse.getFacultyByID(facultyId);
		getJspContext().setAttribute("faculty", faculty, PageContext.REQUEST_SCOPE);
	}

}
