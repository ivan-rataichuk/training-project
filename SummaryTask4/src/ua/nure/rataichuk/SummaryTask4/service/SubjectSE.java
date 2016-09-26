package ua.nure.rataichuk.SummaryTask4.service;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.dao.LocaleDAO;
import ua.nure.rataichuk.SummaryTask4.dao.SubjectDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Subject;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;

/**
 * Subject service layer
 * 
 * @author Ivan Rataichuk
 *
 */
public class SubjectSE {

	private static final Logger LOG = Logger.getLogger(SubjectSE.class);
	
	/**
	 * Creates new Subject from given request information
	 * @param request
	 */
	public void createSubject(List<Subject> subs) {
		Subject s = new Subject();
		try {
			SubjectDAO sdao = SubjectDAO.getInstance();
			for (Subject sub : subs) {
				s.setName(sub.getName());
				s.setLocaleId(sub.getLocaleId());
				sdao.create(s);
			}
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Returns a list of Subjects for given locale
	 * @param loc Locale
	 * @return
	 */
	public List<Subject> getSubjects(Locale loc) {
		List<Subject> subs = null;
		try {
			SubjectDAO sdao = SubjectDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			subs = sdao.read(ldao.read(loc.getLanguage()));
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return subs;
	}

}
