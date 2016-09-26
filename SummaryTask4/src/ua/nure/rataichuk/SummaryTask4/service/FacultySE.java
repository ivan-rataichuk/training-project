package ua.nure.rataichuk.SummaryTask4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.dao.FacultyDAO;
import ua.nure.rataichuk.SummaryTask4.dao.LocaleDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.entities.FacultyInfo;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;

/**
 * Faculty service layer
 * 
 * @author Ivan Rataichuk
 *
 */
public class FacultySE {
	
	private static final Logger LOG = Logger.getLogger(FacultySE.class);
	
	/**
	 * Creates new Faculty from given request information
	 * @param request
	 * @throws ValidationEx 
	 * @throws NumberFormatException 
	 */
	public void createFaculty(Faculty faculty) {
		
		try {
			FacultyDAO fdao = FacultyDAO.getInstance();
			fdao.create(faculty);
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Get List of Facultys with given localization.
	 * 
	 * @param loc Locale
	 * 
	 * @return List of Facultys
	 *
	 */
	public List<Faculty> getAllFacultys(Locale loc) {
		List<Faculty> facultys = null; 
		try {
			FacultyDAO fdao = FacultyDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			facultys = fdao.read(ldao.read(loc.getLanguage()));
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return facultys;
	}
	
	/**
	 * Delete faculty with given id
	 * 
	 * @param facultyId
	 */
	public void deleteFaculty(int facultyId) {
		Faculty f = new Faculty();
		f.setId(facultyId);
		try {
			FacultyDAO fdao = FacultyDAO.getInstance();
			fdao.delete(f);
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Get Faculty with given id and localization.
	 * 
	 * @param facultyId int
	 * @param loc Locale
	 * 
	 * @return List of Facultys
	 *
	 */
	public Faculty getFacultyByID(int facultyId) {
		Faculty faculty = null;
		List<FacultyInfo> fil = new ArrayList<>();
		
		try {
			FacultyDAO fdao = FacultyDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			List<String> langs = ldao.read();
			
			for (String lang : langs) {
				faculty = fdao.read(facultyId, ldao.read(lang));
				faculty.getInfo().setLang(lang);
				fil.add(faculty.getInfo());
			}
			faculty.setInfo(fil);
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return faculty;
	}
	
	/**
	 * Updates Faculty with information from given request 
	 * @param request
	 * @throws ValidationEx 
	 * @throws NumberFormatException 
	 */
	public void updateFaculty(Faculty faculty) {
		
		try {
			FacultyDAO fdao = FacultyDAO.getInstance();
			fdao.update(faculty);
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	
	}
	
}
