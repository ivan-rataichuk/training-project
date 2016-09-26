package ua.nure.rataichuk.SummaryTask4.service;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.dao.FacultyDAO;
import ua.nure.rataichuk.SummaryTask4.dao.GradeDAO;
import ua.nure.rataichuk.SummaryTask4.dao.LocaleDAO;
import ua.nure.rataichuk.SummaryTask4.dao.RegistrationDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.EntrantInfo;
import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.entities.Grade;
import ua.nure.rataichuk.SummaryTask4.entities.Registration;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;

/**
 * Registration service layer
 * 
 * @author Ivan Rataichuk
 *
 */
public class RegistrationSE {
	
	private static final Logger LOG = Logger.getLogger(RegistrationSE.class);
	
	/**
	 * Check registration by given id and block others for given entrant
	 *  
	 * @param regId
	 * 
	 * @param entrantId
	 */
	public void checkRegistration(int regId, int entrantId, String posType) {
		try {
			RegistrationDAO rdao = RegistrationDAO.getInstance();
			if (posType.equals("budget")) {
				rdao.update(regId, entrantId, true);
			} else {
				rdao.update(regId, entrantId, false);
			}
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Create registration for entrant on given faculty
	 * 
	 * @param v
	 * @param facultyId
	 */
	public boolean createRegistration(Visitor v, int facultyId) {
		Registration reg = new Registration();
		Entrant entrant = new Entrant();
		EntrantInfo ei = new EntrantInfo();
		try {
			FacultyDAO fdao = FacultyDAO.getInstance();
			GradeDAO gdao = GradeDAO.getInstance();
			RegistrationDAO rdao = RegistrationDAO.getInstance();
			
			if (v == null) {
				return false;
			}
			
			entrant.setId(v.getEntrantId());
			ei.setLocaleId(1);
			entrant.setInfo(ei);
			
			List<Registration> rl = rdao.read(entrant);
			
			for (Registration r : rl) {
				if (r.getFacultyId() == facultyId) {
					return false;
				}
			}
			
			if (rl.size() >= 3) {
				return false;
			}
			
			Faculty f = fdao.read(facultyId, 1);
			
			int posF = f.getPositionsFilled();
			if (posF >= f.getPositions()) {
				return false;
			}
			
			List<Grade> gl = gdao.read(entrant);
			
			reg.setEntrantId(v.getEntrantId());
			reg.setFacultyId(facultyId);
			
			for (Grade g : gl) {
				if (f.getMsId() == g.getSubjectId()) {
					reg.setMgId(g.getId());
				}
				if (f.getSsId() == g.getSubjectId()) {
					reg.setSgId(g.getId());
				}
				if (f.getTsId() == g.getSubjectId()) {
					reg.setTgId(g.getId());
				}
			}
			rdao.create(reg);
			
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Deletes registration by given id
	 *  
	 * @param regId Registration id
	 */
	public void deleteRegistration(int regId) {
		try {
			RegistrationDAO rdao = RegistrationDAO.getInstance();
			rdao.delete(regId);
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Returns list of registrations for given entrant
	 * 
	 * @param v Visitor
	 * @param loc Locale
	 */
	public List<Registration> getRegistrations(Visitor v, Locale loc) {
		List<Registration> regl = null;
		Entrant entrant = new Entrant();
		EntrantInfo ei = new EntrantInfo();
		
		try {
			RegistrationDAO rdao = RegistrationDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			
			entrant.setId(v.getEntrantId());
			ei.setLocaleId(ldao.read(loc.getLanguage()));
			entrant.setInfo(ei);
			
			regl = rdao.read(entrant);

		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		
		return regl;
	}
	

	
	/**
	 * Reset all registration by given entrant id
	 *  
	 * @param entrantId
	 */
	public void resetRegistration(int entrantId) {
		try {
			RegistrationDAO rdao = RegistrationDAO.getInstance();
			rdao.update(entrantId);
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}

}
