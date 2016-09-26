package ua.nure.rataichuk.SummaryTask4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.dao.LocaleDAO;
import ua.nure.rataichuk.SummaryTask4.dao.SubjectDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.EntrantInfo;
import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.entities.FacultyInfo;
import ua.nure.rataichuk.SummaryTask4.entities.Grade;
import ua.nure.rataichuk.SummaryTask4.entities.Subject;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;

/**
 * Parse instances from request
 * 
 * @author Ivan Rataichuk
 *
 */
public class Parser {
	
	private static final Logger LOG = Logger.getLogger(Parser.class);
	
	/**
	 * Parse Entrant instance from request
	 * 
	 * @param request
	 * @return
	 */
	public Entrant getEntrant(HttpServletRequest request) {
		
		Entrant e = new Entrant();
		Locale loc = (Locale) request.getSession().getAttribute("locale");
		Visitor v = (Visitor) request.getSession().getAttribute("visitor");
		e.setUserId(v.getId());
		e.setEmail(request.getParameter("email"));
		e.setTel(request.getParameter("tel"));
		
		try {
			SubjectDAO sdao = SubjectDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			List<Subject> subs = sdao.read(ldao.read(loc.getLanguage()));
			
			for (Subject s : subs) {
				Grade g = new Grade();
				g.setSubjectId(s.getId());
				g.setGrade(Integer.parseInt(request.getParameter("sub-" + s.getId())));
				e.getGl().add(g);
			}
			
			List<String> langs = ldao.read();
			
			for (String lang : langs) {
				EntrantInfo ei = new EntrantInfo();
				ei.setFirstName(request.getParameter("firstName_" + lang));
				ei.setMiddleName(request.getParameter("middleName_" + lang));
				ei.setLastName(request.getParameter("lastName_" + lang));
				ei.setAdress(request.getParameter("adress_" + lang));
				ei.setOblast(request.getParameter("oblast_" + lang));
				ei.setSchool(request.getParameter("school_" + lang));
				ei.setLocaleId(ldao.read(lang));
				e.getInfoList().add(ei);
			}
		} catch (DBException ex) {
			LOG.error(ex.getMessage());
		}
		return e;
	}
	
	/**
	 * Parse List of localized subjects from request
	 * 
	 * @param request
	 * @return
	 */
	public List<Subject> getSubject(HttpServletRequest request) {
		
		List<Subject> subs = new ArrayList<>();
		
		try {
			LocaleDAO ldao = LocaleDAO.getInstance();
			
			List<String> langs = ldao.read();
			
			for (String lang : langs) {
				Subject sub = new Subject();
				sub.setName(request.getParameter("name_" + lang));
				sub.setLocaleId(ldao.read(lang));
				subs.add(sub);
			}
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return subs;
	}
	
	/**
	 * Parse Faculty instance from request
	 * 
	 * @param request
	 * @return
	 */
	public Faculty getFaculty(HttpServletRequest request) {
		
		Faculty faculty = new Faculty();
		
		faculty.setMsId(Integer.parseInt(request.getParameter("subject_m")));
		faculty.setSsId(Integer.parseInt(request.getParameter("subject_s")));
		faculty.setTsId(Integer.parseInt(request.getParameter("subject_t")));
		faculty.setPositions(Integer.parseInt(request.getParameter("pos_quantity")));
		faculty.setBudgetPositions(Integer.parseInt(request.getParameter("bud_pos_quantity")));
		
		try {
			LocaleDAO ldao = LocaleDAO.getInstance();

			List<String> langs = ldao.read();
			
			for (String lang : langs) {
				FacultyInfo fi = new FacultyInfo();
				fi.setName(request.getParameter("name_" + lang));
				fi.setDescription(request.getParameter("description_" + lang));
				fi.setLocale_id(ldao.read(lang));
				faculty.getInfoList().add(fi);
			}
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		
		return faculty;
	}

}
