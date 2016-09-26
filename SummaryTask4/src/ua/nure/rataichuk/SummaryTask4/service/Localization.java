package ua.nure.rataichuk.SummaryTask4.service;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.dao.LocaleDAO;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;

/**
 * Localization service
 * 
 * @author Ivan Rataichuk
 *
 */
public class Localization {
	
	private static final Logger LOG = Logger.getLogger(Localization.class);
	
	/**
	 * Check incoming request locale
	 * 
	 * @param loc
	 * @return checked Locale
	 */
	public Locale checkLocale(Locale loc) {
		Locale l = loc;
		try {
			LocaleDAO ldao = LocaleDAO.getInstance();
			List<String> langs = ldao.read();
			if (!langs.contains(loc.getLanguage())) {
				l = new Locale("en");
			}
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return l;
	}
}
