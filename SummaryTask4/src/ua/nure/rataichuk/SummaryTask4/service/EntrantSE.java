package ua.nure.rataichuk.SummaryTask4.service;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.dao.EntrantDAO;
import ua.nure.rataichuk.SummaryTask4.dao.GradeDAO;
import ua.nure.rataichuk.SummaryTask4.dao.LocaleDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.EntrantInfo;
import ua.nure.rataichuk.SummaryTask4.entities.Grade;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;

/**
 * Entrant service layer
 * 
 * @author Ivan Rataichuk
 *
 */
public class EntrantSE {
	
	private static final Logger LOG = Logger.getLogger(EntrantSE.class);
	
	/**
	 * Change Entrant block status by given id
	 * 
	 * @param entrantId
	 */
	public void blockEntrant(int entrantId) {
		Entrant entrant = new Entrant();
		EntrantInfo ei = new EntrantInfo();
		entrant.setId(entrantId);
		ei.setLocaleId(1);
		entrant.setInfo(ei);
		try {
			EntrantDAO edao = EntrantDAO.getInstance();
			entrant = edao.read(entrantId, 1);
		    edao.update(!entrant.isBlocked(), entrantId);
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		
	}
	
	/**
	 * Creates Entrant with information from request.
	 * 
	 * @param e Entrant 
	 */
	public int createEntrant(Entrant e) {
		int entrantId = 0;
		try {
			EntrantDAO edao = EntrantDAO.getInstance();
			entrantId = edao.create(e);
			
		} catch (DBException ex) {
			LOG.error(ex.getMessage());
		}
		return entrantId;
		
	}
	
	/**
	 * Returns a list of Subjects for given locale
	 * @param loc Locale
	 * @return
	 */
	public List<Entrant> getAllEntrants(Visitor v, Locale loc) {
		List<Entrant> entrants = null;
		
		try {
			EntrantDAO edao = EntrantDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			GradeDAO gdao = GradeDAO.getInstance();
			entrants = edao.readAll(ldao.read(loc.getLanguage()));
			for (Entrant en : entrants) {
				en.setGl(gdao.read(en));
				Sorter.sortGrades(en.getGl());
			}
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}	
		return entrants;
	}
	
	/**
	 * Return Entrant with Info for all Locales.
	 * 
	 * @param v Visitor
	 * @return Entrant
	 */
	public Entrant getEntrantInfo(Visitor v) {
		Entrant entrant = null;
		
		
		try {
			EntrantDAO edao = EntrantDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			
			entrant = edao.read(v.getEntrantId());
			
			List<EntrantInfo> eil = entrant.getInfoList();
			for (EntrantInfo ei : eil) {
				ei.setLang(ldao.read(ei.getLocaleId()));
			}
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return entrant;
	}
	
	/**
	 * Updates Entrant with information from request.
	 * 
	 * @param e Entrant
	 */
	public void updateEntrant(Entrant e) {
	
		try {
			EntrantDAO edao = EntrantDAO.getInstance();
			edao.update(e);
			
		} catch (DBException ex) {
			LOG.error(ex.getMessage());
		}
	}
	
	public void updateEntrantFile(Visitor v, String file) {
		try {
			EntrantDAO edao = EntrantDAO.getInstance();
			edao.update(v.getEntrantId(), file);
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
	public List<Grade> getGrades(Visitor v, Locale loc) {
		List<Grade> gl = null;
		Entrant entrant = new Entrant();
		EntrantInfo ei = new EntrantInfo();
		entrant.setId(v.getEntrantId());

		try {
			GradeDAO gdao = GradeDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			ei.setLocaleId(ldao.read(loc.getLanguage()));
			entrant.setInfo(ei);
			gl = gdao.read(entrant);
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		
		return gl;
	}

}
