package ua.nure.rataichuk.SummaryTask4.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Custom tag for text localization
 * 
 * @author Ivan Rataichuk
 *
 */
public class LocTag extends SimpleTagSupport {

	private String str;
	
	public LocTag() {

	}
	public void setStr(String str) {
		this.str = str;
	}
	@Override
	public void doTag() throws JspException, IOException {
		Locale loc = (Locale) getJspContext().getAttribute("locale", PageContext.SESSION_SCOPE);
		ResourceBundle rb = ResourceBundle.getBundle("ua.nure.rataichuk.SummaryTask4.locales.SummaryTask4", loc);
		getJspContext().getOut().write(rb.getString(str));
	}

}
