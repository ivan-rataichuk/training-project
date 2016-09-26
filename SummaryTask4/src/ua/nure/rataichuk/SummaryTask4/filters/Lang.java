package ua.nure.rataichuk.SummaryTask4.filters;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import ua.nure.rataichuk.SummaryTask4.service.Localization;

/**
 * Language filter.
 * Selects locale in first incoming request    
 * 
 * @author Ivan Rataichuk
 *
 */
public class Lang implements Filter {

    /**
     * Default constructor. 
     */
    public Lang() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (req.getSession().getAttribute("locale") == null) {
			Locale loc = req.getLocale();
			Localization lc = new Localization();
			req.getSession().setAttribute("locale", lc.checkLocale(loc));
		}
		
		chain.doFilter(req, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
